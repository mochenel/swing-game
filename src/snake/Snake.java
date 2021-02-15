package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;




public class Snake extends JPanel implements ActionListener,KeyListener {

	private static final long serialVersionUID = 1L;
	private int x,y,w,h;
	private int px,py;
	private String direction;
	private int delay;
    private Timer t;
    private JLabel score,timing;
    private int count;
    private int time;
    private BufferedImage image;
    private JButton PlayAgainButton,quitButton;
    private Font f;
    
    ActionListener resetListener  = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
           x=300;
       	   y=620;
       	   direction = "UP";
	   	   px = 100;
	   	   py = 100;
	       time = 100;
	       count = 0;
	       score.setText(String.valueOf(count));
	       delay = 50;
	       t.setDelay(delay);
	       PlayAgainButton.setVisible(false);
	       repaint();
        }
   };
   ActionListener quitListener  = new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent ae) {
    	   try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          System.exit(0);
       }
  };
  

   public Snake(){
	   // snake initial position
	   this.x=300;
	   this.y=620;
	   this.w=10;
	   this.h=10;
	   
	   //oval initial position
	   this.px = 100;
	   this.py = 100;
	   
	   // initial snake direction
	   this.direction = "UP";
	   
	   // initial snake speed
	   this.delay = 50;
	   this.t = new Timer(delay,this);
	   
	   // registering key listener
	   addKeyListener(this);
	   setFocusable(true);
	   
	   // initial score
	   this.count = 0;
	   
	   // add score button
	   this.score = new JLabel(String.valueOf(this.count));
	   add(this.score);
	   
	   // add timing label
	   this.time = 100;
	   this.timing = new JLabel(String.valueOf(this.time));
	   add(new JLabel(" :"));
	   add(timing);
	   
	   // set font properties
	   f= new Font("SansSerif",Font.CENTER_BASELINE,40);
	   
	   // start over button
	   PlayAgainButton =  new JButton("Play Again");
	   add(PlayAgainButton);
	   PlayAgainButton.setVisible(false);
	   
	   // quit button
	   quitButton = new JButton("Quit");
	   add(quitButton);
	   quitButton.addActionListener(quitListener);
	   quitButton.setContentAreaFilled(true);
   }
  
   
 
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        // drawing grid
        boolean green = true;
    	for(int i = 0; i <= 50; i++) {
    		for(int j = 0; j <= 50; j++) {
    			if(green) {
        			g.setColor(Color.gray);
             		g.fillRect(10*j + 80, 10*i + 80 , 10, 10);
             		
        		}
        		else {
        			g.setColor(Color.lightGray);
             		g.fillRect(10*j +80, 10*i + 80 , 10, 10);
        		}
    			green = !green;
    		}
    	
    		
    	}
             		
           
    	// drawing snake 
        g.setColor(Color.red);
        g.fillRect(x, y, w, h);
        
        // drawing oval
        g.setColor(Color.green);
        g.fillOval(px, py, 10, 10);
        
        // moving objects after changing their positions
        t.start();
        
        if(time == 0) {
    		timeOut(g);
    		t.stop();
    	}

        
    }


    public static void main(String[] args) {
    	
 
    	// draw frame
    	
       JFrame f = new JFrame("Snake");
       f.setSize(700,700);
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       f.setLocationRelativeTo(null);
       f.setResizable(false);
       Snake g = new Snake();
       f.add(g);
       f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	time--;
    	timing.setText(String.valueOf(time));
    	
    	if(Math.abs(px - x) < 4 && Math.abs(py - y) < 4) {
			random();
			
			count ++ ;
			if(count >=Integer.MAX_VALUE)
				count = Integer.MAX_VALUE -1;
			score.setText(String.valueOf(count));
			time = time + 100;
			delay = delay -2;
			t.setDelay(delay);
			 
  		 }
    	
    	if(direction.equals("UP")) {
    		 y = y - 10;
    		 
    	}
    	else if(direction.equals("LEFT")) {
    		x = x - 10;
    		
    	}
    	else if(direction.equals("RIGHT")) {
    		x = x + 10;
    		
    	}
    	else {
    		 y = y + 10;
			
    	}
    	
    	
  
        if(y < 80) {
        	y = 580;
        }
        if(y > 580) {
        	y = 80;
        }
        if(x < 80) {
        	x = 580;
        }
        if(x > 580) {
        	x = 80;
        }
        
        repaint();
        
    }
    public void random() {
    	px = (int) (Math.floor(Math.random()*50)*10);
    	if(px < 80 ) {
    		px = 80;
    	}
    	else if(px > 580) {
    		px = 580;
    	}
    	py =  (int) (Math.floor(Math.random()*50)*10);
    	if(py < 80 ) {
    		py = 80;
    	}
    	else if(py > 580) {
    		py = 580;
    	}
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			direction = "LEFT";
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			direction = "UP";
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			direction = "RIGHT";
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			direction = "DOWN";
		}

	}
	public void timeOut(Graphics g) {
				PlayAgainButton.setVisible(true);
	            g.setColor(Color.red);
	            try {
	                // g.drawString("you lost", 350, 300);
	                image = ImageIO.read(getClass().getResource("/images/over.png"));
	                  g.drawImage(image, 180, 180, 300, 300, Color.red, this);
	                  g.setFont(f);
	                  g.setColor(Color.black);
	                  g.drawString("score: "+ String.valueOf(count), 235, 70);
	                  PlayAgainButton.addActionListener(resetListener);
	            } catch (IOException ex) {
	                Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
	            }
	           
	           
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    
}
