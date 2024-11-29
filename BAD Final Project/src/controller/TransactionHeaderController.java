package controller;

import dao.TransactionDetailDAO;
import dao.TransactionHeaderDAO;
import javafx.collections.ObservableList;
import model.Cart;
import model.TransactionDetail;
import model.TransactionHeader;
import util.SessionManager;

public class TransactionHeaderController {
	private TransactionHeaderDAO headerDAO = new TransactionHeaderDAO();
	private TransactionDetailDAO detailDAO = new TransactionDetailDAO();
	
	public TransactionHeader createTransaction(ObservableList<Cart> userCart) {
		TransactionHeader header = new TransactionHeader(SessionManager.getUser());
		header.setTransactionID(this.headerDAO.generateTransactionID());
		
		System.out.println(header.getTransactionID());
		System.exit(0);
		
		// create model instance for details
		for (Cart cart : userCart) {
			header.getTransactionDetails().add(new TransactionDetail(header, cart.getDonut(), cart.getQuantity()));
		}
		
		// store to database
		this.headerDAO.create(header);
		for (TransactionDetail	detail: header.getTransactionDetails()) {
			this.detailDAO.create(detail);
		}
		
		return header;
	}	
}
