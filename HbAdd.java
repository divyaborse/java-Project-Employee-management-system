import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;
class HbAdd
{

public static void main(String args[])
{
	Console c = System.console();
	Configuration cfg = new Configuration();
	cfg.configure("hibernate.cfg.xml");
	SessionFactory sfact = cfg.buildSessionFactory();
	Session session  = sfact.openSession();
	Transaction t = null;
	
	
	try
	{
		System.out.println("begin");
		t = session.beginTransaction();
		Employee e = new Employee();
		int id = Integer.parseInt(c.readLine("enter id "));
		String name = c.readLine("enter name ");
		int salary = Integer.parseInt(c.readLine("Enter salary "));
		
		e.setId(id);
		e.setName(name);
		e.setSalary(salary);
		session.save(e);
		
		t.commit();
		System.out.println("record inserted");
		
		
		System.out.println("end");
		
	}
	catch(Exception e)
	{
		t.rollback();
		
		System.out.println(e);
	}	
	finally{
	session.close();
	}
}

}