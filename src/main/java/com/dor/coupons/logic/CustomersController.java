package com.dor.coupons.logic;

import java.util.List;

import com.dor.coupons.beans.Customer;
import com.dor.coupons.dao.CustomersDao;
import com.dor.coupons.dao.PurchasesDao;
import com.dor.coupons.dao.UsersDao;
import com.dor.coupons.exception.ApplicationException;
import com.dor.coupons.exception.ErrorTypes;
import com.dor.coupons.utils.ValidateUtils;

public class CustomersController {

	private CustomersDao customersDao = new CustomersDao();
	private UsersDao usersDao = new UsersDao();
	private PurchasesDao purchasesDao = new PurchasesDao();

	// ----- constructors ------

	public CustomersController(CustomersDao customersDao) {
		super();
		this.customersDao = customersDao;
	}

	public CustomersController() {
		super();
	}

	// ----- function ------

	public long createCustomer(Customer customer) throws ApplicationException {
		if ((!ValidateUtils.isNameValid(customer.getFirstName()))
				|| (!ValidateUtils.isNameValid(customer.getLastName()))) {
			throw new ApplicationException(ErrorTypes.INVALID_NAME, " The name entered is incorrect");
		}
		long userId = usersDao.createUser(customer.getUser());
		customer.setUserId(userId);
		return customersDao.createCustomer(customer);
	}

	public Customer getCustomer(long customerId) throws ApplicationException {
		return customersDao.getCustomer(customerId);
	}

	public List<Customer> getCustomers() throws ApplicationException {
		return customersDao.getAllCustomers();
	}

	public void updateCustomer(Customer customer) throws ApplicationException {
		if ((!ValidateUtils.isNameValid(customer.getFirstName()))
				|| (!ValidateUtils.isNameValid(customer.getLastName()))) {
			throw new ApplicationException(ErrorTypes.INVALID_NAME, " The name entered is incorrect");
		}
		usersDao.updateUser(customer.getUser());
		customersDao.updateCustomer(customer);
	}

	public void deleteCustomer(long customerId) throws ApplicationException {
		purchasesDao.deletePurchasesByCustomerId(customerId);
		customersDao.deleteCustomer(customerId);
		usersDao.deleteUser(customerId);
	}

}
