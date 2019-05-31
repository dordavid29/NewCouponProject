package com.dor.coupons.logic;

import java.util.Date;
import java.util.List;

import com.dor.coupons.beans.Coupon;
import com.dor.coupons.dao.CouponsDao;
import com.dor.coupons.dao.PurchasesDao;
import com.dor.coupons.enums.Category;
import com.dor.coupons.exception.ApplicationException;
import com.dor.coupons.exception.ErrorTypes;

public class CouponsController {
	
	private CouponsDao couponsDao = new CouponsDao();
	private PurchasesDao purchasesDao = new PurchasesDao();
	
	// ----- constructors ------
	
	public CouponsController(CouponsDao couponsDao) {
		super();
		this.couponsDao = couponsDao;
	}
	
	public CouponsController() {
	}

	// ----- function ------

	public long createCoupon(Coupon coupon) throws ApplicationException {
		if(couponsDao.isCouponExistsByTitle(coupon.getCompanyId(), coupon.getTitle())){
			throw new ApplicationException(ErrorTypes.DATA_EXIST, " The failed to catch if company " + coupon.getCompanyId() + " exists by title");
		}
		validateCouponTitle(coupon.getTitle());
		validateCouponDescription(coupon.getDescription());
		validateCouponDate(coupon.getStartDate(), coupon.getEndDate());
		validateCouponAmount(coupon.getAmount());
		validateCouponPrice(coupon.getPrice());
		
		return couponsDao.createCoupon(coupon);
	}
	
	public Coupon getCoupon(long couponId) throws ApplicationException {
		return couponsDao.getCoupon(couponId);
	}
	
	public List<Coupon> getAllCoupons() throws ApplicationException {
		return couponsDao.getAllCoupons();
	}
	
	public List<Coupon> getAllCouponsByCategory(Category category) throws ApplicationException {
		return couponsDao.getAllCouponsByCategory(category);
	}
	
	public List<Coupon> getAllCouponsByMaxPrice(double maxPrice) throws ApplicationException {
		return couponsDao.getAllCouponsByMaxPrice(maxPrice);
	}
	
	public List<Coupon> getCustomerCoupons(long customerId) throws ApplicationException {
		return couponsDao.getCustomerCoupons(customerId);
	}
	
	public List<Coupon> getCompanyCoupons(long companyId) throws ApplicationException {
		return couponsDao.getCompanyCoupons(companyId);
	}
	
	public List<Coupon> getCustomerCouponsByCategory(Category category, long customerId) throws ApplicationException {
		return couponsDao.getCustomerCouponsByCategory(category, customerId);
	}
	
	public List<Coupon> getCompanyCouponsByCategory(long companyId, Category category) throws ApplicationException {
		return couponsDao.getCompanyCouponsByCategory(companyId, category);
	}
	
	public List<Coupon> getCustomerCouponsByMaxPrice(long customerId, double maxPrice) throws ApplicationException {
		return couponsDao.getCustomerCouponsByMaxPrice(customerId, maxPrice);
	}
	
	public List<Coupon> getCompanyCouponsByMaxPrice(long companyId, double maxPrice) throws ApplicationException {
		return couponsDao.getCompanyCouponsByMaxPrice(companyId, maxPrice);
	}
	
	public void updateCoupon(Coupon coupon) throws ApplicationException {
		Coupon couponOriginal = couponsDao.getCoupon(coupon.getId());
		if (couponOriginal.getCompanyId() != coupon.getCompanyId()) {
			throw new ApplicationException(ErrorTypes.ILLEGAL_OPERATION, " Company ID can not be changed");
		}
		validateCouponTitle(coupon.getTitle());
		validateCouponDescription(coupon.getDescription());
		validateCouponDate(coupon.getStartDate(), coupon.getEndDate());
		validateCouponAmount(coupon.getAmount());
		validateCouponPrice(coupon.getPrice());
		
		couponsDao.updateCoupon(coupon);
	}
	
	public void deleteCoupon(long couponId) throws ApplicationException {
		purchasesDao.deletePurchasesByCouponId(couponId);
		couponsDao.deleteCoupon(couponId);
	}
	
	public void deleteCouponsByCompanyId(long companyId) throws ApplicationException {
		couponsDao.deleteCouponsByCompanyId(companyId);
	}
	
	public void deleteAllExpiredCoupons() throws ApplicationException {
		couponsDao.deleteAllExpiredCoupons();
	}
	
	private void validateCouponTitle(String title) throws ApplicationException {
		if (title.length() < 4) {
			throw new ApplicationException(ErrorTypes.SHORT_VALUE, "The title of the coupon is short");
		}
	}
	
	private void validateCouponDescription(String description) throws ApplicationException {
		if (description.length() < 5) {
			throw new ApplicationException(ErrorTypes.SHORT_VALUE, "The description of the coupon is short");
		}
	}
	
	private void validateCouponDate(Date start_date, Date end_date) throws ApplicationException {
		Date currentDate = new Date();
		if (end_date.after(start_date) && end_date.after(currentDate)) {
			throw new ApplicationException(ErrorTypes.INVALID_ENTRY, "The date is invalid");
		}
	}
	
	private void validateCouponAmount(int amount) throws ApplicationException {
		if (amount < 1) {
			throw new ApplicationException(ErrorTypes.INVALID_ENTRY, "The amount is invalid");
		}
	}
	
	private void validateCouponPrice(double price) throws ApplicationException {
		if (price < 0) {
			throw new ApplicationException(ErrorTypes.INVALID_ENTRY, "The price is invalid");
		}
	}
	
}
