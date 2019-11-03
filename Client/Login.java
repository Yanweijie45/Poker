package game;

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

public class Login extends JFrame implements MouseListener {
	PanelArea Allarea;

	JTextField Loginid;

	JPasswordField Password;

	JLabel lbid, lbpsw1, lbpsw2 ;
	

	JButton LGbutton,RGbutton;//登录、注册按钮

	String serverIP;

	Socket s = null;

	DataInputStream is = null;

	DataOutputStream os = null;

	public Login(String IP) {
		serverIP = IP;
		initGUI();
	}

	public void initGUI() {
		setTitle("用户登录");
		setIconImage(new ImageIcon("pics\\Rush.png").getImage());
		setBounds(450, 140, 400, 400);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Allarea = new PanelArea();
		Allarea.setImageFile(new File("pics\\login_bg.png"));
		add(Allarea);
		Allarea.setLayout(null);
		lbid = new JLabel("账 号:");
		lbid.setBounds(70, 100, 100, 50);
		lbpsw1 = new JLabel("密    码:");
		lbpsw1.setBounds(70, 160, 100, 50);
		Loginid = new JTextField();
		Loginid.setBounds(130, 110, 180, 25);
		Password = new JPasswordField(15);
		Password.setEchoChar('*');
		Password.setBounds(130, 170, 180, 25);
		LGbutton = new JButton("登录");//登录按钮
		RGbutton = new JButton("注册");//注册按钮
		LGbutton.setBounds(100, 280, 60, 40);
		LGbutton.setContentAreaFilled(false);
		LGbutton.addMouseListener((MouseListener) this);
		
		RGbutton.setBounds(250, 280, 60, 40);
		RGbutton.setContentAreaFilled(false);
		RGbutton.addMouseListener((MouseListener) this);
		
		Allarea.add(LGbutton);
		Allarea.add(RGbutton);
		
		Allarea.add(lbid);
		Allarea.add(lbpsw1);
		Allarea.add(Loginid);
		Allarea.add(Password);
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==RGbutton){
			new Register(serverIP);
		}
		if(e.getSource()==LGbutton) {
			Login();
		}
	}
	@SuppressWarnings("deprecation")
	public void Login() {
		if (Loginid.getText().equals("") || Password.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "请输入账号和密码！", "提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		try {
			s = new Socket(serverIP, 6666);
			is = new DataInputStream(s.getInputStream());
			os = new DataOutputStream(s.getOutputStream());
			os.writeUTF("login");
			os.writeUTF(Loginid.getText().trim());
			os.writeUTF(Password.getText().trim());
			String state = is.readUTF();
			if (state.equals("2")) {
				new GameRoom(Loginid.getText().trim(), serverIP, s, is, os);// 启动游戏大厅
				this.dispose();
			} else if (state.equals("1")) {
				JOptionPane.showMessageDialog(this, "此账号已登录！", "提示",
						JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "账号或密码错误！", "提示",
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "服务器尚未开启！", "提示",
					JOptionPane.WARNING_MESSAGE);
		}
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