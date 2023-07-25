package com.altenproducts.Back.persitence.repository;

import com.altenproducts.Back.persitence.entities.AltenProducts;
import org.springframework.data.repository.CrudRepository;

public interface AltenProductsRepository extends CrudRepository <AltenProducts,Integer> {
}
