package com.cse4404.todolist;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Label dat;

    @FXML
    private DialogPane PANE;

    @FXML
    private ListView<String> listview;
    Font customFont = Font.loadFont(getClass().getResourceAsStream("/DS-DIGI.TTF"), 15);

    @FXML
    private DialogPane confirm_dialog;


    @FXML
    public void Readfromfile() throws IOException {
        String filePath = "C:\\Users\\SMART_CLOUD\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\task.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                listview.getItems().add(line);
            }
        }
    }
    private String garpah;

   @FXML
   public void listviewonmouseclicked() throws InterruptedException,NullPointerException {
       try{
       if(!(listview.getSelectionModel().getSelectedItem()).isEmpty()){garpah = listview.getSelectionModel().getSelectedItem();}
           PANE.setVisible(true);}
       catch (NullPointerException e1){
       System.exit(1);
       }
   }



    @FXML
    public void edit_clicked_dialog() throws IOException {
        PANE.setVisible(false);
        File tempFile = new File("C:\\Users\\SMART_CLOUD\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\tempFile2.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write(listview.getSelectionModel().getSelectedItem());
        writer.flush();
        writer.close();
        Scene scene = listview.getParent().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("edit_task.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void delete_clicked_dialog() throws IOException {
        confirm_dialog.setVisible(true);
        PANE.setVisible(false);
    }

    @FXML
    public void Ok() throws IOException {
       LocalDate _D = LocalDate.parse(garpah.substring(0,10));
       String _T = garpah.split(" " )[1];
       String _DE = garpah.split(" " )[2];
       Task t1 = new Task(_D,_T,_DE);
       if(t1.deleteTaskToFile()){
        confirm_dialog.setVisible(false);
        PANE.setVisible(false);
           Scene scene = listview.getParent().getScene();
           Stage stage = (Stage) scene.getWindow();
           Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
       }
    }

    @FXML
    public void Cancel() throws IOException {
        confirm_dialog.setVisible(false);
        PANE.setVisible(false);
    }

    public void initialize() throws IOException {
        try{LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String todayAsString = today.format(formatter);
        dat.setText(todayAsString);
        File filePath1 = new File("C:\\Users\\SMART_CLOUD\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\tempFile.txt");
        if(filePath1.exists()){filePath1.delete();}
        Readfromfile();}
        catch (FileNotFoundException ew){
            File filePath = new File("C:\\Users\\SMART_CLOUD\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\task.txt");
            if (!filePath.exists()){filePath.createNewFile();}
            File filePath1 = new File("C:\\Users\\SMART_CLOUD\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\tempFile.txt");
            filePath1.delete();
        }
    }

    @FXML
    public void on_addtask_Click(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("create_task.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    public void on_edit_task_Click(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("edit_task.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void on_delete_task_Click(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("delte_task.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void on_show_calendar_Click(ActionEvent event) throws IOException{
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("calendar.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void on_help_information_Click(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("help_information.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void on_show_pomodo_Click(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("pomodo.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}