package com.robert;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Usuario {

    private String nome;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    private UUID userId;
    private ArrayList<Integer> numerosEscolhidos;
    public Usuario() {
        this.userId = UUID.randomUUID();
        this.numerosEscolhidos = new ArrayList<>();
    }
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    public ArrayList<Integer> getNumerosEscolhidos() {
        return numerosEscolhidos;
    }
    
    public void adicionarNumerosEscolhidos(ArrayList<Integer> numeros) {
        this.numerosEscolhidos.addAll(numeros);
    }


    private String cartao;
    public String getCartao() {
        return cartao;
    }
    public void setCartao(String cartao) {
        this.cartao = cartao;
    }


    private String cpf;
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    private String senha;
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }


    private double saldo;
    public double getSaldo() {
        return saldo;
    }
    public void realizarDeposito(double valor) {
        saldo += valor;
    }
    public void realizarSaque(double valor) {
        saldo -= valor;
    }   
    

    public void gerarNumeroCartao() {
        Random random = new Random();
        StringBuilder numeroCartao = new StringBuilder();

        // Gere 16 dígitos aleatórios para o número do cartão
        for (int i = 0; i < 16; i++) {
            int digito = random.nextInt(6);
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
