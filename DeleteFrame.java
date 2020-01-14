//deleteframe
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class DeleteFrame extends JFrame{

Container c;
JLabel lblDId;
JTextField txtDId;
JButton btnDeleteBack;
JButton btnDelete;
DeleteFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());

lblDId = new JLabel("EmpId");
txtDId = new JTextField(20);
btnDelete = new JButton("Delete");
btnDeleteBack = new JButton("Back");
c.add(lblDId);
c.add(txtDId);
c.add(btnDelete);
c.add(btnDeleteBack);

btnDelete.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
	int id;
	try{
		id= Integer.parseInt(txtDId.getText());
		if(id<=0)
			 {
				 JOptionPane.showMessageDialog(new JDialog()," Enter EmpId correctly");
				 txtDId.setText("");
				 txtDId.requestFocus();
				 return;
			 }
				DbHandler db = new DbHandler();
				db.deleteEmployee(id);
				txtDId.setText("");
				txtDId.requestFocus();
				
	}
	catch(NumberFormatException e)
	{
		JOptionPane.showMessageDialog(new JDialog(), "Enter integer only for id");
		txtDId.setText("");
		txtDId.requestFocus();
		return;
	}
}
});

btnDeleteBack.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent ae)
{
	MainFrame d = new MainFrame();
	dispose();
}
});

setTitle("Delete Window");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocation(600,200);
		setSize(300,400);
		setVisible(true);
}

}