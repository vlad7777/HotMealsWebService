package hot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hot.Model.Category;
import hot.Model.Receipt;
import hot.Model.ReceiptLine;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Hello:");
        
        ReceiptLine ol = new ReceiptLine(12, 15);
        System.out.println(ol);
        
        ReceiptLine ol1 = new ReceiptLine(2, 5);
        System.out.println(ol1);
        
        Category c = new Category(12, "Moloko");
        System.out.println(c);
        
        Category c1 = new Category(13, "Moloko");
        System.out.println(c1);
    }
}