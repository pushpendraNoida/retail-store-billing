package com.retail.store.billing.process;

import com.retail.store.billing.discount.Discount;
import com.retail.store.billing.model.Customer;

@FunctionalInterface
public interface  RetailStoreBillingTemplate {

	default Customer invoke(Customer input, Discount<Customer> logic){
		return process(input,logic);
	}
	
   public Customer process(Customer input, Discount<Customer> logic);
}
