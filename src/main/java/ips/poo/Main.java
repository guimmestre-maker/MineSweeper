package ips.poo;


import ips.poo.View.Menu;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage){
        stage.setTitle("MineSweeper");
        stage.setResizable(false);
        stage.setScene(new Menu.MenuView(stage).criarScene());
        stage.show();

    }
}