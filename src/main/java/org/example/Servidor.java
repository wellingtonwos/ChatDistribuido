package org.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static final int PORTA = 12345;
    // Lista para guardar os escritores (canais de saída) de todos os clientes
    private static Set<PrintWriter> escritoresClientes = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Servidor de Chat iniciado na porta " + PORTA + "...");

        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            while (true) {
                // O servidor para aqui e espera uma conexão
                Socket socket = serverSocket.accept();
                System.out.println("Novo cliente conectado: " + socket.getInetAddress());

                // Cria um canal de escrita para este novo cliente
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                escritoresClientes.add(out);

                // Cria uma Thread dedicada para tratar as mensagens desse cliente
                new HandlerCliente(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Classe interna para lidar com cada cliente em paralelo
    private static class HandlerCliente extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public HandlerCliente(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // 1. Pede o nome do usuário
                out.println("SUBMITNAME");
                String nome = in.readLine();
                if (nome == null) return;

                System.out.println(nome + " entrou no chat.");
                broadcast("SERVIDOR: " + nome + " entrou na sala.");

                // 2. Loop principal de mensagens
                String mensagem;
                while ((mensagem = in.readLine()) != null) {
                    if (mensagem.equalsIgnoreCase("/sair")) {
                        break;
                    }
                    // Espalha a mensagem para todos
                    broadcast(nome + ": " + mensagem);
                }
            } catch (IOException e) {
                System.out.println("Erro com cliente: " + e.getMessage());
            } finally {
                // Quando o cliente sai, removemos ele da lista e fechamos o socket
                if (out != null) {
                    escritoresClientes.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Método auxiliar para enviar mensagem para TODOS os clientes conectados
    private static void broadcast(String mensagem) {
        for (PrintWriter writer : escritoresClientes) {
            writer.println(mensagem);
        }
    }
}
