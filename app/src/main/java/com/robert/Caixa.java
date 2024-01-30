package com.robert;

import java.util.ArrayList;
import java.util.Arrays;

public class Caixa {


    // Depósito
    public void deposito(Usuario usuario, double valor) {
        if (valor > 0) {
            usuario.realizarDeposito(valor);
            System.out.println("Depósito de valor R$" + valor + " realizado com sucesso!");
            System.out.println("Novo saldo da conta: R$" + usuario.getSaldo());
        } else {
            System.out.println("Valor abaixo do esperado! O depósito deve ser maior que zero.");
        }
    }


    // Saque
    public void saque(Usuario usuario, double valor) {
        if (valor > 0 && valor < usuario.getSaldo()) {
            usuario.realizarSaque(valor);
            System.out.print("Saque de valor R$" + valor + " realizado com sucesso!");
            System.out.println("Novo saldo da conta: R$" + usuario.getSaldo());
        } else {
            System.out.println("Valor de saque inválido ou saldo insuficiente.");
        }
    }


    // Transferência 
    public boolean transferencia(Usuario origem, String cartaoDestino, double valor) {
        if (origem.getSaldo() >= valor) {
            for (Usuario destino : App.getListaUsuarios()) {
                if (destino.getCartao().equals(cartaoDestino)) {
                    double valorTaxa = valor+(valor*(2.5/100));
                    origem.realizarSaque(valorTaxa);
                    destino.realizarDeposito(valor);
                    return true;
                }
            }
        }
        return false;
    }


    // Rifa RR BANK
    public void compraRifa(Usuario usuario, double valor) {
        if (valor > 0 && valor < usuario.getSaldo()) {
            usuario.realizarSaque(valor);
            System.out.println(" ");
            System.out.println("Compra de valor R$" + valor + " realizado com sucesso!");
            System.out.println("Novo saldo da conta: R$" + usuario.getSaldo());
        } else {
            System.out.println("Valor de saque inválido ou saldo insuficiente.");
        }
    }

    public void premioCota(Usuario usuario, double valor) {
        usuario.realizarDeposito(valor); 
    }

    public void cotasPremiadas(Usuario usuario, ArrayList<Integer> numerosEscolhidos) {
        int[] numerosPremiados = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        // Contar o número de cotas premiadas
        int cotasPremiadas = 0;
        for (int numeroEscolhido : numerosEscolhidos) {
            if (Arrays.binarySearch(numerosPremiados, numeroEscolhido) >= 0) {
                cotasPremiadas++;
            }
        }

        if (cotasPremiadas > 0) {
            int premioTotal = cotasPremiadas * 3000;
            premioCota(usuario, premioTotal);
            System.out.println("Parabéns! Você encontrou " + cotasPremiadas + " cotas premiadas.");
            System.out.println("Prêmio de R$" + premioTotal + " nas cotas premiadas!");
            System.out.println("Novo saldo da conta: R$" + usuario.getSaldo());
            System.out.println(" ");
        }
    }

}
