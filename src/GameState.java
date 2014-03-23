
public class GameState {

	public int state = 0;
	
	// Dimensions
	
	
	// Input
	public boolean[] keyDown;
	public boolean mouseDown;
	public int mouseCoordX;
	public int mouseCoordY;
	
	public void init()
	{
		keyDown = new boolean[65535];
		System.out.println("test");
	}
	
	
}
