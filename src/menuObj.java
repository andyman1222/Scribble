import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class menuObj extends JPanel implements ActionListener{
	
	public boolean limitMode = false, mirrorMode = false;
	private static JCheckBox limitModeB = new JCheckBox();
	private static JCheckBox mirrorModeB = new JCheckBox();
	public static JSlider number = new JSlider(0,7);
	public static JSlider speedSlider = new JSlider(0,100);
	public static JSlider lengthSlider = new JSlider(10,50);
	public static JSlider curveSlider = new JSlider(2,10);
	public static JSlider distanceSlider = new JSlider(1,100);
	static JButton clear = new JButton("clear screen");
	private static changeListenerSlider sliderListener = new changeListenerSlider();
	protected static int numberOfDrawers = 7;
	static JFrame menuFrame = new JFrame("");
	JPanel panel = new JPanel();
	public menuObj(){
		number.setValue(8);
		speedSlider.setValue((int)Math.round((Math.random()*100)));
		lengthSlider.setValue((int)Math.round((Math.random()*50)+10));
		curveSlider.setValue((int)Math.round((Math.random()*10)+2));
		distanceSlider.setValue((int)Math.round((Math.random()*100)+1));
		limitModeB.addActionListener(this);
		limitModeB.setActionCommand("limitMode");
		mirrorModeB.addActionListener(this);
		mirrorModeB.setActionCommand("mirrorMode");
		number.addChangeListener(sliderListener);
		clear.addActionListener(this);
		clear.setActionCommand("clear");
		menuFrame.setSize(500,300);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.EAST;
        GridBagConstraints right = new GridBagConstraints();
        right.weightx = 2.0;
        right.fill = GridBagConstraints.HORIZONTAL;
        right.gridwidth = GridBagConstraints.REMAINDER;
		//panel.add(this);
		panel.add(new JLabel("number of drawers: "), left);
		panel.add(number, right);
		panel.add(new JLabel("speed: "), left);
		panel.add(speedSlider, right);
		panel.add(new JLabel("distance: "), left);
		panel.add(curveSlider, right);
		panel.add(new JLabel("curves: "), left);
		panel.add(distanceSlider, right);
		panel.add(new JLabel("mirror mode"), left);
		panel.add(mirrorModeB, right);
		panel.add(new JLabel("Erase mode"), left);
		panel.add(limitModeB, right);
		panel.add(new JLabel("length of drawers (if mirror mode checked): "), left);
		panel.add(lengthSlider, right);
		panel.add(clear, right);
		
		menuFrame.setVisible(true);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.getContentPane().add(panel);
		menuFrame.toFront();
	}
	
	public void actionPerformed(ActionEvent e) {
		if("limitMode".equals(e.getActionCommand())) limitMode = !limitMode;
		if("clear".equals(e.getActionCommand())) main.firstTime = true;
		if("mirrorMode".equals(e.getActionCommand())) mirrorMode = !mirrorMode;
	}
	
}
class changeListenerSlider implements ChangeListener{
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
        if (source.getValueIsAdjusting()) {
        	if(e.getSource().equals(menuObj.number)){
        		menuObj.numberOfDrawers = source.getValue();
        		source.setValue(Math.round(source.getValue()));
        	}
        }
	}
}
