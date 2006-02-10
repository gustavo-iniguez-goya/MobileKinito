
/* Kinito for mobile phones - The first Kinito game for Mobile Phones
 * Copyright (C) 2005-2006 - Gustavo Iñiguez Goya
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Library General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */


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
