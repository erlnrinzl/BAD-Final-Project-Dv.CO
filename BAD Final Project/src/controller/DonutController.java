package controller;

import dao.DonutDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Donut;

public class DonutController {
	private DonutDAO donutDAO;
	private ObservableList<Donut> donutProducts;
	
	public DonutController() {
		super();
		donutDAO = new DonutDAO();
		donutProducts = FXCollections.observableArrayList();
	}

	public ObservableList<Donut> getDonutProducts() {
		return donutProducts;
	}

	public void loadDonutProducts() {
		donutProducts.clear();
		donutProducts.addAll(donutDAO.read());
	
	}
	
	public Donut findDonutByName(String donutName) {
		for (Donut donut : donutProducts) {
			if (donut.getDonutName().equals(donutName)) {
				return donut;
			}
		}
		return null;
	}
}
