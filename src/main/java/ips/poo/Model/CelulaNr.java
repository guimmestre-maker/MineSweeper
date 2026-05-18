package ips.poo.Model;

public class CelulaNr extends Celula{
    private int numeroBombas;

    public CelulaNr(int linhas, int colunas, int nrbomb) {
        super(linhas, colunas);
        this.numeroBombas = nrbomb;
    }

    @Override
    public void revelar() {

    }

    public int getNumeroBombas() {

        return this.numeroBombas;
    }

    public void setNumeroBombas(int var1) {

        this.numeroBombas = var1;
    }
}
