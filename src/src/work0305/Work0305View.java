package work0305;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class Work0305View extends JFrame{
	
	public Work0305View() {
		super(" ");
		Work0305Evt wevt= new Work0305Evt();
	//	addWindowListener(wevt);
		
		
	
		Work0305View wke=new Work0305View();
		JTextField jtf=new JTextField();
		JList<String> jl= new JList<String>();
		JLabel jlbl= new JLabel();

		
	}
//		jrb=wkV
//		
//		setLayout(new FlowLayout());
//		for(JRadioButton tempJrb : jrb) {
//			add( tempJrb );
//		}//end for
//		
//		setSize(800, 300);
//		setVisible(true);
//		
//	}//TableView
//	

//
//	public ButtonGroup getBg() {
//		return bg;
//	}//getBg

	public static void main(String[] args) {
		new Work0305View();
	}//main

}//class
