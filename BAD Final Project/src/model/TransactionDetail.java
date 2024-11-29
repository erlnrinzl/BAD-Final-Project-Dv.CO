package model;

public class TransactionDetail {
	private String transactionID;
	private String donutID;
	private Integer quantity;
	
	private Donut donut;
	private TransactionHeader transactionHeader;
	
	public TransactionDetail(TransactionHeader transactionHeader, Donut donut, Integer qty) {
		super();
		this.transactionHeader = transactionHeader;
		this.transactionID = transactionHeader.getTransactionID();
		this.donutID = donut.getDonutID();
		this.donut = donut;
		this.quantity = qty;
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
