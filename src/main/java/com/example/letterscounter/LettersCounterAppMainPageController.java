package com.example.letterscounter;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class LettersCounterAppMainPageController {

    @FXML
    private TextField inputFileNameTextField;

    @FXML
    private TextField outputFileNameTextField;

    @FXML
    private Button countLettersButton;

    LettersCounter lettersCounter = new LettersCounter();

    @FXML
    private void openFileButtonClicked() {
        String inputFileName = this.inputFileNameTextField.getText();
        if (!lettersCounter.openInputFile(inputFileName)) {
            showAlert("File doesn't exist");
            return;
        }
        outputFileNameTextField.setVisible(true);
        outputFileNameTextField.setDisable(false);
        countLettersButton.setVisible(true);
        countLettersButton.setDisable(false);
    }

    @FXML
    private void countLettersButtonClicked() {
        String outputFileName = this.outputFileNameTextField.getText();
        if (!lettersCounter.openOutputFile(outputFileName)) {
            showAlert("Cannot create file");
            return;
        }
        if (!lettersCounter.countLetters()) {
            showAlert("Cannot count letters");
            return;
        }
        if (!lettersCounter.outputResults()) {
            showAlert("Cannot output results");
        }
    }

    private void showAlert(String message) {
        Alert showFileWasNotOpenMessage = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        showFileWasNotOpenMessage.showAndWait();
    }
}