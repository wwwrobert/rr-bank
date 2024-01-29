package com.robert;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {

    private static ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();

    public static ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> numerosRifas = new ArrayList<Integer>();
        for (int i = 1; i <= 100000; i++) {
            numerosRifas.add(i);
        }

        Usuario usuario = null;
        Caixa caixa = new Caixa();

        int escolha, escolha2;

        Menu menu = new Menu();
        Menu menu2 = new Menu();

        System.out.print(menu.getMenu());
        escolha = scanner.nextInt();
        System.out.println(" ");

        while (escolha != 0) {
            switch (escolha) {
                case 1:
                    System.out.println("=======================");
                    System.out.println("| CADASTRO DE USUÁRIO |");
                    System.out.println("=======================");
                    System.out.println(" ");

                    usuario = new Usuario();

                    System.out.println("Digite seu nome:");
                    String nome = scanner.next();
                    usuario.setNome(nome);

                    System.out.println("Digite seu cpf:");
                    String cpf = scanner.next();
                    while (cpf.length() != 11 || usuario.cpfExistente(cpf, listaUsuarios)) {
                        if (cpf.length() != 11) {
                            System.out.println("O CPF está incorreto!");
                        } else {
                            System.out.println("Este CPF já está em uso. Digite outro.");
                        }
                    
                        System.out.print("Digite novamente: ");
                        cpf = scanner.next();
                    }
                    usuario.setCpf(cpf);

                    System.out.println("Digite uma senha:");
                    String senha = scanner.next();
                    while (senha.length() < 6) {
                        System.out.println("A senha deve conter no mínimo 6 caracteres!");
                        System.out.print("Digite novamente: ");
                        senha = scanner.next();
                    }
                    usuario.setSenha(senha);

                    usuario.gerarNumeroCartao();

                    listaUsuarios.add(usuario);

                    Clear.limparConsole();

                    System.out.print(menu2.getMenu2());
                    escolha2 = scanner.nextInt();
                    System.out.println(" ");

                    while (escolha2 != 0) {
                        switch (escolha2) {
                            case 1:
                                System.out.print("Digite o valor do depósito: R$");
                                double valor = scanner.nextDouble();
                                caixa.deposito(usuario, valor);

                                System.out.println("");
                                Clear.aguardarEnter(scanner);
                                System.out.println("");
                                Clear.limparConsole();
                                break;
                            case 2:
                                System.out.println("Digite o valor do saque: R$");
                                valor = scanner.nextDouble();
                                caixa.saque(usuario, valor);

                                System.out.println("");
                                Clear.aguardarEnter(scanner);
                                System.out.println("");
                                Clear.limparConsole();
                                break;
                            case 3:
                                System.out.println("===============");
                                System.out.println("|  SUA CONTA  |");
                                System.out.println("===============");

                                System.out.println(usuario);

                                System.out.println("");
                                Clear.aguardarEnter(scanner);
                                System.out.println("");
                                Clear.limparConsole();
                                break;
                            case 4:
                                System.out.println("=======================");
                                System.out.println("| FAZER TRANSFERÊNCIA |");
                                System.out.println("=======================");
                                System.out.println(" ");

                                System.out.print("Digite o número do cartão de destino: ");
                                String cartaoDestino = scanner.next();

                                System.out.print("Digite o valor da transferência: R$");
                                double valorTransferencia = scanner.nextDouble();

                                if (caixa.transferencia(usuario, cartaoDestino, valorTransferencia)) {
                                    System.out.println("Transferência realizada com sucesso!");
                                } else {
                                    System.out.println(
                                            "Falha na transferência. Verifique o número do cartão de destino ou saldo disponível.");
                                }

                                System.out.println("");
                                Clear.aguardarEnter(scanner);
                                System.out.println("");
                                Clear.limparConsole();
                                break;
                            case 5:
                                System.out.println("======================");
                                System.out.println("|    MEGA RR BANK    |");
                                System.out.println("======================");
                                System.out.println(" ");
                                System.out.println("Resultado pela loteria federal!");
                                System.out.println("(1) Comprar números da mega.");
                                System.out.println("(2) Consultar meus números.");
                                System.out.println(" ");
                                System.out.print("Selecione uma opção:");
                                int opcaoMegaRRBank = scanner.nextInt();
                                Clear.limparConsole();
 
                                switch (opcaoMegaRRBank) {
                                    case 1:  
                                        System.out.println("Valor da cota R$0.99");
                                        System.out.print("Escolha a quantidade de cotas: ");
                                        int quantCotas = scanner.nextInt();
                                        double valorCompra = (quantCotas * 0.99);

                                        if (usuario.getSaldo() >= valorCompra) {
                                            ArrayList<Integer> numerosJogo = new ArrayList<>();

                                            for (int i = 0; i < quantCotas; i++) {                                       
                                                int numeroAleatorio = numerosRifas.remove(new Random().nextInt(numerosRifas.size()));
                                                numerosJogo.add(numeroAleatorio);
                                            }
                                            System.out.println(" ");
                                            System.out.print("Cotas compradas: ");
                                            System.out.println(numerosJogo);
                                            System.out.println(" ");

                                            usuario.adicionarNumerosEscolhidos(numerosJogo); 
                                            caixa.compraRifa(usuario, valorCompra);
                                        } else {
                                            System.out.println("Saldo abaixo do valor de compra.");
                                        }

                                        System.out.println("");
                                        Clear.aguardarEnter(scanner);
                                        System.out.println("");
                                        Clear.limparConsole();
                                        break;
                                    case 2:
                                        System.out.println("=======================");
                                        System.out.println("|  CONSULTAR NÚMEROS  |");
                                        System.out.println("=======================");

                                        System.out.println(usuario.getNumerosEscolhidos());

                                        System.out.println("");
                                        Clear.aguardarEnter(scanner);
                                        System.out.println("");
                                        Clear.limparConsole();
                                        break;
                                }
                                break;
                        }

                        System.out.print(menu2.getMenu2());
                        escolha2 = scanner.nextInt();
                        System.out.println(" ");
                    }
                    Clear.limparConsole();
                    break;
                case 2:
                    System.out.println("======================");
                    System.out.println("| ENTRE NA SUA CONTA |");
                    System.out.println("======================");
                    System.out.println(" ");

                    Usuario usuarioAutenticacao = new Usuario();

                    System.out.println("Digite seu cpf:");
                    cpf = scanner.next();
                    usuarioAutenticacao.setCpf(cpf);

                    System.out.println("Digite sua senha:");
                    senha = scanner.next();
                    usuarioAutenticacao.setSenha(senha);

                    Usuario usuarioAutenticado = usuarioAutenticacao.autenticar(cpf, senha, listaUsuarios);

                    Clear.limparConsole();

                    if (usuarioAutenticado != null) {
                        System.out.print(menu2.getMenu2());
                        escolha2 = scanner.nextInt();
                        System.out.println(" ");

                        while (escolha2 != 0) {
                            switch (escolha2) {
                                case 1:
                                    System.out.print("Digite o valor do depósito: R$");
                                    double valor = scanner.nextDouble();
                                    caixa.deposito(usuarioAutenticado, valor);

                                    System.out.println("");
                                    Clear.aguardarEnter(scanner);
                                    System.out.println("");
                                    Clear.limparConsole();
                                    break;
                                case 2:
                                    System.out.println("Digite o valor do saque: R$");
                                    valor = scanner.nextDouble();
                                    caixa.saque(usuarioAutenticado, valor);

                                    System.out.println("");
                                    Clear.aguardarEnter(scanner);
                                    System.out.println("");
                                    Clear.limparConsole();
                                    break;
                                case 3:
                                    System.out.println("===============");
                                    System.out.println("|  SUA CONTA  |");
                                    System.out.println("===============");

                                    System.out.println(usuarioAutenticado);

                                    System.out.println("");
                                    Clear.aguardarEnter(scanner);
                                    System.out.println("");
                                    Clear.limparConsole();
                                    break;
                                case 4:
                                    System.out.println("=======================");
                                    System.out.println("| FAZER TRANSFERÊNCIA |");
                                    System.out.println("=======================");
                                    System.out.println(" ");

                                    System.out.print("Digite o número do cartão de destino: ");
                                    String cartaoDestino = scanner.next();

                                    System.out.print("Digite o valor da transferência: R$");
                                    double valorTransferencia = scanner.nextDouble();

                                    if (caixa.transferencia(usuarioAutenticado, cartaoDestino, valorTransferencia)) {
                                        System.out.println("Transferência realizada com sucesso!");
                                    } else {
                                        System.out.println(
                                                "Falha na transferência. Verifique o número do cartão de destino ou saldo disponível.");
                                    }

                                    System.out.println("");
                                    Clear.aguardarEnter(scanner);
                                    System.out.println("");
                                    Clear.limparConsole();
                                    break;
                                case 5:
                                    System.out.println("======================");
                                    System.out.println("|    MEGA RR BANK    |");
                                    System.out.println("======================");
                                    System.out.println(" ");
                                    System.out.println("(1) Comprar números da mega.");
                                    System.out.println("(2) Consultar meus números.");
                                    System.out.println(" ");
                                    System.out.print("Selecione uma opção:");
                                    int opcaoMegaRRBank = scanner.nextInt();
                                    Clear.limparConsole();
     
                                    switch (opcaoMegaRRBank) {
                                        case 1:  
                                            System.out.println("Valor da cota R$0.99");
                                            System.out.print("Escolha a quantidade de cotas: ");
                                            int quantCotas = scanner.nextInt();
                                            double valorCompra = (quantCotas * 0.99);
    
                                            if (usuario.getSaldo() >= valorCompra) {
                                                ArrayList<Integer> numerosJogo = new ArrayList<>();
    
                                                for (int i = 0; i < quantCotas; i++) {                                       
                                                    int numeroAleatorio = numerosRifas.remove(new Random().nextInt(numerosRifas.size()));
                                                    numerosJogo.add(numeroAleatorio);
                                                }
                                                System.out.println(" ");
                                                System.out.print("Cotas compradas: " );
                                                System.out.println(numerosJogo);
                                                System.out.println(" ");
    
                                                usuario.adicionarNumerosEscolhidos(numerosJogo); 
                                                caixa.compraRifa(usuario, valorCompra);
                                            } else {
                                                System.out.println("Saldo abaixo do valor de compra.");
                                            }
    
                                            System.out.println("");
                                            Clear.aguardarEnter(scanner);
                                            System.out.println("");
                                            Clear.limparConsole();
                                            break;
                                        case 2:
                                            System.out.println("=======================");
                                            System.out.println("|  CONSULTAR NÚMEROS  |");
                                            System.out.println("=======================");
    
                                            System.out.println(usuario.getNumerosEscolhidos());
    
                                            System.out.println("");
                                            Clear.aguardarEnter(scanner);
                                            System.out.println("");
                                            Clear.limparConsole();
                                            break;
                                    }
                                    break;    
                            }

                            System.out.print(menu2.getMenu2());
                            escolha2 = scanner.nextInt();
                            System.out.println(" ");
                        }
                        Clear.limparConsole();
                        break;
                    } else {
                        System.out.println("CPF ou senha incorretos, tente novamente!");
                    }
            }

            System.out.print(menu.getMenu());
            escolha = scanner.nextInt();
            System.out.println(" ");
        }
        scanner.close();
    }
}
