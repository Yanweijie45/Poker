package x;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class Login extends JFrame implements ActionListener,MouseListener {
	PanelArea Allarea;

	JTextField Loginid;

	JPasswordField Password1, Password2;

	JLabel lbid, lbpsw1, lbpsw2 ;
	
	

	JButton LGbutton,RGbutton;//µÇÂ¼¡¢×¢²á°´Å¥

	String serverIP;

	Socket s = null;

	DataInputStream is = null;

	DataOutputStream os = null;

	public Login(String IP) {
		serverIP = IP;
		initGUI();
	}

	public void initGUI() {
		setTitle("ÓÃ»§µÇÂ¼");
		setIconImage(new ImageIcon("pics\\Rush.png").getImage());
		setBounds(450, 140, 400, 400);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Allarea = new PanelArea();
		Allarea.setImageFile(new File("pics\\login_bg.png"));
		add(Allarea);
		Allarea.setLayout(null);
		lbid = new JLabel("ÕË ºÅ:");
		lbid.setBounds(70, 100, 100, 50);
		lbpsw1 = new JLabel("ÃÜ    Âë:");
		lbpsw1.setBounds(70, 160, 100, 50);
		Loginid = new JTextField();
		Loginid.setBounds(130, 110, 180, 25);
		Password1 = new JPasswordField(15);
		Password1.setEchoChar('*');
		Password1.setBounds(130, 170, 180, 25);
		LGbutton = new JButton("µÇÂ¼");//µÇÂ¼°´Å¥
		RGbutton = new JButton("×¢²á");//×¢²á°´Å¥
		LGbutton.setBounds(100, 280, 60, 40);
		LGbutton.setContentAreaFilled(false);

		RGbutton.setBounds(250, 280, 60, 40);
		RGbutton.setContentAreaFilled(false);
		RGbutton.addMouseListener((MouseListener) this);

		Allarea.add(LGbutton);
		Allarea.add(RGbutton);
		


		Allarea.add(lbid);
		Allarea.add(lbpsw1);
		Allarea.add(Loginid);
		Allarea.add(Password1);
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==RGbutton){
			new Register(serverIP);
		}
	}


	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
