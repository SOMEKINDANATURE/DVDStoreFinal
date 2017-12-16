import java.awt.Cursor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
public class Driver {

	
	static LinkedPositionalList<DVD> DVDlist = new LinkedPositionalList<>( );
	static LinkedPositionalList<CustomerType> Cuslist = new LinkedPositionalList<>();
	static LinkedPositionalList<CheckedOut> COlist = new LinkedPositionalList<>();
	public static void main(String[] args) 
	{
		//addCus();
		//addDvd();
		deserializeCus();
		deserializeDVD();
		
				
	
		System.out.println(DVDlist.iterator().next().Title);
		
		System.out.println(searchForD("Star Wars").toString());
		
		System.out.println(DVDstock());
		
		System.out.println(DVDlist.toString());
		Cuslist.toString();
		COlist.toString();
		CheckedOut n = new CheckedOut();
		n.CheckedOut(searchForC("Jack Rowell"), searchForD("Star Wars"));
		n.addDVD(searchForD("Pulp Fiction"));
		
		COlist.addFirst(n);
		printCheckedOut();
				
		serializeDVD(DVDlist);
		serializeCus(Cuslist);
	}
//------------------- End of Main-----------------------------------
	
	//----------------------------------------------


	public static void Rent(String title, String name)
	{
		boolean f = false;
		Iterator<CheckedOut> itr = COlist.iterator();
		if(!searchForD(title).available())
		{
			System.out.println("DVD out of stock"); 
			return;
		}
		
		DVD temp = new DVD();
		temp = searchForD(title);
		searchForD(title).rent();
		while(itr.hasNext())
		{
			if(COlist.iterator().next().getName().equals(name))
			{
				COlist.iterator().next().addDVD(temp);
				f = true;
			}
		}
		if(f == false)
		{
			CheckedOut n = new CheckedOut();
			n.CheckedOut(searchForC(name), temp);
			COlist.addLast(n);
		}
		/*take temp and put into checkedout positional list
		* test for account existence in checkedout list
		* create instance node in C/O if none exists
		* 
		*
		*/
	}
	public static void printCheckedOut()
	{	Iterator<CheckedOut> itr = COlist.iterator();
		while(itr.hasNext())
		{
			itr.next().printChecked();
			
		}
	}
	public static void Return(String title, String name)
	{
		Iterator<CheckedOut> itr = COlist.iterator();
		
		searchForD(title).Return();
		while(itr.hasNext())
		{
			if(itr.next().getName().equals(name))
			{
				itr.next().findandRemoveMovie(title);
				if(searchForO(name).dvd == null)
				{
					Position<CheckedOut> cursor;
					cursor = COlist.first( );
		
		 			while (cursor != null) 
		 			{ 
			 			if(searchForC(name).name.equals(name))
				 		{
				 			COlist.remove(cursor);
				 			return;
				 		}		 
		 			}
		
				}
			}
				
		}

	}
	
	public void RemoveStock(String title)
	{
		Position<DVD> cursor;
		cursor = DVDlist.first( );
		
		 while (cursor != null) { 
			 if(searchForD(title).Title.equals(title))
				 {
				 	System.out.println(searchForD(title).Title);
				 	DVDlist.remove(cursor);
				 	return;
				 }
				
		 cursor = DVDlist.after(cursor); // advance to the next position (if any)
		 }
		 System.out.println("DVD no longer carried.");
		 return;
	}
	
	
	//Searches the list of DVD's to find a matching Title and returns the element 
	public static DVD searchForD(String title)
	{
		int counter = 0;
		DVDlist.iterator();
		Iterator<DVD> itr = DVDlist.iterator();
		while(itr.hasNext())
		{
			if(itr.next().Title.compareTo(title) == 0)
			{
				itr = DVDlist.iterator();
				for(counter = counter; counter > 0; counter--)
				{
					itr.next();
				}
				return itr.next();
			}
			counter++;
		}
		System.out.println("No DVD found.");
		return null;
	}
	

	// Searched list of customers for account with the same name and returns the element from the list
	public static CustomerType searchForC(String name)
	{
		int counter = 0;
		Iterator<CustomerType> itr = Cuslist.iterator();
		while(itr.hasNext())
		{
			if(itr.next().name.compareTo(name) == 0)
			{
				itr = Cuslist.iterator();
				for(counter = counter; counter > 0; counter--)
				{
					itr.next();
				}
				return itr.next();
			}
			counter++;
		}
		System.out.println("No Account found.");
		return null;
	}
	
	
	// Searches list of accounts with checkout movies for matching name and returns the element from the list
	public static CheckedOut searchForO(String name)
	{
		int counter = 0;
		Iterator<CheckedOut> itr = COlist.iterator();
		while(itr.hasNext())
		{
			if(itr.next().customer.name.compareTo(name) == 0)
			{
				itr = COlist.iterator();
				for(counter = counter; counter > 0; counter--)
				{
					itr.next();
				}
				return itr.next();
			}
			counter++;
		}
		System.out.println("Account has nothing rented.");
		return null;
	}
	
	public static String DVDstock()
	{
		String Tlist = "[ ";
		Tlist += DVDlist.first().getElement().Title + ": " + DVDlist.first().getElement().count;
		Position<DVD> cursor;
		cursor = DVDlist.first( );
		
		 while (cursor != DVDlist.last()) { 
			 Tlist +=  ", " + DVDlist.after(cursor).getElement().Title + ": " + DVDlist.after(cursor).getElement().count;
				
			 cursor = DVDlist.after(cursor); // advance to the next position (if any)
		 }
		 Tlist += " ]";
		 return Tlist;
	}
	
