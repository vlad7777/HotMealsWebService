package com.ericpol.hotmeals.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends CrudRepository<Category, Long> {

	List<Category> findAll();

	List<Category> findBySupplierId(long supplierId);
	
	List<Category> findBySupplierIdAndName(long supplierId, String name);
}