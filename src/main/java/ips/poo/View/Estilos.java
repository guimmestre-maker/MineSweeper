package ips.poo.View;

public class Estilos {
    private Estilos() {}

    public static final String DISPLAY =
            "-fx-background-color: black;" +
                    "-fx-text-fill: red;" +
                    "-fx-padding: 4 12 4 12;" +
                    "-fx-border-color: #7b7b7b #ffffff #ffffff #7b7b7b;" +
                    "-fx-border-width: 2;";

    public static final String TOPO =
            "-fx-background-color: #c0c0c0;" +
                    "-fx-border-color: #ffffff #7b7b7b #7b7b7b #ffffff;" +
                    "-fx-border-width: 2;";

    public static final String CELULA =
            "-fx-background-color: #c0c0c0;" +
                    "-fx-background-radius: 0;" +
                    "-fx-border-color: #ffffff #7b7b7b #7b7b7b #ffffff;" +
                    "-fx-border-width: 2;";

    public static final String CELULA_HOVER =
            "-fx-background-color: #d4d4d4;" +
                    "-fx-background-radius: 0;" +
                    "-fx-border-color: #ffffff #7b7b7b #7b7b7b #ffffff;" +
                    "-fx-border-width: 2;";

    public static final String CELULA_REVELADA =
            "-fx-background-color: #d4d4d4; -fx-border-color: #808080;"
                    + "-fx-font-weight: bold; -fx-font-size: 14;"
                    + "-fx-padding: 0;";

    public static final String CELULA_BOMBA =
            "-fx-background-color: #ff4040; -fx-border-color: #808080;";

    public static String celulaNumero(int n) {
        String cor = switch (n) {
            case 1 -> "blue";
            case 2 -> "green";
            case 3 -> "red";
            case 4 -> "darkblue";
            case 5 -> "darkred";
            case 6 -> "teal";
            case 7 -> "black";
            default -> "gray";
        };
        return CELULA_REVELADA + "-fx-text-fill: " + cor + ";";
    }
}
