package com.altenproducts.Back.domain.service;

import com.altenproducts.Back.converter.ProductsConverter;
import com.altenproducts.Back.domain.port.AltenInterface;
import com.altenproducts.Back.form.AltenProductsForm;
import com.altenproducts.Back.persitence.entities.AltenProducts;
import com.altenproducts.Back.persitence.repository.AltenProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AltenProductsService implements AltenInterface {

    Logger logger = LoggerFactory.getLogger(AltenProductsService.class);
    private final AltenProductsRepository altenProductsRepository;

    public AltenProductsService(AltenProductsRepository altenProductsRepository) {
        this.altenProductsRepository = altenProductsRepository;
    }

    @Autowired
    ProductsConverter productsConverter;

    @Override
    public AltenProductsForm getDataById(int id) {
        logger.debug("[service] Appel methode GetDataByID");
        Optional<AltenProducts> altenProducts = altenProductsRepository.findById(id);
        if(altenProducts.isEmpty()){
            return null;
        }
        return productsConverter.toForm(altenProducts.get());

    }

    @Override
    public boolean deleteOne(int id) {
        logger.debug("[service] Appel methode Delete");
        altenProductsRepository.deleteById(id);
        return true;
    }

    public AltenProducts register(AltenProductsForm altenProductsForm){
        logger.debug("[service] Appel methode register");
        return this.altenProductsRepository.save(productsConverter.toEntity(altenProductsForm));

    }

    public List<AltenProductsForm> getAllData(){
        logger.info("[service] Appel methode getAllData");
        List<AltenProducts> altenProductsList = (List<AltenProducts>) this.altenProductsRepository.findAll();
        List<AltenProductsForm> altenProductsFormList = new ArrayList<>();
        for (AltenProducts altenProducts: altenProductsList){
            altenProductsFormList.add(productsConverter.toForm(altenProducts));
        }
        return altenProductsFormList;
    }

    public AltenProducts update(AltenProductsForm altenProductsForm){
        logger.info("[Update] Appel methode update");
        Optional<AltenProducts> altenProducts = altenProductsRepository.findById(altenProductsForm.getId());
        if (altenProducts.isEmpty()){
            return null;
        }
        productsConverter.updateProducts(altenProductsForm, altenProducts.get());
        return altenProductsRepository.save(altenProducts.get());
    }
}
