package ips.poo.Model;

public class CelulaNr extends Celula {

    private final int minasVizinhas;

    public CelulaNr(int linha, int coluna, int minasVizinhas) {
        super(linha, coluna);
        this.minasVizinhas = minasVizinhas;
    }

    public int getMinasVizinhas() {
        return minasVizinhas;
    }

    @Override
    public int getValor() {
        return minasVizinhas;
    }
}