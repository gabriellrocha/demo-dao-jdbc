package aplication;

import db.DB;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao  sellerDao = new SellerDaoJDBC(DB.getConnection());
		
		System.out.println("=== Test 1: seller findById ===");
		Seller seller = sellerDao.findById(7);
		
		System.out.println(seller);
	}

}
