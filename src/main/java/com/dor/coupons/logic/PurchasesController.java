package com.dor.coupons.logic;

import java.util.List;

import com.dor.coupons.beans.Coupon;
import com.dor.coupons.beans.Purchase;
import com.dor.coupons.dao.CouponsDao;
import com.dor.coupons.dao.PurchasesDao;
import com.dor.coupons.exception.ApplicationException;
import com.dor.coupons.exception.ErrorTypes;

public class PurchasesController {

	private PurchasesDao purchasesDao = new PurchasesDao();
	private CouponsDao couponsDao = new CouponsDao();

	// ----- constructors ------

	public PurchasesController(PurchasesDao purchasesDao) {
		super();
		this.purchasesDao = purchasesDao;
	}

	public PurchasesController() {
	}

	// ----- function ------
	
	public long createPurchase(Purchase purchase) throws ApplicationException {
		if (couponsDao.getCoupon(purchase.getCouponId()) == null) {
			throw new ApplicationException(ErrorTypes.DATA_NOT_EXIST, " There is purchase not exists");
		}
		ReducingAmountOfCoupons(purchase);
		return purchasesDao.createPurchase(purchase);
	}
	
	public Purchase getPurchase(long purchaseId) throws ApplicationException {
		return purchasesDao.getPurchase(purchaseId);
	}
	
	public List<Purchase> getAllPurchases() throws ApplicationException {
		return purchasesDao.getAllPurchases();
	}
	
	public void deletePurchase(long purchaseId) throws ApplicationException {
		purchasesDao.deletePurchase(purchaseId);
	}
	
	public void deletePurchasesByCustomerId(long customerId) throws ApplicationException {
		purchasesDao.deletePurchasesByCustomerId(customerId);
	}
	
	public void deletePurchasesByCouponId(long couponId) throws ApplicationException {
		purchasesDao.deletePurchasesByCouponId(couponId);
	}
	
	public void deletePurchasesByCompanyId(long companyId) throws ApplicationException {
		purchasesDao.deletePurchasesByCompanyId(companyId);
	}
	
	public void deleteExpiredPurchases() throws ApplicationException {
		purchasesDao.deleteExpiredPurchases();
	}
	
	private void ReducingAmountOfCoupons(Purchase purchase) throws ApplicationException {
		
		if (purchase.getPurchaseAmount() < 1) {
			throw new ApplicationException(ErrorTypes.INVALID_ENTRY, " invalid amount entered");
		}
		if (purchasesDao.isPurchaseExistsById(purchase.getPurchaseId())){
			throw new ApplicationException(ErrorTypes.DATA_EXIST, " There is purchase exists");	
		}
		if (couponsDao.getCoupon(purchase.getCouponId()).getAmount() - purchase.getPurchaseAmount() < 0){
			throw new ApplicationException(ErrorTypes.OUT_OF_STOCK, " This coupon is out of stock");
		}
		Coupon currentCoupon = couponsDao.getCoupon(purchase.getCouponId());
		currentCoupon.setAmount(couponsDao.getCoupon(purchase.getCouponId()).getAmount() - purchase.getPurchaseAmount());
		couponsDao.updateCoupon(currentCoupon);
	}
}