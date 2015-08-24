package hot.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private int supplier_id;
    private String name;

    protected Category() {/* for JPA only */}

    public Category( int supplier_id, String name) {
        this.supplier_id = supplier_id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Category[id=%d, supplier_id=%d, name='%s']",
                             id, supplier_id, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
