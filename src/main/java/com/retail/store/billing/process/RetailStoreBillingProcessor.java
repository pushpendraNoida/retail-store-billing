package com.retail.store.billing.process;

import com.retail.store.billing.discount.Discount;
import com.retail.store.billing.model.Customer;

public class RetailStoreBillingProcessor {
	
	
	protected  Customer execute(Customer input, Discount<Customer> logic,RetailStoreBillingTemplate template){
		return template.invoke(input,logic);
    }

}
