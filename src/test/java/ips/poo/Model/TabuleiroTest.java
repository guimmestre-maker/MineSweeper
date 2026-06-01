package ips.poo.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TabuleiroTest {

    private Tabuleiro tabuleiro;

    @BeforeEach
    void setUp() {
        // Começamos sempre com um tabuleiro limpo e no modo fácil antes de cada teste
        tabuleiro = new Tabuleiro(Dificuldade.FACIL);
    }

    @Test
    void tabuleiroInicializaComDimensoesCorretas() {
        // Garante que o tabuleiro não é criado com tamanho zero ou negativo
        assertTrue(tabuleiro.getLinhas() > 0);
        assertTrue(tabuleiro.getColunas() > 0);
        assertTrue(tabuleiro.getNumeroMinas() > 0);
    }

    @Test
    void tabuleiroComecaEmCurso() {
        // O jogo tem de começar ativo, ninguém quer abrir um jogo que já acabou
        assertEquals(SituacaoJogo.EM_CURSO, tabuleiro.getSituacao());
    }

    @Test
    void todasAsCelulasDevemEstarInstanciadas() {
        // Corre o tabuleiro todo para ter a certeza que o mapa foi bem preenchido e não há buracos (null)
        for (int l = 0; l < tabuleiro.getLinhas(); l++) {
            for (int c = 0; c < tabuleiro.getColunas(); c++) {
                assertNotNull(tabuleiro.getCelula(l, c));
            }
        }
    }

    @Test
    void marcarCelulaMudaEstadoEPermiteDesmarcar() {
        // Se o jogador clicar com o botão direito, mete a bandeira, se clicar outra vez, tira
        tabuleiro.marcar(0, 0);
        assertTrue(tabuleiro.getCelula(0, 0).estaMarcada());

        tabuleiro.marcar(0, 0);
        assertFalse(tabuleiro.getCelula(0, 0).estaMarcada());
    }

    @Test
    void naoDeveSerPossivelMarcarCelulaRevelada() {
        // Procura a primeira célula segura (sem mina) para podermos clicar
        int l = 0, c = 0;
        while (tabuleiro.getCelula(l, c).temMina()) {
            c++;
        }

        // Revela a célula e depois tenta meter-lhe uma bandeira. O jogo deve ignorar a marcação
        tabuleiro.revelar(l, c);
        assertTrue(tabuleiro.getCelula(l, c).estaRevelada());

        tabuleiro.marcar(l, c);
        assertFalse(tabuleiro.getCelula(l, c).estaMarcada());
    }

    @Test
    void revelarMinaGaranteDerrotaERevelaTodasAsMinas() {
        // Varre o tabuleiro para descobrir onde o Random escondeu uma mina
        int linhaMina = -1;
        int colunaMina = -1;

        for (int l = 0; l < tabuleiro.getLinhas(); l++) {
            for (int c = 0; c < tabuleiro.getColunas(); c++) {
                if (tabuleiro.getCelula(l, c).temMina()) {
                    linhaMina = l;
                    colunaMina = c;
                    break;
                }
            }
        }

        // Força o clique na mina detetada
        tabuleiro.revelar(linhaMina, colunaMina);

        // O jogo tem de dar Game Over e mostrar onde estavam as outras minas todas para o jogador ver
        assertEquals(SituacaoJogo.DERROTA, tabuleiro.getSituacao());
        assertTrue(tabuleiro.getCelula(linhaMina, colunaMina).estaRevelada());

        for (int l = 0; l < tabuleiro.getLinhas(); l++) {
            for (int c = 0; c < tabuleiro.getColunas(); c++) {
                if (tabuleiro.getCelula(l, c).temMina()) {
                    assertTrue(tabuleiro.getCelula(l, c).estaRevelada());
                }
            }
        }
    }


}