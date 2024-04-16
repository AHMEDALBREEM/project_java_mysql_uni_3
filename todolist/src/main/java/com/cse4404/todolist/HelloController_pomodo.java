package com.cse4404.todolist;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;

import java.io.IOException;

public class HelloController_pomodo {
    @FXML
    private Label timerLabel;
    @FXML
    private Label instructionlabel;
    @FXML
    private VBox main_vbox;
    @FXML
    private Button startButton;
    @FXML
    private Button shortBreakButton;
    @FXML
    private Button longBreakButton;
    @FXML
    private Button continueButton;
    @FXML
    private Button stopButton;
    Font customFont = Font.loadFont(getClass().getResourceAsStream("/DS-DIGI.TTF"), 15);
    private Timeline timeline;
    private int minutes = 30;
    private int seconds = 0;
    private boolean isRunning = false;


    @FXML
    public void initialize(){
        startButton.setFont(customFont);
        stopButton.setFont(customFont);
        shortBreakButton.setFont(customFont);
        longBreakButton.setFont(customFont);
        continueButton.setFont(customFont);
        instructionlabel.setFont(customFont);

        instructionlabel.setOpacity(0);
    }

    @FXML
    public void onbackclick(ActionEvent event) throws IOException {
        Button Button = (Button) event.getSource();
        Scene scene = Button.getParent().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void handleStartButton(ActionEvent event) {

        if (!isRunning) {

            instructionlabel.setOpacity(1);
            isRunning = true;
            startButton.setDisable(true);
            stopButton.setDisable(false);

            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                if (seconds == 0) {
                    minutes--;
                    seconds = 59;
                } else {
                    seconds--;
                }

                timerLabel.setText(String.format("%02d:%02d", minutes, seconds));

                if (minutes == 0 && seconds == 0) {
                    timeline.stop();
                    isRunning = false;
                    shortBreakButton.setDisable(false);
                    longBreakButton.setDisable(false);
                    stopButton.setDisable(true);

                    instructionlabel.setOpacity(0);

                }
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

    @FXML
    private void handleShortBreakButton(ActionEvent event) {
        minutes = 5;
        seconds = 00;
        timerLabel.setText("05:00");
        shortBreakButton.setDisable(true);
        longBreakButton.setDisable(true);
        continueButton.setDisable(false);

        instructionlabel.setOpacity(1);
        handleStartButton(null);
    }

    @FXML
    private void handleLongBreakButton(ActionEvent event) {
        minutes = 15;
        seconds = 00;
        timerLabel.setText("15:00");
        shortBreakButton.setDisable(true);
        longBreakButton.setDisable(true);
        continueButton.setDisable(false);
        handleStartButton(null);

        instructionlabel.setOpacity(1);
    }

    @FXML
    private void handleContinueButton(ActionEvent event) {
        minutes = 5;
        seconds = 0;
        timerLabel.setText("05:00");
        continueButton.setDisable(true);
        startButton.setDisable(true);

        instructionlabel.setOpacity(1);
    }

    @FXML
    private void handleStopButton(ActionEvent event) {
        if (timeline != null) {
            timeline.stop();
        }
        isRunning = false;
        minutes = 30;
        seconds = 0;
        timerLabel.setText("30:00");
        startButton.setDisable(false);
        stopButton.setDisable(true);
        shortBreakButton.setDisable(true);
        longBreakButton.setDisable(true);
        continueButton.setDisable(true);
        instructionlabel.setOpacity(0);
    }
}