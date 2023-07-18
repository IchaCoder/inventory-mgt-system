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
import java.sql.*;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class AddGoodController implements Initializable {

    ObservableList<Goods> data;
    @FXML
    private TableView<Goods> drugsTable;

    @FXML
    private TableColumn<Goods, String> name;
    @FXML
    private TableColumn<Goods, String> category;
    @FXML
    private TableColumn<Goods, Integer> id;
    @FXML
    private TableColumn<Goods, Integer> quantity;

    @FXML
    ComboBox categoryInput;
    @FXML
    TextField nameInput;
    @FXML
    TextField quantityInput;

    public void handleAddGood () {
        String category = categoryInput.getValue().toString();
        String name = nameInput.getText();
        int quantity = parseInt(quantityInput.getText());

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String selectQuery = "INSERT INTO drugs(name, category, quantity) VALUES (?,?,?)";

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

                    // Add the new Drugs object with the generated ID to the observable list
                    data = FXCollections.observableArrayList();
                    data.add(new Goods(name, id, category, quantity));
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


    private ObservableList<Goods> fetchDataFromMySQL() {
        data = FXCollections.observableArrayList();
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String selectQuery = "SELECT * FROM drugs";

            PreparedStatement preparedStatement = connectDB.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                String category = resultSet.getString("category");
                int quantity = resultSet.getInt("quantity");
                data.add(new Goods(name, id, category, quantity));

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
        name.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
        id.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id"));
        category.setCellValueFactory(new PropertyValueFactory<Goods, String>("category"));
        quantity.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("quantity"));

        drugsTable.setItems(fetchDataFromMySQL());
    }
}
