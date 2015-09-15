package com.ericpol.hotmeals.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
	//List<User> findAll();
	List<User> findByLogin(String login);
}
