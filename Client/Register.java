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

public class Register extends JFrame implements MouseListener {
	PanelArea Allarea;

	JTextField Loginid;

	JPasswordField Password1, Password2;

	JLabel lbid, lbpsw1, lbpsw2;
	
	JLabel incomplerror,passwerror,usererror,servererror;
	
	JLabel error;

	JButton RGbutton;

	String serverIP;

	Socket s = null;

	DataInputStream is = null;

	DataOutputStream os = null;

	public Register(String IP) {
		serverIP = IP;
		initGUI();
	}

	public void initGUI() {
		setTitle("�û�ע��");
		setIconImage(new ImageIcon("pics\\Rush.png").getImage());
		setBounds(450, 140, 400, 400);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Allarea = new PanelArea();
		Allarea.setImageFile(new File("pics\\regist.png"));
		add(Allarea);

		Allarea.setLayout(null);
		lbid = new JLabel("�� ��:");
		lbid.setBounds(80, 80, 100, 50);
		lbpsw1 = new JLabel("��    ��:");
		lbpsw1.setBounds(80, 140, 100, 50);
		lbpsw2 = new JLabel("�ظ�����:");
		lbpsw2.setBounds(80, 200, 100, 50);
		Loginid = new JTextField();
		Loginid.setBounds(140, 90, 180, 25);
		Password1 = new JPasswordField(15);
		Password1.setEchoChar('*');
		Password1.setBounds(140, 150, 180, 25);
		Password2 = new JPasswordField(15);
		Password2.setEchoChar('*');
		Password2.setBounds(140, 210, 180, 25);

		RGbutton = new JButton("ȷ��ע��");
		RGbutton.setContentAreaFilled(false);
		RGbutton.setBounds(150, 300, 100, 40);
		//RGbutton.addMouseListener(this);
		//RGbutton.addActionListener(this);
		RGbutton.addActionListener (new ActionListener ()
		{
			@Override
			public void actionPerformed ( ActionEvent e )
			{
				Regist();
				setVisible (true);
				// ��swing�е����̣߳��������£�
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
		

		Allarea.add(lbid);
		Allarea.add(lbpsw1);
		Allarea.add(lbpsw2);
		Allarea.add(Loginid);
		Allarea.add(Password1);
		Allarea.add(Password2);
		Allarea.add(RGbutton);
	}
	
	public void Regist() {
		if (Loginid.getText().trim().equals("")
				|| Password1.getText().trim().equals("")
				||Password2.getText().trim().equals("")) {

			incomplerror = new JLabel("��Ϣ��д������");
			incomplerror.setBounds(150, 240, 170, 50);
			incomplerror.setForeground(Color.RED);
			Allarea.add(incomplerror);
			error = incomplerror;
			repaint();
			return;
			
		}else if (!Password1.getText().trim().equals(
				Password2.getText().trim())) {
			JOptionPane.showMessageDialog(this, "�������벻һ�£�", "������ʾ",
					JOptionPane.WARNING_MESSAGE);
			repaint();
			passwerror = new JLabel("�������벻һ��");
			passwerror.setBounds(150, 240, 170, 50);
			passwerror.setForeground(Color.RED);
			Allarea.add(passwerror);
			error = passwerror;
			Password1.setText(null);
			Password2.setText(null);
			return;
		}
		try {
			s = new Socket(serverIP, 6666);
			is = new DataInputStream(s.getInputStream());
			os = new DataOutputStream(s.getOutputStream());
			os.writeUTF("register");
			os.writeUTF(Loginid.getText().trim());
			os.writeUTF(Password1.getText().trim());
			String result=is.readUTF();
			if(result.equals("success")){
				JOptionPane.showMessageDialog(this, "ע��ɹ���", "������ʾ",
						JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				new Login(serverIP);
				
			}else 
				
				JOptionPane.showMessageDialog(this, "�û����Ѵ��ڣ�", "������ʾ",
						JOptionPane.WARNING_MESSAGE);
			    repaint();
			    usererror = new JLabel("�û����Ѵ���");
			    usererror.setBounds(150, 240, 170, 50);
			    usererror.setForeground(Color.RED);
			    Allarea.add(usererror);
			    error = usererror;


		} catch (Exception exp) {
			JOptionPane.showMessageDialog(this, "δ���ӷ�������", "������ʾ",
					JOptionPane.WARNING_MESSAGE);
			repaint();
			servererror = new JLabel("δ���ӷ�����");
			servererror.setBounds(150, 240, 170, 50);
			servererror.setForeground(Color.RED);
			Allarea.add(servererror);
			error = servererror;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==RGbutton) {
			Regist();
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
