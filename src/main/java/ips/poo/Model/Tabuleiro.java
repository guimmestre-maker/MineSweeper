package ips.poo.Model;

import java.util.Random;

public class Tabuleiro {

    private final int linhas;
    private final int colunas;
    private final int numeroMinas;
    private final Celula[][] celulas;
    private SituacaoJogo situacao;
    private int celulasReveladas;

    public Tabuleiro(Dificuldade dificuldade) {
        this.linhas = dificuldade.getLinhas();
        this.colunas = dificuldade.getColunas();
        this.numeroMinas = dificuldade.getNumeroMinas();
        this.celulas = new Celula[linhas][colunas];
        this.situacao = SituacaoJogo.EM_CURSO;
        this.celulasReveladas = 0;

        colocarMinas();
        preencherCelulasRestantes();
    }

    private void colocarMinas() {
        Random random = new Random();
        int colocadas = 0;
        while (colocadas < numeroMinas) {
            int l = random.nextInt(linhas);
            int c = random.nextInt(colunas);
            if (celulas[l][c] == null) {
                celulas[l][c] = new CelulaMina(l, c);
                colocadas++;
            }
        }
    }

    private void preencherCelulasRestantes() {
        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                if (celulas[l][c] == null) {
                    int minasVizinhas = contarMinasVizinhas(l, c);
                    if (minasVizinhas == 0) {
                        celulas[l][c] = new CelulaVazia(l, c);
                    } else {
                        celulas[l][c] = new CelulaNr(l, c, minasVizinhas);
                    }
                }
            }
        }
    }

    private int contarMinasVizinhas(int linha, int coluna) {
        int contagem = 0;
        for (int dl = -1; dl <= 1; dl++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dl == 0 && dc == 0) continue;
                int vl = linha + dl;
                int vc = coluna + dc;
                // depois
                if (dentroDosLimites(vl, vc)
                        && celulas[vl][vc] != null
                        && celulas[vl][vc].temMina()) {
                    contagem++;
                }
            }
        }
        return contagem;
    }

    private boolean dentroDosLimites(int linha, int coluna) {
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    public void revelar(int linha, int coluna) {
        if (situacao != SituacaoJogo.EM_CURSO) return;
        if (!dentroDosLimites(linha, coluna)) return;

        Celula celula = celulas[linha][coluna];
        if (celula.estaRevelada() || celula.estaMarcada()) return;

        celula.revelar();

        if (celula.temMina()) {
            situacao = SituacaoJogo.DERROTA;
            revelarTodasAsMinas();
            return;
        }

        celulasReveladas++;

        if (celula.eVazia()) {
            for (int dl = -1; dl <= 1; dl++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dl == 0 && dc == 0) continue;
                    revelar(linha + dl, coluna + dc);
                }
            }
        }

        if (celulasReveladas == linhas * colunas - numeroMinas) {
            situacao = SituacaoJogo.VITORIA;
        }
    }

    private void revelarTodasAsMinas() {
        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                if (celulas[l][c] instanceof CelulaMina) {
                    celulas[l][c].revelar();
                }
            }
        }
    }

    public void marcar(int linha, int coluna) {
        if (situacao != SituacaoJogo.EM_CURSO) return;
        if (!dentroDosLimites(linha, coluna)) return;

        Celula celula = celulas[linha][coluna];
        if (celula.estaRevelada()) return;

        if (celula.estaMarcada()) {
            celula.desmarcar();
        } else {
            celula.marcar();
        }
    }

    public Celula getCelula(int linha, int coluna) {
        return celulas[linha][coluna];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public int getNumeroMinas() {
        return numeroMinas;
    }

    public SituacaoJogo getSituacao() {
        return situacao;
    }
}