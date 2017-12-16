
public class CustomerType extends Person
{
	int acct_number;
	String checkedOut[];
	
	/*public CustomerType() {
		super();
		this.acct_number = 1234;
		this.mWant = "Pirates of the Caribbean";
	}*/
	
	public CustomerType(String name, String address, String email, int actNumber) {
		super(name, address, email);
		acct_number = actNumber;
		
		this.checkedOut = new String[5];
	}

	public CustomerType() {
		super();
		this.name = "Harvey Mcbutteface";
		this.address = "Lame Rd WI";
		this.email = "wowisuck@gmail.com";
		this.acct_number = 123444;
		this.checkedOut = new String[2];
		// TODO Auto-generated constructor stub
	}


	public int getAcct_number() 
	{
		return acct_number;
	}


	public void setAcct_number(int n) 
	{
		this.acct_number = n;
	}

//Needs exception for full array
	public String Rent(String title)
	{
		for(int i = 0; i < checkedOut.length; i++)
		{
			if(checkedOut[i] == null)
			{
				checkedOut[i] = title;
			}
		}
		return title;
	}
	
	public String Return(String title)
	{
		for(int i = 0; i < checkedOut.length; i++)
		{
			if(checkedOut[i] == title)
			{
				checkedOut[i] = null;
				return title;
			}
		}
		return "Item has not been rented by this account.";
	}
	
	public String toString()
	{
		return "Name: " + this.name +  ", Address:" + this.address + ", Email:" + this.email + ", Account Number: " + acct_number;
	}
	
}
