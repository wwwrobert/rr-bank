package com.robert;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Usuario {

    private String nome;
    private UUID userId;
    private ArrayList<Integer> numerosEscolhidos;
    private String cartao;
    private String cpf;
    private String senha;
    private double saldo;

    public Usuario() {
        this.userId = UUID.randomUUID();
        this.numerosEscolhidos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }

    public UUID getUserId() {
        return userId;
    }

    public ArrayList<Integer> getNumerosEscolhidos() {
        return numerosEscolhidos;
    }

    public void adicionarNumerosEscolhidos(ArrayList<Integer> numeros) {
        this.numerosEscolhidos.addAll(numeros);
    }

    public String getCartao() {
        return cartao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        // Adicione validação de CPF aqui, se necessário
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void realizarDeposito(double valor) {
        if (valor > 0) {
            saldo += valor;
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void realizarSaque(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Valor de saque inválido ou saldo insuficiente.");
        }
    }

    public void gerarNumeroCartao() {
        Random random = new Random();
        StringBuilder numeroCartao = new StringBuilder();

        // Gere 16 dígitos aleatórios para o número do cartão
        for (int i = 0; i < 16; i++) {
            int digito = random.nextInt(10);
            numeroCartao.append(digito);
        }

        this.cartao = numeroCartao.toString();
    }

    public Usuario autenticar(String cpf, String senha, ArrayList<Usuario> listaUsuarios) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean cpfExistente(String cpf, ArrayList<Usuario> listaUsuarios) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return true; // Já existe um usuário com este CPF
            }
        }
        return false; // CPF não encontrado na lista
    }

    @Override
    public String toString() {
        return "\nID = " + userId + "\nNome = " + nome + "\nCartão = " + cartao + "\nCPF = " + cpf
                + "\nSenha = " + senha + "\nSaldo = " + saldo + "\n";
    }
}
