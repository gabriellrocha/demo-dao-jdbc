package aplication;

import java.util.Date;
import java.util.List;

import db.DB;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao  sellerDao = new SellerDaoJDBC(DB.getConnection());
		
		System.out.println("=== Test 1: seller findById ===");
		Seller seller = sellerDao.findById(7);
		System.out.println(seller);
		
		System.out.println("\n=== Test 2: seller findByDepartment ===");
		
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		
		for (Seller s : list) {
			System.out.println(s);
		}
		
		System.out.println("\n=== Test 3: seller findByAll ===");
		list = sellerDao.findAll();
		
		for (Seller s : list) {
			System.out.println(s);
		}
		
		System.out.println("\n=== Test 4: seller insert ===");
		Seller newseller = new Seller(null, "Pedro", "pedro@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newseller);
		
		System.out.println("Inserted! new Id = " + newseller.getId());
		
	}

}
