//importing packages
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color; 

public class Borders extends JPanel
{
  int p1Score = 0;//creating a variable called p1Score to track player 1 score and setting it equal to 0 to start
  int p2Score = 0;//creating a variable called p2Score to track player 2 score and setting it equal to 0 to start

  public void drawBorder(Graphics g)
  {
    //point borders, left and right
    g.setColor(Color.RED);
    g.fillRect(0,0,5,340);
    g.fillRect(565,0,5,350);

    //bounce borders, top and bottom
    g.setColor(Color.GRAY);
    g.fillRect(0,0,565,10);
    g.fillRect(0,340,565,10);

    //Show scores
    g.drawString(Integer.toString(p1Score), 263, 20);
    g.drawString(Integer.toString(p2Score), 295, 20);
  }
  void resetScore()//procedure to reset scores of both players
  {
    this.p1Score = 0;
    this.p2Score = 0;
  }
}