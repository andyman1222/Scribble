import java.awt.Color;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class main extends JPanel{
	Drawer drawer1 = new Drawer(new Color(255,0,00), 1);
	Drawer drawer2 = new Drawer(new Color(0,255,00), 2);
	Drawer drawer3 = new Drawer(new Color(0,0,255), 3);
	Drawer drawer4 = new Drawer(new Color(255,255,0), 4);
	Drawer drawer5 = new Drawer(new Color(0,255,255), 5);
	Drawer drawer6 = new Drawer(new Color(255,0,255), 6);
	Drawer drawer7 = new Drawer(new Color(255,255,255), 7);
	static boolean firstTime = true;
	static menuObj menu = new menuObj();
	static JFrame frame = new JFrame();
	public static void main(String[] args){
		
		frame.setSize(1000, 1000);
		frame.add(new main());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocation(500,0);
	}
	
	int oldWidth;
	int oldHeight;
	protected static int width, height;
	Point oldLocation = getLocation();
	public void paint(Graphics g){
		width = getWidth();
		height = getHeight();
		if(firstTime){
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, width, height);
			firstTime = false;
		}
		
		if((oldWidth != getWidth())||(oldHeight != getHeight())||(!oldLocation.equals(getLocationOnScreen()))){
			firstTime = true;
			oldWidth = getWidth();
			oldHeight = getHeight();
			oldLocation = getLocationOnScreen();
		}
		
		setSize(oldWidth,oldHeight);
		try {
			Thread.sleep(menuObj.speedSlider.getValue());
		}
		catch(Exception ex) {
			System.out.println("Error!");
		}
		draw(g);
		repaint();	
	}
	public void draw(Graphics g){
		//while(true){
			//g.fillRect(0,0,10000,10000);
			//g.setColor(Color.red);
			drawer1.render(g);
			drawer2.render(g);
			drawer3.render(g);
			drawer4.render(g);
			drawer5.render(g);
			drawer6.render(g);
			drawer7.render(g);
			menu.menuFrame.toFront();
		//}
	}
}
