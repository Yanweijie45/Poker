package game;

import java.awt.Color;
import java.awt.Component;
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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;





public class rank extends JFrame implements MouseListener {
    
	public static void setColumnColor(JTable table) {
		try
		{
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(){
				private static final long serialVersionUID = 1L;
				public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column){
					if(row%2 == 0)
						setBackground(Color.WHITE);//设置奇数行底色
					else if(row%2 == 1)
						setBackground(new Color(220,230,241));//设置偶数行底色
					return super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
				}
			};
			for(int i = 0; i < table.getColumnCount(); i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}
			tcr.setHorizontalAlignment(JLabel.CENTER);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	
	JLabel rank;
	JLabel head,left,right;
	
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
		setSize(500, 400);
		setTitle("rank");
		setLayout(null);
		head = new JLabel();
		head.setBounds(0, 0, 500, 50);
		Icon icon=new ImageIcon("pics\\rankhead.png"); 
		head.setIcon( icon);
		add(head);
		
		left = new JLabel();
		left.setBounds(0, 50, 95, 350);
		Icon icon1=new ImageIcon("pics\\rankleft.png"); 
		left.setIcon( icon1);
		add(left);
		
		right = new JLabel();
		right.setBounds(95, 50,90, 350);
		Icon icon2=new ImageIcon("pics\\rankright.png"); 
		right.setIcon( icon2);
		add(right);
		right.setOpaque(false);
		
		setColumnColor(table);//设置表格颜色
		table.setOpaque(false);//设置背景透明
		
		table.getTableHeader().setBackground(new Color(51, 102, 255));
		 
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setLayout(null);
		add(table.getTableHeader());
		add(table);
		table.getTableHeader().setBounds(95, 50, 300, 15);
		table.setBounds(95 , 65, 300, 250);
	
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
