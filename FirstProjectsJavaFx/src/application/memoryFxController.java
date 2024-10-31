package application;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class memoryFxController {
    @FXML
    private GridPane gameBoard;

    @FXML
    private Label playerOneScore, playerTwoScore, currentPlayerTurn;

    private int currentPlayer = 1;
    private int playerOnePoints = 0;
    private int playerTwoPoints = 0;
    private List<Button> revealedButtons = new ArrayList<>();

    private List<String> boardValues = new ArrayList<>();

    @FXML
    public void initialize() {
        setupBoard(3, 4); // Configuración para 3x4
        updateTurnLabel();
    }

    private void setupBoard(int rowCount, int colCount) {
        gameBoard.getChildren().clear();
        int totalCells = rowCount * colCount;
        boardValues = generateBoardValues(totalCells / 2); 
        Collections.shuffle(boardValues);

        int index = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                Button button = new Button();
                button.setPrefSize(100, 100);
                button.setUserData(boardValues.get(index++));
                button.setOnAction(e -> handleButtonClick(button));
                gameBoard.add(button, j, i);
            }
        }
    }

    private List<String> generateBoardValues(int pairCount) {
        List<String> values = new ArrayList<>();
        for (int i = 1; i <= pairCount; i++) {
            values.add(String.valueOf(i));
            values.add(numberToEnglish(i));
        }
        return values;
    }

    private String numberToEnglish(int number) {
        switch (number) {
            case 1: return "one";
            case 2: return "two";
            case 3: return "three";
            case 4: return "four";
            case 5: return "five";
            case 6: return "six";
            // Agrega más nombres según sea necesario
            default: return "";
        }
    }

    private void handleButtonClick(Button button) {
        if (revealedButtons.contains(button)) return;

        button.setText(button.getUserData().toString());
        revealedButtons.add(button);

        if (revealedButtons.size() == 2) {
            checkForMatch();
        }
    }

    private void checkForMatch() {
        Button firstButton = revealedButtons.get(0);
        Button secondButton = revealedButtons.get(1);

        String firstValue = firstButton.getUserData().toString();
        String secondValue = secondButton.getUserData().toString();

        if (isMatchingPair(firstValue, secondValue)) {
            addPointToCurrentPlayer();
            firstButton.setDisable(true);
            secondButton.setDisable(true);
        } else {
            hideButtons(firstButton, secondButton);
            switchPlayer();
            updateTurnLabel();
        }

        revealedButtons.clear();
      
    }

    private boolean isMatchingPair(String value1, String value2) {
        try {
            // Si `value1` es un número, verificamos si su versión en inglés coincide con `value2`
            if (isNumeric(value1) && numberToEnglish(Integer.parseInt(value1)).equals(value2)) {
                return true;
            }
            // Si `value2` es un número, verificamos si su versión en inglés coincide con `value1`
            else if (isNumeric(value2) && numberToEnglish(Integer.parseInt(value2)).equals(value1)) {
                return true;
            }
        } catch (NumberFormatException e) {
            // En caso de error de formato, devolvemos falso
            return false;
        }
        return false;
    }

    // Método auxiliar para verificar si una cadena es numérica
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void addPointToCurrentPlayer() {
        if (currentPlayer == 1) {
            playerOnePoints++;
            playerOneScore.setText(playerOnePoints + "");
        } else {
            playerTwoPoints++;
            playerTwoScore.setText(playerTwoPoints + "");
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    private void updateTurnLabel() {
        currentPlayerTurn.setText("Turno del Jugador " + currentPlayer);
    }

    private void hideButtons(Button firstButton, Button secondButton) {
        // Crear un retraso de 1 segundo para mostrar el texto antes de ocultarlo
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            firstButton.setText("");
            secondButton.setText("");
        });
        pause.play();
    }
}
