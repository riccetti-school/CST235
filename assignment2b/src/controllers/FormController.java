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

	//@EJB
	//public MyTimerService timer;
	
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

		// add the order to the database
		s.insertOrder(order);
		// resend the list
		List<Order> list = s.getOrders();
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
		
		//timer.setTimer(100);
		
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
	
	
	// db
	public String deleteOrder() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
        int id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("id"));		
		
        System.out.println("ID: " + id);
        
		// resend the list
		List<Order> list = s.getOrders();
		s.setOrders(list);
		
		String stmt = "DELETE FROM testapp.ORDERS where id=" + id;

		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Doom-123");
			
			Statement s = conn.createStatement();
			s.execute(stmt);
			s.close();
			
		} catch (SQLException e) {
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		return "TestResponse.xhtml";		
		
	}
	
}
