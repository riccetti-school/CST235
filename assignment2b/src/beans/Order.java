package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@ManagedBean
@SessionScoped
@XmlRootElement(name = "Orders")
public class Order {

	private String orderNumber = "";
	private String productName = "";
	private float price = 0.0f;
	private int quantity = 0;
	private int id;
	
	
	public Order() {
		
	}
	
	public Order(String orderNumber, String productName, float price, int quantity, int id) {
		this.setOrderNumber(orderNumber);
		this.setPrice(price);
		this.setProductName(productName);
		this.setQuantity(quantity);
		this.setId(id);
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
