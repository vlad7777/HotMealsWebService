package hot.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import hot.Model.Category;
import hot.Model.CategoriesRepository;

@RestController
@RequestMapping("/hotmeals/")
public class CategoriesController {
	
	@Autowired
	private CategoriesRepository cr;
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	List<Category> fetchCat() {
		return cr.findAll();
	}

	@RequestMapping(value = "/suppliers/{supplierId}/categories", method = RequestMethod.GET)
	List<Category> fetchCat(@PathVariable Long supplierId) {
		return cr.findBySupplierId(supplierId);
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	@ResponseBody Category addCat(@RequestBody Category c) {
		if(cr.exists(c.getSupplierId()) && c.getName().equals("Soup"))
			return cr.save(new Category(10, "mama"));
		else
			return cr.save(c);
	}
}
