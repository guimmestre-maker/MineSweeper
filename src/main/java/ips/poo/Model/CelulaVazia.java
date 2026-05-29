package ips.poo.Model;

public class CelulaVazia extends Celula {

    public CelulaVazia(int linha, int coluna) {
        super(linha, coluna);

        // Tal como nas outras células, garantimos que ninguém tenta criar uma posição impossível no mapa.
        if (linha < 0 || coluna < 0) {
            throw new IllegalArgumentException("As coordenadas de uma célula vazia não podem ser negativas!");
        }
    }

    @Override
    public boolean eVazia() {
        // Método crucial para o tabuleiro saber que, ao clicar aqui, pode disparar a abertura em cascata dos vizinhos.
        return true;
    }
}