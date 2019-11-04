package game;

import java.awt.Color;
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
	
	JLabel pawderror,emptyerror,serverclose,userexit;
	
	JLabel error;
	

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
		//LGbutton.addMouseListener((MouseListener) this);

		LGbutton.addActionListener (new ActionListener ()
		{
			@Override
			public void actionPerformed ( ActionEvent e )
			{
				Login();
				setVisible (true);
				// 在swing中调用线程，必须如下：
				SwingUtilities.invokeLater (new Runnable()
				{
					
					@Override
					public void run ()
					{
						try
						{
							
							Thread.sleep (1000);
						}
						catch (InterruptedException e1)
						{
							e1.printStackTrace();
						}	
						repaint();
						error.setVisible(false);						
					}
				});
			}
		});
		
		
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
			Allarea.setVisible(false);
			setVisible(false);
		}
		if(e.getSource()==LGbutton) {
			Login();
		}
	}
	@SuppressWarnings("deprecation")
	public void Login() {
		if (Loginid.getText().equals("") || Password.getText().equals("")) {

			repaint();
			emptyerror = new JLabel("请输入账号和密码:");
			emptyerror.setBounds(150, 200, 170, 50);
			emptyerror.setForeground(Color.RED);
			Allarea.add(emptyerror);
			error = emptyerror;
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

				repaint();
				userexit = new JLabel("此账号已登录:");
				userexit.setBounds(150, 200, 170, 50);
				userexit.setForeground(Color.RED);
				Allarea.add(userexit);
				error = userexit;
			} else {

				repaint();
				pawderror = new JLabel("账号或密码错误");
				pawderror.setBounds(150, 200, 170, 50);
				pawderror.setForeground(Color.RED);
				Allarea.add(pawderror);
				error = pawderror;
			}
		} catch (Exception e) {

			repaint();
			serverclose = new JLabel("服务器尚未开启:");
			serverclose.setBounds(150, 200, 170, 50);
			serverclose.setForeground(Color.RED);
			Allarea.add(serverclose);
			error = serverclose;
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