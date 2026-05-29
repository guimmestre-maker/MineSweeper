package ips.poo;


import ips.poo.View.Menu;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        // Ponto de entrada padrão do Java que delega o arranque para o ciclo de vida do JavaFX
        launch(args);
    }


    @Override
    public void start(Stage stage){
        // Configurações básicas da janela principal antes de a mostrar ao utilizador
        stage.setTitle("MineSweeper");
        stage.setResizable(false); // Impede o utilizador de redimensionar a janela para não desformatar o layout do jogo

        // Instancia a vista do menu e define-a como a cena inicial que vai aparecer no ecrã
        stage.setScene(new Menu.MenuView(stage).criarScene());
        stage.show(); // Torna a janela finalmente visível

    }
}