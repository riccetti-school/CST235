package beans;

import javax.faces.bean.ManagedBean;

@SuppressWarnings("deprecation")
@ManagedBean
public class User {


	private String firstName;
	private String lastName;
	

	public User() {

		this.setFirstName("Dominic");
		this.setLastName("Riccetti");
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
