package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchProductController {

    @FXML
    private VBox productContainer;

    @FXML
    private ImageView keranjang;

    @FXML
    private TextField searchField;

    @FXML
    private Label kategoriDekor;
    @FXML
    private Label kategoriFashion;
    @FXML
    private Label kategoriMainan;
    @FXML
    private Label kategoriMiniature;
    @FXML
    private Label kategoriFurnitur;

    private List<Product> products = new ArrayList<>();
    private Label activeLabel;
    
    

    @FXML
    public void initialize() {
        loadProductsFromCSV("C:\\Users\\BEST LAPTOP\\Documents\\EXPO GIGGLE TEAM\\demooo2strukturdata4connect1\\demooo2strukturdata4\\demooo2\\demooo\\target\\produk.csv");
        displayProducts(products);

        // Add mouse click event for category labels
kategoriDekor.setOnMouseClicked(this::handleCategoryClick);
kategoriFashion.setOnMouseClicked(this::handleCategoryClick);
kategoriMainan.setOnMouseClicked(this::handleCategoryClick);
kategoriMiniature.setOnMouseClicked(this::handleCategoryClick);
kategoriFurnitur.setOnMouseClicked(this::handleCategoryClick);


        // // Add mouse click event for category labels
        // kategoriDekor.setOnMouseClicked(this::handleCategoryClick);
        // kategoriFashion.setOnMouseClicked(this::handleCategoryClick);
        // kategoriMainan.setOnMouseClicked(this::handleCategoryClick);
        // kategoriMiniature.setOnMouseClicked(this::handleCategoryClick);
        // kategoriFurnitur.setOnMouseClicked(this::handleCategoryClick);
    }


public void handleCategoryClick(MouseEvent event) {
    Label clickedLabel = (Label) event.getSource(); // Get the clicked label
    String category = clickedLabel.getText(); // Get the category text from the label

    // Filter products based on the category
    List<Product> filteredProducts = new ArrayList<>();
    for (Product product : products) {
        if (product.getCategory().equalsIgnoreCase(category)) {
            filteredProducts.add(product);
        }
    }

    // Display the filtered products
    displayProducts(filteredProducts);

    // Update the active label style
    if (activeLabel != null) {
        activeLabel.setFont(Font.font("System", FontWeight.NORMAL, 12));
    }
    clickedLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
    activeLabel = clickedLabel;
}


    private void loadProductsFromCSV(String filePath) {
        String line;
        boolean headerSkipped = false;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Skip the header line
                }
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Product product = new Product(values[0], values[1], Integer.parseInt(values[2]), values[3], values[4], values[5]);
                products.add(product);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void displayProducts(List<Product> products) {
        productContainer.getChildren().clear();
        GridPane gridPane = new GridPane();
        int column = 0;
        int row = 0;

        for (Product product : products) {
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(10));
            vbox.setStyle("-fx-background-color: #fff;");

            ImageView imageView = new ImageView();
            imageView.setFitHeight(115);
            imageView.setFitWidth(150);
            imageView.setPreserveRatio(true);
            //imageView.setImage(new Image(product.getImageFileName()));

            Label nameLabel = new Label(product.getName());
            nameLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

            Label priceLabel = new Label("Rp. " + product.getPrice());
            priceLabel.setFont(Font.font("System", FontWeight.BOLD, 12));

            vbox.getChildren().addAll(imageView, nameLabel, priceLabel);

            gridPane.add(vbox, column, row);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }

        productContainer.getChildren().add(gridPane);
    }

    @FXML
    private void handleSearch() {
        String query = searchField.getText().toLowerCase();
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(query) ||
                product.getCategory().toLowerCase().contains(query) ||
                product.getDescription().toLowerCase().contains(query) ||
                product.getKeywords().toLowerCase().contains(query)) {
                filteredProducts.add(product);
            }
        }
        displayProducts(filteredProducts);
    }

    // @FXML
    // public void handleCategoryClick(ActionEvent event) {
    //     Label clickedLabel = (Label) event.getSource(); // Get the clicked label
    //     String category = clickedLabel.getText(); // Get the category text from the label

    //     // Filter products based on the category
    //     List<Product> filteredProducts = new ArrayList<>();
    //     for (Product product : products) {
    //         if (product.getCategory().equalsIgnoreCase(category)) {
    //             filteredProducts.add(product);
    //         }
    //     }

    //     // Display the filtered products
    //     displayProducts(filteredProducts);

    //     // Update the active label style
    //     if (activeLabel != null) {
    //         activeLabel.setFont(Font.font("System", FontWeight.NORMAL, 12));
    //     }
    //     clickedLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
    //     activeLabel = clickedLabel;
    // }
}
