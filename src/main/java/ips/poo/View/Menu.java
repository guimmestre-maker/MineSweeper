package ips.poo.View;

import ips.poo.Model.Dificuldade;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Menu {

    public static class MenuView {

        private Stage stage;

        public MenuView(Stage stage) {
            this.stage = stage;
        }

        public Scene criarScene() {

            Label titulo = new Label("MineSweeper");
            titulo.setFont(Font.font("Monospaced", FontWeight.BOLD, 32));
            titulo.setTextFill(Color.RED);

            Label subtitulo = new Label("Escolhe a dificuldade");
            subtitulo.setFont(Font.font("Monospaced", 14));
            subtitulo.setTextFill(Color.GRAY);

            Button btnFacil   = criarBotao("Fácil\n9×9 | 10 bombas",     Dificuldade.FACIL);
            Button btnMedio   = criarBotao("Médio\n16×16 | 40 bombas",   Dificuldade.MEDIO);
            Button btnDificil = criarBotao("Difícil\n16×30 | 99 bombas", Dificuldade.DIFICIL);

            VBox opcoes = new VBox(12, btnFacil, btnMedio, btnDificil);
            opcoes.setAlignment(Pos.CENTER);

            VBox principal = new VBox(20, titulo, subtitulo, opcoes);
            principal.setAlignment(Pos.CENTER);
            principal.setPadding(new Insets(80));

            return new Scene(principal);
        }

        private Button criarBotao(String texto, Dificuldade dif) {
            Button btn = new Button(texto);
            btn.setFont(Font.font("Monospaced", 13));
            btn.setPrefSize(160, 70);
            btn.setTextAlignment(TextAlignment.CENTER);
            btn.setOnAction(e -> iniciarJogo(dif));
            return btn;
        }

        private void iniciarJogo(Dificuldade dif) {
            stage.setScene(new Jogo.JogoView(stage, dif).criarScene());


        }
    }
}