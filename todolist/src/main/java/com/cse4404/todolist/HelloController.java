package com.cse4404.todolist;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class HelloController {
    @FXML
    private Label dat;
    @FXML
    private Label x_x;
    @FXML
    private Label x_y;
    @FXML
    private DialogPane PANE;
    @FXML
    private DialogPane error_dialog;

    String PATH     = "C:\\Users\\ahmed\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\task.txt";
    String _PATH     = "C:\\Users\\ahmed\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\tempFile2.txt";

    @FXML
    private ListView<String> listview;

    int x=0;
    int y=0;


    @FXML
    private DialogPane confirm_dialog;
    private String garpah = null;

    @FXML
    public void Readfromfile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                listview.getItems().add(line);
                x++;
                String[] parts = line.split(" ");
                if(parts[2].equals("true")){
                    y++;
                }
            }
        }
    }


    @FXML
    public void quotes_DIALAG(ActionEvent event) throws IOException {
        Button Button = (Button) event.getSource();
        Scene scene = Button.getParent().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("quotes.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void TOMOROW_TASKS_VIEW(ActionEvent event) throws IOException {
        Button Button = (Button) event.getSource();
        Scene scene = Button.getParent().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tomorow_tasks.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


   @FXML
   public void listviewonmouseclicked() throws NullPointerException {
       try{
       if(!(listview.getSelectionModel().getSelectedItem()).isEmpty()){garpah = listview.getSelectionModel().getSelectedItem();}
           PANE.setVisible(true);}
       catch (NullPointerException e1){
           error_dialog.setVisible(true);
       }
   }

   @FXML
   public void complete_clicked_dialog() throws  IOException, SQLException {
           Task T1 = new Task();
       String[] parts = garpah.split(" ");
       String id = parts[1];
        if(T1.markComplete(id)){
           PANE.setVisible(false);
            initialize();
            }
        else{
            PANE.setVisible(false);
            error_dialog.setVisible(true);
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
    public void Error_detection(){
        error_dialog.setVisible(false);
    }


    @FXML
    public void Ok() throws IOException,SQLException{
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
        listview.getItems().clear();
        x=0;y=0;
        LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String todayAsString = today.format(formatter);
            dat.setText(todayAsString);
            Readfromfile();
            x_x.setText("The Total Created Tasks : "+Integer.toString(x));
            x_y.setText("The Total Completed Tasks : "+Integer.toString(y));
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
    @FXML
    public void on_show_completed_tasks_Click(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view_completed.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void on_show_detailed_calendar_Click(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("show_detailed_calendar.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}