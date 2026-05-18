package ips.poo.Model;

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

    public void setRevelada(boolean rev) {

        this.revelada = rev;
    }

    public boolean isTemBandeira() {

        return this.temBandeira;
    }

    public void setTemBandeira(boolean ban) {

        this.temBandeira = ban;
    }
}
