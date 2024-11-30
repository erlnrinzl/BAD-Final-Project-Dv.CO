package model;

public class Cart {
	private String userID, donutID;
	private Integer quantity = 0;
	
	private Donut donut;
	
	public Cart(String userID, Integer quantity, Donut donut) {
		super();
		this.userID = userID;
		this.donut = donut;
		this.donutID = donut.getDonutID();
		this.quantity = quantity;
	}

	public String getUserID() {
		return this.userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getDonutID() {
		return this.donut.getDonutID();
	}

	public void setDonutID(String donutID) {
		this.donut.setDonutID(donutID);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Donut getDonut() {
		return donut;
	}

	public void setDonut(Donut donut) {
		this.donut = donut;
	}
	
	public Double getTotalPrice() {
		return this.donut.getDonutPrice() * this.quantity;
	}

}
