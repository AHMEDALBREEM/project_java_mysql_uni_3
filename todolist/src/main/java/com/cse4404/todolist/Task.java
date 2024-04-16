package com.cse4404.todolist;

import javafx.fxml.FXML;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task {
    private String Task_title;
    private String Task_Details;
    private LocalDate  Date;

    Task(LocalDate Date_,String Task_title_,String Task_Details_){
    this.Date = Date_;
    this.Task_Details = Task_Details_;
    this.Task_title = Task_title_;
    }
    public void sort_tasks() throws IOException{
        String filePath = "C:\\Users\\SMART_CLOUD\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\task.txt";
        ArrayList<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
                    lines.add(scanner.nextLine());
        }
                scanner.close();
                Collections.sort(lines);
                FileWriter writer = new FileWriter(filePath);
                for (String line : lines) {
                    writer.write(line + "\n");
                }
                writer.close();

        }


    public boolean AppendToFileFiles() throws IOException {
        String filePath = "C:\\Users\\SMART_CLOUD\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\task.txt";
        String textToAppend = Date.toString() + " " + Task_title + " " + Task_Details + "\n";
        Files.write(Paths.get(filePath), textToAppend.getBytes(), StandardOpenOption.APPEND);
        sort_tasks();
        return true;
    }
    public boolean deleteTaskToFile() throws IOException {
        boolean x =false;
        File filePath = new File("C:\\Users\\SMART_CLOUD\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\task.txt");
        if (!filePath.exists()){filePath.createNewFile();}
        File tempFile = new File("C:\\Users\\SMART_CLOUD\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\tempFile.txt");
        String lineToDelete = Date.toString() + " " + Task_title + " " + Task_Details ;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            if (!currentLine.equals(lineToDelete)) {
                writer.write(currentLine+ System.getProperty("line.separator"));
            }
            if (currentLine.equals(lineToDelete)) {
                x=true;
            }
        }
        reader.close();
        writer.close();
        if(!x){
            return false;
        }
        if (filePath.delete()) {
            tempFile.renameTo(filePath);
        }
        sort_tasks();
        return true;
    }
    public boolean editTask(String _Date,String _Task_title,String _Task_Details) throws IOException,FileNotFoundException {
        boolean x =false;
        File filePath = new File("C:\\Users\\SMART_CLOUD\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\task.txt");
        if (!filePath.exists()){filePath.createNewFile();}
        File tempFile = new File("C:\\Users\\SMART_CLOUD\\IdeaProjects\\todolist\\src\\main\\java\\com\\cse4404\\todolist\\tempFile.txt");
        String lineToDelete = Date.toString() + " " + Task_title + " " + Task_Details ;
        String _lineToDelete = _Date + " " + _Task_title + " " + _Task_Details ;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            if (!currentLine.equals(lineToDelete)) {
                writer.write(currentLine+ System.getProperty("line.separator"));
            }
            if (currentLine.equals(lineToDelete)) {
                x= true;
                writer.write(_lineToDelete+ System.getProperty("line.separator"));
            }
        }
        reader.close();
        writer.close();
        if(!x){
            return false;
        }
        if(filePath.delete()){
        tempFile.renameTo(filePath);}
        sort_tasks();
        return true;
    }
}