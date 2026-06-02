package ips.poo.View;

import ips.poo.Model.Celula;
import ips.poo.Model.Dificuldade;
import ips.poo.Model.SituacaoJogo;
import ips.poo.Model.Tabuleiro;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Jogo {

    public static class JogoView {

        private static final double TAM_CELULA = 28;

        private final Stage stage;
        private final Dificuldade dif;
        private final Tabuleiro tabuleiro;
        private final Button[][] botoes;

        private Timeline cronometro;
        private int segundos = 0;
        private int marcadas = 0;

        private Label minasLabel;
        private Label tempoLabel;
        private Button voltar;

        public JogoView(Stage stage, Dificuldade dif) {
            this.stage = stage;
            this.dif = dif;
            this.tabuleiro = new Tabuleiro(dif);
            this.botoes = new Button[dif.getLinhas()][dif.getColunas()];
        }

        public Scene criarScene() {
            BorderPane root = new BorderPane();
            root.setPadding(new Insets(10));
            root.setTop(criarTopo());
            root.setCenter(criarGrelha());
            return new Scene(root);
        }

        private GridPane criarTopo() {
            voltar = new Button("☺");
            voltar.setFont(Font.font(28));
            voltar.setPrefSize(50, 50);
            voltar.setStyle(Estilos.DISPLAY + "-fx-cursor: hand;-fx-background-color: #c0c0c0;-fx-text-fill: black;");
            voltar.setOnAction(e -> {
                if (cronometro != null) cronometro.stop();
                stage.setScene(new Menu.MenuView(stage).criarScene());
            });

            minasLabel = new Label(" " + dif.getNumeroMinas());
            minasLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 22));
            minasLabel.setTextFill(Color.RED);
            minasLabel.setAlignment(Pos.CENTER);
            minasLabel.setStyle(Estilos.DISPLAY);

            tempoLabel = new Label("000");
            tempoLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 22));
            tempoLabel.setTextFill(Color.RED);
            tempoLabel.setAlignment(Pos.CENTER);
            tempoLabel.setStyle(Estilos.DISPLAY);

            cronometro = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                segundos++;
                tempoLabel.setText(String.format("%03d", Math.min(segundos, 999)));
            }));
            cronometro.setCycleCount(Timeline.INDEFINITE);
            cronometro.play();

            ColumnConstraints colEsq = new ColumnConstraints();
            colEsq.setHgrow(Priority.ALWAYS);
            colEsq.setHalignment(HPos.CENTER);

            ColumnConstraints colMeio = new ColumnConstraints();
            colMeio.setHalignment(HPos.CENTER);

            ColumnConstraints colDir = new ColumnConstraints();
            colDir.setHgrow(Priority.ALWAYS);
            colDir.setHalignment(HPos.CENTER);

            GridPane topo = new GridPane();
            topo.getColumnConstraints().addAll(colEsq, colMeio, colDir);
            topo.setPadding(new Insets(8, 12, 8, 12));
            topo.setStyle(Estilos.TOPO);
            topo.add(tempoLabel, 0, 0);
            topo.add(voltar, 1, 0);
            topo.add(minasLabel, 2, 0);
            return topo;
        }

        private GridPane criarGrelha() {
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setStyle(Estilos.TOPO);
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(1);
            grid.setVgap(1);

            for (int l = 0; l < dif.getLinhas(); l++) {
                for (int c = 0; c < dif.getColunas(); c++) {
                    final int linha = l;
                    final int coluna = c;

                    Button cel = new Button();
                    cel.setPrefSize(TAM_CELULA, TAM_CELULA);
                    cel.setMinSize(TAM_CELULA, TAM_CELULA);
                    cel.setFocusTraversable(false);
                    cel.setEllipsisString("");
                    cel.setFont(Font.font(14));
                    cel.setStyle(Estilos.CELULA);

                    cel.setOnMouseEntered(e -> {
                        Celula g = tabuleiro.getCelula(linha, coluna);
                        if (g.estaRevelada()) return;
                        if (g.estaMarcada()) {
                            cel.setStyle(Estilos.CELULA_HOVER + ";-fx-font-size: 10px;");
                        } else {
                            cel.setStyle(Estilos.CELULA_HOVER);
                        }
                    });
                    cel.setOnMouseExited(e -> {
                        Celula g = tabuleiro.getCelula(linha, coluna);
                        if (g.estaRevelada()) return;
                        if (g.estaMarcada()) {
                            cel.setStyle(Estilos.CELULA + ";-fx-font-size: 10px;");
                        } else {
                            cel.setStyle(Estilos.CELULA);
                        }
                    });

                    cel.setOnMouseClicked(e -> tratarClique(linha, coluna, e.getButton()));

                    botoes[l][c] = cel;
                    grid.add(cel, c, l);
                }
            }
            return grid;
        }

        private void tratarClique(int linha, int coluna, MouseButton botao) {
            if (tabuleiro.getSituacao() != SituacaoJogo.EM_CURSO) return;

            Celula celula = tabuleiro.getCelula(linha, coluna);

            if (botao == MouseButton.PRIMARY) {
                if (celula.estaMarcada()) return;
                tabuleiro.revelar(linha, coluna);
            } else if (botao == MouseButton.SECONDARY) {
                if (celula.estaRevelada()) return;
                boolean estavaMarcada = celula.estaMarcada();
                tabuleiro.marcar(linha, coluna);
                marcadas += estavaMarcada ? -1 : 1;
                minasLabel.setText(" " + (dif.getNumeroMinas() - marcadas));
            }

            actualizar();
        }

        private void actualizar() {
            for (int l = 0; l < dif.getLinhas(); l++) {
                for (int c = 0; c < dif.getColunas(); c++) {
                    actualizarBotao(l, c);
                }
            }
            verificarFimDeJogo();
        }

        private void actualizarBotao(int linha, int coluna) {
            Button botao = botoes[linha][coluna];
            Celula celula = tabuleiro.getCelula(linha, coluna);

            if (celula.estaRevelada()) {
                if (celula.temMina()) {
                        botao.setText("✸");
                        botao.setStyle(Estilos.CELULA_BOMBA + ";-fx-font-size: 10px;");

                } else if (celula.getValor() > 0) {
                    botao.setText(String.valueOf(celula.getValor()));
                    botao.setStyle(Estilos.celulaNumero(celula.getValor()));
                } else {
                    botao.setStyle(Estilos.CELULA_REVELADA);
                }
            } else if (celula.estaMarcada()) {
                botao.setText("⚑");
                botao.setStyle(Estilos.CELULA + ";-fx-font-size: 10px;");

            } else {
                botao.setText("");
                botao.setStyle(Estilos.CELULA);
            }
        }

        private void verificarFimDeJogo() {
            SituacaoJogo s = tabuleiro.getSituacao();
            if (s == SituacaoJogo.EM_CURSO) return;

            cronometro.stop();

            String mensagem = s == SituacaoJogo.VITORIA ? "Ganhaste!" : "Perdeste!";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, mensagem);
            alert.setHeaderText(null);
            alert.showAndWait();
            stage.setScene(new Menu.MenuView(stage).criarScene());
        }

    }
}