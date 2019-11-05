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
	

	JButton LGbutton,RGbutton;//��¼��ע�ᰴť

	String serverIP;

	Socket s = null;

	DataInputStream is = null;

	DataOutputStream os = null;

	public GameOptions(String IP) {
		serverIP = IP;
		initGUI();
	}

	public void initGUI() {
		setTitle("��Ϸ����");
		setIconImage(new ImageIcon("pics\\Rush.png").getImage());
		setBounds(450, 140, 400, 400);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Allarea = new PanelArea();
		Allarea.setImageFile(new File("pics\\login_bg.png"));
		add(Allarea);
		Allarea.setLayout(null);
		lbid = new JLabel("����Ϸ����XXX�Ŷ���������ð�ؾ��� ");
		lbid.setBounds(70, 60, 250, 50);
		lbpsw1 = new JLabel("���˶�������һ�����ٽ��˵���Ϸ��ϣ��");
		lbpsw1.setBounds(70, 80, 250, 50);
		lbpsw2 = new JLabel("������Ϸ�Ĺ������ܹ�������");
		lbpsw2.setBounds(70, 100, 250, 50);
		
		music = new JLabel("��Ч��");
		music.setBounds(70, 180, 250, 50);
		

		group = new ButtonGroup();
		close = new JRadioButton("�ر�");
		close.setBounds(140, 180, 100, 50);
		close.setContentAreaFilled(false);
		
		open = new JRadioButton("����");
		open.setBounds(220, 180, 100, 50);
		open.setContentAreaFilled(false);
		
		Judge();
		
		group.add(close);
		group.add(open);
		
		
		LGbutton = new JButton("ȡ��");//��¼��ť
		RGbutton = new JButton("��������");//ע�ᰴť
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
	private void Judge() {
		// TODO Auto-generated method stub
		try {
			s = new Socket(serverIP, 6666);
			is = new DataInputStream(s.getInputStream());
			os = new DataOutputStream(s.getOutputStream());
			os.writeUTF("GameOption1");
			String state = is.readUTF();
			if(state.equals("close")) {
				close.setSelected(true);
				close.setSelected(false);
			}
			else {
				open.setSelected(true);
				open.setSelected(false);
			}
			
		} catch (Exception e) {

		}	
		repaint();		
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==RGbutton){
			JOptionPane.showMessageDialog(this, "����ɹ���", "��ʾ",
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