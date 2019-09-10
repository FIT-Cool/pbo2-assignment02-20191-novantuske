package com.kevin.controller;

import com.kevin.entity.Category;
import com.kevin.entity.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TampilanControler implements Initializable {

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
    }

    @FXML
    private void Help(ActionEvent actionEvent) {
    }

    @FXML
    private void btnSave(ActionEvent actionEvent) {
        Item i = new Item();
        i.setName(txtNama.getText().trim());
        i.setPrice(Double.parseDouble(txtPrice.getText()));

        Category c = new Category();
        c.setCatName(txtCatName.getText());
        i.setCategory(c);

        items.add(i);
    }

    @FXML
    private void btnReset(ActionEvent actionEvent) {
    }

    @FXML
    private void btnUpdate(ActionEvent actionEvent) {
    }

    @FXML
    private void btnSaveCat(ActionEvent actionEvent) {
        Category c = new Category();
        c.setCatName(txtCatName.getText());

        categories.add(c);

    }

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        Item i = tblData.getSelectionModel().getSelectedItem();
        txtNama.setText(i.getName());
        txtPrice.setText(String.valueOf(i.getPrice()));
        txtCatName.setText(i.getCategory().getCatName());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        items = FXCollections.observableArrayList();
        tblData.setItems(items);

        colName.setCellValueFactory(data ->{
            Item i = data.getValue();
            return new SimpleStringProperty(i.getName());
        });

        colPrice.setCellValueFactory(data ->{
            Item i = data.getValue();
            return new SimpleStringProperty(String.valueOf(i.getPrice()));
        });

        colCategory.setCellValueFactory(data ->{
            Item i = data.getValue();
            return new SimpleStringProperty(i.getCategory().getCatName());
        });

        categories = FXCollections.observableArrayList();
        boxChoice.setItems(categories);
    }


}
