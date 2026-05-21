package ips.poo.Model;

public abstract class Celula {

    private final int linha;
    private final int coluna;
    private boolean revelada;
    private boolean marcada;

    public Celula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.revelada = false;
        this.marcada = false;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public boolean estaRevelada() {
        return revelada;
    }

    public boolean estaMarcada() {
        return marcada;
    }

    public void revelar() {
        this.revelada = true;
    }

    public void marcar() {
        this.marcada = true;
    }

    public void desmarcar() {
        this.marcada = false;
    }

    public boolean temMina() {
        return false;
    }

    public boolean eVazia() {
        return false;
    }

    public int getValor() {
        return 0;
    }
}