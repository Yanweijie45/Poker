package x;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Welcome extends JFrame implements MouseListener {
	PanelArea allarea;

	JLabel loginarea, login, exit, close, register, LOGIN, SET, ipsetarea;

	JTextField loginid, serverip;

	JPasswordField password;

	String serverIP = null;

	Socket s = null;

	DataInputStream is = null;

	DataOutputStream os = null;

	Welcome() {
		super("Rush");
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

	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == exit) {
			loginarea.setVisible(false);
		}
	}

	public void mouseEntered(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		if (e.getSource() == LOGIN) {
			LOGIN.setBounds(215,175,156,36);
			LOGIN.setIcon(new ImageIcon("pics//登录on.png"));
		}
		if (e.getSource() == SET) {
			SET.setBounds(215,245,156,36);
			SET.setIcon(new ImageIcon("pics//游戏设置on.png"));
		}
	}

	public void mouseExited(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		if (e.getSource() == LOGIN) {
			LOGIN.setBounds(220,180,156,36);
			LOGIN.setIcon(new ImageIcon("pics//登录.png"));
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