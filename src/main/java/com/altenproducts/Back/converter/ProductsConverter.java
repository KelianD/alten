package com.altenproducts.Back.converter;

import com.altenproducts.Back.domain.service.AltenProductsService;
import com.altenproducts.Back.form.AltenProductsForm;
import com.altenproducts.Back.persitence.entities.AltenProducts;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Component
public class ProductsConverter implements IConverter<AltenProducts,AltenProductsForm>{

    public AltenProducts toEntity (AltenProductsForm altenProductsForm){
        AltenProducts altenEntity = new AltenProducts();
        altenEntity.setCode(altenProductsForm.getCode());
        altenEntity.setName(altenProductsForm.getName());
        altenEntity.setDescription(altenProductsForm.getDescription());
        altenEntity.setPrice(altenProductsForm.getPrice());
        altenEntity.setQuantity(altenProductsForm.getQuantity());
        altenEntity.setInventoryStatus(altenProductsForm.getInventoryStatus());
        altenEntity.setCategory(altenProductsForm.getCategory());
        altenEntity.setImage(altenProductsForm.getImage());
        altenEntity.setRating(altenProductsForm.getRating());
        return altenEntity;
    }
    @Override
    public AltenProductsForm toForm(AltenProducts altenProducts) {
        AltenProductsForm altenProductsForm = new AltenProductsForm();
        altenProductsForm.setId(altenProducts.getId());
        altenProductsForm.setCode(altenProducts.getCode());
        altenProductsForm.setName(altenProducts.getName());
        altenProductsForm.setDescription(altenProducts.getDescription());
        altenProductsForm.setPrice(altenProducts.getPrice());
        altenProductsForm.setQuantity(altenProducts.getQuantity());
        altenProductsForm.setInventoryStatus(altenProducts.getInventoryStatus());
        altenProductsForm.setCategory(altenProducts.getCategory());
        altenProductsForm.setImage(altenProducts.getImage());
        altenProductsForm.setRating(altenProducts.getRating());
        return altenProductsForm;
    }

    public void updateProducts(AltenProductsForm altenProductsForm,AltenProducts altenProducts){
        altenProducts.setCode(altenProductsForm.getCode());
        altenProducts.setName(altenProductsForm.getName());
        altenProducts.setDescription(altenProductsForm.getDescription());
        altenProducts.setPrice(altenProductsForm.getPrice());
        altenProducts.setQuantity(altenProductsForm.getQuantity());
        altenProducts.setInventoryStatus(altenProductsForm.getInventoryStatus());
        altenProducts.setCategory(altenProductsForm.getCategory());
        altenProducts.setImage(altenProductsForm.getImage());
        altenProducts.setRating(altenProductsForm.getRating());
    }
}
