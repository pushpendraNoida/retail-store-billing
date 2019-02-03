package com.retail.store.billing.exception;

public class RetailBillingTransactionException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int status;
	private final String messge;

    public RetailBillingTransactionException(String messge,int status) {
        this.status = status;
        this.messge = messge;
    }

    public int getStatus() {
        return status;
    }
    public String getMessge() {
        return messge;
    }

}
