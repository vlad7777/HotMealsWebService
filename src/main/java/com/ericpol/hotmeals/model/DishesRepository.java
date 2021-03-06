package com.ericpol.hotmeals.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishesRepository extends CrudRepository<Dish, Long> {

	List<Dish> findAll();

	List<Dish> findBySupplierId(long supplierId);

	List<Dish> findBySupplierIdAndCategoryIdAndName(long supplierId, long categoryId, String name);
}
