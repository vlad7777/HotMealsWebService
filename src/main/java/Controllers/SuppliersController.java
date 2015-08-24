package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Entities.Dish;
import Entities.Supplier;

@RestController
@RequestMapping("/hotmeals/suppliers")
public class SuppliersController {

    private List<Supplier> suppliers = null;
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = RequestMethod.GET)
    public List<Supplier> fetchSuppliers() {
        if (suppliers == null)
            syncWithDatabase();
        return suppliers;
    }

    @RequestMapping(value = "/{supplierId}/dishes", method = RequestMethod.GET)
    public List<Dish> fetchDishes(@PathVariable String supplierId) {
        if (suppliers == null)
            syncWithDatabase();
        for (Supplier supplier : suppliers) {
            if (supplier.getId() == Integer.parseInt(supplierId))
                return supplier.getDishes();
        }
        return new ArrayList<Dish>();
    }

    @RequestMapping(value = "/{supplierId}/dishes/{date}", method = RequestMethod.GET)
    public List<Dish> fetchDishes(@PathVariable String supplierId, @PathVariable String date) {
        if (suppliers == null)
            syncWithDatabase();
        for (Supplier supplier : suppliers) {
            if (supplier.getId() == Integer.parseInt(supplierId))
                return supplier.selectDishes(date);
        }
        return new ArrayList<Dish>();
    }

    private void syncWithDatabase() {
        suppliers = new ArrayList<Supplier>();
        suppliers.add(new Supplier(0, "Bronte"));
        suppliers.add(new Supplier(1, "KFC"));
        suppliers.add(new Supplier(2, "doorknob"));
        suppliers.add(new Supplier(3, "switch"));
        suppliers.add(new Supplier(4, "delicious"));
        suppliers.add(new Supplier(5, "Gourmet"));
    }
}