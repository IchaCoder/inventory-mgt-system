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

public class ViewBillController implements Initializable {

    public class ViewBill {
        private String drugName, category;
        private int id, quantity;

        public ViewBill(String drugName, String category, int id, int quantity) {
            this.drugName = drugName;
            this.category = category;
            this.id = id;
            this.quantity = quantity;
        }

        public String getName() {
            return drugName;
        }

        public String getCategory() {
            return category;
        }

        public int getId() {
            return id;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    @FXML
    private TableView<ViewBill> salesTable;

    @FXML
    private TableColumn<ViewBill, String> name;
    @FXML
    private TableColumn<ViewBill, String> category;
    @FXML
    private TableColumn<ViewBill, Integer> id;
    @FXML
    private TableColumn<ViewBill, Integer> quantity;

    private ObservableList<ViewBill> fetchDataFromMySQL() {
        ObservableList<ViewBill> data = FXCollections.observableArrayList();
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String selectQuery = "SELECT * FROM sales";

            PreparedStatement preparedStatement = connectDB.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                String category = resultSet.getString("category");
                int quantity = resultSet.getInt("quantity");
                data.add(new ViewBill(name, category, id, quantity));
                System.out.println(name);
                System.out.println(id);
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
        name.setCellValueFactory(new PropertyValueFactory<ViewBill, String>("name"));
        id.setCellValueFactory(new PropertyValueFactory<ViewBill, Integer>("id"));
        category.setCellValueFactory(new PropertyValueFactory<ViewBill, String>("category"));
        quantity.setCellValueFactory(new PropertyValueFactory<ViewBill, Integer>("quantity"));

        salesTable.setItems(fetchDataFromMySQL());
    }
}
