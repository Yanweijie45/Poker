import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame implements ActionListener {
	JTextArea text;

	JButton close;

	ServerSocket server;

	Socket you;

	InetAddress youraddress;

	Dao dao;

	// 存放线程
	ArrayList<ServerThread> playerlist = new ArrayList<ServerThread>();

	// 花色
	String[] color = { "c", "d", "s", "h" };

	// 牌数字
	String[] num = { "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
			"14", "15" };

	// 随机分成的四组
	String[][] player = new String[4][25];//4个人

	Server() {//服务器GUI
		setTitle("服务器");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new ExitWindow());
		init();
		validate();
		dao = new Dao();
		startServer(6666);
	}

	class ExitWindow extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			dao.setAllState("0");
			System.exit(0);
		}
	}

	public void init() {
		text = new JTextArea();
		String myaddress = null;
		try {
			myaddress = InetAddress.getLocalHost().getHostAddress().toString(); // 服务器IP地址
		} catch (UnknownHostException e) {
		}
		text.setText("启动服务器...(IP地址: " + myaddress + ")\n");
		text.setEditable(false);
		add(text, BorderLayout.CENTER);
		JScrollPane scroll = new JScrollPane(text);
		add(scroll, BorderLayout.CENTER);
		close = new JButton("关闭服务器");
		add(close, BorderLayout.SOUTH);
		close.addActionListener(this);
	}

	
	
	
	/*
	 * 随机分成四组牌分发给玩家
	 */
	public void Randomization() {
		int[] a = new int[4];
		for (int i = 0; i < 4; i++)
			a[i] = 0;//标记每个人牌的数量
		for (int i = 0; i < 13; i++)//牌的点数
			for (int j = 0; j < 4; j++) {//牌的花色
				int row = (new Random().nextInt(100)) % 4;//随机的人
				if (a[row] < 25)
					player[row][a[row]++] = "" + color[j] + num[i];
				else {
					while (a[row] >= 25)//牌发满了就顺延续到下个人
						row = (row + 1) % 4;
					player[row][a[row]++] = "" + color[j] + num[i];
				}

			}
	}
