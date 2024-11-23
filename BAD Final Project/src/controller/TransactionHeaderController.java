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
		// to be removed soon!
		TransactionHeader header = new TransactionHeader(SessionManager.getUser());
		header.setTransactionID(this.headerDAO.generateTransactionID());
		header.initializeTransactionDetails(userCart);
		
		this.headerDAO.create(header);
		for (TransactionDetail	detail: header.getTransactionDetails()) {
			this.detailDAO.create(detail);
		}
		
		return header;
	}
}
