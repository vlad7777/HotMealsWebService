package com.ericpol.hotmeals.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptLinesRepository extends CrudRepository<ReceiptLine, Long>{

	List<ReceiptLine> findByReceiptId(long receiptId);
	
	List<ReceiptLine> findByDishId(long dishId);
}
