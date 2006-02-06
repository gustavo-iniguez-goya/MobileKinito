
package Kinito;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import Kinito.Kino;

public class Kinito extends MIDlet implements CommandListener{

    public static final Command cmdComenzar  = new Command("Jugar", Command.SCREEN, 1);
    private boolean isInitialized = false;
    Form frm = new Form ("Kinito 1.0");
    Kino k;

    public Kinito ()
    {
		splash_screen ();
    }

    public void startApp() {
	    try{
	        if (isInitialized) {
        	    return;
	        }
	        isInitialized = true;
	    	Display.getDisplay(this).setCurrent(frm);
	    }
	    catch (java.lang.NullPointerException e){
		    System.out.println(e.getMessage());
	    }
	
    }


    public void splash_screen ()
    {
	    try{
		if (Display.getDisplay(this).isColor()){
		    Image img_tmp = Image.createImage ("/icons/splash100x80.jpg");
		    ImageItem imgItem = new ImageItem ("", img_tmp, 3, "");

		    frm.append (imgItem);
		}
		else{
		    frm.append ("Gainan Taldea presenta\n\n\tKinito1.0 - Modo texto\n\nCopyright 2005-2006 - Gustavo Iñiguez Goya");
		}
		    frm.addCommand (cmdComenzar);
		    frm.setCommandListener (this);
		    //About.showAbout (Display.getDisplay(this));
		    //Display.getDisplay(this).setCurrent(alt);
	    }
	    catch (java.lang.NullPointerException e){
		    frm.append ("Gainan Taldea presenta\n\n\tKinito1.0 - Modo texto\n\nCopyright 2005-2006 - Gustavo Iñiguez Goya");
		    frm.addCommand (cmdComenzar);
		    frm.setCommandListener (this);
	    }
	    catch (java.io.IOException ex){
		    frm.append ("Gainan Taldea presenta\n\n\tKinito1.0 - Modo texto\n\nCopyright 2005-2006 - Gustavo Iñiguez Goya");
		    frm.addCommand (cmdComenzar);
		    frm.setCommandListener (this);
	    }
    }

    public void destroyApp(boolean unconditional) 
    {
    }

    public void pauseApp() {}

    public void commandAction(Command c, Displayable d) 
    {
		    if (c == cmdComenzar){
			k = new Kino(this);
		    }
	    //System.out.println("Kinito(): " + c.getLabel());
    }

}
