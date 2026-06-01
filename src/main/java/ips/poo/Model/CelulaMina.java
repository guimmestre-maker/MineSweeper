package ips.poo.Model;

public class CelulaMina extends Celula {

    public CelulaMina(int linha, int coluna) {
        // Passamos as coordenadas para a superclasse, mas antes validamos se não são posições impossíveis no plano (valores negativos)
        super(linha, coluna);
        if (linha < 0 || coluna < 0) {
            throw new IllegalArgumentException("As coordenadas de uma célula com mina não podem ser negativas!");
        }
    }

    @Override
    public boolean temMina() {
        // Esta subclasse serve especificamente para representar as armadilhas,por isso responde sempre que sim
        return true;
    }
}