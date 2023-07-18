package com.example.group;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    public class  Invoice {
        public double amount;
        public String name;
        public int id, quantity;

        public Invoice(double amount, String name, int id, int quantity) {
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
    private TableView<Invoice> printInvoiceTable;

    @FXML
    private TableColumn<Invoice, Double> amount;

    @FXML
    private TableColumn<Invoice, Integer> quantity;

    @FXML
    private TableColumn<Invoice, String> name;

    @FXML
    private TableColumn<Invoice, Integer> id;

    ObservableList<Invoice> list = FXCollections.observableArrayList(
            new Invoice(25.99, "Paracetamol", 1, 50),
            new Invoice(15.99, "Gebedol", 2, 25),
            new Invoice(50.99, "Zupes", 3, 3)
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<Invoice, String>("name"));
        id.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("id"));
        amount.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("amount"));
        quantity.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("quantity"));

        printInvoiceTable.setItems(list);
    }
}
