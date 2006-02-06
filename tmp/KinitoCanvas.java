
import javax.microedition.lcdui.*;

public class KinitoCanvas extends Canvas {
	public CommandListener listener;
	
	public void KinitoCanvas (CommandListener cmdLst)
	{
		listener = cmdLst;
	}
	

	public void paint (Graphics g)
	{
		g.setColor (255, 0, 0);
		g.fillRect (0, 0, getWidth(), getHeight());
		g.setColor (255, 255, 255);
		g.drawString ("Hello Gainanes!", 0, 0, g.TOP | g.LEFT);
	}

}
