package ips.poo.Model;

public enum Dificuldade {
    // Definimos os três modos clássicos de jogo com os tamanhos oficiais do Campo Minado
    FACIL(9, 9, 10),
    MEDIO(16, 16, 40),
    DIFICIL(16, 30, 99);

    private final int linhas;
    private final int colunas;
    private final int numeroMinas;

    private Dificuldade(int linhas, int colunas, int minas) {
        // Garante que nenhuma configuração futura do Enum quebre as regras do jogo
        if (linhas <= 0 || colunas <= 0 || minas <= 0) {
            throw new IllegalArgumentException("As dimensões e o número de minas da dificuldade devem ser maiores que zero!");
        }

        // Impede a criação de um modo de jogo onde haja mais minas do que espaços disponíveis no tabuleiro
        if (minas >= (linhas * colunas)) {
            throw new IllegalArgumentException("Configuração impossível: o número de minas não pode preencher todo o tabuleiro!");
        }

        // Guardamos as configurações de cada nível de forma imutável assim que o Enum é carregado
        this.linhas = linhas;
        this.colunas = colunas;
        this.numeroMinas = minas;
    }

    public int getLinhas() {

        return this.linhas;
    }

    public int getColunas() {

        return this.colunas;
    }

    public int getNumeroMinas() {

        return this.numeroMinas;
    }
}