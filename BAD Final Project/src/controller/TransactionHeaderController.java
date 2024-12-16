package controller;

import dao.TransactionDetailDAO;
import dao.TransactionHeaderDAO;
import javafx.collections.ObservableList;
import model.Cart;
import model.TransactionDetail;
import model.TransactionHeader;
import model.User;
import util.SessionManager;

public class TransactionHeaderController {
	private TransactionHeaderDAO headerDAO = new TransactionHeaderDAO();
	private TransactionDetailDAO detailDAO = new TransactionDetailDAO();

	public TransactionHeader createTransaction(ObservableList<Cart> userCart) {
		User user = SessionManager.getUser();
		String transactionID = this.headerDAO.generateTransactionID();

		TransactionHeader header = new TransactionHeader(user);
		header.setTransactionID(transactionID);

		for (Cart cart : userCart) {
			TransactionDetail detail = new TransactionDetail(header, cart.getDonut(), cart.getQuantity());
			header.getTransactionDetails().add(detail);
		}

		this.headerDAO.create(header);
		for (TransactionDetail detail : header.getTransactionDetails()) {
			this.detailDAO.create(detail);
		}

		return header;
	}
}
