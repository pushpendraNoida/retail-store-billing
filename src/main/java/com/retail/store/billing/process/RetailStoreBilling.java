package com.retail.store.billing.process;

import com.retail.store.billing.discount.Discount;
import com.retail.store.billing.model.Customer;

@FunctionalInterface
public interface RetailStoreBilling {

	Customer netPayable(Customer input, Discount<Customer> logic);
}
