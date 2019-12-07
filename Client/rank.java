package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import java.util.Enumeration;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;





public class rank extends JFrame implements MouseListener {
    
	public static void setColumnColor(JTable table) {
		try
		{
			final Color c = new Color(255,255,224);
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(){
				private static final long serialVersionUID = 1L;
				public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column){
					if(row%2 == 0)
						setBackground(c);//设置奇数行底色
					else if(row%2 == 1)
						setBackground(new Color(255,255,220));//设置偶数行底色
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
            {"Joe",  70, 60, 210},
            {"Joe",  70, 60, 210},
            {"Joe",  70, 60, 210}
           
         
	};
	JTable table = new JTable(rowData, columnNames);

	
	rank(){
	initGUI();
	}

	 
	public void initGUI() {
		
		
	
		setSize(500, 400);
		Color c = new Color(255,255,224);
		setTitle("rank");
		setLayout(null);
		head = new JLabel();
		head.setBounds(0, 0, 500, 50);
		Icon icon=new ImageIcon("pics\\rankhead.png"); 
		head.setIcon( icon);
		add(head);
		
		left = new JLabel();
		left.setBounds(0, 50, 95, 350);
		//Icon icon1=new ImageIcon("pics\\rankleft.png"); 
		//left.setIcon( icon1);
		left.setOpaque(true);  
		left.setBackground(c);
		add(left);
		
		right = new JLabel();
		right.setBounds(395, 50,100, 350);
		//Icon icon2=new ImageIcon("pics\\rankright.png"); 
		//right.setIcon( icon2);
		
		right.setOpaque(true);  
		right.setBackground(c);
		add(right);
		//right.setOpaque(false);
		
		
		

		setColumnColor(table);//设置表格颜色
		//table.setOpaque(false);//设置背景透明
		table.setOpaque(true);  
		table.setBackground(c);
		table.setEnabled(false);
	
		table.getTableHeader().setBackground(c);
		table.getTableHeader().setFont(new Font("宋体", Font.BOLD, 20)); 
		table.setFont(new Font("幼圆", Font.BOLD, 15)); 
	
	    //行高
	    JTableHeader head = table.getTableHeader();
	    table.getTableHeader().setPreferredSize(new Dimension(head.getWidth(), 40));
	    table.setRowHeight(55);
	    
	    new JScrollPane(table);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setLayout(null);
		add(table.getTableHeader());
		add(table);
		table.getTableHeader().setBounds(95, 51, 300, 40);
		table.setBounds(95 , 90, 300, 310);
		
		JScrollPane scrollPane = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED ,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//scrollPane.add(table.getTableHeader());
		rank =new JLabel ("rank bang");
	    add(scrollPane);
	    scrollPane.setBounds(95,51, 300, 310);
	 
	    scrollPane.setBackground(c);
	  
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
