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
	String[] pai= new String[108];
	String[] dizhupai= new String[8];
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
	
	
	public static void Paixu(int[] x, int begin, int end) {
		
		
		
		for (int i = begin; i < end; i++) {
			for (int j = i + 1; j <= end; j++) {
				if (x[i] > x[j]) {
					int temp = x[i];
					x[i] = x[j];
					x[j] = temp;
				}
			}
		}
	}
	public void Randomization() {
		
		  int n=0;
			for (int i = 0; i < 13; i++)//牌的点数
				for (int j = 0; j <8; j++) {//牌的花色
					pai[n++]=	 color[j] + num[i];}
			
			pai[n++]="b15";
			pai[n++]="b15";
			pai[n++]="r15";
			pai[n++]="r15";
			
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
			for (int i = 0; i < 4; i++) {  //洗牌
			maoPao(player[i]);
				
			
		}}
		
	public static void maoPao(String[] x) {
		
		for (int i = 0; i < 25; i++) {
			for (int j = i + 1; j < 25; j++) {
				if (Integer.parseInt(x[i].substring(1,x[i].length()))
						>Integer.parseInt(x[j].substring(1,x[j].length()))) {
					String temp = x[i];
					x[i] = x[j];
					x[j] = temp;
					
				}
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
		public void run() {
			SimpleDateFormat tempDate = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String nowtime;
			String s = "";
			while (true) {
				try {
					s = is.readUTF();
					/*
					 * 登录信息
					 */
					if (s.startsWith("login")) {
						id = is.readUTF();
						psw = is.readUTF();
						String result = dao.Check(id, psw);
						if (result.equals("2")) {
							/*
							 * 成功登录,发送到客户端信息
							 */
							dao.setState("1", id);
							os.writeUTF("2");
							/*
							 * 向客户端发送当前有哪些玩家在线
							 */
							os.writeUTF("otheronlineplayer");
							for (int i = 0; i < playerlist.size(); i++) {
								ServerThread th = playerlist.get(i);
								if (th != this && th.location.equals("hall"))
									os.writeUTF(th.id);
							}
							os.writeUTF("END1");
							/*
							 * 向客户端发送seat里面的玩家
							 */
							for (int i = 0; i < playerlist.size(); i++) {
								ServerThread th = playerlist.get(i);
								if (th.location.equals("seat")) {
									os.writeUTF((th.tablenum + "").toString());
									os.writeUTF((th.seatnum + "").toString());
									os.writeUTF(dao.getIcon(th.id));
								}
							}
							os.writeUTF("END2");
							os.writeUTF(dao.getScore(this.id) + "");
							os.writeUTF(dao.getIcon(this.id));
							/*
							 * 登陆后将自己加入到playerlist链表中，并向其他玩家发送进入房间信息
							 */
							playerlist.add(this);
							for (int i = 0; i < playerlist.size(); i++) {
								ServerThread th = playerlist.get(i);
								th.os.writeUTF("Newcomer");
								th.os.writeUTF(id);
							}

							// 服务器记录日志
							nowtime = tempDate.format(new java.util.Date());
							text.append(nowtime + "\n用户：" + id + "\nIP地址："
									+ youraddress.toString() + " 进入了房间\n");

						} else if (result.equals("1")) {
							os.writeUTF("1"); // 重复登录
							break;
						} else {
							os.writeUTF("0"); // 错误
							break;
						}
					}
					/*
					 * 注册信息
					 */
					if (s.equals("register")) {
						String name=is.readUTF();
						String psw=is.readUTF();
						if(!dao.Check(name)){
							dao.Adduser(name, psw);
							os.writeUTF("success");
						}else
							os.writeUTF("failed");
					}
					if (s.equals("gameinfo")) {
						String type = is.readUTF();// 不出、出牌或超时
						if (type.equals("jiaofen")) {
							for (int i = 0; i < playerlist.size(); i++) {
								ServerThread th = playerlist.get(i);
								if (th != this && th.tablenum == this.tablenum
										&& th.location.equals("seat")) {
									th.os.writeUTF("oneplayerjiaofen");
									th.os.writeUTF(this.seatnum + "");
								}
							}
						}
						else if (type.equals("buchu")) {
							for (int i = 0; i < playerlist.size(); i++) {
								ServerThread th = playerlist.get(i);
								if (th != this && th.tablenum == this.tablenum
										&& th.location.equals("seat")) {
									th.os.writeUTF("oneplayerbuchu");
									th.os.writeUTF(this.seatnum + "");
								}
							}
						} else if (type.equals("chaoshi")) {
							for (int i = 0; i < playerlist.size(); i++) {
								ServerThread th = playerlist.get(i);
								if (th != this && th.tablenum == this.tablenum
										&& th.location.equals("seat")) {
									th.os.writeUTF("oneplayerchaoshi");
									th.os.writeUTF(this.seatnum + "");
								}
							}
						} else if (type.equals("chupai")) {
							String info = is.readUTF();
							for (int i = 0; i < playerlist.size(); i++) {
								ServerThread th = playerlist.get(i);
								if (th != this && th.tablenum == this.tablenum
										&& th.location.equals("seat")) {
									th.os.writeUTF("oneplayerchupai");
									th.os.writeUTF(this.seatnum + "");
									th.os.writeUTF(info);
								}
							}
						}

					}
				} catch (IOException e) {
					try {
						socket.close();
					} catch (IOException e1) {
					}
	
					break;
				}
			}
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		int result = JOptionPane.showConfirmDialog(this, "确定退出？", "提示",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			dao.setAllState("0");
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new Server();
		
	}
}
