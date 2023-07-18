package com.example.group;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class SalesController implements Initializable {

    public class Sales {
        private String drugName, category;
        private int id, quantity;

        public Sales(String drugName, String category, int id, int quantity) {
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
    private TableView<Sales> salesTable;

    @FXML
    private TableColumn<Sales, String> name;
    @FXML
    private TableColumn<Sales, String> category;
    @FXML
    private TableColumn<Sales, Integer> id;
    @FXML
    private TableColumn<Sales, Integer> quantity;

    @FXML
    ComboBox categoryInput;
    @FXML
    TextField nameInput;
    @FXML
    TextField quantityInput;

    public void handleAddDrug () {
        String category = categoryInput.getValue().toString();
        String name = nameInput.getText();
        int quantity = parseInt(quantityInput.getText());

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String selectQuery = "INSERT INTO sales(name, category, quantity) VALUES (?,?,?)";

            PreparedStatement preparedStatement = connectDB.prepareStatement(selectQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,category);
            preparedStatement.setInt(3,quantity);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item added to MySQL table successfully!");

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    System.out.println("New ID for the Drugs row: " + id);

                }
            } else {
                System.out.println("Failed to add item to MySQL table.");
            }

            preparedStatement.close();
            connectDB.close();
            nameInput.setText("");
            quantityInput.setText("");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private ObservableList<Sales> fetchDataFromMySQL() {
        ObservableList<Sales> data = FXCollections.observableArrayList();
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
                data.add(new Sales(name, category, id, quantity));
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
        name.setCellValueFactory(new PropertyValueFactory<Sales, String>("name"));
        id.setCellValueFactory(new PropertyValueFactory<Sales, Integer>("id"));
        category.setCellValueFactory(new PropertyValueFactory<Sales, String>("category"));
        quantity.setCellValueFactory(new PropertyValueFactory<Sales, Integer>("quantity"));

        salesTable.setItems(fetchDataFromMySQL());
    }
}
