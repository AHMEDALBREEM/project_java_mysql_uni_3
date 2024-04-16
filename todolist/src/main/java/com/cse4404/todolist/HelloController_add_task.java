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
import java.time.format.DateTimeFormatter;


public class HelloController_add_task {

    @FXML

    private TextField details;

    @FXML
    private TextField title;

    @FXML

    private DatePicker date;
    @FXML

    private Label alert;

    @FXML
    public void on_back_click(ActionEvent event) throws IOException {
        Button Button = (Button) event.getSource();
        Scene scene = Button.getParent().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    public void on_create_click(ActionEvent event) throws IOException {
        String taskTitle = title.getText();
        String taskDetails = details.getText();
        LocalDate taskDate = date.getValue();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (!taskTitle.isEmpty() && !taskDetails.isEmpty() && taskDate != null && ((taskDate.isAfter(today)) || (taskDate.isEqual(today)))) {
            Task task = new Task(taskDate,taskTitle, taskDetails);
            if(task.AppendToFileFiles()){on_back_click(event);}else{alert.setText("Check the I/O files !!");}
        } else {
            alert.setText("Check the nullity and realability content enteries !!");
        }
    }

}