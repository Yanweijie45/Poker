
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
	String[] color = { "c", "d", "s", "h" ,"c", "d", "s", "h"};

	// 牌数字
	String[] num = { "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
			"14", "15" };

	// 随机分成的四组
	String[][] player = new String[4][25];
	String[] pai= new String[108];
	String[] dizhupai= new String[8];
	Server() {
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
		
	  int n=0;
		for (int i = 0; i < 13; i++)//牌的点数
			for (int j = 0; j <8; j++) {//牌的花色
				pai[n++]=	 color[j] + num[i];}
		
		pai[n++]="b1";
		pai[n++]="b2";
		pai[n++]="r1";
		pai[n++]="r2";
		
		int [] ischoose=new int[108];
		for (int i = 0; i < 108; i++) 
			ischoose[i]=0;//标记是否被发
		
		
		

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 25; j++) {
			int row = (new Random().nextInt(108));
			if(ischoose[row]==0) {
			player[i][j]=pai[row];
			ischoose[row]=1;
			}
			else {
				while(ischoose[row]==1) {
				row = (new Random().nextInt(108));
				}//一定要发出这张派
				player[i][j]=pai[row];
					
				}
				
			}
			}	
		 
		for (int i = 0; i < 108; i++) {//找出地主牌
			int n1=0;
			if(ischoose[i]==0) {
				dizhupai[n1++]=pai[i];
				
			}
			
			
			
		}
	
	}
	
	
	public void startServer(int port) {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "服务器已启动！", "提示",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		while (true) {
			try {
				you = server.accept();
				youraddress = you.getInetAddress();
			} catch (IOException e) {
			}
			if (you != null) {
				new ServerThread(you, youraddress).start();
			}
		}
	}

	class ServerThread extends Thread {
		Socket socket;

		InetAddress address;

		DataInputStream is = null;

		DataOutputStream os = null;

		String id = null, psw = null; // 用户信息

		int tablenum; // 用户进入的table号

		int seatnum; // table的seat号

		/*
		 * 该线程的位置属性,"hall"表示大厅线程，"seat"表示seat线程,
		 * 一个player刚登陆时启动一个线程，由于socket不能共享，故在
		 * 进入seat后的线程和刚刚进入大厅的线程标志区别就在location上
		 */
		String location = null;

		boolean readyflag = false; // 游戏是否准备好

		ServerThread(Socket s, InetAddress addr) {
			tablenum = -1;
			seatnum = -1;
			socket = s;
			location = "hall";
			address = addr;
			try {
				is = new DataInputStream(socket.getInputStream());
				os = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
			}
		}
