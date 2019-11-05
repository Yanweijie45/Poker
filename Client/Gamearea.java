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

	public void initGUI() {
		setTitle("Rush");
		setIconImage(new ImageIcon("pics\\rush.png").getImage());
		setSize(940, 700);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new ExitWindow());

		allarea = new PanelArea();
		allarea.setImageFile(new File("pics\\bgxx.png"));
		add(allarea);
		allarea.setLayout(null);
		gamearea = new PanelArea();
		gamearea.setImageFile(new File("pics\\bgx.png"));
		gamearea.setBounds(0, 0, 730, 680);
		allarea.add(gamearea);

		gamearea.setLayout(null);
		start = new JButton();
		start.setIcon(new ImageIcon("pics\\button\\startbutton.png"));
		start.setBounds(330, 570, 66, 34);
		start.setContentAreaFilled(false);
		start.setBorderPainted(false);
		start.addMouseListener((MouseListener) this);
		/*
		 * three buttons:chupai、buchu、tishi
		 */
		chupai = new JButton();
		chupai.setBounds(330, 450, 55, 22);
		chupai.setIcon(new ImageIcon("pics\\button\\出牌.png"));
		chupai.setContentAreaFilled(false);
		chupai.setBorderPainted(false);
		chupai.addMouseListener((MouseListener) this);
		
		buchu = new JButton();
		buchu.setBounds(400, 450, 55, 22);
		buchu.setIcon(new ImageIcon("pics\\button\\不出.png"));
		buchu.setContentAreaFilled(false);
		buchu.setBorderPainted(false);
		buchu.addMouseListener((MouseListener) this);
		
		no = new JButton();
		no.setText("不叫");
		no.setBounds(260, 450, 55, 22);
		no.setContentAreaFilled(true);
		no.setBorderPainted(false);
		no.addMouseListener((MouseListener) this);
		
		one = new JButton();
		one.setText("一分");
		one.setBounds(330, 450, 55, 22);
		one.setContentAreaFilled(true);
		one.setBorderPainted(false);
		one.addMouseListener((MouseListener) this);
		
		two = new JButton();
		two.setText("两分");
		two.setBounds(400, 450, 55, 22);
		two.setContentAreaFilled(true);
		two.setBorderPainted(false);
		two.addMouseListener((MouseListener) this);
		
		three = new JButton();
		three.setText("两分");
		three.setBounds(470, 450, 55, 22);
		three.setContentAreaFilled(true);
		three.setBorderPainted(false);
		three.addMouseListener((MouseListener) this);

		chupai.setVisible(false);
		buchu.setVisible(false);
		no.setVisible(false);
		one.setVisible(false);
		two.setVisible(false);
		three.setVisible(false);
		gamearea.add(start);
		gamearea.add(chupai);
		gamearea.add(buchu);
		gamearea.add(no);
		gamearea.add(one);
		gamearea.add(two);
		gamearea.add(three);
		for (int i = 0; i < 4; i++) {
			role[i] = new JLabel();
			role[i].setIcon(new ImageIcon("pics\\role\\rolebg_x.png"));
			gamearea.add(role[i]);
			role[i].setLayout(null);
			rolefropic[i] = new JLabel();
			rolefropic[i].setBounds(-5, -20, 130, 210);
			ready[i] = new JLabel();
			ready[i].setBounds(65, 135, 100, 40);
			playerinfo[i] = new JLabel();
			playerinfo[i].setBounds(30, 160, 100, 30);
			playerinfo[i].setForeground(Color.white);
			leftcardnum[i] = new JLabel();
			leftcardnum[i].setBounds(30, 190, 100, 20);
			leftcardnum[i].setForeground(Color.white);
			leftcardnum[i].setVisible(false);
			role[i].add(ready[i]);
			role[i].add(rolefropic[i]);
			role[i].add(playerinfo[i]);
			role[i].add(leftcardnum[i]);
		}
		role[seatnum].setBounds(300, 350, 130, 210);
		role[(seatnum + 1) % 4].setBounds(500, 190, 130, 210);
		role[(seatnum + 2) % 4].setBounds(300, 30, 130, 210);
		role[(seatnum + 3) % 4].setBounds(100, 190, 130, 210);
		rolefropic[seatnum].setIcon(new ImageIcon("pics//role//boyshow2.gif"));
		playerinfo[seatnum].setText("用户ID:" + playerID);

		for (int i = 0; i < 4; i++) {
			reready[i] = new JLabel();
			gamearea.add(reready[i]);
		}
		reready[seatnum].setBounds(330, 420, 66, 34);
		reready[(seatnum + 1) % 4].setBounds(550, 250, 66, 34);
		reready[(seatnum + 2) % 4].setBounds(330, 200, 66, 34);
		reready[(seatnum + 3) % 4].setBounds(180, 250, 66, 34);

		timearea = new JLabel();
		timearea.setBounds(640, 8, 80, 80);
		gamearea.add(timearea);

		for (int i = 0; i < 4; i++) {
			clock[i] = new JLabel();
			clock[i].setVisible(false);
			clock[i].setIcon(new ImageIcon("pics//time//clock.png"));
			gamearea.add(clock[i]);
		}
		clock[seatnum].setBounds(145, 400, 40, 43);
		clock[(seatnum + 1) % 4].setBounds(645, 140, 40, 43);
		clock[(seatnum + 2) % 4].setBounds(150, 20, 40, 43);
		clock[(seatnum + 3) % 4].setBounds(45, 140, 40, 43);

		/*
		 * 三个player出的牌
		 */
		for (int i = 0; i < 13; i++) {
			cardfront[(seatnum + 1) % 4][12 - i] = new Card();
			cardfront[(seatnum + 1) % 4][12 - i].setVisible(false);
			cardfront[(seatnum + 1) % 4][12 - i].setBounds(570 - i * 15, 250,
					65, 100);
			gamearea.add(cardfront[(seatnum + 1) % 4][12 - i]);
			cardfront[(seatnum + 1) % 4][12 - i].addMouseListener(this);
		}
		for (int i = 0; i < 13; i++) {
			cardfront[(seatnum + 2) % 4][12 - i] = new Card();
			cardfront[(seatnum + 2) % 4][12 - i].setVisible(false);
			cardfront[(seatnum + 2) % 4][12 - i].setBounds(440 - i * 15, 140,
					65, 100);
			gamearea.add(cardfront[(seatnum + 2) % 4][12 - i]);
			cardfront[(seatnum + 2) % 4][12 - i].addMouseListener(this);
		}
		for (int i = 0; i < 13; i++) {
			cardfront[(seatnum + 3) % 4][12 - i] = new Card();
			cardfront[(seatnum + 3) % 4][12 - i].setVisible(false);
			cardfront[(seatnum + 3) % 4][12 - i].setBounds(280 - i * 15, 245,
					65, 100);
			gamearea.add(cardfront[(seatnum + 3) % 4][12 - i]);
			cardfront[(seatnum + 2) % 4][12 - i].addMouseListener(this);
		}

		for (int i = 0; i < 4; i++) {
			buchuarea[i] = new JLabel();
			buchuarea[i].setIcon(new ImageIcon("pics//不出.png"));
			buchuarea[i].setVisible(false);
			gamearea.add(buchuarea[i]);
		}
		buchuarea[seatnum].setBounds(330, 420, 90, 40);
		buchuarea[(seatnum + 1) % 4].setBounds(550, 250, 90, 40);
		buchuarea[(seatnum + 2) % 4].setBounds(330, 200, 90, 40);
		buchuarea[(seatnum + 3) % 4].setBounds(180, 250, 90, 40);

		receive = new JTextArea();
		receive.setBounds(735, 300, 190, 300);
		receive.setLineWrap(true);
		receive.setWrapStyleWord(true);
		receive.setEditable(false);
		receive.setOpaque(false);
		receive.setForeground(Color.black);
		receive.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(255, 0, 51)));
		allarea.add(receive);

		send = new JTextField();
		send.setBounds(735, 610, 190, 25);
		send.setOpaque(false);
		send.setForeground(Color.black);
		send.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));
		send.addActionListener(this);
		allarea.add(send);

		exit = new JLabel();
		exit.setBounds(785, 10, 100, 100);
		exit.setIcon(new ImageIcon("pics//sets.png"));
		exit.addMouseListener(this);
		allarea.add(exit);
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JButton) {
			if (e.getSource() == start) {
				Playsound.play("audio//click.wav");
				if (Isfirst == false)
					reready[seatnum]
							.setIcon(new ImageIcon("pics//reready.png"));
				else
					ready[seatnum].setIcon(new ImageIcon("pics//ready.png"));
				start.setVisible(false);

				try {
					os.writeUTF("ready");
				} catch (IOException e1) {
				}

			}
			if(e.getSource() == no) {
				try {
					os.writeUTF("gameinfo");
					os.writeUTF("jiaofen");
					os.writeUTF("0");
				} catch (IOException e1) {
				}
				Isnext = true;
				no.setVisible(false);
				one.setVisible(false);
				two.setVisible(false);
				three.setVisible(false);
			}
			if(e.getSource() == one) {
				try {
					os.writeUTF("gameinfo");
					os.writeUTF("jiaofen");
					os.writeUTF("1");
				} catch (IOException e1) {
				}
				Isnext = true;
				no.setVisible(false);
				one.setVisible(false);
				two.setVisible(false);
				three.setVisible(false);
			}
			if(e.getSource() == two) {
				try {
					os.writeUTF("gameinfo");
					os.writeUTF("jiaofen");
					os.writeUTF("2");
				} catch (IOException e1) {
				}
				Isnext = true;
				no.setVisible(false);
				one.setVisible(false);
				two.setVisible(false);
				three.setVisible(false);
			}
			if(e.getSource() == three) {
				try {
					os.writeUTF("gameinfo");
					os.writeUTF("jiaofen");
					os.writeUTF("3");
				} catch (IOException e1) {
				}
				Isnext = true;
				no.setVisible(false);
				one.setVisible(false);
				two.setVisible(false);
				three.setVisible(false);
			}
			if (e.getSource() == chupai) {
				int k = 0;
				String info = "";
				for (int i = 1; i <= 13; i++) {
					if (cardclick[i] && !cardsend[i]) {
						cardsend[i] = true;
						info += card[i].cardtype + card[i].priority + "#";
						++k;
					}
				}
				mycard = info.split("#");
				if (k == 0)// 没选中任何牌
					return;
				if (Rule.Issame(myoldcard, cardinfo)) {
					if (!Rule.Isregular(mycard)) {
						for (int i = 1; i <= 13; i++) {
							if (cardclick[i] && cardsend[i])
								cardsend[i] = false;
						}
						return;
					}
				} else {
					if (!Rule.Isregular(mycard, cardinfo)) {
						for (int i = 1; i <= 13; i++) {
							if (cardclick[i] && cardsend[i])
								cardsend[i] = false;
						}
						return;
					} else {
						for (int i = 0; i < 4; i++) {
							buchuarea[i].setVisible(false);
						}
					}
				}
				Repaintleftcard(13 - k);
				if (k != 0) {
					getPos(k);
					new Cardanimation(k).start();
				}
				myoldcard = mycard;
				cardinfo = mycard;
				Isnext=true;
				/*
				 * 发送给服务器出的牌
				 */
				try {
					os.writeUTF("gameinfo");
					os.writeUTF("chupai");
					os.writeUTF(info);

				} catch (IOException e1) {
				}
				myleftcard -= mycard.length;

				/*
				 * 如果剩余牌为0,说明自己赢了
				 */
				if (myleftcard == 0)
					try {
						os.writeUTF("oneplayerwin");
					} catch (IOException e1) {
					}
				Isnext = true;
				chupai.setVisible(false);
				buchu.setVisible(false);
			}

			if (e.getSource() == buchu) {
				buchuarea[seatnum].setVisible(true);
				Playsound.play("audio//Man//buyao"
						+ (new Random().nextInt(100) % 4 + 1) + ".wav");
				Cardreturn();

				try {
					os.writeUTF("gameinfo");
					os.writeUTF("buchu");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Isnext = true;
				hideDown();
				chupai.setVisible(false);
				buchu.setVisible(false);
			}
		}
		if (e.getSource() instanceof Card) {
			for (int i = 1; i <= 13; i++)
				if (e.getSource() == card[i] && !cardsend[i]) {
					if (cardclick[i] == false) {
						cardclick[i] = true;
						Cardup(card[i], 15);
					} else {
						cardclick[i] = false;
						Cardup(card[i], -15);
					}
					break;
				}
		}
		if (e.getSource() == exit)
			sureExit();
	}
	public void run() {
		while (true) {
			try {
				String s = is.readUTF();
				if (s.equals("gamestart")) {
					String allcards = is.readUTF();
					firstchupai = Integer.parseInt(is.readUTF());// 最先出牌的seat号
					CARDS = allcards.split("#");
					initcard();
					startflag = true; // 本局开始
					if (!startanimation.isAlive()) {
						startanimation = new Startanimation();
						startanimation.start();
					}
					Playsound.play("audio//start.wav");
					leftcardnum[(seatnum + 1) % 4].setVisible(true);
					leftcardnum[(seatnum + 1) % 4].setText("剩余张数：13");
					leftcardnum[(seatnum + 2) % 4].setVisible(true);
					leftcardnum[(seatnum + 2) % 4].setText("剩余张数：13");
					leftcardnum[(seatnum + 3) % 4].setVisible(true);
					leftcardnum[(seatnum + 3) % 4].setText("剩余张数：13");
				}
				
				if (s.equals("oneplayerjiaofen")) {
					int sn = Integer.parseInt(is.readUTF());
					int number = Integer.parseInt(is.readUTF());
					Isnext = true;
					if ((sn + 1) % 4 == this.seatnum&&flag<=4) {
						flag++;
						if(number==0) {
							no.setVisible(true);
							one.setVisible(true);
							two.setVisible(true);
							three.setVisible(true);
						}
						else if(number==1) {
							no.setVisible(true);
							two.setVisible(true);
							three.setVisible(true);
						}
						else if(number==2) {
							no.setVisible(true);
							three.setVisible(true);
						}

					}
					if((sn + 1) % 4 == this.seatnum&&flag>4) {
						no.setVisible(false);
						one.setVisible(false);
						two.setVisible(false);
						three.setVisible(false);
						
					}
					repaint();
				}
				if(s.equals("jiaofenjieguo")) {
					firstchupai = Integer.parseInt(is.readUTF());
					if(firstchupai==this.seatnum) {
						chupai.setVisible(true);
						flag=5;
					}
					repaint();
				}
			} catch (IOException e) {
				this.dispose();
			}
		}
	}
}
