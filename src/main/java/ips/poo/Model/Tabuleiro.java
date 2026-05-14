package ips.poo.Model;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private int numeroBombas;
    private Celula[][] celulas;
    private SituacaoJogo situacao;

    public Tabuleiro(Dificuldade dif) {
        this.linhas = dif.getLinhas();
        this.colunas = dif.getColunas();
        this.numeroBombas = dif.getNumeroBombas();
        this.celulas = new Celula[this.linhas][this.colunas];
        this.situacao = SituacaoJogo.EM_CURSO;
    }

    public int getLinhas() {
        return this.linhas;
    }

    public int getColunas() {
        return this.colunas;
    }

    public int getNumeroBombas() {
        return this.numeroBombas;
    }

    public Celula[][] getCelulas() {
        return this.celulas;
    }

    public Celula getCelula(int l, int c) {
        return this.celulas[l][c];
    }

    public SituacaoJogo getSituacao() {
        return this.situacao;
    }

    public void setSituacao(SituacaoJogo sj) {
        this.situacao = sj;
    }
}
