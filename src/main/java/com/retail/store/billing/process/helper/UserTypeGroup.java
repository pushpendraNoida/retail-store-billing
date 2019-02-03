package com.retail.store.billing.process.helper;

import com.retail.store.billing.discount.Discount;
import com.retail.store.billing.model.Customer;


/**
 * 
 * @author PS
 *
 */
@FunctionalInterface
public interface UserTypeGroup {
	
	/**
	 * 
	 * @param userVO
	 * @return
	 */
	Discount<Customer> getDiscountAmount(Customer customer);
}