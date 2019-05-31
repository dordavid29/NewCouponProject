package com.dor.coupons.logic;

import java.util.List;

import com.dor.coupons.beans.User;
import com.dor.coupons.dao.UsersDao;
import com.dor.coupons.enums.ClientType;
import com.dor.coupons.dao.CompaniesDao;
import com.dor.coupons.exception.ApplicationException;
import com.dor.coupons.exception.ErrorTypes;
import com.dor.coupons.utils.ValidateUtils;

public class UsersController {

	private UsersDao usersDao = new UsersDao();
	private CompaniesDao companiesDao = new CompaniesDao();

	// ----- constructors ------

	public UsersController(UsersDao usersDao) {
		super();
		this.usersDao = usersDao;
	}

	// ----- function ------

	public long createUser(User user) throws ApplicationException {

		if (usersDao.isUserExistsByEmail(user.getUserLoginDetails().getEmail())) {
			throw new ApplicationException(ErrorTypes.DATA_EXIST, " The user already exists");
		}
		if (!ValidateUtils.isEmailValid(user.getUserLoginDetails().getEmail())) {
			throw new ApplicationException(ErrorTypes.INVALID_EMAIL, " The email entered is incorrect");
		}

		if (!ValidateUtils.isPasswordValid(user.getUserLoginDetails().getPassword())) {
			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD, " The password entered is incorrect");
		}

		if (user.getCompanyId() != null) {
			if (!user.getUserLoginDetails().getType().equals(ClientType.COMPANY)) {
				throw new ApplicationException(ErrorTypes.INVALID_ENTRY, " The client type is invalid");
			}
			if (!companiesDao.isCompanyExistsById(user.getCompanyId())) {
				throw new ApplicationException(ErrorTypes.DATA_NOT_EXIST, " The company doesn't exist");
			}
		} else if (user.getUserLoginDetails().getType().equals(ClientType.COMPANY)){
			throw new ApplicationException(ErrorTypes.INVALID_ENTRY, " The client type is invalid");
		}
		return usersDao.createUser(user);
	}

	public User getUser(long userId) throws ApplicationException {
		return usersDao.getUser(userId);
	}

	public List<User> getAllUsers() throws ApplicationException {
		return usersDao.getAllUsers();
	}

	public void updateUser(User user) throws ApplicationException {

		if (!ValidateUtils.isEmailValid(user.getUserLoginDetails().getEmail())) {
			throw new ApplicationException(ErrorTypes.INVALID_EMAIL, " The email entered is incorrect");
		}

		if (!ValidateUtils.isPasswordValid(user.getUserLoginDetails().getPassword())) {
			throw new ApplicationException(ErrorTypes.INVALID_PASSWORD, " The password entered is incorrect");
		}

		if (user.getCompanyId() != null) {
			if (!companiesDao.isCompanyExistsById(user.getCompanyId())) {
				throw new ApplicationException(ErrorTypes.DATA_NOT_EXIST, " The company doesn't exist");
			}
		}

		if (usersDao.getUser(user.getUserId()).getUserLoginDetails().getType() != user.getUserLoginDetails()
				.getType()) {
			throw new ApplicationException(ErrorTypes.UPDATE_ERROR, " Can't update user type");
		}

		usersDao.updateUser(user);
	}

	public void deleteUser(long userId) throws ApplicationException {
		CustomersController customerController = new CustomersController();

		if (usersDao.getUser(userId) == null) {
			throw new ApplicationException(ErrorTypes.DATA_NOT_EXIST, " get user " + userId + " id was failed");
		}
		if (usersDao.getUser(userId).getUserLoginDetails().getType() == ClientType.CUSTOMER) {
			customerController.deleteCustomer(userId);
			return;
		}
		if (usersDao.getUser(userId).getUserLoginDetails().getType() == ClientType.ADMINISTRATOR) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, " You can not delete an adminstrator user");
		}
		usersDao.deleteUser(userId);
	}

	public ClientType login(String userName, String password) throws ApplicationException {
		return usersDao.login(userName, password);
	}

}