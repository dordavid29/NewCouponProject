package com.dor.coupons.test;

import java.util.Date;

import com.dor.coupons.beans.Company;
import com.dor.coupons.beans.Coupon;
import com.dor.coupons.dao.CompaniesDao;
import com.dor.coupons.dao.CouponsDao;
import com.dor.coupons.enums.Category;
import com.dor.coupons.logic.CouponsController;



public class TestCoupon {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
//		Company maorLiveInMovie = new Company("maor","maor@gmail.com");
//		CompaniesDao companiesDao = new CompaniesDao();
//		companiesDao.createCompany(maorLiveInMovie);
//		CouponsDao couponsDao = new CouponsDao();
		Date currentDate = new Date();
		Date startDate = new Date();
		Date endDate = new Date();
		startDate.setYear(100);
		endDate.setYear(110);
//		System.out.println(currentDate);
//		System.out.println(startDate + ", " + endDate);
				
		Coupon couponTest = new Coupon(10, Category.COFFEE_SHOPS, "Bibi","1+1 for business", startDate, endDate, 50, 60,"www.bibi.com");
		CouponsController couponsController = new CouponsController();
//		System.out.println(couponTest);
		couponsController.createCoupon(couponTest);
//		System.out.println(couponsController.getCompanyCoupons(5));
//		System.out.println(couponsController.getCoupon(14));
//		System.out.println(couponsController.getCustomerCoupons(23));
//		System.out.println(couponsController.getAllCoupons());
//		System.out.println(couponsController.getAllCouponsByCategory(Category.FOOTBALL_GAMES));
//		System.out.println(couponsController.getAllCouponsByMaxPrice(200));
//		System.out.println(couponsController.getCustomerCouponsByCategory(Category.FOOTBALL_GAMES, 22));
//		System.out.println(couponsController.getCompanyCouponsByCategory(8, Category.HOTELS));
//		System.out.println(couponsController.getCompanyCouponsByMaxPrice(8, 301));
//		System.out.println(couponsController.getCustomerCouponsByMaxPrice(22, 70));
//		couponsController.updateCoupon(couponTest);

//		couponsController.deleteCoupon(17);
//		couponsController.deleteCouponsByCompanyId(7);
//		couponsController.deleteAllExpiredCoupons();
	
	}

}
