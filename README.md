# 💬 Chat Distribuído em Java

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Status](https://img.shields.io/badge/Status-Concluído-brightgreen)

Um sistema de chat multi-usuário baseado na arquitetura **Cliente-Servidor**, desenvolvido em Java. Este projeto demonstra o uso de **Sockets** para comunicação em rede e **Threads** para gerenciamento de múltiplas conexões simultâneas.

---

## 🚀 Funcionalidades

* **Arquitetura Cliente-Servidor:** Um servidor central gerencia todas as mensagens.
* **Múltiplos Clientes:** Suporte para vários usuários conectados ao mesmo tempo.
* **Broadcast de Mensagens:** Quando um usuário envia uma mensagem, todos na sala recebem.
* **Multithreading:** O servidor cria uma nova Thread para cada cliente, garantindo que o processamento de um não trave os outros.
* **Interface via Console:** Simples e direta para focar na lógica de redes.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (JDK 8 ou superior)
* **Redes:** `java.net.Socket`, `java.net.ServerSocket`
* **I/O:** `java.io.PrintWriter`, `java.io.BufferedReader`
* **IDE Recomendada:** IntelliJ IDEA

---

## ⚙️ Como Rodar o Projeto

Como este é um sistema distribuído, a ordem de execução é importante.

### Pré-requisitos
Certifique-se de ter o **Java JDK** instalado e o projeto aberto na sua IDE (IntelliJ, Eclipse ou NetBeans).

### Passo 1: Iniciar o Servidor 🖥️
O servidor deve ser o primeiro a ser executado para "abrir a sala" e ouvir a porta.

1.  Navegue até a classe `Servidor.java` (no pacote `br.com.chat`).
2.  Execute o arquivo (Run 'Servidor.main()').
3.  O console exibirá: `Servidor de Chat iniciado na porta 12345...`

### Passo 2: Iniciar os Clientes 👤
Agora você pode conectar quantos clientes quiser.

1.  Navegue até a classe `Cliente.java`.
2.  Execute o arquivo.
3.  No console do Cliente, digite seu nome quando solicitado.
4.  Comece a enviar mensagens!

---

## 💡 Dica para IntelliJ IDEA: Rodar Múltiplos Clientes

Por padrão, o IntelliJ pode impedir que você rode a mesma classe (`Cliente`) duas vezes. Para simular uma conversa entre duas pessoas na mesma máquina:

1.  No canto superior direito, clique na lista de configurações de execução (ao lado do botão Play) e selecione **Edit Configurations...**.
2.  Selecione a configuração **Application > Cliente** no menu esquerdo.
3.  No lado direito, clique em **Modify options** (ou procure diretamente) e marque a opção **Allow multiple instances** (Permitir múltiplas instâncias).
4.  Clique em **OK**.
5.  Agora você pode dar "Play" na classe `Cliente` várias vezes. Cada clique abrirá uma nova aba de Console (Cliente 1, Cliente 2, etc.).

---

## 📂 Estrutura do Projeto

```text
src/
└── br/
    └── com/
        └── chat/
            ├── Servidor.java      # Gerencia conexões e faz o broadcast
            └── Cliente.java       # Conecta ao servidor, envia e recebe mensagens
