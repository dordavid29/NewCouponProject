package com.dor.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dor.coupons.beans.Company;
import com.dor.coupons.dao.interfaces.ICompaniesDao;
import com.dor.coupons.exception.ApplicationException;
import com.dor.coupons.exception.ErrorTypes;
import com.dor.coupons.utils.JdbcUtils;


public class CompaniesDao implements ICompaniesDao {

	public long createCompany(Company company) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "INSERT INTO companies (company_name, company_email) VALUES (?,?)";
			preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, company.getCompanyName());
			preparedStatement.setString(2, company.getCompanyEmail());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				long id = resultSet.getLong(1);
				System.out.println("Company id "+ id + " created successfully");
				return id;
			} else {
				throw new ApplicationException(ErrorTypes.CREATE_ERROR, " get company id was failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, ErrorTypes.CREATE_ERROR, " Create company has failed");
		} finally {
			System.out.println("created your " + company.toString());
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public Company getCompany(long companyId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "SELECT * from companies WHERE company_id = ?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setLong(1, companyId);
			result = preparedStatement.executeQuery();

			if (result.next()) {
				return extractCompanyFromResultSet(result);
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, ErrorTypes.READ_ERROR, " Get company has failed");
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement, result);
		}

	}

	public List<Company> getAllCompanies() throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		List<Company> companysList = new ArrayList<Company>();

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "SELECT * FROM companies";
			preparedStatement = connection.prepareStatement(sqlStatement);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				Company validCompany = extractCompanyFromResultSet(result);
				companysList.add(validCompany);
			}
			return companysList;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, ErrorTypes.READ_ERROR, " Get all companies have failed");
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement, result);
		}
	}

	public void updateCompany(Company company) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "UPDATE companies SET company_name =?, company_email=? WHERE company_id=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, company.getCompanyName());
			preparedStatement.setString(2, company.getCompanyEmail());
			preparedStatement.setLong(3, company.getCompanyId());
			preparedStatement.executeUpdate();
			System.out.println("Company id "+ company.getCompanyId() + " updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, ErrorTypes.UPDATE_ERROR, " Update company has failed");
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void deleteCompany(long companyId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "DELETE FROM companies WHERE company_id=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setLong(1, companyId);
			preparedStatement.executeUpdate();
			System.out.println("Company id "+ companyId + " deleted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, ErrorTypes.DELETE_ERROR, " Delete company has failed");
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public boolean isCompanyExistsById(long companyId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "SELECT * FROM companies WHERE company_id=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setLong(1, companyId);

			result = preparedStatement.executeQuery();

			if ((result.next())) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, ErrorTypes.DATA_NOT_EXIST, " The company " + companyId + " doesn't found");
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	
	public boolean isCompanyExistsByName(String companyName) throws ApplicationException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "SELECT * FROM companies WHERE company_name=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, companyName);

			result = preparedStatement.executeQuery();

			if ((result.next())) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, ErrorTypes.DATA_NOT_EXIST, " The failed to catch if company " + companyName + " exists by name");
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	
public boolean isCompanyExistsByEmail(String companyEmail) throws ApplicationException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "SELECT * FROM companies WHERE company_email=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, companyEmail);

			result = preparedStatement.executeQuery();

			if ((result.next())) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e, ErrorTypes.DATA_NOT_EXIST, " The failed to catch if company " + companyEmail + " exists by email");
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	private Company extractCompanyFromResultSet(ResultSet result) throws SQLException {

		Company company = new Company();

		company.setCompanyId(result.getLong("company_id"));
		company.setCompanyName(result.getString("company_name"));
		company.setCompanyEmail(result.getString("company_email"));

		return company;

	}

}
