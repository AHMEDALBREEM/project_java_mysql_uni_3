package com.cse4404.todolist;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class HelloController {
    @FXML
    private Label dat;

    @FXML
    private DialogPane PANE;
    String PATH     = "C:\\Users\\ahmed\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\task.txt";
    String _PATH     = "C:\\Users\\ahmed\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\tempFile2.txt";

    @FXML
    private ListView<String> listview;

    @FXML
    private DialogPane confirm_dialog;
    private String garpah = null;

    @FXML
    public void Readfromfile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                listview.getItems().add(line);
            }
        }
    }





   @FXML
   public void listviewonmouseclicked() throws NullPointerException {
       try{
       if(!(listview.getSelectionModel().getSelectedItem()).isEmpty()){garpah = listview.getSelectionModel().getSelectedItem();}
           PANE.setVisible(true);}
       catch (NullPointerException e1){
       System.exit(1);
       }
   }
   @FXML
   public void complete_clicked_dialog() throws  IOException {
           Task T1 = new Task();
       String[] parts = garpah.split(" ");
       String id = parts[1];
        if(T1.markComplete(id)){
           PANE.setVisible(false);
            Scene scene = listview.getParent().getScene();
            Stage stage = (Stage) scene.getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("edit_task.fxml")));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();}
        else{
            System.exit(1);
        }
   }



    @FXML
    public void edit_clicked_dialog() throws IOException {
        PANE.setVisible(false);
        File tempFile = new File(_PATH);
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String id = garpah.split(" ")[1];
        writer.write(id);
        writer.flush();
        writer.close();
        Scene scene = listview.getParent().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("edit_task.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void delete_clicked_dialog()  {
        confirm_dialog.setVisible(true);
        PANE.setVisible(false);
    }

    @FXML
    public void Ok() throws IOException {
     Task t1 = new Task();
        String id = garpah.split(" ")[1];
        String str  = t1.searchInfo(id);
       if(t1.deleteTaskToFile(str)){
        confirm_dialog.setVisible(false);
        PANE.setVisible(false);
           Scene scene = listview.getParent().getScene();
           Stage stage = (Stage) scene.getWindow();
           Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
       }
    }


    @FXML
    public void Cancel()  {
        confirm_dialog.setVisible(false);
        PANE.setVisible(false);
    }


    @FXML
    public void initialize() throws IOException{
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String todayAsString = today.format(formatter);
            dat.setText(todayAsString);
            Readfromfile();
    }


    @FXML
    public void on_addtask_Click(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("create_task.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    public void on_edit_task_Click(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("edit_task.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void on_delete_task_Click(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delte_task.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void on_show_calendar_Click(ActionEvent event) throws IOException{
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("calendar.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void on_help_information_Click(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("help_information.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void on_show_pomodo_Click(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pomodo.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}