package innovate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import innovate.entity.Contact;
import innovate.utils.DbUtil;

public class JdbcContactsDAO implements ContactsDAO {

	@Override
	public Contact addContact(Contact contact) throws DaoException {
		String sql = "INSERT INTO contacts(name, gender, email, phone, city, country) VALUES (?,?,?,?,?,?)";
		
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		){
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getGender());
			stmt.setString(3, contact.getEmail());
			stmt.setString(4, contact.getPhone());
			stmt.setString(5, contact.getCity());
			stmt.setString(6, contact.getCountry());
			
			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			contact.setId(keys.getInt(1));
			keys.close();
			return contact;
			} catch(Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Contact findById(Integer id) throws DaoException {
		String sql = "SELECT * FROM contacts WHERE id=?";
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				Contact c = toContact(rs);
				rs.close();
				return c;
			}
			rs.close();
		}catch(Exception e) {
			throw new DaoException(e);
		}
		return null;
	}

	private Contact toContact(ResultSet rs) throws SQLException {
		Contact c = new Contact();
		c.setId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		c.setGender(rs.getString("gender"));
		c.setEmail(rs.getString("email"));
		c.setPhone(rs.getString("phone"));
		c.setCity(rs.getString("city"));
		c.setCountry(rs.getString("country"));
		return c;
	}

	@Override
	public Contact updateContact(Contact contact) throws DaoException {
		String sql = "UPDATE contacts SET name=?, gender=?, email=?, phone=?, city=?, country=? WHERE id=?";
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getGender());
			stmt.setString(3, contact.getEmail());
			stmt.setString(4, contact.getPhone());
			stmt.setString(5, contact.getCity());
			stmt.setString(6, contact.getCountry());
			stmt.setInt(7, contact.getId());
			
			int count = stmt.executeUpdate();
			if(count == 0) {
				throw new DaoException("No Records Updated. Invalid ID: " + contact.getId());
			}
		}catch(Exception e) {
			throw new DaoException(e);
		}
		return contact;
	}

	@Override
	public void deleteContact(Integer id) throws DaoException {
		String sql = "DELETE FROM contacts WHERE id=?";
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setInt(1, id);
			int count = stmt.executeUpdate();
			if(count == 0) {
				throw new DaoException("No Records Deleted. Invalid ID: " + id);
			}
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Contact> findAll() throws DaoException {
		String sql = "SELECT * FROM contacts";
		List<Contact> list = new ArrayList<Contact>();
		
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
		){
			while(rs.next()){
				Contact c = toContact(rs);
				list.add(c);
			}
		}catch(Exception e) {
			throw new DaoException(e);
		}
		return list;
	}

	@Override
	public List<Contact> findByCity(String city) throws DaoException {
		String sql = "SELECT * FROM contacts WHERE city=?";
		List<Contact> list = new ArrayList<Contact>();
		
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setString(1,city);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				Contact c = toContact(rs);
				list.add(c);
			}
			rs.close();
		}catch(Exception e) {
			throw new DaoException(e);
		}
		return list;
	}

	@Override
	public List<Contact> findByCountry(String country) throws DaoException {
		String sql = "SELECT * FROM contacts WHERE country=?";
		List<Contact> list = new ArrayList<Contact>();
		
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setString(1,country);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				Contact c = toContact(rs);
				list.add(c);
			}
			rs.close();
		}catch(Exception e) {
			throw new DaoException(e);
		}
		return list;
	}

}
