package com.example.group;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private BorderPane mainPane;

    public void handleWelcomeClick (ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("home");
        mainPane.setCenter(view);
        System.out.println("You clicked me");
    }

    public void handleAddGood (ActionEvent event) throws IOException {
        AddGoodController addGood = new AddGoodController();
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("add-good");
        mainPane.setCenter(view);
        System.out.println("You clicked me");
    }

    public void handleViewBills (ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("view-bills");
        mainPane.setCenter(view);
        System.out.println("You clicked me");
    }

    public void handleVendors(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("vendors");
        mainPane.setCenter(view);
        System.out.println("You clicked me");
    }

    public void handleIssuedGoods(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("issued-goods");
        mainPane.setCenter(view);
        System.out.println("You clicked me");
    }
}