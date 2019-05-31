package com.dor.coupons.api;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dor.coupons.beans.Coupon;

@RestController
@RequestMapping("/coupons")
	
public class CouponsApi {

	@PostMapping
	public void createCoupon(@RequestBody Coupon coupon) {
		System.out.println(coupon);
	}
	
	@PutMapping
	public void updateCoupon(@RequestBody Coupon coupon) {
		System.out.println(coupon);
	}
	
	@GetMapping("/{couponId}")
	public void getCoupon(@PathParam("couponId") long id) {
		System.out.println("returned a coupon: " + id);
	}
	
	@DeleteMapping("/{couponId}")
	public void deleteCoupon(@PathParam("couponId") long id) {
		
	}
}
