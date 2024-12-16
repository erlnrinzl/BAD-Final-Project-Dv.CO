package controller;

import java.util.List;

import dao.DonutDAO;
import exception.FormException;
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
		List<Donut> donuts = donutDAO.read();

		donutProducts.clear();
		donutProducts.addAll(donuts);
	}

	public Donut findDonutByName(String donutName) {
		for (Donut donut : donutProducts) {
			if (donut.getDonutName().equals(donutName))
				return donut;
		}

		return null;
	}

	public void create(Donut donut) throws FormException {
		this.validate(donut);
		donutDAO.create(donut);
	}

	public void update(Donut donut) throws FormException {
		this.validate(donut);
		donutDAO.update(donut);
	}

	public void delete(Donut donut) throws FormException {
		if (donut == null)
			throw new FormException("Please select a donut!");
		
		donutDAO.delete(donut.getDonutID());
	}

	private void validate(Donut donut) throws FormException {
		if (donut.getDonutName().isBlank())
			throw new FormException("Donut Name cannot be empty!");

		if (donut.getDonutDescription().isBlank())
			throw new FormException("Donut Description cannot be empty!");

		if (donut.getDonutPrice().toString().isBlank())
			throw new FormException("Donut Price cannot be empty!");

		if (donut.getDonutPrice() <= 0)
			throw new FormException("Donut Price should be more than 0");
	}

}
