package hot.Model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends CrudRepository<Category, Long> {
	 List<Category> findAll();
	// boolean exists(int id);
}