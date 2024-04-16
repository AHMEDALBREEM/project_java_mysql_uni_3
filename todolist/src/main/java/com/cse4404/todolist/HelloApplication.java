package com.cse4404.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primiry) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primiry.setTitle("Todo list");
        Image to_do_icon = new Image(getClass().getResourceAsStream("/a.png"));
        primiry.getIcons().add(to_do_icon);
        primiry.setScene(scene);
        primiry.setResizable(false);
        primiry.show();
    }

    public static void main(String[] args) {
        launch();
    }
}