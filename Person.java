import java.io.Serializable;

public class Person implements Serializable {
	
	String name;
	String address;
	String email;
	public Person(String name, String address, String email) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
	}
	public Person() {
		this.name = "";
		this.address = "";
		this.email = "";
		// TODO Auto-generated constructor stub
	}

}
