package com.dor.coupons.test;

import com.dor.coupons.beans.Purchase;
import com.dor.coupons.dao.PurchasesDao;
import com.dor.coupons.logic.PurchasesController;

public class TestPurchase {

	public static void main(String[] args) throws Exception {
		try {
//		PurchasesDao purdao = new PurchasesDao();
			Purchase pur = new Purchase(22, 21, 2);
//		purdao.createPurchase(pur);
//		System.out.println(purdao.getAllCompanies());
//		purdao.updatePurchase(pur);
//		purdao.deletePurchase(2);
			PurchasesController purchasesController = new PurchasesController();
//		purchasesController.createPurchase(pur);
//		System.out.println(purchasesController.getPurchase(4));
//		System.out.println(purchasesController.getAllPurchases());
//		purchasesController.deletePurchase(4);
//		purchasesController.deletePurchasesByCompanyId(8);
//		purchasesController.deletePurchasesByCouponId(23);
//		purchasesController.deletePurchasesByCustomerId(22);
//		purchasesController.deleteExpiredPurchases();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
