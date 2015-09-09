package hot.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hot.Model.Receipt;
import hot.Model.ReceiptsRepository;

@RestController
@RequestMapping("/hotmeals")
public class ReceiptsController {

	@Autowired
	private ReceiptsRepository or;
	
	@RequestMapping(value = "/users/{userId}/orders", method = RequestMethod.GET)
	List<Receipt> fetchOrders(@PathVariable Long userId) {
		return or.findByUserId(userId);
	}
}
