package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Orders {

	private List<Order> orders;

	public Orders() {

		orders = new ArrayList<Order>();
	}

	public void fillAllOrders() {
		orders.clear();
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Doom-123");

			String stmt = "select * from testapp.orders";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(stmt);
			
			while(rs.next()) {
				orders.add(new Order(rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getInt(1)));
			}
			
			// close them out
			rs.close();
			s.close();
			
			
		} catch (SQLException e) {
			
			System.out.println("Failure!! -- " + e.getMessage());
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
		
	}
	
	public List<Order> getOrders() {
		fillAllOrders();
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	
}
