package ips.poo.Model;

public abstract class Celula {

    private final int linha;
    private final int coluna;
    private boolean revelada;
    private boolean marcada;

    public Celula(int linha, int coluna) {
        // Inicializamos a célula nas suas coordenadas e garantimos que começa o jogo "escondida" e sem bandeiras
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
        // Segurança ativa: impede que o jogo revele acidentalmente uma célula protegida por uma bandeira
        if (this.marcada) {
            throw new IllegalStateException("Não é possível revelar uma célula que está marcada com uma bandeira!");
        }
        this.revelada = true;
    }

    public void marcar() {
        // Se a célula já está aberta e o número está à vista, colocar uma bandeira seria um erro de lógica
        if (this.revelada) {
            throw new IllegalStateException("Não é possível marcar uma célula que já foi revelada!");
        }
        this.marcada = true;
    }

    public void desmarcar() {
        // Só tentamos retirar uma bandeira se ela de facto lá estiver, evitando inconsistências no estado do jogo
        if (!this.marcada) {
            throw new IllegalStateException("Não podes desmarcar uma célula que não está marcada!");
        }
        this.marcada = false;
    }

    public boolean temMina() {
        // Comportamento padrão. Apenas a subclasse CelulaMina irá sobrepor este método para devolver true
        return false;
    }

    public boolean eVazia() {
        // Identificador para células sem minas ao redor, essencial para o algoritmo que abre espaços vazios em cadeia
        return false;
    }

    public int getValor() {
        // Devolve o número de minas vizinhas (usado na CelulaNr). Por defeito é zero para as restantes
        return 0;
    }
}