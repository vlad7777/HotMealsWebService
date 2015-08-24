package hot.Model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersRepository extends CrudRepository<Supplier, Long> {
	List<Supplier> findAll();
}
