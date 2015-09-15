package com.ericpol.hotmeals.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
<<<<<<< HEAD
	//List<User> findAll();
=======
	List<User> findAll();
>>>>>>> 693792cf893a852f8417ad4a609e30e91c44979d
	List<User> findByLogin(String login);
}
