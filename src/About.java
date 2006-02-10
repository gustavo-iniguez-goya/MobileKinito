
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

import javax.microedition.lcdui.*;

public class About {

    private static String copyright =
     "Kinito - Juego antológico para cocerse sin conocimiento\n\n"
    + "Copyright (C) 2005-2006\nGustavo Iñiguez Goya <ga@kutxa.homeunix.org> - Oier Peral Nuñez <>\n\n"
    + "This program is free software; you can redistribute it and/or modify"
    + "it under the terms of the GNU General Public License as published by"
    + "the Free Software Foundation; either version 2 of the License, or "
    + "(at your option) any later version.\n\n"
    + "This program is distributed in the hope that it will be useful,"
    + "but WITHOUT ANY WARRANTY; without even the implied warranty of"
    + "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the"
    + "GNU General Public License for more details.\n\n"
    + "You should have received a copy of the GNU General Public License"
    + "along with this program; if not, write to the Free Software"
    + "Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA";



    private About() {};

    public static void showAbout(Display display, CommandListener cmdli, Kino k) {

	Command cmdExit = new Command ("Volver", Command.BACK, 1);
	Form frm = new Form ("Kinito 1.0");

	if (display.numColors() > 2) {
	    String icon = (display.isColor()) ?
		"/icons/splash100x80.jpg" : "";

	    
	    try {
		if (display.isColor()){
	        	Image image = Image.createImage(icon);
			ImageItem imgItem = new ImageItem ("Acerca de Kinito 1.0", image, 3, "Kinito 1.0");
			frm.append(imgItem);
	    	}
		else{
			frm.append ("Acerca de Kinito 1.0 - Modo texto");
		}
	    } catch (java.io.IOException x) {
		String str="<Imagen Kino>";
		frm.append (str);
	    }
	   
	}
	frm.append ("http://gainan-taldea.ath.cx\n");
	frm.append(copyright);
	frm.addCommand (cmdExit);
	frm.setCommandListener (cmdli);

	display.setCurrent(frm);
    }

}
