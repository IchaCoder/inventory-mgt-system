package com.example.group;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.lang.Integer.parseInt;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private BorderPane mainPane;

//    public void connectButton(ActionEvent event) {
//        DatabaseConnection connectNow = new DatabaseConnection();
//        Connection connectDB = connectNow.getConnection();
////        String connectQuery = "SELECT drug_name FROM drugs";
//        String connectQuery = "SELECT id FROM drugs";
//
//        try {
//            Statement statement = connectDB.createStatement();
//            ResultSet queryOutput = statement.executeQuery(connectQuery);
//            while (queryOutput.next()) {
//                int fetchedName = parseInt(queryOutput.getString("id"));
//                System.out.println(fetchedName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


    public void handleWelcomeClick (ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("home");
        mainPane.setCenter(view);
        System.out.println("You clicked me");
    }

    public void handleAddDrug (ActionEvent event) throws IOException {
        AddDrugController addDrug = new AddDrugController();
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("add-drug");
        mainPane.setCenter(view);
//        addDrug.updateTable();
        System.out.println("You clicked me");
    }

    public void handleSaleClick (ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("sale");
        mainPane.setCenter(view);
        System.out.println("You clicked me");
    }

    public void handlePurchaseHistoryClick (ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("purchase-history");
        mainPane.setCenter(view);
        System.out.println("You clicked me");
    }

    public void handleInvoiceClick (ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("invoice");
        mainPane.setCenter(view);
        System.out.println("You clicked me");
    }
}