package com.orizon.system.message.application;

import com.orizon.system.message.config.AccountConfigurations;
import com.orizon.system.message.domain.model.User;
import com.orizon.system.message.domain.ports.services.MessageService;
import com.orizon.system.message.domain.ports.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ApplicationRunner {
    private final UserService userService;
    private final MessageService messageService;
    private final Scanner scan = new Scanner(System.in);

    @Autowired
    public ApplicationRunner(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    public void start() {
        boolean authenticated = false;

        while (!authenticated) {
            System.out.println("\n=== BEM-VINDO ===");
            System.out.println("1. Login");
            System.out.println("2. Registrar novo usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int initialChoice = scan.nextInt();
            scan.nextLine(); // Limpar buffer

            switch (initialChoice) {
                case 1 -> {
                    System.out.println("\n=== LOGIN ===");
                    System.out.print("Login: ");
                    String login = scan.nextLine();
                    System.out.print("Senha: ");
                    String password = scan.nextLine();

                    AccountConfigurations.login(userService.login(login, password));
                    System.out.println("Login bem-sucedido!");
                    authenticated = true;
                    showMainMenu();
                }
                case 2 -> registerNewUser();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void registerNewUser(){
        System.out.println("\n=== NOVO CADASTRO ===");
        System.out.print("Login: ");
        String login = scan.nextLine();

        System.out.print("Senha: ");
        String password = scan.nextLine();

        // Por padrão, novos usuários não são administradores
        userService.create(login, password);

        System.out.println("Usuário registrado com sucesso! Faça login para continuar.");
    }

    private void showMainMenu(){
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Enviar mensagem");
            System.out.println("2. Ler mensagem");
            System.out.println("3. Listar mensagens");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scan.nextInt();
            scan.nextLine(); // Limpar buffer

            switch (choice) {
                case 1 -> sendMessage();
                case 2 -> readMessage();
                case 3 -> listMessages();
                case 4 -> {
                    running = false;
                    System.out.println("Saindo...");
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void sendMessage() {
        System.out.println("\n=== ENVIAR MENSAGEM ===");
        System.out.print("ID do destinatário: ");
        Long receiverId = scan.nextLong();
        User receiverUser = userService.findById(receiverId);
        System.out.print("Conteúdo: ");
        String content = scan.nextLine();

        messageService.send(receiverUser, content);
        System.out.println("Mensagem enviada com sucesso!");
    }

    private void readMessage() {
        System.out.println("\n=== LER MENSAGEM ===");
        System.out.print("ID da mensagem: ");
        Long messageId = scan.nextLong();
        scan.nextLine(); // Limpar buffer

        String content = messageService.receive(messageId);
        System.out.println("Conteúdo da mensagem: " + content);
    }

    private void listMessages() {
        System.out.println("\n=== LISTAR MENSAGENS ===");
        System.out.println("Funcionalidade ainda não implementada.");
    }
}
