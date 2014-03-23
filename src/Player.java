import java.awt.Color;
import java.awt.Graphics;


public class Player extends Element{

	double x = 200;
	double y = 200;

	double waveAmplitude = 5;
	double waveFrequency = 0.01;
	double waveOffset = 0;
	double waveState = 0;
	
	
	public void render(Graphics g)
	{
		int clr = (int)((waveState*0.5)%255); // TODO
		g.setColor(new Color(clr,clr,clr));
		g.fillOval((int)x-25, (int)y-25, 50, 50);
	}
	
	public void tick(GameState s)
	{
		waveState++;
		double strength = Math.sin((waveState+waveOffset)*waveFrequency)*waveAmplitude;
		strength = Math.abs(strength);
		
		moveTowards(s.mouseCoordX, s.mouseCoordY, strength);
	}
	
	private void moveTowards(double x, double y, double magnitude)
	{
		double xDiff = Math.abs(this.x-x);
		double yDiff = Math.abs(this.y-y);
		
		double xComp = magnitude*0.5; // TODO
		if(this.x>x) xComp *= -1;
		double yComp = magnitude*0.5; // TODO
		if(this.y>y) yComp *= -1;
		
		if(xDiff > xComp) this.x = this.x+xComp;
		else this.x = x;
		
		if(yDiff > yComp) this.y = this.y+yComp;
		else this.y = y;
	}
	
}
