package ips.poo.Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class CelulaTest {

    private Celula celula;

    @BeforeEach
    void setUp() {
        celula = new CelulaVazia(0,0);
    }

    @Test
    void celulaNovaNaoEstaRevelada() {
        assertFalse(celula.estaRevelada());
    }

    @Test
    void revelarMudaEstado() {
        celula.revelar();
        assertTrue(celula.estaRevelada());
    }

    @Test
    void celulaNovaNaoEstaMarcada() {
        assertFalse(celula.estaMarcada());
    }

    @Test
    void marcarCelulaMudaEstado() {
        celula.marcar();
        assertTrue(celula.estaMarcada());
    }

    @Test
    void celulaVaziaNaoTemMina() {
        assertFalse(celula.temMina());
    }
}