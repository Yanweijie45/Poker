package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Register extends JFrame implements ActionListener {
	PanelArea area;
	JTextField Loginid;
	JPasswordField Password;
	JLabel label1, label2;
	JButton OK;
	String serverIP;
	Socket s = null;
	DataInputStream is = null;
	DataOutputStream os = null;

	public Register(String IP) {
		serverIP = IP;
		Registerlabel();
	}

	public void Registerlabel() {
		setTitle("注册");
		setIconImage(new ImageIcon("pics\\Rush.png").getImage());
		setBounds(450, 140, 400, 400);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		area = new PanelArea();
		area.setImageFile(new File("pics\\bgxx.png"));
		add(area);
		area.setLayout(null);
		label1 = new JLabel("账 号:");
		label1.setBounds(80, 80, 100, 50);
		label2 = new JLabel("密    码:");
		label2.setBounds(80, 140, 100, 50);
		Loginid = new JTextField();
		Loginid.setBounds(140, 90, 180, 25);
		Password = new JPasswordField(15);
		Password.setEchoChar('*');
		Password.setBounds(140, 150, 180, 25);

		OK = new JButton("确认");
		OK.setContentAreaFilled(false);
		OK.addActionListener(this);
		OK.setBounds(150, 300, 100, 40);

		area.add(label1);
		area.add(label2);
		area.add(Loginid);
		area.add(Password);
		area.add(OK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == OK) {
			if (Loginid.getText().trim().equals("")
					|| Password.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "信息填写不完整！", "友情提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			} 
			try {
				s = new Socket(serverIP, 6666);
				is = new DataInputStream(s.getInputStream());
				os = new DataOutputStream(s.getOutputStream());
				os.writeUTF("register");
				os.writeUTF(Loginid.getText().trim());
				os.writeUTF(Password.getText().trim());
				String result=is.readUTF();
				if(result.equals("success")){
					JOptionPane.showMessageDialog(this, "注册成功！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				}else
					JOptionPane.showMessageDialog(this, "用户名已存在！", "友情提示",
							JOptionPane.WARNING_MESSAGE);
			} catch (Exception exp) {
				JOptionPane.showMessageDialog(this, "未连接服务器！", "友情提示",
						JOptionPane.WARNING_MESSAGE);
			}
		}

	}
}
