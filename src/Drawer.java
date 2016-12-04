import java.awt.Color;
import java.awt.Graphics;

public class Drawer {
	public double y=(Math.random()*500)+100;
	public double x=(Math.random()*500)+100;
	
	//decrease distance for larger curves
	float distance = (float).1;
	
	//increases the number of curves the drawer makes in its path at the cost of distance. Counter with distance variable.
	//curves= 2 = 8 turns to make a full "circle"
	//curves= 1.5 =4 turns to make a full "circle"
	//curves= 3 = 16 """"""""
	//for curvy curves, recommended values between 4 and 6
	public float curves = (float)2;
	public boolean rainbowMode;
	public boolean isMirror;
	protected int number;
	private double oldX = x, oldY = y;
	public int lengthOfSegment = 50;
	
	private int[] limitModeX = new int[lengthOfSegment];
	private int[] limitModeY = new int[lengthOfSegment];
	private double xChange, yChange, direction, directionChange;
	Graphics g;
	private Color penColor, baseColor;
	Drawer mirrorX;
	Drawer mirrorY;
	Drawer mirrorXY;
	private Drawer drawerToMirror;
	private int whatToMirror;
	int getColorFromBase(int color){
		if((color+(int)(Math.sin(colorShade/2)*20)-20)<0){
			return 0;
		}
		else{
			return color+(int)(Math.sin(colorShade/2)*20)-20;
		}
	}
	
	public Drawer(Color color, int number){
		this.g = g;
		baseColor = color;
		this.number = number;
	}

	int colorShade;
	public void render(Graphics g){
		curves = main.menu.curveSlider.getValue();
		distance = (float) (main.menu.distanceSlider.getValue()*.01);
		if(main.menu.numberOfDrawers >= number){
			generateDirection();
			penColor = new Color(getColorFromBase(baseColor.getRed()),getColorFromBase(baseColor.getGreen()),getColorFromBase(baseColor.getBlue()),main.menu.transparency.getValue());
			x+=(int)(xChange);
			y+=(int)(yChange);
			if(x>=main.width-20){
				x = main.width-20;
			}
			if(y>=main.height-20){
				y = main.height-20;
			}
			colorShade++;
			g.setColor(penColor);
			g.fillOval((int)Math.abs(x),(int)Math.abs(y),10,10);

			if(main.menu.mirrorMode){

				g.fillOval((int)(x*-1)+(Math.round(main.width)),(int)y,10,10);
				g.fillOval((int)(x*-1)+(Math.round(main.width)),(int)(y*-1)+(Math.round(main.height)),10,10);
				g.fillOval((int)x,(int)(y*-1)+(Math.round(main.height)),10,10);
				}
			}
			if(main.menu.limitMode){
				lengthOfSegment = main.menu.lengthSlider.getValue();
				for(int i = 0; i < lengthOfSegment-1; i++){
					//System.out.println(i < lengthOfSegment-1);
					limitModeX[i] = limitModeX[i+1];
					limitModeY[i] = limitModeY[i+1];
				}
				limitModeX[lengthOfSegment-1] = (int) Math.abs(x);
				limitModeY[lengthOfSegment-1] = (int) Math.abs(y);
				g.setColor(Color.BLACK);
				g.fillOval(limitModeX[0]-1,limitModeY[0]-1,15,15);
				oldX = limitModeX[0];
				oldY = limitModeY[0];
				if(main.menu.mirrorMode){
					g.fillOval((int)(oldX*-1)+(Math.round(main.width))-1,(int)oldY-1,15,15);
					g.fillOval((int)(oldX*-1)+(Math.round(main.width))-1,(int)(oldY*-1)+(Math.round(main.height))-1,15,15);
					g.fillOval((int)oldX-1,(int)(oldY*-1)+(Math.round(main.height))-1,15,15);
				}
			}
		}
	private int loop = 0;
	public void generateDirection(){
		if(!isMirror){
			if(loop == 0){
				loop = (int)Math.round(Math.random()*10)+5;
				if(Math.random()>=.5){
					directionChange = distance;
				}
				else{
					directionChange = -distance;
				}
			}
			else{
				loop--;
				direction += directionChange;
			}
			if((x<10)||(y<10)){
				directionChange = -distance;
			}
			xChange = (Math.cos(direction)*curves);
			yChange = (Math.sin(direction)*curves);
		}
	}
		
}
