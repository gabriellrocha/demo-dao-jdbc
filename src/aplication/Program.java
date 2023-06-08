package aplication;

import db.DB;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao  sellerDao = new SellerDaoJDBC(DB.getConnection());
		
		Seller seller = sellerDao.findById(7);
		
		System.out.println(seller);
	}

}
