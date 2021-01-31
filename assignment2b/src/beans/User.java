package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class User {


	private String firstName;
	private String lastName;
	

	public User() {

		this.setFirstName("Dominic");
		this.setLastName("Riccetti");
	}
	
	@NotNull
	@Size(min=5,max=15)
	public String getFirstName() {
		return firstName;
	}
	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	@NotNull
	@Size(min=5,max=15)	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
