//importing packages
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
public class Paddle extends JPanel
{
  int x,y;//declaring variables for location of paddle
  
  public Paddle(int x, int y)//paddle constructor to initialize variables
  {
    this.x = x;
    this.y = y;
  }

  public void paint(Graphics g)//setting colour of paddle to green and filling rectangle shape
  {
    Color Green = new Color(0,204,0);
    g.setColor(Green);
    g.fillRect(x,y,20,50);
  }
}