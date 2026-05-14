package ips.poo;

import ips.poo.Model.Dificuldade;
import ips.poo.Model.Tabuleiro;
import javafx.scene.chart.StackedBarChart;

public class Main {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(Dificuldade.FACIL);
        System.out.printf("%d", tabuleiro.getNumeroBombas());
    }
}