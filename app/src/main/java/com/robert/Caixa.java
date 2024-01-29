package com.robert;

public class Caixa {

    public void deposito(Usuario usuario, double valor) {
        if (valor > 0) {
            usuario.realizarDeposito(valor);
            System.out.println("Depósito de valor " + valor + " realizado com sucesso!");
            System.out.println("Novo saldo da conta: R$" + usuario.getSaldo());
        } else {
            System.out.println("Valor abaixo do esperado! O depósito deve ser maior que zero.");
        }
    }

    public void saque(Usuario usuario, double valor) {
        if (valor > 0 && valor < usuario.getSaldo()) {
            usuario.realizarSaque(valor);
            System.out.println("Saque de valor " + valor + " realizado com sucesso!");
            System.out.println("Novo saldo da conta: R$" + usuario.getSaldo());
        } else {
            System.out.println("Valor de saque inválido ou saldo insuficiente.");
        }
    }

    public boolean transferencia(Usuario origem, String cartaoDestino, double valor) {
        if (origem.getSaldo() >= valor) {
            for (Usuario destino : App.getListaUsuarios()) {
                if (destino.getCartao().equals(cartaoDestino)) {
                    origem.realizarSaque(valor);
                    destino.realizarDeposito(valor);
                    return true;
                }
            }
        }
        return false;
    }

    public void compraRifa(Usuario usuario, double valor) {
        if (valor > 0 && valor < usuario.getSaldo()) {
            usuario.realizarSaque(valor);
            System.out.println("Compra de valor " + valor + " realizado com sucesso!");
            System.out.println("Novo saldo da conta: R$" + usuario.getSaldo());
        } else {
            System.out.println("Valor de saque inválido ou saldo insuficiente.");
        }
    }

}
