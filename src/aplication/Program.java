package aplication;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		SellerDao  sellerDao = DaoFactory.createSellerDao();
		
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
		
		System.out.println("\n=== Test 5: seller update ===");
		seller = sellerDao.findById(8);
		seller.setName("Ana");
		seller.setEmail("ana@gmail.com");
		sellerDao.update(seller);
		System.out.println("Update completed");
		
		System.out.println("\n=== Test 6: seller delete");
		System.out.println("Enter id for delete test: ");
		int id = scan.nextInt();
		
		sellerDao.deleteById(id);
		
		scan.close();
	}

}
