package com.dor.coupons.logic;

import java.util.List;

import com.dor.coupons.beans.Company;
import com.dor.coupons.dao.CompaniesDao;
import com.dor.coupons.dao.PurchasesDao;
import com.dor.coupons.dao.UsersDao;
import com.dor.coupons.exception.ApplicationException;
import com.dor.coupons.exception.ErrorTypes;
import com.dor.coupons.utils.ValidateUtils;

public class CompaniesController {

	private CompaniesDao companiesDao = new CompaniesDao();

	private PurchasesDao purchasesDao = new PurchasesDao();
	private UsersDao usersDao = new UsersDao();

	public long createCompany(Company company) throws ApplicationException {
		if ((companiesDao.isCompanyExistsByName(company.getCompanyName()))
				|| (companiesDao.isCompanyExistsByEmail(company.getCompanyEmail()))) {
			throw new ApplicationException(ErrorTypes.DATA_EXIST, " The company already exists");
		}
		if (!ValidateUtils.isEmailValid(company.getCompanyEmail())) {
			throw new ApplicationException(ErrorTypes.INVALID_EMAIL, " The email entered is incorrect");
		}
		validateCompanyName(company.getCompanyName());
		return companiesDao.createCompany(company);
	}

	public Company getCompany(long companyId) throws ApplicationException {
		return companiesDao.getCompany(companyId);
	}

	public List<Company> getAllCompanies() throws ApplicationException {
		return companiesDao.getAllCompanies();
	}

	public void updateCompany(Company company) throws ApplicationException {
		if (!companiesDao.isCompanyExistsById(company.getCompanyId())) {
			throw new ApplicationException(ErrorTypes.DATA_NOT_EXIST,
					" The company " + company.getCompanyId() + " doesn't found");
		}
		if (!ValidateUtils.isEmailValid(company.getCompanyEmail())) {
			throw new ApplicationException(ErrorTypes.INVALID_EMAIL, " The email entered is incorrect");
		}
		validateCompanyName(company.getCompanyName());
		companiesDao.updateCompany(company);
	}

	public void deleteCompany(long companyId) throws ApplicationException {
		purchasesDao.deletePurchasesByCompanyId(companyId);
		usersDao.deleteUserByCompanyId(companyId);
		companiesDao.deleteCompany(companyId);
	}

	private void validateCompanyName(String companyName) throws ApplicationException {
		if (companyName.length() < 2) {
			throw new ApplicationException(ErrorTypes.SHORT_VALUE, " The name of the company is short");
		}
	}
}
