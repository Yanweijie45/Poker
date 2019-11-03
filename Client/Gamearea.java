import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gamearea extends JFrame implements MouseListener, Runnable,
		ActionListener {
	PanelArea allarea, gamearea; // 总体区域，游戏区域

	JLabel exit;

	JLabel[] playerinfo = new JLabel[4];

	JLabel[] leftcardnum = new JLabel[4]; // 剩余张数

	int myleftcard = 25;

	JLabel timearea; // 时间显示区域

	JLabel[] buchuarea = new JLabel[4]; // 不出

	JLabel[] clock = new JLabel[4]; // 时钟,一共设置四个

	JLabel[] role = new JLabel[4]; // 角色区域

	JButton start, chupai, buchu; // 开始、出牌、提示按钮

	JLabel[] reready = new JLabel[4];

	JLabel[] ready = new JLabel[4];

	JLabel[] rolefropic = new JLabel[4];

	Card[] card = new Card[26];

	Card[][] cardfront = new Card[4][25]; // player出的牌

	Startanimation startanimation;

	Timepass timepass;

	boolean[] cardclick = new boolean[26]; // 是否已点击牌

	boolean[] cardsend = new boolean[26]; // 牌是否出了

	int firstchupai = -1;// 先出牌的seat号

	String[] CARDS = new String[2]; // 用于接收13张牌

	Point[] endpos = new Point[26];

	Point[] startpos = new Point[26];

	boolean startflag = false; // 游戏是否开始

	boolean Isnext = false; // 是否是下一个人出牌了，在自己点击出牌或不出的时候，Isnext置true

	boolean Isfirst = true; // 是否是刚进来的第一盘

	String playerID = null; // 自己的ID

	String[] cardinfo = new String[25]; // 记录上一个出牌player的牌信息

	String[] mycard = new String[25]; // 记录自己出牌信息

	String[] myoldcard = new String[25]; // 记录自己前一论出的牌

	int tablenum, seatnum;// 桌子号，seat号

	Socket socket = null;

	String serverIP = null;

	DataInputStream is = null;

	DataOutputStream os = null;

	public Gamearea(String _playerID, String _serverIP, int _tn, int _sn) {
		playerID = _playerID;
		serverIP = _serverIP;
		tablenum = _tn;
		seatnum = _sn;
		try {
			socket = new Socket(serverIP, 6666);
			is = new DataInputStream(socket.getInputStream());
			os = new DataOutputStream(socket.getOutputStream());
			os.writeUTF("enterseat");
			os.writeUTF(playerID);
			os.writeUTF(tablenum + "");
			os.writeUTF(seatnum + "");
		} catch (IOException e) {
		}
		for (int i = 0; i < 25; i++) {// 初始化
			cardinfo[i] = "";
			mycard[i] = "";
			myoldcard[i] = "";
		}
		initGUI();
		startanimation = new Startanimation();
		timepass = new Timepass();
		new Thread(this).start();
	}
