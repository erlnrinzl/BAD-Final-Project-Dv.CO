package model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TransactionHeader {
	private String transactionID;
	private String userID;
	
	private List<TransactionDetail> transactionDetails = new ArrayList<>();
	private User user;
	
	public TransactionHeader(User user) {
		super();
		this.user = user;
		this.userID = user.getUserID();
	}
	
    public void initializeTransactionDetails(ObservableList<Cart> userCart) {
        for (Cart cart : userCart) {
            this.transactionDetails.add(new TransactionDetail(cart, this));
        }
    }

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public List<TransactionDetail> getTransactionDetails() {
		return transactionDetails;
	}

	public void setTransactionDetails(List<TransactionDetail> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
