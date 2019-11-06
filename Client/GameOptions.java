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

public class GameOptions extends JFrame implements MouseListener {
	PanelArea Allarea;

	JTextField Loginid;

	JPasswordField Password;

	JLabel lbid, lbpsw1, lbpsw2 ;
	
	JLabel music;
	
	ButtonGroup group;
	JRadioButton close,open;
	

	JButton LGbutton,RGbutton;//登录、注册按钮

	String serverIP;

	Socket s = null;

	DataInputStream is = null;

	DataOutputStream os = null;

	public GameOptions(String IP) {
		serverIP = IP;
		initGUI();
	}

	public void initGUI() {
		setTitle("游戏设置");
		setIconImage(new ImageIcon("pics\\Rush.png").getImage());
		setBounds(450, 140, 400, 400);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Allarea = new PanelArea();
		Allarea.setImageFile(new File("pics\\login_bg.png"));
		add(Allarea);
		Allarea.setLayout(null);
		lbid = new JLabel("本游戏是由XXX团队制作，仿冒必究！ ");
		lbid.setBounds(70, 60, 250, 50);
		lbpsw1 = new JLabel("四人斗地主是一个老少皆宜的游戏，希望");
		lbpsw1.setBounds(70, 80, 250, 50);
		lbpsw2 = new JLabel("你在游戏的过程中能够玩的愉快");
		lbpsw2.setBounds(70, 100, 250, 50);
		
		music = new JLabel("音效：");
		music.setBounds(70, 180, 250, 50);
		

		group = new ButtonGroup();
		close = new JRadioButton("关闭");
		close.setBounds(140, 180, 100, 50);
		close.setContentAreaFilled(false);
		
		open = new JRadioButton("开启");
		open.setBounds(220, 180, 100, 50);
		open.setContentAreaFilled(false);
		
	
		
		group.add(close);
		group.add(open);
		
		
		LGbutton = new JButton("取消");//登录按钮
		RGbutton = new JButton("保存设置");//注册按钮
		LGbutton.setBounds(60, 280, 60, 40);
		LGbutton.setContentAreaFilled(false);
		LGbutton.addMouseListener((MouseListener) this);
	
		RGbutton.setBounds(230, 280, 100, 40);
		RGbutton.setContentAreaFilled(false);
		RGbutton.addMouseListener((MouseListener) this);

		Allarea.add(LGbutton);
		Allarea.add(RGbutton);

		Allarea.add(close);
		Allarea.add(open);
		
		Allarea.add(music);
		
		Allarea.add(lbid);
		Allarea.add(lbpsw1);
		Allarea.add(lbpsw2);
		Allarea.add(Loginid);
		Allarea.add(Password);
		
		
	}


	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==RGbutton){
			JOptionPane.showMessageDialog(this, "保存成功！", "提示",
			JOptionPane.WARNING_MESSAGE);
			save();
			Allarea.setVisible(false);
			setVisible(false);
		}
		if(e.getSource()==LGbutton) {
			this.dispose();
		}
	}
	@SuppressWarnings("deprecation")
	public void save() {
		try {
			s = new Socket(serverIP, 6666);
			is = new DataInputStream(s.getInputStream());
			os = new DataOutputStream(s.getOutputStream());
			os.writeUTF("GameOption2");
			if(open.isSelected()==true) {
				os.writeUTF("1");
			}
			else {
				os.writeUTF("2");
			}		
		} catch (Exception e) {

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
