package com.example.group;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

public class FxmlLoader {
    private Pane view;

    public Pane getPage (String fileName) {
        try {
            URL fileUrl = HelloApplication.class.getResource(fileName + ".fxml");
            System.out.println(fileUrl);
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }

            view = new FXMLLoader().load(fileUrl);
        }
        catch (Exception e) {
            System.out.println("No page " + fileName + " please check FxmlLoader.");
        }
        return view;
    }
}
