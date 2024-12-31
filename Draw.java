//importing packages
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Draw extends JPanel implements KeyListener {
	Ball ball = new Ball(275, 150);// creating instance of class Ball and setting the location of the ball
	Paddle paddle1 = new Paddle(15, 140);// creating instance of class Paddle for player 1 and setting location to left
											// side
	Paddle paddle2 = new Paddle(535, 140);// creating instance of class Paddle or player 2 and setting location to right
											// side

	Borders border = new Borders();//creating instance of class Border called border
    GameFrame game = new GameFrame();//creating instance of class GameFrame called game
	public boolean firsthit = false;//variable to check the moment the ball hits the right paddle for the first time so that the ball can speed up
	public boolean check = true;//boolean to enable or disable right paddle depending on which mode is selected
	public boolean clicked = false;//boolean to check when a button is clicked in menu so that the game can start
	public boolean win = false;//variable to check if someone won the game
	public Draw()// draw constructor
	{
		super();
		super.setFocusable(true);
		super.addKeyListener(this);
	}

	public void paint(Graphics g)// paint procedure to paint ball, and paddles
	{
		g.clearRect(0, 0, getWidth(), getHeight());
		border.drawBorder(g);
		ball.paint(g);// painting ball
		paddle1.paint(g);// painting paddle 1
		paddle2.paint(g);// painting paddle 2
	}

	@Override
	public void keyPressed(KeyEvent arg0)// procedure to make y coordinate of paddles change depending on which keys are
											// pressed and then repaint paddles
	{
		if (arg0.getKeyCode() == KeyEvent.VK_S && paddle1.y < 290)
			paddle1.y += 8;
		if (arg0.getKeyCode() == KeyEvent.VK_W && paddle1.y > 10)
			paddle1.y -= 8;
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN && paddle2.y < 290 && check)//if check is true then right paddle can move down
			paddle2.y += 8;
		if (arg0.getKeyCode() == KeyEvent.VK_UP && paddle2.y > 10 && check)//if check is true then right paddle can move up
			paddle2.y -= 8;
		repaint();
	}

	@Override // not used
	public void keyReleased(KeyEvent arg0) {

	}

	@Override // not used
	public void keyTyped(KeyEvent arg0) {

	}

	public void update(boolean x) {//update procedure to call collision and repaint procedures with boolean perameter
    	if (clicked)//checking if a button is clicked so that update can work (crucial so that game doesnt run in background before it starts)
		{
			if(x)//checking for perameter (playAi)
			{
				AIPaddle();//calling AI procedure
				check = false;//setting check false (disables right paddle)
			}
			collision();//checking for any collisions and updating ball position
			win();//checking to see if anyone won the game
			repaint();//repainting objects
		}
	}

	public void collision() {//procedure to check all possible collisions of the ball
		if (ball.x <= 0) {
			// Adds score to player 2 total
			border.p2Score += 1;

			// repaints and resets the objects
			repaint();
			resetObj();
			// setting it so that it can bounce with any object again
		} else if (ball.x >= 545) {
			// Adds score to player 1 total
			border.p1Score += 1;

			// repaints and resets the objects
			repaint();
			resetObj();
		}
		Random rand = new Random();//creating instance of Random class called rand
		if (ball.x == 525 && ball.y >= paddle2.y - 40 && ball.y <= paddle2.y + 40)// right paddle collision
		{
			firsthit = true;//setting firsthit to true so velocity can speed up
			int num = rand.nextInt(0, 3);//generating random integer between 1 and 3 and storing it in variables called num
			if (ball.x > 0 && ball.y > 0)// top of right paddle
			{
				if (num == 1) {
					ball.xVel *= -1;
					ball.yVel *= 0.8;
					repaint();
				} else if (num == 2) {
					ball.xVel *= -1;
					repaint();
				} else {
					ball.xVel *= -1;
					ball.yVel *= 1.3;
					repaint();
				}
			} else if (ball.x > 0 && ball.y < 0)// bottom of right paddle
			{
				if (num == 1) {
					ball.xVel *= -1;
					ball.yVel *= 0.8;
					repaint();
				} else if (num == 2) {
					ball.xVel *= -1;
					ball.yVel *= 1;
					repaint();
				} else {
					ball.xVel *= -1;
					ball.yVel *= 1.3;
					repaint();
				}

			}
		}
		if (ball.x == 33 && ball.y >= paddle1.y - 40 && ball.y <= paddle1.y + 40)// left paddle collision
		{
			int num2 = rand.nextInt(0, 3);
			if (ball.x > 0 && ball.y > 0)// top of left paddle
			{
				if (num2 == 1) {
					ball.xVel *= -1;
					ball.yVel *= 0.8;
					repaint();
				} else if (num2 == 2) {
					ball.xVel *= -1;
					ball.yVel*= 1;
					repaint();
				} else {
					ball.xVel *= -1;
					ball.yVel *= 1.3;
					repaint();
				}
			} else if (ball.x > 0 && ball.y < 0)// bottom of left paddle
			{
				if (num2 == 1) {
					ball.xVel *= -1;
					ball.yVel *= 0.8;
					repaint();
				} else if (num2 == 2) {
					ball.xVel *= -1;
					ball.yVel *= 1;
					repaint();
				} else {
					ball.xVel *= -1;
					ball.yVel *= 1.3;
					repaint();
				}
			}
		}
		if (ball.y >= 325 && ball.yVel > 0)// bottom border collision detection
		{
			int num3 = rand.nextInt(0, 3);
			if (ball.xVel > 0 && ball.y > 0)// ball coming from left side
			{
				if (num3 == 1) {
					ball.yVel *= -0.8;
					repaint();
				} else if (num3 == 2) {
					ball.yVel *= -1;
					repaint();
				} else {
					ball.yVel *= -1.3;
					repaint();
				}
			} else if (ball.xVel < 0 && ball.y > 0)// ball coming from right side
			{
				if (num3 == 1) {
					ball.yVel *= -0.8;
					repaint();
				} else if (num3 == 2) {
					ball.yVel *= -1;
					repaint();
				} else {
					ball.yVel *= -1.3;
					repaint();
				}
			}
		}
		if (ball.y <= 10 && ball.yVel < 0)// top border collision detection
		{
			int num4 = rand.nextInt(0, 3);
			if (ball.xVel > 0)// ball coming from left side
			{
				if (num4 == 1) {
					ball.yVel *= -0.8;
					repaint();
				} else if (num4 == 2) {
					ball.yVel *= -1;
					repaint();
				} else {
					ball.yVel *= -1.3;
					repaint();
				}
			} else if (ball.xVel < 0)// ball coming from right side
			{
				if (num4 == 1) {
					ball.yVel *= -0.8;
					repaint();
				} else if (num4 == 2) {
					ball.yVel *= -1;
					repaint();
				} else {
					ball.yVel *= -1.3;
					repaint();
				}
			}
		}
		if (firsthit == false)//if ball never hit the paddle then x velocity is slower
		{
			ball.x += ball.initialxVel;
			ball.y += ball.yVel;
		}
		else//once ball hits the paddle speed up and add the normal x velocity
		{
			ball.x += ball.xVel;
		    ball.y += ball.yVel;
		}
		
	}

	public void resetObj() {//procedure for resetting everything but the score after someone scores
		try {//adds delay
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Resets ball and paddle positions and firsthit variable
		ball = new Ball(275, 150);
		paddle1 = new Paddle(15, 140);
		paddle2 = new Paddle(535, 140);
		firsthit = false;
	}

  public void AIPaddle()
  {
    for(int i = 0; i < 4; i++) 
    {
      // Only tracks the ball within a certain range so that the game can be lose
      if((paddle2.y - ball.y)>0 && ball.x>50 && ball.x <470)
      {
        //makes sure the paddle will not go outside the borders
        if(paddle2.y <= 5)
        {
        }
        else
        {
          //changes the y value of the paddle
          paddle2.y -= 1;
        }
      }
      else if((paddle2.y - ball.y)<0 && ball.x>15 && ball.x <535)
      {
        //makes sure the paddle will not go outside the borders
        if(paddle2.y >= 290)
        {
        }
        else
        {
          //changes the y value of the paddle
          paddle2.y += 1;
        }
      }
    }
  }

  void win()//procedure to check if anyone won the game
  {
	  if (border.p1Score == 10)//checking for winning score for player 1
	  {
		  JOptionPane.showMessageDialog(null, "Player 1 wins");
		  resetObj();
		  border.resetScore();
		  win = true;
		  game.playAi = false;
		  repaint();
	  }
	  else if (border.p2Score == 10)//checking for winning score for player 2
	  {
		  JOptionPane.showMessageDialog(null, "Player 2 wins");
		  resetObj();
		  border.resetScore();
		  win = true;
		  game.playAi = false;
		  repaint();
	  }
  }
}