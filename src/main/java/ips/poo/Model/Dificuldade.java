package ips.poo.Model;

public enum Dificuldade {
    FACIL(9, 9, 10),
    MEDIO(16, 16, 40),
    DIFICIL(16, 30, 99);

    private final int linhas;
    private final int colunas;
    private final int numeroBombas;

    private Dificuldade(int linhas, int colunas, int bombas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.numeroBombas = bombas;
    }

    public int getLinhas() {

        return this.linhas;
    }

    public int getColunas() {

        return this.colunas;
    }

    public int getNumeroBombas() {

        return this.numeroBombas;
    }
}
