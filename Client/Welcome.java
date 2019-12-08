package game;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Welcome extends JFrame implements MouseListener {
	MusicBg bg;
	
	PanelArea allarea;
	
	PanelArea loginarea;

	JLabel  login, exit, close, register, LOGIN, SET, ipsetarea;

	JTextField loginid, serverip;

	JPasswordField password;

	String serverIP = null;

	Socket s = null;

	DataInputStream is = null;

	DataOutputStream os = null;

	Welcome() {
		super("四人斗地主");
		setIconImage(new ImageIcon("pics\\rush.png").getImage());//程序的图标
		setSize(600, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		allarea = new PanelArea();
		allarea.setImageFile(new File("pics\\welcomebg.jpg"));
		add(allarea);
		allarea.setLayout(null);

		LOGIN = new JLabel();
		LOGIN.setBounds(220,180,156,36);
		LOGIN.setIcon(new ImageIcon("pics//登录.png"));
		LOGIN.addMouseListener(this);
		SET = new JLabel();
		SET.setBounds(220,250,156,36);
		SET.setIcon(new ImageIcon("pics//游戏设置.png"));
		SET.addMouseListener(this);

		allarea.add(LOGIN);
		allarea.add(SET);

		repaint();

		// 初始化为
		serverIP = "127.0.0.1";
	}

	/*
	 * 初始化游戏设置区域
	 */
	 void initipsetarea() {
		ipsetarea = new JLabel();
		ipsetarea.setIcon(new ImageIcon("pics//服务器IP.png"));
		ipsetarea.setBounds(100, 200, 204, 86);
		allarea.add(ipsetarea);
		ipsetarea.setLayout(null);
		serverip = new JTextField();
		serverip.setBounds(87, 18, 100, 25);
		serverip.setText("127.0.0.1");
		ipsetarea.add(serverip);
		close = new JLabel();
		close.setIcon(new ImageIcon("pics//button//close.png"));
		close.setBounds(80, 57, 50, 20);
		close.addMouseListener(this);
		ipsetarea.add(close);
		ipsetarea.setVisible(false);
	}

	/*
	 * 以下皆为鼠标操作
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == LOGIN) {
			if(bg.readbg().equals("1")) {
				Playsound.play("audio//click.wav");
			}
			new Login(serverIP);	
			setVisible(false);
			allarea.setVisible(false);
		}
		if (e.getSource() == SET) {
			
			bg.createbg();
			if(bg.readbg().equals("1")) {
				Playsound.play("audio//click.wav");
			}
			new GameOptions(serverIP);
		}
		if (e.getSource() == close) {
			// 此处获得服务器IP地址
			Playsound.play("audio//click.wav");
			serverIP = serverip.getText().trim();
			ipsetarea.setVisible(false);
		}
		if(e.getSource()==register){
			new Register(serverIP);
		}
	}

	public void mouseEntered(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		if (e.getSource() == login) {
			login.setIcon(new ImageIcon("pics//button//loginbuttonon.png"));
		}

		if (e.getSource() == SET) {
			SET.setBounds(215,245,156,36);
			SET.setIcon(new ImageIcon("pics//游戏设置on.png"));
		}

	}

	public void mouseExited(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		if (e.getSource() == login) {
			login.setIcon(new ImageIcon("pics//button//loginbutton.png"));
		}
		if (e.getSource() == SET) {
			SET.setBounds(220,250,156,36);
			SET.setIcon(new ImageIcon("pics//游戏设置.png"));
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public static void main(String[] args) {
		new Welcome();
	}
}
