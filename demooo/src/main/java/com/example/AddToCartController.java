package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AddToCartController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ImageView MenuIcon;

    @FXML
    private Label SecondLife;

    @FXML
    private HBox navBar1;

    @FXML
    private ImageView SearchIcon;

    @FXML
    private TextField SearchField;

    @FXML
    private ImageView Keranjang;

    @FXML
    private ImageView Profil;

    @FXML
    private Button TombolTambahKeranjang;

    @FXML
    private ImageView VasBungaMotifHewan;

    private Product product; // Untuk menyimpan data produk yang ditambahkan

    public void initialize() {
        // Initialize the controller. This method is automatically called after the FXML
        // file is loaded.
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        // Set up any event handlers for the UI components here
        TombolTambahKeranjang.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (product != null) {
                    showAlert();
                    // Tambahan: Tambahkan produk ke keranjang atau lakukan operasi yang sesuai di sini
                    // Contoh: addToCart(product);
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Produk tidak ditemukan.");
                    alert.showAndWait();
                }
            }
        });
    }

    public void initData(Product product) {
        this.product = product;
        displayProductDetails();
    }

    private void displayProductDetails() {
        if (product != null) {
            // Tampilkan detail produk di sini sesuai dengan kebutuhan UI Anda
            // Contoh:
            SecondLife.setText(product.getName());
            try {
                Image image = new Image(product.getImageFileName());
                VasBungaMotifHewan.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Produk tidak ditemukan.");
            alert.showAndWait();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informasi");
        alert.setHeaderText(null);
        alert.setContentText("Produk anda berhasil ditambahkan ke keranjang!");
        alert.showAndWait();
        
        // Tampilkan halaman keranjang setelah menambahkan produk
        showCartPage();
    }

    private void showCartPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CartPage.fxml"));
            AnchorPane cartPage = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Keranjang Anda");
            stage.initModality(Modality.APPLICATION_MODAL); // Membuat halaman modal
            stage.setScene(new Scene(cartPage));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
