package controller;

import dao.TransactionDetailDAO;
import dao.TransactionHeaderDAO;
import javafx.collections.ObservableList;
import model.Cart;
import model.TransactionDetail;
import model.TransactionHeader;
import model.User;

public class TransactionHeaderController {
	private TransactionHeaderDAO headerDAO = new TransactionHeaderDAO();
	private TransactionDetailDAO detailDAO = new TransactionDetailDAO();
	
	private User user = new User(
				"Dummy Name",
				"email@mail.com",
				"pass123",
				"male",
				"Indonesia",
				"08231231231",
				"User",
				10
			);
	
	public TransactionHeader createTransaction(ObservableList<Cart> userCart) {
		// to be removed soon!
		this.user.setUserID("US001");
		
		TransactionHeader header = new TransactionHeader(this.user);
		header.setTransactionID(this.headerDAO.generateTransactionID());
		header.initializeTransactionDetails(userCart);
		
		this.headerDAO.create(header);
		for (TransactionDetail	detail: header.getTransactionDetails()) {
			this.detailDAO.create(detail);
		}
		
		return header;
	}
}
