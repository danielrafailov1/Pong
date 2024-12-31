//importing packages
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.*;
public class Ball extends JPanel
{
  double x,y;//declaring variables for location of ball
  Random rand = new Random();
  double xVel, yVel;
  double initialxVel;
	
  public Ball(int x, int y)//ball constructor to initialize variables
  {
    this.x = x;
    this.y = y;
    this.xVel = 2;
    this.initialxVel = 1;
	  
    while(this.yVel == 0) 
    {
      this.yVel = rand.nextInt(-1,2);
    }
  }

  public void paint(Graphics g)//setting colour of ball to green and filling oval shape
  {
    Color Green = new Color(0,204,0);
    g.setColor(Green);
    g.fillOval((int)x,(int)y,20,20);
  }
}