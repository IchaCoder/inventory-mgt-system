package com.example.group;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PurchaseHistoryController implements Initializable {

    @FXML
    private TableColumn<PurchaseHistory, Double> amount;

    @FXML
    private TableColumn<PurchaseHistory, String> drugPurchased;

    @FXML
    private TableView<PurchaseHistory> purchaseHistoryTable;

    @FXML
    private TableColumn<PurchaseHistory, Integer> quantity;

    @FXML
    private TableColumn<PurchaseHistory, String> customerName;

    private ObservableList<PurchaseHistory> fetchDataFromMySQL() {
        ObservableList<PurchaseHistory> data = FXCollections.observableArrayList();
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String selectQuery = "SELECT * FROM purchase_history";

            PreparedStatement preparedStatement = connectDB.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String customerName = resultSet.getString("customer_name");
                double amount = resultSet.getDouble("amount");
                String drugPurchased = resultSet.getString("drug_purchased");
                int quantity = resultSet.getInt("quantity");
                data.add(new PurchaseHistory(drugPurchased, customerName, quantity, amount));
            }

            resultSet.close();
            preparedStatement.close();
            connectDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        amount.setCellValueFactory(new PropertyValueFactory<PurchaseHistory, Double>("amount"));
        drugPurchased.setCellValueFactory(new PropertyValueFactory<PurchaseHistory, String>("drugPurchased"));
        customerName.setCellValueFactory(new PropertyValueFactory<PurchaseHistory, String>("customerName"));
        quantity.setCellValueFactory(new PropertyValueFactory<PurchaseHistory, Integer>("quantity"));

        purchaseHistoryTable.setItems(fetchDataFromMySQL());
    }
}
