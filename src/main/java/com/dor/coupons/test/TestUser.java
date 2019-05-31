package com.dor.coupons.test;

import com.dor.coupons.beans.User;
import com.dor.coupons.beans.UserLoginDetails;
import com.dor.coupons.dao.UsersDao;
import com.dor.coupons.enums.ClientType;
import com.dor.coupons.exception.ApplicationException;
import com.dor.coupons.logic.UsersController;;

public class TestUser {

	public static void main(String[] args) throws ApplicationException {
		try {
		UsersDao userDao1 = new UsersDao();
		UsersController usersController = new UsersController(userDao1);
		User userTest1 = new User(7l, new UserLoginDetails("Dd4580", "51400@gmail.com", ClientType.COMPANY));
//		userDao1.createUser(userTest1);
		usersController.createUser(userTest1);
//		System.out.println(userDao1.login("israel@gmail", "888"));
//		System.out.println(userDao1.isUserExistsById(2));
//		System.out.println(userDao1.getUser(2));
//		
//		for (User u:userDao1.getAllUsers()) {
//			System.out.println(u);
//		}
//		User userTest2 = new User(28,null, new UserLoginDetails("1000", "1000@gmail.com", ClientType.CUSTOMER));
//		System.out.println(userDao1.createUser(userTest2));
//		userDao1.updateUser(userTest2);
//		System.out.println(userTest2);
//		userDao1.deleteUser(28);
//		System.out.println(userDao1.isUserExistsByEmail("d@gmail"));
//		System.out.println(usersController.getUser(53));
//		System.out.println(usersController.getAllUsers());
//		usersController.updateUser(userTest1);
//		usersController.deleteUser(10);
//		System.out.println(usersController.login("858@gmail.com", "Dd4580"));
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
