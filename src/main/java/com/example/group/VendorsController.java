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

public class VendorsController implements Initializable {



    @FXML
    private TableView<Vendors> purchaseHistoryTable;

    @FXML
    private TableColumn<Vendors, Integer> id;

    @FXML
    private TableColumn<Vendors, String> customerName;

    private ObservableList<Vendors> fetchDataFromMySQL() {
        ObservableList<Vendors> data = FXCollections.observableArrayList();
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String selectQuery = "SELECT * FROM purchase_history";

            PreparedStatement preparedStatement = connectDB.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String customerName = resultSet.getString("customer_name");
                int id = resultSet.getInt("id");
                data.add(new Vendors(customerName,id));
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
        customerName.setCellValueFactory(new PropertyValueFactory<Vendors, String>("customerName"));
        id.setCellValueFactory(new PropertyValueFactory<Vendors, Integer>("id"));

        purchaseHistoryTable.setItems(fetchDataFromMySQL());
    }
}
