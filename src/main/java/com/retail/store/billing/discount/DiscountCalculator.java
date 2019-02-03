package com.retail.store.billing.discount;

import com.retail.store.billing.model.Customer;

public class DiscountCalculator {

	private Customer customer;

	
	public DiscountCalculator(Customer customer) {
		super();
		this.customer = customer;
	}

	public Double calculateDiscount(Discount<?> discount) {
		return discount.discount(customer).get();
	}

}
