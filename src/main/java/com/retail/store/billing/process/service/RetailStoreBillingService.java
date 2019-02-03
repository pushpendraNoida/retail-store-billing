package com.retail.store.billing.process.service;

import com.retail.store.billing.model.Customer;

public interface RetailStoreBillingService {
	
	static int EXIT_STATUS_TRANSCATION_FAILED = 2000;
	static int EXIT_STATUS_BILLED_AMOUNT_NOT_CORRECT = 2001;
    static int EXIT_STATUS_CUSTOMER_TYPE_NOT_CORRECT = 2002;
    static int EXIT_STATUS_TRANSACTION_NO_NOT_CORRECT = 2003;
	Double netPayableAmt(Customer customer);
	
}
