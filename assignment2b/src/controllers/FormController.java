package controllers;

import java.io.Serializable;
import java.util.List;
import java.sql.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.graalvm.compiler.api.replacements.Snippet.VarargsParameter;

import beans.Order;
import beans.User;
import business.LoginService;
import business.MyTimerService;
import business.OrdersBusinessInterface;
import business.OrdersBusinessService;
import business.RegistrationService;
import services.DataService;

@ManagedBean
public class FormController implements Serializable {


	@Inject
	public OrdersBusinessInterface s;

	@EJB
	public MyTimerService timer;
	
	@EJB
	public LoginService login;
	
	@EJB
	public RegistrationService reg;
	
	
	public List<Order> getOrders(){
		return s.getOrders();
	}
	
	public String newProduct() {
		FacesContext context = FacesContext.getCurrentInstance();
		Order order = context.getApplication().evaluateExpressionGet(context, "#{order}", Order.class);
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		List<Order> list = s.getOrders();
		list.add(order);
		s.setOrders(list);
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		return "TestResponse.xhtml";		
	}
	
	public String onSubmit () {
		
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		System.out.println("firstName: " + user.getFirstName() + " lastName: " + user.getLastName());
		
		reg.save(user);
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		return "TestResponse.xhtml";
	}
	
	public String onLogin() {
		
		timer.setTimer(100);
		

		getAllOrders();
		
		insertOrder();
		
		getAllOrders();
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		System.out.println("onLogin :::: email: " + user.getEmail() + " password: " + user.getPassword());
		
		// register this user
		if(login.validate(user)) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
			return "TestResponse.xhtml";		
			
		}
		
		
		return "login.xhtml";
		
	}
	
	private void insertOrder() {
		
		String stmt = "INSERT INTO  testapp.ORDERS(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES('001122334455', 'This was inserted new', 25.00, 100)";

		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Doom-123");
			System.out.println("Success!!");
			
			Statement s = conn.createStatement();
			s.executeUpdate(stmt);
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
	
	private void getAllOrders() {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Doom-123");
			System.out.println("Success!!");
			

			String stmt = "select * from testapp.orders";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(stmt);
			
			while(rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(3) + " " + rs.getFloat(4));
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
	
	
}
