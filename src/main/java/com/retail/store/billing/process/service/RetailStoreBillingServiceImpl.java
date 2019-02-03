package com.retail.store.billing.process.service;

import java.util.Arrays;

import org.apache.log4j.Logger;

import com.retail.store.billing.discount.Discount;
import com.retail.store.billing.exception.RetailBillingTransactionException;
import com.retail.store.billing.model.Customer;
import com.retail.store.billing.model.CustomerType;
import com.retail.store.billing.process.RetailStoreBilling;
import com.retail.store.billing.process.RetailStoreBillingRepository;
import com.retail.store.billing.process.helper.UserTypeGroupMapping;

public class RetailStoreBillingServiceImpl implements RetailStoreBillingService {

	final  Logger logger = Logger.getLogger(RetailStoreBillingServiceImpl.class);
	
	@Override
	public Double netPayableAmt(Customer customer) {

		RetailStoreBilling retailStoreBilling = new RetailStoreBillingRepository();

		if (customer.getBilledAmount() == null
				|| customer.getBilledAmount() <= 0.0) {
			throw new RetailBillingTransactionException(
					"Billed amount should be a positive number.",
					EXIT_STATUS_BILLED_AMOUNT_NOT_CORRECT);
		}
		if (customer.getCustomerType() == null
				|| !Arrays.asList(CustomerType.values()).contains(
						customer.getCustomerType())) {
			throw new RetailBillingTransactionException(
					"Customer Type should be correct.",
					EXIT_STATUS_CUSTOMER_TYPE_NOT_CORRECT);
		}
		if (customer.getTransactionNumber() == null) {
			throw new RetailBillingTransactionException(
					"Transaction Number should not be blank.",
					EXIT_STATUS_TRANSACTION_NO_NOT_CORRECT);
		}
		try {

			/**
			 * by using State pattern on run time it will call dynamically the
			 * appropriate logic for calculate the discount amount on User Type,
			 * By using this pattern, It removed if/else and making loosely
			 * coupled.
			 **/
			Discount<Customer> discount = UserTypeGroupMapping
					.getUserGroupMappingType(
							customer.getCustomerType().toString())
					.getDiscountAmount(customer);
			
			retailStoreBilling.netPayable(customer, discount);
		} catch (Exception ex) {
			logger.error("Transaction failed!!!!"+EXIT_STATUS_TRANSCATION_FAILED); 
			throw new RetailBillingTransactionException(
					"Transaction failed!!!!", EXIT_STATUS_TRANSCATION_FAILED);
		}

		return customer.getNetPayableAmount();
	}

	public static void main(String[] args) {
		RetailStoreBillingService retailStoreBillingService = new RetailStoreBillingServiceImpl();
		Customer customer = new Customer();
		customer.setCustomerType(CustomerType.GROCEIES);
		customer.setBilledAmount(450.78);
		customer.setTransactionNumber("1233555AAE455");
		Double result = retailStoreBillingService.netPayableAmt(customer);

		System.err.println("1111111111111111---->>" + result);
		
		
	}

}
