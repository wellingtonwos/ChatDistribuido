
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static final String ENDERECO_SERVIDOR = "127.0.0.1"; // Localhost
    private static final int PORTA = 12345;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(ENDERECO_SERVIDOR, PORTA);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner teclado = new Scanner(System.in);

        // Thread para OUVIR mensagens do servidor (Background)
        // Isso permite receber mensagens enquanto você está digitando outra coisa
        new Thread(() -> {
            try {
                String mensagemDoServidor;
                while ((mensagemDoServidor = in.readLine()) != null) {
                    if (mensagemDoServidor.startsWith("SUBMITNAME")) {
                        System.out.print("Digite seu nome: ");
                    } else {
                        System.out.println(mensagemDoServidor);
                    }
                }
            } catch (IOException e) {
                System.out.println("Conexão encerrada.");
            }
        }).start();

        // Thread Principal: LER do teclado e ENVIAR para o servidor
        while (true) {
            String inputUsuario = teclado.nextLine();
            out.println(inputUsuario);
            if (inputUsuario.equalsIgnoreCase("/sair")) {
                break;
            }
        }

        socket.close();
        teclado.close();
    }
}
