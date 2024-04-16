package com.cse4404.todolist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class HelloController_delete_task {

    @FXML
    private TextField details;

    @FXML
    private TextField title;

    @FXML
    private DatePicker date;
    @FXML
    private Label alter;



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
    public void ondeleteclick(ActionEvent event)throws IOException {
        String taskTitle = title.getText();
        String taskDetails = details.getText();
        LocalDate taskDate = date.getValue();

        if (!taskTitle.isEmpty() && !taskDetails.isEmpty() && taskDate != null) {
            Task task = new Task(taskDate,taskTitle, taskDetails);
            if(task.deleteTaskToFile()){onbackclick(event);}else{alter.setText("Check the correctness content !!");}
        } else {
            alter.setText("Check the nullity of content !!");
        }
    }
}