package ips.poo.Model;

public class CelulaNr extends Celula{
    private int numeroBombas;

    public CelulaNr(int var1, int var2, int var3) {
        super(var1, var2);
        this.numeroBombas = var3;
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
