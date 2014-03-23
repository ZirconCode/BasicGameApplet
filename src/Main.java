
/*
 * 	Java Game Applet Basic Structure
 *  by: Simon Gruening / ZirconCode
 */


import java.applet.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.util.Vector;
  
public class Main extends Applet implements MouseMotionListener, MouseListener, KeyListener, Runnable
{
  
	boolean running;
	Thread UpdaterThread;
	
	Graphics bufferg; 
	Image bufferi;
	Dimension bufferdim; 
	Font MyFont;
	
	int tickTime = 5;
	
	GameState state;
	
	Vector<Element> elements;
	
	// Setup
	
	public void init() 
    { 
   	    
		setSize(800, 600);
		this.resize(800,600);
		
        bufferdim = getSize(bufferdim);
	    bufferi = createImage(bufferdim.width,bufferdim.height); 
	    bufferg = bufferi.getGraphics();
	    
	    setBackground(Color.black);
	    MyFont = new Font("Arial",Font.BOLD,16);
	    addMouseListener(this);
	    addMouseMotionListener(this); 
	    addKeyListener(this);
		
	    state = new GameState();
   	 	gameSetup();
	    
		running = true;
		UpdaterThread = new Thread(this);
   	    UpdaterThread.start();
    }
	
	public void gameSetup()
	{
		elements = new Vector<Element>();
		elements.add(new Player());
	}
	
	// Render
	
    public void update(Graphics g) 
    { 
    	paint(g); 
    }
	
	public void paint(Graphics g) 
    { 
		bufferg.setColor(Color.green);
        bufferg.fillRect(0,0,bufferdim.width,bufferdim.height);
        
        bufferg.setFont(MyFont);
        
        // -- Render Game
        
        for(int i = 0; i<elements.size(); i++)
        	elements.get(i).render(bufferg);
        
        // --
        
		g.drawImage(bufferi,0,0,this); 
    }
	
	// Game Loop
	
	public void run() 
    { 
         while (running) 
         { 
        	 long nextTick = (System.currentTimeMillis() + tickTime);
        	 
        	 // -- Update Game State
        	 
        	 for(int i = 0; i<elements.size(); i++)
             	elements.get(i).tick(state);
        	 
        	 // --
        	 
        	 while(nextTick > System.currentTimeMillis()) { /* blergh */ }
        	 repaint();
         }
    }
    
    // Teardown
    
	public void stop() 
    { 
		
    }
    
	public void destroy() 
    { 
		running = false; 
		UpdaterThread = null; 
    }
	

	// Input
	
    public void mouseMoved(MouseEvent me)  
    {  
    	state.mouseCoordX = me.getX();
    	state.mouseCoordY = me.getY();
    }
    
    public void mouseDragged(MouseEvent me)  
    { 

    } 

    public void mouseClicked (MouseEvent me) 
    {

    } 
    
    public void mouseEntered (MouseEvent me) 
    {

    } 
    
    public void mousePressed (MouseEvent me) 
    {
    	state.mouseDown = true;	
    } 
    
    public void mouseReleased (MouseEvent me) 
    {
    	state.mouseDown = false;
    }  
    
    public void mouseExited (MouseEvent me) 
    {

    }

	public void keyPressed(KeyEvent e) {
		state.keyDown[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		state.keyDown[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent arg0) {
		
	}  	
	
}