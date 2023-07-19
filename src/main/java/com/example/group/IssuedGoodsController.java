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

public class IssuedGoodsController implements Initializable {

    public class  IssuedGoods {
        public double amount;
        public String name;
        public int id, quantity;

        public IssuedGoods(double amount, String name, int id, int quantity) {
            this.amount = amount;
            this.name = name;
            this.id = id;
            this.quantity = quantity;
        }

        public double getAmount() {
            return amount;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public int getQuantity() {
            return quantity;
        }
    }
    @FXML
    private TableView<IssuedGoods> printInvoiceTable;

    @FXML
    private TableColumn<IssuedGoods, Double> amount;

    @FXML
    private TableColumn<IssuedGoods, Integer> quantity;

    @FXML
    private TableColumn<IssuedGoods, String> name;

    @FXML
    private TableColumn<IssuedGoods, Integer> id;

    private ObservableList<IssuedGoods> fetchDataFromMySQL() {
        ObservableList<IssuedGoods> data = FXCollections.observableArrayList();
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String selectQuery = "SELECT * FROM issued_goods";

            PreparedStatement preparedStatement = connectDB.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                double amount = resultSet.getDouble("amount");
                int quantity = resultSet.getInt("quantity");

                data.add(new IssuedGoods(amount, name,id, quantity));
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
        name.setCellValueFactory(new PropertyValueFactory<IssuedGoods, String>("name"));
        id.setCellValueFactory(new PropertyValueFactory<IssuedGoods, Integer>("id"));
        amount.setCellValueFactory(new PropertyValueFactory<IssuedGoods, Double>("amount"));
        quantity.setCellValueFactory(new PropertyValueFactory<IssuedGoods, Integer>("quantity"));

        printInvoiceTable.setItems(fetchDataFromMySQL());
    }
}
