//addframe coding
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;
import java.util.regex.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
  
class addFrame extends JFrame
{
	Container c;
	JLabel lblId,lblName,lblSalary;
	JTextField txtId,txtName,txtSalary;
	JButton btnBack,btnSave;
	public addFrame()
	{
		c = getContentPane();
		FlowLayout f = new FlowLayout();
		c.setLayout(f);
		lblId = new JLabel("EmpId");
		txtId = new JTextField(20);
		lblName = new JLabel("Name");
		txtName = new JTextField(20);
		lblSalary = new JLabel("Salary");
		txtSalary = new JTextField(20);
		btnBack = new JButton("Back");
		btnSave = new JButton("Save");
		
		
		c.add(lblId);
		c.add(txtId);
		c.add(lblName);
		c.add(txtName);
		c.add(lblSalary);
		c.add(txtSalary);
		c.add(btnSave);
		c.add(btnBack);
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
			 
		int id;
		String name;
		String str;
		double salary;
		
	try{
	 id = Integer.parseInt(txtId.getText());

	
		 if(id<=0)
			 {
				 	
				
				 JOptionPane.showMessageDialog(new JDialog()," Enter EmpId correctly");
					
				 txtId.setText("");
				 txtId.requestFocus();
				 return;
			 }
	
		 name = txtName.getText();
		 if(name.length() <2)
			 {
				 JOptionPane.showMessageDialog(new JDialog(), "Name should contain at least two letters");
				 txtName.setText("");
				 txtName.requestFocus();
				 return;
			 }
		if(name.isEmpty())
			{
				JOptionPane.showMessageDialog(new JDialog(), " Enter name,please");
				txtName.setText("");
				txtName.requestFocus();
				return;
			}
			//pattern is used to define a pattern for regex engine
		Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
		//it creates a matcher that matches input with given pattern
		Matcher m = p.matcher(name);
		
		
//find() = finds the next expression that matches the pattern
//return boolean value
		if(m.find()){
		   JOptionPane.showMessageDialog(new JDialog(), " Enter characters only for name");
		   txtName.setText("");
		   txtName.requestFocus();
		   return;
		}
		salary = Double.parseDouble(txtSalary.getText());	
		if(salary < 8000.00)
			{
				JOptionPane.showMessageDialog(new JDialog(), " Minimum salary should be 8000");
				txtSalary.setText("");
				txtSalary.requestFocus();
				return;
			}
		
			
			DbHandler db = new DbHandler();
			db.addEmployee(id,name,salary);
			
			txtId.setText("");
			txtName.setText("");
			txtSalary.setText("");
			txtId.requestFocus();
	}	
				catch(Exception e)
			{
				try{
				id = Integer.parseInt(txtId.getText());}
				catch(NumberFormatException b)
				{
				JOptionPane.showMessageDialog(new JDialog(),"Enter integer only for id");
				txtId.setText("");
				txtId.requestFocus();
				return;
				}
				try{
				salary = Double.parseDouble(txtSalary.getText());}
				catch(NumberFormatException c)
				{
				JOptionPane.showMessageDialog(new JDialog(),"Enter integer only for salary");
				txtSalary.setText("");
				txtSalary.requestFocus();
				return;}
			}}
		});
		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				MainFrame a = new MainFrame();
				dispose();
			}
		});
		setLocation(600,200);
		setSize(300,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	

}


