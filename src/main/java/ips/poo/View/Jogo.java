package ips.poo.View;

import ips.poo.Model.Dificuldade;
import ips.poo.Model.Tabuleiro;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.nio.charset.Charset;



public class Jogo {

    public static class JogoView {

        private static final double TAM_CELULA = 28;

        private final Stage stage;
        private final Dificuldade dif;
        private final Tabuleiro tabuleiro;
        private Timeline cronometro;
        private int segundos = 000;



        public JogoView(Stage stage, Dificuldade dif) {
            this.stage = stage;
            this.dif = dif;
            this.tabuleiro = new Tabuleiro(dif);
        }

        public Scene criarScene() {
            BorderPane root = new BorderPane();
            root.setPadding(new Insets(10));

            root.setTop(criarTopo());
            root.setCenter(criarGrelha());

            return new Scene(root);
        }

        private GridPane criarTopo() {

            Button voltar = new Button("☺");
            voltar.setFont(Font.font(28));
            voltar.setPrefSize(50, 50);
            voltar.setStyle(Estilos.DISPLAY + "-fx-cursor: hand;" + "-fx-background-color: #c0c0c0;" + "-fx-text-fill: black;");

            voltar.setOnAction(e ->
                    stage.setScene(new Menu.MenuView(stage).criarScene())
            );

            Label bombas = new Label( " " + dif.getNumeroBombas());
            bombas.setFont(Font.font("Monospaced", FontWeight.BOLD, 22));
            bombas.setTextFill(Color.RED);
            bombas.setAlignment(Pos.CENTER);
            bombas.setStyle(Estilos.DISPLAY);

            Label tempo = new Label("000");
            tempo.setFont(Font.font("Monospaced", FontWeight.BOLD, 22));
            tempo.setTextFill(Color.RED);
            tempo.setAlignment(Pos.CENTER);
            tempo.setStyle(Estilos.DISPLAY);
            cronometro = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                segundos++;
                tempo.setText( String.format("%03d", Math.min(segundos, 999)));
            }));
            cronometro.setCycleCount(Timeline.INDEFINITE);
            cronometro.play();

            // configuração das colunas
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

            topo.add(tempo, 0, 0);
            topo.add(voltar, 1, 0);
            topo.add(bombas, 2, 0);

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
                    Button cel = new Button();
                    cel.setPrefSize(TAM_CELULA, TAM_CELULA);
                    cel.setMinSize(TAM_CELULA, TAM_CELULA);
                    cel.setFocusTraversable(false);
                    cel.setStyle(Estilos.CELULA);

                    cel.setOnMouseEntered(e -> cel.setStyle(Estilos.CELULA_HOVER));
                    cel.setOnMouseExited (e -> cel.setStyle(Estilos.CELULA));

                    grid.add(cel, c, l);
                }

            }

            return grid;
        }
    }
}