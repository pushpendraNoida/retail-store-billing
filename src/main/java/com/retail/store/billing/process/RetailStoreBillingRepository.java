package com.retail.store.billing.process;

import org.apache.log4j.Logger;

import com.retail.store.billing.discount.Discount;
import com.retail.store.billing.discount.DiscountCalculator;
import com.retail.store.billing.model.Customer;

public class RetailStoreBillingRepository implements RetailStoreBilling{
	final  Logger logger = Logger.getLogger(RetailStoreBillingRepository.class);
	@Override
	public Customer netPayable(Customer input, Discount<Customer> logic){
		
		return new RetailStoreBillingProcessor().execute(input, logic,  (Customer inputx, Discount<Customer> logicx)-> {
			
				  DiscountCalculator calculator=new DiscountCalculator(input);
				  Double totaldiscount=calculator.calculateDiscount(logic); 
				  Double netPayable=input.getBilledAmount()-totaldiscount; 
				  
				  logger.debug("netPayable====>"+netPayable);
				  input.setNetPayableAmount(netPayable);
				  return input;
			
		});

	}

}
