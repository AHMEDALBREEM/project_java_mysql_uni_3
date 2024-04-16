package com.cse4404.todolist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelloController_edit_task {

    @FXML
    private TextField details;

    @FXML
    private TextField title;

    @FXML
    private TextField new_data;

    @FXML
    private DatePicker date;
    @FXML
    private Label alter;
    @FXML
    private RadioButton date_radio;

    @FXML
    private RadioButton title_radio;

    @FXML
    private RadioButton details_radio;

    @FXML
    public void ondate_radio_clicked() throws IOException {
        date_radio.setSelected(true);
        title_radio.setSelected(false);
        details_radio.setSelected(false);
    }

    @FXML
    public void on_TITLE_radio_clicked() throws IOException {
        date_radio.setSelected(false);
        title_radio.setSelected(true);
        details_radio.setSelected(false);
    }

    @FXML
    public void on_details_radio_clicked() throws IOException {
        date_radio.setSelected(false);
        title_radio.setSelected(false);
        details_radio.setSelected(true);
    }


    public void initialize() throws IOException {
        File tempFile = new File("C:\\Users\\SMART_CLOUD\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\tempFile2.txt");
        if(tempFile.exists()){
        BufferedReader reader = new BufferedReader(new FileReader(tempFile));
        String str = reader.readLine();
        LocalDate _D = LocalDate.parse(str.substring(0,10));
        String _T = str.split(" " )[1];
        String _DE = str.split(" " )[2];
        title.setText(_T);
        details.setText(_DE);
        date.setValue(_D);
        tempFile.delete();
        }
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
    public void editclick(ActionEvent event) throws IOException {
        String taskTitle = title.getText();
        String taskDetails = details.getText();
        LocalDate taskDate = date.getValue();
        String new_data_ = new_data.getText();

        if (!taskTitle.isEmpty() && !taskDetails.isEmpty() && taskDate != null &&(!new_data_.isEmpty())) {
            Task task = new Task(taskDate,taskTitle, taskDetails);
            if(date_radio.isSelected()){
            if(task.editTask(new_data_,taskTitle, taskDetails)){onbackclick(event);}else{alter.setText("Check the correctness content !!");}}
            if(title_radio.isSelected()){
                if(task.editTask(taskDate.toString(),new_data_, taskDetails)){onbackclick(event);}else{alter.setText("Check the correctness content !!");}}
            if(details_radio.isSelected()){
                if(task.editTask(taskDate.toString(),taskTitle, new_data_)){onbackclick(event);}else{alter.setText("Check the correctness content !!");}}
        } else {
            alter.setText("Check the nullity of content !!");
        }
    }
}