	public static void serializeDVD(LinkedPositionalList<DVD> e)
	{
	Position<DVD> cursor = e.first();
	ArrayList<DVD> dvd = new ArrayList<DVD>();
    
	while (cursor != null)
   {
		dvd.add(cursor.getElement( ));
    	cursor = e.after(cursor);
    
    }

    
       try
        {   
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("DVDList.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
             
            // Method for serialization of object
            out.writeObject(dvd);
             
            out.close();
            file.close();
             
            System.out.println("Object has been serialized");

        }
         
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

}
	public static void deserializeDVD()
	{    
		
	
    
    	try
	{   
        	// Reading the object from a file
        	FileInputStream file = new FileInputStream("DVDList.txt");
        	ObjectInputStream in = new ObjectInputStream(file);
         
        	// Method for deserialization of object
        	ArrayList<DVD> dvd = (ArrayList<DVD>)in.readObject();
        for(int i = 0; i < dvd.size(); i++)
        {
        	DVDlist.addFirst(dvd.get(i));
        }
        	in.close();
        	file.close();
         
        	System.out.println("Object has been deserialized ");

    	}
     
    	catch(IOException ex)
    	{
        	System.out.println("IOException is caught");
    	}
     
    	catch(ClassNotFoundException ex)
    	{
        	System.out.println("ClassNotFoundException is caught");
    	}
    }
    	  public static void serializeCus(LinkedPositionalList<CustomerType> e)
    	     {
    	 		Position<CustomerType> cursor = e.first();
    	 		ArrayList<CustomerType> cus = new ArrayList<CustomerType>();
    	         
    	 		while (cursor != null)
    	        {
    	 			cus.add(cursor.getElement( ));
    	         	cursor = e.after(cursor);
    	         
    	        }       
    	            try
    	             {   
    	                 //Saving of object in a file
    	                 FileOutputStream file = new FileOutputStream("customers.txt");
    	                 ObjectOutputStream out = new ObjectOutputStream(file);
    	                  
    	                 // Method for serialization of object
    	                 out.writeObject(cus);
    	                  
    	                 out.close();
    	                 file.close();
    	                  
    	                 System.out.println("Object has been serialized");
    	     
    	             }
    	              
    	             catch(IOException ex)
    	             {
    	                 System.out.println("IOException is caught");
    	             }
    	     
    	     }
    	     public static void deserializeCus()
    	     {    
    	   	
    	         
    	         try
    	         {   
    	             // Reading the object from a file
    	             FileInputStream file = new FileInputStream("customers.txt");
    	             ObjectInputStream in = new ObjectInputStream(file);
    	              
    	             // Method for deserialization of object
    	             ArrayList<CustomerType> cus = (ArrayList<CustomerType>)in.readObject();
    	             for(int i = 0; i < cus.size(); i++)
    	             {
    	             	Cuslist.addFirst(cus.get(i));
    	             }
    	             in.close();
    	             file.close();
    	              
    	             System.out.println("Object has been deserialized ");

    	         }
    	          
    	         catch(IOException ex)
    	         {
    	             System.out.println("IOException is caught");
    	         }
    	          
    	         catch(ClassNotFoundException ex)
    	         {
    	             System.out.println("ClassNotFoundException is caught");
    	         }
    	         
    	         
	}
    	     public static void addDvd()
    	 	{
    	 		DVD pulpFiction = new DVD("Pulp Fiction",2,"Harvey Weinstein"," Quentin Tarantino", "Miramax", 5);
    	 		pulpFiction.setStar("Bruce Willis");
    	 		pulpFiction.setStar("Uma Thruman");
    	 	
    	 		DVD starWars = new DVD("Star Wars",2,"Gary Kurtz","George Lucas", "20th Century Fox", 3);
    	 		starWars.setStar("Mark Hammil");
    	 		starWars.setStar("Harrison Ford");	
    	 		DVD clerks = new DVD("Clerks",2,"Scott Mosier","Kevin Smith", "Askew", 2);
    	 		clerks.setStar("Brian O'Halloran");
    	 		clerks.setStar("Jeff Anderson");
    	 		DVD paulBlart = new DVD("Paul Blart",2,"Barry Bernadi","Steve Carr", "Columbia", 4);
    	 		paulBlart.setStar("Kevin James");
    	 		paulBlart.setStar("Keit O'Donnell");

    	 		DVD piratesCarribean = new DVD();
    	 		DVDlist.addFirst(pulpFiction);
    	 		DVDlist.addLast(starWars);
    	 		DVDlist.addLast(clerks);
    	 		DVDlist.addLast(paulBlart);
    	 		DVDlist.addLast(piratesCarribean);
    	 		
    	 	}
    	 	public static void addCus()
    	 	{
    	 		CustomerType jack = new CustomerType("Jack Rowell","8778 wallaby way, Sydney", "Jack.Rowell95@gmail.com", 1998);
    	 		CustomerType Curt = new CustomerType("Curt Cherti","2032 Joel dr, Muskego", "Curt.Cherti95@gmail.com", 5085);
    	 		CustomerType Jason = new CustomerType("Jason Poole","1111 Fake st, Hollywood", "Jason.Poole95@gmail.com", 2018);
    	 		CustomerType Nate = new CustomerType("Nate Rolbiecki","1989 old st, forgotten", "Nate.Rolbiecki@gmail.com", 1372);
    	 		CustomerType Kyle = new CustomerType("Kyle Endl","2020 vision way, vision", "Kyle.Endl@gmail.com",3675);
    	 		Cuslist.addFirst(jack);
    	 		Cuslist.addLast(Curt);
    	 		Cuslist.addLast(Jason);
    	 		Cuslist.addLast(Nate);
    	 		Cuslist.addLast(Kyle);
    	 	}
}


