package ips.poo.Model;

public class CelulaMina extends Celula {

    public CelulaMina(int linha, int coluna) {
        super(linha, coluna);
    }

    @Override
    public boolean temMina() {
        return true;
    }
}