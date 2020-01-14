//MainFrameCoding

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
  
class MainFrame extends JFrame
{

Container c;
JButton btnAdd,btnView,btnUpdate,btnDelete,btnClose;

public MainFrame()
	{
		c = getContentPane();
		FlowLayout f = new FlowLayout();
		c.setLayout(f);
		btnAdd = new JButton("Add");
		btnView = new JButton("View");
		btnUpdate =new JButton("Update");
		btnDelete = new JButton("Delete");
		btnClose = new JButton("Close");
		
		c.add(btnAdd);
		c.add(btnView);
		c.add(btnUpdate);
		c.add(btnDelete);
		c.add(btnClose);
		
		//to display addFrame
		btnAdd.addActionListener (new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		{
					addFrame a = new addFrame();
					dispose();
					
		}
		});
		
		//to display ViewFrame
		btnView.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		{
			ViewFrame v = new ViewFrame();
			dispose();
		}
		});
		
		btnUpdate.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		{
			
			
		UpdateFrame u = new UpdateFrame();
		dispose();
		}
		});
		
		btnDelete.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		{	
		DeleteFrame d = new DeleteFrame();
		dispose();
		}
		});
		
		btnClose.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		{
			System.exit(1);
		}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(600,200);
		setSize(200,250);
		setVisible(true);
		
	}



public static void main(String args[])
	{
		MainFrame f = new MainFrame();
	}
}
class DbHandler
{
public void playSound(String soundName)
 {
   try 
   {
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
   }
   catch(Exception ex)
   {
     System.out.println("Error with playing sound.");
     ex.printStackTrace();
   }
}
	public void addEmployee(int id,String name,double salary)
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
		

		
		e.setId(id);
		e.setName(name);
		e.setSalary(salary);
		session.save(e);
		
		t.commit();
				playSound("C:\\demo\\java\\L20\\Project2\\right.wav");
	JOptionPane.showMessageDialog(new JDialog(), " Record inserted");		

		System.out.println("end");
		
	}
	catch(Exception e)
	{
		t.rollback();
		
		playSound("C:\\demo\\java\\L20\\Project2\\false.wav");
		JOptionPane.showMessageDialog(new JDialog(), e);
		
		
	}	
	finally{
	session.close();
	}
	}

public String viewEmployee()
{
	StringBuffer sb = new StringBuffer();
		Configuration cfg = new Configuration();
	cfg.configure("hibernate.cfg.xml");
	SessionFactory sfact = cfg.buildSessionFactory();
	Session session  = sfact.openSession();
	
	
	
	try
	{
			
		System.out.println("begin");
		
		List<Employee> emp = new ArrayList<>();
		emp  = session.createQuery("from Employee").list();
		/*for(Student s:stu)
			System.out.println(s.getRno() + " " + s.getName() );*/
	
		for(Employee e:emp)
			sb.append("id:  " + e.getId() + "   " + "name:  " + e.getName() +"  " +"Salary:  " + e.getSalary() + "\n");
	
		System.out.println("end");
		
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(new JDialog(), " some isssues" + e);
	}	
	finally{
	session.close();
	}
	return sb.toString();

}

public int deleteEmployee(int id)
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
		
		Employee e = (Employee)session.get(Employee.class,id);
		if(e!=null)
		{
			
			
			session.delete(e);
			t.commit();
		playSound("C:\\demo\\java\\L20\\Project2\\right.wav");
			JOptionPane.showMessageDialog(new JDialog()," Record deleted");
		}
		else
			//playSound("C:\\demo\\java\\L20\\Project2\\false.wav");
			JOptionPane.showMessageDialog(new JDialog(),"record does not exists");
		System.out.println("end");
		
	}
	catch(Exception e)
	{
		t.rollback();
		playSound("C:\\demo\\java\\L20\\Project2\\false.wav");
		JOptionPane.showMessageDialog(new JDialog(),e);
	}	
	finally{
	session.close();
	}
return 0;
}
public void updateEmployee(int id,String name,double salary)
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
		
		Employee e= (Employee)session.get(Employee.class,id);
		if(e!=null)
		{
			//String name= c.readLine("enter new name");
			e.setName(name);
			e.setSalary(salary);
			session.save(e);
			t.commit();
							playSound("C:\\demo\\java\\L20\\Project2\\right.wav");
			JOptionPane.showMessageDialog(new JDialog(),"Record updated");
		}
		else
		//	playSound("C:\\demo\\java\\L20\\Project2\\false.wav");
			JOptionPane.showMessageDialog(new JDialog(),"record does not exists");
		System.out.println("end");
		
	}
	catch(Exception e)
	{
		t.rollback();
		playSound("C:\\demo\\java\\L20\\Project2\\false.wav");
		JOptionPane.showMessageDialog(new JDialog(),e);
	}	
	finally{
	session.close();
	}

}

}

