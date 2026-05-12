package org.example;

public abstract class Celula {
    private int linha;
    private int coluna;
    private boolean revelada;
    private boolean temBandeira;

    public Celula(int linhas, int colunas ){
        this.linha = linhas;
        this.coluna = colunas;
        this.revelada = false;
        this.temBandeira = false;
    }
    public abstract void revelar();

    public int getLinha() {

        return this.linha;
    }

    public int getColuna() {

        return this.coluna;
    }

    public boolean isRevelada() {

        return this.revelada;
    }

    public void setRevelada(boolean var1) {

        this.revelada = var1;
    }

    public boolean isTemBandeira() {

        return this.temBandeira;
    }

    public void setTemBandeira(boolean var1) {

        this.temBandeira = var1;
    }
}
