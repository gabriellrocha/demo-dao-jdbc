package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection connection;
	
	public DepartmentDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Department department) {
		PreparedStatement st = null;
		
		try {
			st = connection.prepareStatement(""
					+ "INSERT INTO Department (Name)"
					+ "VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, department.getName());
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					department.setId(id);
					DB.closeResultSet(rs);
					
				} else { 
					throw new DbException("Unexpected error! No rows affected!");
				}
			}
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closeStatement(st);
		}
		
		
	}

	@Override
	public void update(Department department) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement(""
					+ "SELECT * FROM Department "
					+ "WHERE id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department department = new Department();
				department.setId(rs.getInt("Id"));
				department.setName(rs.getString("Name"));
				return department;
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		return null;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		List<Department> list = new ArrayList<>();
		
		try {
			
			st = connection.prepareStatement("SELECT * FROM department "
					+ "ORDER BY Name");
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				list.add(new Department(rs.getInt("Id"), rs.getString("Name")));
			}
			
			DB.closeResultSet(rs);
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closeStatement(st);
		}
		
		return list;
	}
}
