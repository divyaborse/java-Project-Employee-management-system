//ViewFrame
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class ViewFrame extends JFrame{

Container c;
JTextArea txtView;
JButton btnBack;

public ViewFrame()
	{
		c = getContentPane();
		FlowLayout f = new FlowLayout();
		c.setLayout(f);
		txtView = new JTextArea(5,30);
		btnBack = new JButton("Back");
		txtView.setEditable(false);
		c.add(txtView);
		c.add(btnBack);
		DbHandler db = new DbHandler();
		String data = db.viewEmployee();
		if(data.length() ==0)
			txtView.setText("no data");
		else
			txtView.setText(data);

		
		btnBack.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		{
		MainFrame v = new MainFrame();
		dispose();
		}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(600,200);
		setSize(400,400);
		setVisible(true);
	}
}

