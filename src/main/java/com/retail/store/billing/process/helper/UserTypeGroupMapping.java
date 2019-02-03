package com.retail.store.billing.process.helper;

import org.apache.log4j.Logger;

import com.retail.store.billing.discount.Discount;
import com.retail.store.billing.exception.RetailBillingTransactionException;
import com.retail.store.billing.model.Customer;


/**
 * 
 * @author PS
 * @Note Implementation of State pattern for calculating discount offer on Customer type.
 *
 */
public enum UserTypeGroupMapping implements UserTypeGroup {
	
	//,,,
	
	/**
	 * calculate the Get store type EMPLOYEE DISCOUNT
	 * 
	 */
	STORE_EMPLOYEE {
		@Override
		public Discount<Customer> getDiscountAmount(Customer customer) throws RetailBillingTransactionException{
			Discount<Customer> discount = (Customer customerx) -> {
				return () -> ((30 * customer.getBilledAmount()) / 100)
						+ ((customer.getBilledAmount() / 100) * 5);
			};
          return discount;
			
			
		}
	},
	
	/**
	 * calculate the Get type AFFILIATE Customer DISCOUNT
	 * 
	 */
	AFFILATED_CUSTOMER {
		@Override
		public Discount<Customer> getDiscountAmount(Customer customer) throws RetailBillingTransactionException{
			Discount<Customer> discount = (Customer customerx) -> {
				return () -> ((10 * customer.getBilledAmount()) / 100)
						+ ((customer.getBilledAmount() / 100) * 5);
			};
          return discount;
			
		}
	},
	/**
	 * calculate the Get type Loyal Customer (aligned with store from 2 years) DISCOUNT
	 * 
	 */
	CUSTOMER_OVER_2_YEARS {
		@Override
		public Discount<Customer> getDiscountAmount(Customer customer) throws RetailBillingTransactionException{
			Discount<Customer> discount = (Customer customerx) -> {
				return () -> ((5 * customer.getBilledAmount()) / 100)
						+ ((customer.getBilledAmount() / 100) * 5);
			};
          return discount;
		}
	},
	
	/**
	 * calculate the Get type GROCERY Customer
	 * 
	 */
	GROCEIES {
		@Override
		public Discount<Customer> getDiscountAmount(Customer customer) throws RetailBillingTransactionException{
			Discount<Customer> discount = (Customer customerx) -> {
				return ()->(customer.getBilledAmount()/100)*5;
			};
          return discount;
						
		}
	},
	
	
	/**
	 * if User/Customer is UNKNOWN
	 * 
	 */
	UNKNOWN {
		@Override
		public Discount<Customer> getDiscountAmount(Customer customer)throws RetailBillingTransactionException {
			Discount<Customer> discount = (Customer customerx) -> {
				return ()->0.0;
			};
          return discount;
		}
	};
	
	
	/**
	 * 
	 * @param pass the Customer TYpe as runTime (Generic)
	 * @return
	 */
	public static UserTypeGroupMapping getUserGroupMappingType(String runTimeValue) {
		final  Logger logger = Logger.getLogger(UserTypeGroupMapping.class);
		try {
			return UserTypeGroupMapping.valueOf(runTimeValue.toUpperCase());
		} catch (Exception ex) {
			logger.error(ex.getMessage()); 
			return UserTypeGroupMapping.valueOf("UNKNOWN");
		}
	}
}
