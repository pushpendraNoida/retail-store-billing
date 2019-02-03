package com.retail.store.billing.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.retail.store.billing.exception.RetailBillingTransactionException;
import com.retail.store.billing.model.Customer;
import com.retail.store.billing.model.CustomerType;
import com.retail.store.billing.process.service.RetailStoreBillingService;
import com.retail.store.billing.process.service.RetailStoreBillingServiceImpl;
import com.retail.store.billing.test.TestingSecurityManager.TestExitException;

import static org.junit.jupiter.api.Assertions.assertNotNull;






@ExtendWith(MockitoExtension.class)
public class RetailStoreBillingServiceTest {

	static SecurityManager originalSecurityManager;
	static RetailStoreBillingService retailStoreBillingService=null;
    @BeforeAll
    public static void setup() {
        // Insert our own custom SecurityManager that throws an exception when System.exit() is called.
        originalSecurityManager = System.getSecurityManager();
        System.setSecurityManager(new TestingSecurityManager());
        retailStoreBillingService=new RetailStoreBillingServiceImpl();
    }
    
    
    @AfterAll
    public static void tearDown() {
        // Reinsert the original SecurityManager now that we are done with these tests.
        System.setSecurityManager(originalSecurityManager);
    }
    
    
    @Test
    public void testRetrievNetPayableBilledAmountGROCEIES() {
    	Customer customer=new Customer();
		 customer.setCustomerType(CustomerType.GROCEIES);
		 customer.setBilledAmount(450.78);
		 customer.setTransactionNumber("1233555AAE455");
		 Double result=retailStoreBillingService.netPayableAmt(customer);
		 assertNotNull(result);
    }
    
    @Test
    public void testRetrievNetPayableBilledAmountSTORE_EMPLOYEE() {
    	Customer customer=new Customer();
		 customer.setCustomerType(CustomerType.STORE_EMPLOYEE);
		 customer.setBilledAmount(450.78);
		 customer.setTransactionNumber("1233555AAE455");
		 Double result=retailStoreBillingService.netPayableAmt(customer);
		 assertNotNull(result);
    }
    
    @Test
    public void testRetrievNetPayableBilledAmountAFFILATED_CUSTOMER() {
    	Customer customer=new Customer();
		 customer.setCustomerType(CustomerType.AFFILATED_CUSTOMER);
		 customer.setBilledAmount(450.78);
		 customer.setTransactionNumber("1233555AAE455");
		 Double result=retailStoreBillingService.netPayableAmt(customer);
		 assertNotNull(result);
    }
    @Test
    public void testRetrievNetPayableBilledAmountCUSTOMER_OVER_2_YEARS() {
    	Customer customer=new Customer();
		 customer.setCustomerType(CustomerType.CUSTOMER_OVER_2_YEARS);
		 customer.setBilledAmount(450.78);
		 customer.setTransactionNumber("1233555AAE455");
		 Double result=retailStoreBillingService.netPayableAmt(customer);
		 assertNotNull(result);
    }

    @Test
    public void testWrongCustomerTypeArgument() {
       
        try {
        	Customer customer=new Customer();
   		    //customer.setCustomerType(CustomerType.CUSTOMER_OVER_2_YEARS);
   		    customer.setBilledAmount(450.78);
   		    customer.setTransactionNumber("1233555AAE455");
   		    Double result=retailStoreBillingService.netPayableAmt(customer);
   		    //assertNotNull(result);
            // Our custom SecurityManager should have thrown an exception when HelloApp exited.
            // This means this line below cannot be reached. To make sure that our custom SecurityManager
            // works as expected, we fail the test if this line is ever reached:
            fail("Unreachable.");
        } catch (RetailBillingTransactionException e) {
        	//e.printStackTrace();
            // Did the program exit with the expected error code?
            assertThat(e.getStatus(), is(RetailStoreBillingService.EXIT_STATUS_CUSTOMER_TYPE_NOT_CORRECT));
        }
    }

    @Test
    public void testWrongBilledAmountArgument() {
      
        try {
        	Customer customer=new Customer();
   		    customer.setCustomerType(CustomerType.CUSTOMER_OVER_2_YEARS);
   		    //customer.setBilledAmount(450.78);
   		    customer.setTransactionNumber("1233555AAE455");
   		    Double result=retailStoreBillingService.netPayableAmt(customer);
   		    //assertNotNull(result);
            fail("Unreachable.");
        } catch (RetailBillingTransactionException e) {
            // Did the program exit with the expected error code?
            assertThat(e.getStatus(), is(RetailStoreBillingService.EXIT_STATUS_BILLED_AMOUNT_NOT_CORRECT));
        }
    }

    @Test
    public void testWrongTransactionNoArgument() {
    	try {
        	Customer customer=new Customer();
   		    customer.setCustomerType(CustomerType.CUSTOMER_OVER_2_YEARS);
   		    customer.setBilledAmount(450.78);
   		    //customer.setTransactionNumber("1233555AAE455");
   		    Double result=retailStoreBillingService.netPayableAmt(customer);
   		    //assertNotNull(result);
            fail("Unreachable.");
        } catch (RetailBillingTransactionException e) {
            // Did the program exit with the expected error code?
            assertThat(e.getStatus(), is(RetailStoreBillingService.EXIT_STATUS_TRANSACTION_NO_NOT_CORRECT));
        }
    }

    @Test
    public void classInstanceForCodeCoverageTest() {
        // Strictly speaking this test doesn't achieve anything, because HelloApp contains only a single static
        // method, but for purposes of full code coverage it is included. In general,
        // it is easier to aim for full code coverage and be done with it, than to remember why class X is stuck at
        // 95% code coverage.
    	new RetailStoreBillingServiceImpl();
    }

}
