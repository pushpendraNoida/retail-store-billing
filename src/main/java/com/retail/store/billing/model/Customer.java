package com.retail.store.billing.model;

public class Customer {
private String customerName;
private CustomerType customerType;
private Double billedAmount;
private Double netPayableAmount;
private String transactionNumber;


public Double getBilledAmount() {
	return billedAmount;
}
public void setBilledAmount(Double billedAmount) {
	this.billedAmount = billedAmount;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public CustomerType getCustomerType() {
	return customerType;
}
public void setCustomerType(CustomerType customerType) {
	this.customerType = customerType;
}
public Double getNetPayableAmount() {
	return netPayableAmount;
}
public void setNetPayableAmount(Double netPayableAmount) {
	this.netPayableAmount = netPayableAmount;
}

public String getTransactionNumber() {
	return transactionNumber;
}

public void setTransactionNumber(String transactionNumber) {
	this.transactionNumber = transactionNumber;
}



}
