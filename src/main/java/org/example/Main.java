package org.example;

public class Main {
    public static void main(String[] args) {
       Tabuleiro jogo = new Tabuleiro(Dificuldade.FACIL);

       System.out.printf("%d", jogo.getNumeroBombas());
    }
}