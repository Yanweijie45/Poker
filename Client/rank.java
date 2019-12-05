package game;

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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class rank extends JFrame implements MouseListener {
    
	JLabel rank;
	Object[] columnNames = {"账号", "积分", "排名"};
	Object[][] rowData = {
			{"张三", 80, 80, 80},
            {"John", 70, 80, 240},
            {"Sue", 70, 70, 210},
            {"Jane",  70, 60, 210},
            {"Joe",  70, 60, 210}
	};
	JTable table = new JTable(rowData, columnNames);

	
	rank(){
	initGUI();
	}
	public void initGUI() {
		setTitle("rank");
		setIconImage(new ImageIcon("pics\\rush.png").getImage());
		setSize(500, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setLayout(null);
		add(table.getTableHeader());
		add(table);
		table.getTableHeader().setBounds(0 , 50, 300, 15);
		table.setBounds(0 , 65, 300, 250);
	    rank =new JLabel ("rank bang");
	    add(rank);
	    rank.setBounds(220, 20, 80, 20);
	   
	    
	    
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new rank();
	}
	
}
