package controllers;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.sql.*;

import javax.annotation.security.RolesAllowed;
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
@RolesAllowed({"GcuUser"})
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
	
	
	public void init() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		// Get the logged in Principle
		Principal principle= FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
			if(principle == null)
			{
				user.setFirstName("Unknown");
				user.setLastName("");
			}
			else
			{
				user.setFirstName(principle.getName());
				user.setLastName("");
			}
	}
	
	public String onLogoff() {
		// Invalidate the Session to clear the security token
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			
		// Redirect to a protected page (so we get a full HTTP Request) to get Login Page
		return "TestResponse.xhtml?faces-redirect=true";
	}
	
	public String onSendOrder() {
		Order o = new Order();
		o.setId(200);
		o.setOrderNumber("AX200");
		o.setPrice(12f);
		o.setProductName("Message Product");
		o.setQuantity(122);
		s.sendOrder(o);
		return "TestResponse.xhtml";
	}
	
	
	public String updateName() {
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
        int id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("id"));
        String name = context.getExternalContext().getRequestParameterMap().get("name");
	
		String stmt = "UPDATE testapp.ORDERS set PRODUCT_NAME='"+name+"' where id=" + id;

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
	
	
	// db
	public String deleteOrder() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
        int id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("id"));		
		
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
