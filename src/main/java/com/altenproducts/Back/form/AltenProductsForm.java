package com.altenproducts.Back.form;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class AltenProductsForm {

    @Schema(name = "id", example = "1")
    private int id;

    @Schema(name = "code", example = "AlT001")

    private String code;

    @Schema(name = "name", example = "montre")
    private String name;

    @Schema(name = "description", example = "Montre kiprun 200 by Coros noir mat")
    private String description;

    @Schema(name = "price", example = "19.06")
    // mettre des regles comme : @DecimalMin(value = "0.00001", message = "Saisie du prix obligatoire")
    @NotNull(message = "Prix obligatoire")
    private float price;

    @Schema(name = "quantity", example = "1")
    @NotNull(message = "quantité obligatoire")
    private int quantity;

    @Schema(name = "inventoryStatus", example = "En stock")
    private String inventoryStatus;

    @Schema(name = "category", example = "Montre")
    private String category;

    @Schema(name = "image", example = "//img/kiprun200.jpg")
    private String image;

    @Schema(name = "rating", example = "5 étoiles")
    private String rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
