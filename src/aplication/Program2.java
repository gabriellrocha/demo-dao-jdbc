package aplication;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== Test 1: findById ===");
		
		Department department = departmentDao.findById(1);
		System.out.println(department);
		
		System.out.println("\n=== Test 2: findAll ===");
		List<Department> list = departmentDao.findAll();
		
		for(Department d : list) {
			System.out.println(d);
		}
		
		System.out.println("\n=== Test 3: insert ===");
		Department newDepartment = new Department(null, "Music");
		departmentDao.insert(newDepartment);
		System.out.println("Inserted! new Id = " + newDepartment.getId());
		
		System.out.println("\n=== Test 4: update ===");
		department = departmentDao.findById(6);
		department.setName("Food");
		departmentDao.update(department);
		System.out.println("Update completed");
		
		System.out.println("\n=== Test 5: delete ===");
		System.out.print("Enter Id for delete test: ");
		int id = scan.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete completed");
		
		scan.close();
	}
}
