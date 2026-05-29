package ips.poo.Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class CelulaTest {

    private Celula celula;

    @BeforeEach
    void setUp() {
        // Antes de cada teste começar, criamos uma célula limpa para garantir que um teste não mexe no outro
        celula = new CelulaVazia(0,0);
    }

    @Test
    void celulaNovaNaoEstaRevelada() {
        // Quando o jogo começa, todas as células têm de nascer escondidas do jogador
        assertFalse(celula.estaRevelada());
    }

    @Test
    void revelarMudaEstado() {
        // Se o jogador clicar na célula, ela tem de passar obrigatoriamente para o estado de revelada
        celula.revelar();
        assertTrue(celula.estaRevelada());
    }

    @Test
    void celulaNovaNaoEstaMarcada() {
        // Uma célula nova não pode vir com uma bandeira colocada por defeito
        assertFalse(celula.estaMarcada());
    }

    @Test
    void marcarCelulaMudaEstado() {
        // Quando o jogador suspeita de uma mina e mete uma bandeira, a célula tem de ficar marcada
        celula.marcar();
        assertTrue(celula.estaMarcada());
    }

    @Test
    void celulaVaziaNaoTemMina() {
        // Teste de segurança: uma instância de CelulaVazia nunca pode fingir que tem uma mina lá dentro
        assertFalse(celula.temMina());
    }
}