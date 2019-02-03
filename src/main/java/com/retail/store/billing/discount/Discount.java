package com.retail.store.billing.discount;


import java.util.function.Supplier;

import com.retail.store.billing.model.Customer;

@FunctionalInterface
public interface Discount<C> {
	
	Supplier<Double> discount(Customer customer);

}
