package model;

public class TransactionDetail {
	private String transactionID;
	private String donutID;
	private Integer quantity;
	
	private Cart cart;
	private Donut donut;
	private TransactionHeader transactionHeader;
	
	public TransactionDetail(Cart cart, TransactionHeader transactionHeader) {
		super();
		this.cart = cart;
		this.transactionHeader = transactionHeader;
		this.transactionID = transactionHeader.getTransactionID();
		this.donutID = this.cart.getDonutID();
		this.donut = this.cart.getDonut();
		this.quantity = this.cart.getQuantity();
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getDonutID() {
		return donutID;
	}

	public void setDonutID(String donutID) {
		this.donutID = donutID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public TransactionHeader getTransactionHeader() {
		return transactionHeader;
	}
	
	public void setTransactionHeader(TransactionHeader transactionHeader) {
		this.transactionHeader = transactionHeader;
	}

	public Donut getDonut() {
		return donut;
	}

	public void setDonut(Donut donut) {
		this.donut = donut;
	}

}
