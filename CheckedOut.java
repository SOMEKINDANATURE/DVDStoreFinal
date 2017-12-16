public class CheckedOut 
{
	CustomerType customer;
	DVD[] dvd = new DVD[5];
	int cnt = 0;
	public void CheckedOut(CustomerType cus, DVD d)
	{
		customer = cus;
		if (cnt < 5)
		{
			dvd[cnt] = d;
			cnt++;
		}
		else
		{
			System.out.println("You cant have more than 5 movies checked out at a single time");
			
		}
	}
	public void findandRemoveMovie(String title)
	{
		for(int i = cnt; i > 0; i-- )
		{
			if(dvd[i].Title.equals(title))
			{
				dvd[i].Return();
				dvd[i] = null;
			}
		}
	}
	public int getCnt()
	{
		return this.cnt;
	}
	
	public int getId()
	{
		return this.customer.acct_number;
	}
	public String getName()
	{
		return this.customer.name;
	}
	public void addDVD(DVD d)
	{
		if (cnt < 5)
		{
			dvd[cnt] = d;
			cnt++;
		}
		else
		{
			System.out.println("You cant have more than 5 movies checked out at a single time");
			
		}
	}
	public void printChecked()
	{
	
		String s;
		System.out.println("Name: " + getName() + "\n" +"Account Number " + customer.acct_number + " \n" + "DVD's");
		for(int i = 0; i < cnt; i++)
		{
			System.out.print(dvd[i].Title);
			System.out.println();
		}
	}

	

}