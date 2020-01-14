//updateFrame
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.*;
class UpdateFrame extends JFrame{

Container c;
JLabel lblUId,lblUName,lblUSalary;
JTextField txtUId,txtUName,txtUSalary;
JButton btnUpdateSave,btnUpdateBack;

public UpdateFrame()
{
	c = getContentPane();
	c.setLayout(new FlowLayout());
	lblUId = new JLabel("EmpId");
	lblUName = new JLabel("Name");
	lblUSalary = new JLabel("Salary");
	txtUId = new JTextField(20);
	txtUName = new JTextField(20);
	txtUSalary = new JTextField(20);
	btnUpdateBack = new JButton("Back");
	btnUpdateSave = new JButton("Update");
	
	c.add(lblUId);
	c.add(txtUId);
	c.add(lblUName);
	c.add(txtUName);
	c.add(lblUSalary);
	c.add(txtUSalary);
	c.add(btnUpdateSave);
	c.add(btnUpdateBack);
	
	btnUpdateBack.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ae)
	{
		MainFrame u = new MainFrame();
		dispose();
	}
	});
	btnUpdateSave.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ae)
	{
		int id;
		String name;
		double salary;
		try{
			
		  id = Integer.parseInt(txtUId.getText());
		 if(id<=0)
			 {
				 JOptionPane.showMessageDialog(new JDialog()," Enter EmpId correctly");
				 txtUId.setText("");
				 txtUId.requestFocus();
				 return;
			 }
		
		name = txtUName.getText();
		 if(name.length() <2)
			 {
				 JOptionPane.showMessageDialog(new JDialog(), "Name should contain at least two letters");
				 txtUName.setText("");
				 txtUName.requestFocus();
				 return;
			 }
		if(name.isEmpty())
			{
				JOptionPane.showMessageDialog(new JDialog(), " Enter name,please");
				txtUName.setText("");
				txtUName.requestFocus();
				return;
			}
			//pattern is used to define a pattern for regex engine
		Pattern p = Pattern.compile("([0-9])");
		//it creates a matcher that matches input with given pattern
		Matcher m = p.matcher(name);
		
		
//find() = finds the next expression that matches the pattern
//return boolean value
		if(m.find()){
		   JOptionPane.showMessageDialog(new JDialog(), " Enter characters only");
		   txtUName.setText("");
		   txtUName.requestFocus();
		   return;
		}
		salary = Double.parseDouble(txtUSalary.getText());
				if(salary < 8000.00)
			{
				JOptionPane.showMessageDialog(new JDialog(), " Minimum salary should be 8000");
				txtUSalary.setText("");
				txtUSalary.requestFocus();
				return;
			}
		DbHandler db = new DbHandler();
		db.updateEmployee(id,name,salary);
		txtUId.setText("");
		txtUName.setText("");
		txtUSalary.setText("");
		txtUId.requestFocus();
	}
		catch(Exception e)
			{
				try{
				id = Integer.parseInt(txtUId.getText());
				}
				catch(NumberFormatException b)
				{
				JOptionPane.showMessageDialog(new JDialog(),"Enter integer only for Empid");
				txtUId.setText("");
				txtUId.requestFocus();
				return;
				}
				try{
					salary = Double.parseDouble(txtUSalary.getText());
				}
				catch(NumberFormatException c)
				{
				JOptionPane.showMessageDialog(new JDialog(), " Enter integer only for salary");
				txtUSalary.setText("");
				txtUSalary.requestFocus();
				return;
				}
			}
		
	}});
	setTitle("Update Window");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocation(600,200);
		setSize(300,400);
	setVisible(true);
}


}