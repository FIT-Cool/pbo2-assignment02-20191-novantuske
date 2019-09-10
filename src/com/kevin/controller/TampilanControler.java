package com.kevin.controller;

import com.kevin.entity.Category;
import com.kevin.entity.Item;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Kevin Novantus (1772011)
 */
public class TampilanControler implements Initializable {

    public Button btnUpdate;
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtPrice;
    @FXML
    private ChoiceBox boxChoice;
    @FXML
    private TextField txtCatName;
    @FXML
    private TableColumn<Item, String> colName;
    @FXML
    private TableColumn<Item, String> colPrice;
    @FXML
    private TableColumn<Item, String> colCategory;
    @FXML
    private TableView<Item> tblData;
    private ObservableList<Item> items;
    private ObservableList<Category> categories;

    @FXML
    private void Close(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    private void Help(ActionEvent actionEvent) {

    }

    /**
     * method untuk menambahkan data nama, price, category dalam tabel view
     * @param actionEvent
     */
    @FXML
    private void btnSave(ActionEvent actionEvent) {
        if (txtNama.getText().isEmpty() || txtPrice.getText().isEmpty() || boxChoice.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please fill name / price / category");

            alert.show();
        } else {
            Item i;
            i = new Item();
            i.setName(txtNama.getText().trim());
            i.setPrice(Double.parseDouble(txtPrice.getText().trim()));

            Category c = new Category();
            c.setCatName(boxChoice.getSelectionModel().getSelectedItem().toString().trim());
            i.setCategory(c);

            items.add(i);

            txtNama.clear();
            txtPrice.clear();
        }

    }

    /**
     * method untuk menghapus value pada text field yang sedang terisi
     * @param actionEvent
     */
    @FXML
    private void btnReset(ActionEvent actionEvent) {
        txtNama.clear();
        txtPrice.clear();
        txtCatName.clear();
        boxChoice.getSelectionModel().clearSelection();
    }

    /**
     * method untuk merubah value pada baris table view yang dipilih
     * @param actionEvent
     */
    @FXML
    private void btnUpdate(ActionEvent actionEvent) {
        btnUpdate.setDisable(false);

        if (txtNama.getText().isEmpty() || txtPrice.getText().isEmpty() || boxChoice.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please fill name / price / category");

            alert.show();
        }
        else {
            items.remove(tblData.getSelectionModel().getSelectedItem());

            Item i = new Item();
            i.setName(txtNama.getText().trim());
            i.setPrice(Double.parseDouble(txtPrice.getText().trim()));

            Category c = new Category();
            c.setCatName(boxChoice.getSelectionModel().getSelectedItem().toString().trim());
            i.setCategory(c);

            items.add(tblData.getSelectionModel().getSelectedIndex(), i);
            txtNama.clear();
            txtPrice.clear();
            btnUpdate.setDisable(true);
        }
    }

    /**
     * method untuk menambahkan category name kedalam category
     * @param actionEvent
     */
    @FXML
    private void btnSaveCat(ActionEvent actionEvent) {
        int i = 0;
        for (Category c : categories) {
            if (txtCatName.getText().equals(c.getCatName())) {
                i++;
            }
        }

        if (i >= 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Duplicate category name");

            alert.show();
        } else {
            if (txtCatName.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Please fill category name");

                alert.show();

            } else {
                Category c;
                c = new Category();
                c.setCatName(txtCatName.getText());

                categories.add(c);

                txtCatName.clear();
            }
        }

    }

    /**
     * method untuk membuat button update menjadi aktif bila table view di klik
     * @param mouseEvent
     */
    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        if (items.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Table is empty");

            alert.show();
        } else {
            btnUpdate.setDisable(false);
        }
    }

    /**
     * method untuk melakukan link dari table ke list
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        items = FXCollections.observableArrayList();
        tblData.setItems(items);

        colName.setCellValueFactory(data -> {
            Item i = data.getValue();
            return new SimpleStringProperty(i.getName());
        });

        colPrice.setCellValueFactory(data -> {
            Item i = data.getValue();
            return new SimpleStringProperty(String.valueOf(i.getPrice()));
        });

        colCategory.setCellValueFactory(data -> {
            Item i = data.getValue();
            return new SimpleStringProperty(i.getCategory().getCatName());
        });

        categories = FXCollections.observableArrayList();
        boxChoice.setItems(categories);
    }

}
