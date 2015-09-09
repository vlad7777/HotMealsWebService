package hot.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReceiptLine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long receiptId;
	private long dishId;
	
	public ReceiptLine(long receiptId, long dishId) {
		this.receiptId = receiptId;
		this.dishId = dishId;
	}
	
	@Override
	public String toString() {
		return String.format("ReceiptLine[ id = %d, orderId = %d, dishId = %d]", id, receiptId, dishId);
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getReceiptId() {
		return receiptId;
	}
	
	public void setReceiptId(long orderId) {
		this.receiptId = orderId;
	}
	
	public long getDishId() {
		return dishId;
	}
	
	public void setDishId(long dishId) {
		this.dishId = dishId;
	}
}
