package ips.poo.Model;

public class CelulaVazia extends Celula {

    public CelulaVazia(int linha, int coluna) {
        super(linha, coluna);
    }

    @Override
    public boolean eVazia() {
        return true;
    }
}