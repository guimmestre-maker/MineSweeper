package ips.poo.Model;

public class CelulaNr extends Celula {

    private final int minasVizinhas;

    public CelulaNr(int linha, int coluna, int minasVizinhas) {
        super(linha, coluna);

        // Defesa do modelo: uma célula com número só faz sentido num tabuleiro de duas dimensões normais (coordenadas positivas).
        if (linha < 0 || coluna < 0) {
            throw new IllegalArgumentException("As coordenadas da célula numérica não podem ser negativas!");
        }

        // Regra de negócio do Campo Minado: uma célula de número tem de ter pelo menos 1 mina à volta (senão seria uma CelulaVazia)
        // e no máximo 8 minas (o limite físico de vizinhos numa matriz).
        if (minasVizinhas < 1 || minasVizinhas > 8) {
            throw new IllegalArgumentException("O número de minas vizinhas deve estar obrigatoriamente entre 1 e 8!");
        }

        this.minasVizinhas = minasVizinhas;
    }

    public int getMinasVizinhas() {
        return minasVizinhas;
    }

    @Override
    public int getValor() {
        // Sobreescrevemos o método abstrato da superclasse para devolver o número real de perigos detetados ao redor desta célula
        return minasVizinhas;
    }
}