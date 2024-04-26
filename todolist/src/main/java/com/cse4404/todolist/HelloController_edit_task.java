package com.cse4404.todolist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.*;
import java.sql.SQLException;
import java.util.Objects;


public class HelloController_edit_task {

    @FXML
    private TextField task_id;

    @FXML
    private TextField new_data;

    @FXML
    private Label alter;
    @FXML
    private RadioButton date_radio;

    @FXML
    private RadioButton title_radio;

    @FXML
    private RadioButton details_radio;

    @FXML
    public void ondate_radio_clicked()  {
        date_radio.setSelected(true);
        title_radio.setSelected(false);
        details_radio.setSelected(false);
    }

    @FXML
    public void on_TITLE_radio_clicked()  {
        date_radio.setSelected(false);
        title_radio.setSelected(true);
        details_radio.setSelected(false);
    }

    @FXML
    public void on_details_radio_clicked()  {
        date_radio.setSelected(false);
        title_radio.setSelected(false);
        details_radio.setSelected(true);
    }


    public void initialize() throws IOException {
        File tempFile = new File("C:\\Users\\ahmed\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\tempFile2.txt");
        if (tempFile.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(tempFile));
            String str = reader.readLine();
            task_id.setVisible(true);
            task_id.setText(str);
        }
    }


    @FXML
    public void onbackclick(ActionEvent event) throws IOException, SQLException {
        Button Button = (Button) event.getSource();
        Scene scene = Button.getParent().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void editclick(ActionEvent event) throws IOException, SQLException {
        String taskId = task_id.getText().trim();
        String _a_ = new_data.getText().trim();

        if (taskId.isEmpty() || _a_.isEmpty()) {
            alter.setText("Please enter a Task ID and new data!");
        }

        Task task = new Task();
        String taskInfo = task.searchInfo(taskId);
        String editedTaskString = null;

        String[] parts = taskInfo.split(" ");
        String Date = parts[0];
        String id = parts[1];
        String comp = parts[2];
        String Task_title = parts[3];
        String Task_Details = parts[4];
        if (date_radio.isSelected()) {
            editedTaskString = buildEditedTaskString(_a_, id,comp, Task_title, Task_Details);
        } else if (title_radio.isSelected()) {
            editedTaskString = buildEditedTaskString(Date, id,comp, _a_, Task_Details);
        } else if (details_radio.isSelected()) {
            editedTaskString = buildEditedTaskString(Date, id,comp, Task_title, _a_);
        }
        if (editedTaskString != null && task.editTask(taskInfo,editedTaskString)) {
            onbackclick(event);
        } else {
            alter.setText("An error occurred while editing the task!");
        }
    }

    private String buildEditedTaskString(String date, String id, String comp, String title, String details) {
        return date + " " + id + " " + comp + " " + title + " " + details;
    }


}
