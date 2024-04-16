package com.cse4404.todolist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class HelloController_calendar {


    @FXML
    private ListView<String> listView;
    @FXML
    private DatePicker date;
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
    public void onsearchclick() throws IOException {
        listView.getItems().clear();
            if (date.getValue() != null) {
                LocalDate taskDate = date.getValue();
                String filePath = "C:\\Users\\SMART_CLOUD\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\task.txt";
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        LocalDate _D = LocalDate.parse(line.substring(0, 10));
                        if (_D.equals(taskDate)) {
                            listView.getItems().add(line);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }


}