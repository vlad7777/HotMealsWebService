package com.ericpol.hotmeals.Model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptsRepository extends CrudRepository<Receipt, Long>{
	List<Receipt> findAll();
	List<Receipt> findByUserId(long userId);
}
