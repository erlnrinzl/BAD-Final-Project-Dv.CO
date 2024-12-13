package model;

public class Donut {
	private String donutID;
	private String donutName;
	private String donutDescription;
	private Double donutPrice;
	
	public Donut() {
	}
	
	public Donut(String donutID, String donutName, String donutDescription, Double donutPrice) {
		super();
		this.donutID = donutID;
		this.donutName = donutName;
		this.donutDescription = donutDescription;
		this.donutPrice = donutPrice;
	}

	public String getDonutID() {
		return donutID;
	}

	public void setDonutID(String donutID) {
		this.donutID = donutID;
	}

	public String getDonutName() {
		return donutName;
	}

	public void setDonutName(String donutName) {
		this.donutName = donutName;
	}

	public String getDonutDescription() {
		return donutDescription;
	}

	public void setDonutDescription(String donutDescription) {
		this.donutDescription = donutDescription;
	}

	public Double getDonutPrice() {
		return donutPrice;
	}

	public void setDonutPrice(Double donutPrice) {
		this.donutPrice = donutPrice;
	}
}
