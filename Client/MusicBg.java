package game;

import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class MusicBg {
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
			JOptionPane.showMessageDialog(this, "保存成功！", "提示",
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
