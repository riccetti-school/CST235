package controllers;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;
import business.OrdersBusinessInterface;
import business.OrdersBusinessService;
import services.DataService;

@ManagedBean
public class FormController implements Serializable {

	@EJB
	public OrdersBusinessService S;
	
	public String onSubmit () {
		
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		System.out.println("firstName: " + user.getFirstName() + " lastName: " + user.getLastName());
		
		// register this user
		DataService ds = new DataService();
		ds.save(user);
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		return "TestResponse.xhtml";
	}
	
	public String onLogin() {
		
		S.test();
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		System.out.println("onLogin :::: email: " + user.getEmail() + " password: " + user.getPassword());
		
		// register this user
		DataService ds = new DataService();
		if(ds.validate(user)) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
			return "TestResponse.xhtml";		
			
		}
		
		
		return "login.xhtml";
		
	}
	
}
