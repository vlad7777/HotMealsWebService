package hello;

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

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = RequestMethod.GET)
    public List<Supplier> fetchSuppliers() {
        List<Supplier> suppliers = new ArrayList<Supplier>();
        suppliers.add(new Supplier(0, "Drug dealer"));
        suppliers.add(new Supplier(1, "KFC"));
        suppliers.add(new Supplier(2, "Macdonald's"));
        suppliers.add(new Supplier(3, "Beltelecom"));
        return suppliers;
    }

    @RequestMapping(value = "/{supplierId}/dishes", method = RequestMethod.GET)
    public List<Dish> fetchDishes(@PathVariable String supplierId) {
        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish(0, "Soup", "Troll's fat in vodka", 12000));
        dishes.add(new Dish(1, "Soup", "Żurek", 20000));
        dishes.add(new Dish(2, "Main Dish", "Chicken with fries", 25000));
        dishes.add(new Dish(3, "Main Dish", "VGA cable", 14000));
        dishes.add(new Dish(4, "Main Dish", "Burned curry wurst", 30000));
        dishes.add(new Dish(5, "Dessert", "Dragon's eye", 2300000));
        dishes.add(new Dish(6, "Dessert", "Salt", 10));
        return dishes;
    }

    @RequestMapping(value = "/{supplierId}/dishes/{date}", method = RequestMethod.GET)
    public List<Dish> fetchDishes(@PathVariable String supplierId, @PathVariable String date) {
        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish(7, "Atrocity", supplierId + " for " + date, 12000));
        dishes.add(new Dish(0, "Soup", "Troll's fat in vodka", 12000));
        dishes.add(new Dish(1, "Soup", "Żurek", 20000));
        dishes.add(new Dish(2, "Main Dish", "Chicken with fries", 25000));
        dishes.add(new Dish(3, "Main Dish", "VGA cable", 14000));
        dishes.add(new Dish(4, "Main Dish", "Burned curry wurst", 30000));
        dishes.add(new Dish(5, "Dessert", "Dragon's eye", 2300000));
        dishes.add(new Dish(6, "Dessert", "Salt", 10));
        return dishes;
    }
}