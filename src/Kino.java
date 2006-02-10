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
import javax.microedition.midlet.*;
import java.util.Random;

public class Kino implements CommandListener {

	private int ContDebug;
	private int dado1, dado2;
	private Image imgCached[];
	//private Vector imgCached;
	private String strThemePrefix = "/themes/light/dado_";
	private int imgCachedCounter, imgCachedTotal;
	private int kino_intentos, kino_dados, kino_dado1, kino_dado2, kino_vasos;
	private Random rnd;
	private boolean useImages;
	private int pares_intentos;

    	public static final Command cmd_lev  = new Command("Opciones", Command.SCREEN, 2);
    	public static final Command cmdTaparPasar  = new Command("Tapar", Command.SCREEN, 2);
    	public static final Command cmdLevantar  = new Command("Levantar", Command.SCREEN, 2);
    	public static final Command cmdTirarAbelardo  = new Command("Abelardo", Command.SCREEN, 2);
    	public static final Command cmdAbout  = new Command("Acerca de", Command.HELP, 15);
    	public static final Command cmdTiraKino  = new Command("Pasar kinito", Command.SCREEN, 2);
    	public static final Command cmdKinoCiega  = new Command("Ciega", Command.SCREEN, 2);
    	public static final Command cmdKinoDado1  = new Command("Dado 1", Command.SCREEN, 2);
    	public static final Command cmdKinoDado2  = new Command("Dado 2", Command.SCREEN, 2);
   	public static final Command cmdContinuar  = new Command("Continuar", Command.SCREEN, 2);
    	public static final Command cmdVolver  = new Command("Volver", Command.BACK, 2);
	public static final Command exitCmd  = new Command("Salir", Command.EXIT, 20);
	public static final Command cmdTirar = new Command("Tirar", Command.SCREEN, 1);
	public static final Command cmdSuperarPares = new Command("Superar pares", Command.SCREEN, 1);
	//public static final Command cmdConTexto = new Command("Modo texto", Command.SCREEN, 1);
	//public static final Command cmdConImagenes = new Command("Modo grafico", Command.SCREEN, 1);
        public Form formTirada = new Form("Kinito 1.0");
	public Form fGeneral = new Form ("levanta o tira");
	public Form fLevantado = new Form ("Tirada anterior");
	public Form fAbelardo = new Form ("Abelardo");
	public Form formKinito = new Form("Kinito! Suerte!");
	public Form fTirada = new Form ("Tu tirada es");
	public Form fKino = new Form ("Kinito jugador 1");
	public Form fSuperaPares = new Form ("Superar pares");

	private boolean isInitialized = false;
	private CommandListener cmdli;
	public Kinito mdl;

    public Kino (Kinito mid){
	  //  try{
	mdl = mid;
	cmdli = this;
	imgCachedCounter = 0;
	imgCachedTotal = 5;
	rnd = new Random ();
	useImages = true;
	dado1 = dado2 = 1;

	if (Display.getDisplay(mdl).isColor()){
		useImages = true;
	}
	else{
		useImages=false;
	}


	// fill the neccesary data: images, forms, and so..
	loadImages();
	CreateFormTirada ();
	CreateFormKinoInicio ();
	CreateFormKinoTirada ();
	CreateFormAbelardo ();
	CreateFormTapado ();
	CreateFormComenzar ();
	CreateFormLevantado ();
	CreateFormSuperarPares ();
	mostrar_form_tirada ();
	//	}
	//    catch (java.io.IOException e){
	//    }
    }

    // los formularios son como "plantillas". Creamos una plantilla por defecto, y luego en ejecución la modificamos
    public void CreateFormKinoInicio (){
	    try{
		    if (useImages){
			formKinito.append (new ImageItem("", imgCached[1], 3, ""));
			formKinito.append (new ImageItem("", imgCached[2], 3, ""));
		    }

		    formKinito.append ("Tienes 3 tiradas.\nPares repite tirada.\n");
		    formKinito.addCommand(cmdTiraKino);
		    formKinito.addCommand(cmdTirar);
		    formKinito.setCommandListener(cmdli);
	    }	
	    catch (java.lang.NullPointerException e){
		ShowAlert ("Null Exception");
	    }
    }

    public void CreateFormKinoTirada (){
	    try{
		fKino.setTitle("Contra Kino");
		    if (useImages){
			fKino.append (imgCached[1]);
			fKino.append (imgCached[2]);
		    }
		fKino.addCommand (cmdTiraKino);
		fKino.addCommand (cmdTaparPasar);
		fKino.setCommandListener (cmdli);
	    }	
	    catch (java.lang.NullPointerException e){
		ShowAlert ("Null Exception");
	    }
    }

    public void CreateFormTirada (){
	    try{
		    if (useImages){
			fTirada.append (imgCached[1]);
			fTirada.append (imgCached[1]);
		    }
		    else{
			    fTirada.append("Tirada");
		    }
			
		    fTirada.addCommand (cmdTaparPasar);
		    //fTirada.append ("Memoria libre: " + Runtime.getRuntime().freeMemory());
		    fTirada.append ("");	
		    fTirada.setCommandListener(cmdli);
	    }	
	    catch (java.lang.NullPointerException e){
		//System.out.println("opciones_kinito()");
	    }
    }
    
    public void CreateFormAbelardo (){
	    try{
	        fAbelardo.append("¡Bebes!");
		if (useImages){
			fAbelardo.append (imgCached[1]);
	    	}
	        fAbelardo.addCommand(cmdVolver);
	        fAbelardo.addCommand(cmdTirar);
	        fAbelardo.setCommandListener(cmdli);
	    }	
	    catch (java.lang.NullPointerException e){
		ShowAlert ("Null Exception");
	    }
    }

    public void CreateFormSuperarPares(){
	    try{
		    if (useImages){
			    fSuperaPares.append (imgCached[1]);
			    fSuperaPares.append (imgCached[1]);
		    }
		    //fSuperaPares.addCommand (cmdSuperarPares);
		    fSuperaPares.addCommand (cmdTirar);
		    fSuperaPares.setCommandListener (cmdli);
	    }	
	    catch (java.lang.NullPointerException e){
		ShowAlert ("Null Exception");
	    }
    }

    public void CreateFormLevantado (){
	    try{
		    if (useImages){
			fLevantado.append (imgCached[1]);
			fLevantado.append (imgCached[1]);
		    }
			//fLevantado.addCommand(cmdVolver);
		    fLevantado.addCommand(cmdTirar);
		    fLevantado.setCommandListener(cmdli);
	    }	
	    catch (java.lang.NullPointerException e){
		ShowAlert ("Null Exception");
	    }
    }

    public void CreateFormTapado (){
	    try{
		fGeneral.append ("Qué eliges, ¿Levantar o seguir jugando?");
		fGeneral.addCommand (cmdTirar);
		fGeneral.addCommand (cmdVolver);
		fGeneral.addCommand (cmdLevantar);
		fGeneral.addCommand(cmdSuperarPares);
		fGeneral.setCommandListener (cmdli);
	    }	
	    catch (java.lang.NullPointerException e){
		ShowAlert ("Null Exception");
	    }
    }

    public void CreateFormComenzar (){
	    try{
	        formTirada.append("Pulsa en \"Tirar\" para empezar a jugar");
	        formTirada.addCommand(cmdTirar);
	       	formTirada.addCommand(exitCmd);
	        formTirada.addCommand(cmdAbout);
	        formTirada.setCommandListener(cmdli);
	    }	
	    catch (java.lang.NullPointerException e){
		//System.out.println("opciones_kinito()");
		ShowAlert ("Null Exception");
	    }
    }
    
    public void destroyApp (boolean d) {
            mdl.destroyApp(false);
            mdl.notifyDestroyed();
    }
    public void pauseApp () {}
    public void startApp () {}

    public void commandAction(Command c, Displayable d) {
	    try{
	        if (c == exitCmd) {
	            mdl.destroyApp(false);
	            mdl.notifyDestroyed();
	            return;
	        }
		else if (c == cmdTirar){
			tirada (useImages); // true uses images, false do not
		}
		//else if (c.getLabel() == "Tirar"){
		//}
		else if (c == cmdTirar){
			mostrar_opciones ();
		}
		else if (c == cmdTaparPasar){
			form_tapado ();
		}
		else if (c == cmdLevantar){
			form_levantado (useImages); // true uses images, false do not
		}
		else if (c == cmdTirarAbelardo){
			form_abelardo ();
		}
		else if (c == cmdAbout){
			About.showAbout(Display.getDisplay(mdl), cmdli, this);
		}
		else if (c == cmdTiraKino){ // Invocado desde mostrar_form_kino()
			tira_kino_player1(0); // 0 == tirar los 2 dados
		}
		else if (c == cmdKinoDado1){ // Invocado desde mostrar_form_kino()
			tira_kino_player1(1); // 1 == tirar el dado 1
		}
		else if (c == cmdKinoDado2){ // Invocado desde mostrar_form_kino()
			tira_kino_player1(2); // 2 tirar el dado 2
		}
		else if (c == cmdContinuar){
			tirada (useImages); // true uses images, false do not
		}
		else if (c == cmdKinoCiega){
			dado1 = getNumber();
			dado2 = getNumber();
			form_tapado ();
		}
		else if (c == cmdSuperarPares){
			formSuperarPares ();
		}

		/*
		if (c.getCommandType() == Command.BACK){
			mostrar_form_tirada ();
		}
		if (c.getCommandType() == Command.OK){
			System.out.println("Command.OK pressed!");
		}
		if (c.getCommandType() == Command.CANCEL){
			System.out.println("Command.CANCEL pressed!");
		}
		*/
	    }	
	    catch (java.lang.NullPointerException e){
		//System.out.println("opciones_kinito()");
		ShowAlert ("Null Exception: commandAction()");
	    }
	
	//System.out.println("Command Label: " + c.getLabel() + " - Tipo: " + c.getCommandType());
    }

    public void formSuperarPares ()
    {
	    try{
		    int d1 = getNumber ();
		    int d2 = getNumber ();
				    
		    CleanForm (fSuperaPares);
		    if (useImages){
			    fSuperaPares.append (imgCached[d1-1]);
			    fSuperaPares.append (imgCached[d2-1]);
		    }
		    else{
			    fSuperaPares.append (new StringBuffer().append(d1).append(" - ").append(d2).append("\n").toString());
		    }
		    fSuperaPares.setTitle("Supera los pares");	
		    fSuperaPares.addCommand (cmdSuperarPares);
		    fSuperaPares.addCommand (cmdTirar);
			   
		    fSuperaPares.append (new StringBuffer().append("Te quedan ").append(pares_intentos).append (" intentos").toString());
		    //System.out.println ("Intentos: " + pares_intentos);
		    if ((d1 == d2) && (dado1 == dado2)){
			if (d1 > dado1 && d2 > dado2){
			    fSuperaPares.setTitle("Pares superados!");
			    //fSuperaPares.append ("Mándale beber 1 vaso");
			    fSuperaPares.removeCommand (cmdSuperarPares);	
			    pares_intentos = 2;
			}
			else{
			    pares_intentos -= 1;
			}
		    }
		    else if (pares_intentos == 0){
			pares_intentos = 2;
			//fSuperaPares.append ("Bebes 1 vaso");
			fSuperaPares.removeCommand (cmdSuperarPares);
		    }
		    else{
			    //System.out.println ("Dados: " + dado1 + " - " + dado2 + "\nD: " + d1 + " - " + d2);
			    pares_intentos -= 1;
		    }
	    }	
	    catch (java.lang.NullPointerException e){
		//System.out.println("opciones_kinito()");
		ShowAlert ("Null Exception: formSuperarPares()");
	    }

        Display.getDisplay(mdl).setCurrent(fSuperaPares);
    }

    public void mostrar_form_tirada ()
    {
	//cleanMemory ();
        Display.getDisplay(mdl).setCurrent(formTirada);
    }

    public void form_abelardo ()
    {
	    try{
		int n=getNumber();
		int num = n-1;
		fAbelardo.setTitle ("Abelardo");
		// deletes the first item and decrement the counter, so the next item will be the "0" again
		fAbelardo.delete (0);
	    	if (useImages){
			fAbelardo.delete (0);
			fAbelardo.append (imgCached[num]);
		}
		else{
			fAbelardo.append (new StringBuffer().append(num).append("\n").toString());
		}
	        fAbelardo.append(new StringBuffer().append("Bebe la/el ").append(n).append(" ª/º\n").toString());
		Display.getDisplay(mdl).setCurrent(fAbelardo);
		//fAbelardo = null;
	    }
	    catch (java.lang.IllegalStateException e){
		ShowAlert ("Illegal State Exception");
	    }	
	    catch (java.lang.NullPointerException e){
		ShowAlert ("Null Exception: form_abelardo()");
	    }
    }

    public void form_tapado ()
    {
	    try{

		fGeneral.setTitle ("levanta o tira");
		Display.getDisplay(mdl).setCurrent (fGeneral);
	    }
	    catch (java.lang.IllegalStateException e){
		//System.out.println(e.toString());
		ShowAlert ("Illegal State Exception");
	    }
	    catch (java.lang.NullPointerException e){
		ShowAlert ("Null Exception: form_tapado()");
	    }
    }

    /*
     * Muestra los dados de la tirada anterior
     *
     * */
    public void form_levantado (boolean showImages)
    {
	try{
		CleanForm (fLevantado);
		fLevantado.setTitle ("Anterior tirada");

		int suma = dado1 + dado2;
		String str_t = new StringBuffer().append("La anterior tirada ha sido:\n\n").append("Dado1: ").append(dado1).append("\nDado2: ").append(dado2).append("\n\nTotal: ").append(suma).toString();
		
		if (showImages){
			fLevantado.append (imgCached[dado1-1]);
			fLevantado.append (imgCached[dado2-1]);
			/*
			String strAltText = new StringBuffer().append(dado1).append("").append(dado2).toString();
			if (!isImageCached(strAltText)){
				ImageItem imgItem = new ImageItem ("", getImage (dado1, dado2), Item.LAYOUT_CENTER, "Kinito 1.0");
				fLevantado.append (imgItem);
			}
			else{
				fLevantado.append(getImageCached(strAltText));
			}
			strAltText = null;
			*/
		}
		else{
			fLevantado.append (str_t);
		}
		Display.getDisplay (mdl).setCurrent (fLevantado);
		str_t = null;
		//fLevantado = null;
	    }
	    catch (java.lang.IllegalStateException e){
		ShowAlert ("Illegal State Exception");
	    }
	    catch (java.lang.NullPointerException e){
		ShowAlert ("Null Exception: form_levantado()");
	    }
		/*
	}
	catch (java.io.IOException e){
	    System.out.println("ERROR Levantado: " + e.toString());
	    form_levantado(false); // Si las imágenes no funcionan, volvemos a ejecutar la función pero con solo texto
	}
	*/
    }
    
    public void tira_kino_player1(int TiraDados)
    {
		//cleanMemory ();
	    try{
		    // clean the form

		    CleanForm (fKino);

		    fKino.setTitle("Contra Kinito");

		int suma=0;
		fKino.removeCommand (cmdTirar);
		fKino.removeCommand (cmdKinoCiega);
		fKino.removeCommand (cmdTiraKino);
		fKino.removeCommand (cmdTaparPasar);
		fKino.removeCommand (cmdKinoDado1);
		fKino.removeCommand (cmdKinoDado2);
		if (TiraDados == 0){
			dado1 = getNumber(); 
			dado2 = getNumber();
		}
		else if (TiraDados == 1){
			dado2 = getNumber();
		}
		else if (TiraDados == 2){
			dado1 = getNumber();
		}
		if (useImages){
			fKino.append (imgCached[dado1-1]);
			fKino.append (imgCached[dado2-1]);
		}
		else{
			fKino.append (new StringBuffer().append(dado1).append(" - ").append(dado2).toString());
		}
		kino_intentos += 1;
		if ((dado1 == dado2) || (dado2 == dado1)){
			kino_intentos -= 1;
		}
		if (kino_intentos < 3){
			fKino.append (new StringBuffer().append("Vas ").append(kino_intentos).append(" tiradas de 3\n\nVais ").append(kino_vasos).append(" vasos acumulados").toString());
		}
		if ((dado1 == 1 && dado2 == 2) 
				|| (dado1 == 2 && dado2 == 1)
				|| (dado1 == 5 && dado2 == 6)
				|| (dado1 == 6 && dado2 == 5)){
			fKino.append (new StringBuffer().append("PERFECTO!\n\nDevuélvele el kino y machácalo!!!\n\n").toString());
			kino_intentos = 0;
			kino_vasos += 1;
			fKino.addCommand (cmdTiraKino);
			fKino.addCommand (cmdTaparPasar);
		}
		else if (kino_intentos == 3){
			if (kino_vasos == 0) kino_vasos = 1;
			fKino.append (new StringBuffer().append("PERDISTE!\nBébete ").append(kino_vasos).append(" pedazo de vasos").toString());
			kino_intentos = 0;
			kino_vasos = 1;
			fKino.addCommand (cmdTirar);
			fKino.addCommand (cmdKinoCiega);
			fKino.removeCommand (cmdTiraKino);
			fKino.removeCommand (cmdTaparPasar);
			fKino.removeCommand (cmdKinoDado1);
			fKino.removeCommand (cmdKinoDado2);
		}
		else{
			fKino.addCommand (cmdTiraKino);
			fKino.addCommand (cmdTaparPasar);
			if (dado1 == 1 || dado1 == 2 || dado1 == 6 || dado1 == 5 ||
				dado2 == 1 || dado2 == 2 || dado2 == 6 || dado2 == 5){
				fKino.addCommand (cmdKinoDado1);
				fKino.addCommand (cmdKinoDado2);
			}
		}
		Display.getDisplay (mdl).setCurrent(fKino);
		//fkino1 = null;
	}
	catch (java.lang.IllegalStateException e){
		ShowAlert ("Illegal State Exception");
	}
	catch (Error ex){
		//System.out.println ("Excepción!:\n" + ex.toString());
	}
	catch (java.lang.NullPointerException e){
		ShowAlert ("Null Exception: tira_kino_player1()");
	}
    }
	
    public void opciones_kinito()
    {
	try{
		// clean the cached objects
		//cleanMemory ();
		Form fkino1 = new Form ("Kinito");
		fkino1.addCommand (cmdTirar);
		fkino1.addCommand (cmdTiraKino);
		fkino1.setCommandListener (cmdli);
		Display.getDisplay(mdl).setCurrent(fkino1);
		fkino1 = null;
	}
	catch (java.lang.NullPointerException e){
		ShowAlert ("Null Exception: opciones_kinito()");
	}
    }

    /*
     * Función principal para procesar los datos de los dados, generar los
     * dados aleatoriamente,...
    */

    public void tirada (boolean showImages)
    {
	    try{
		// clean the cached objects
		//cleanMemory ();
		// do not execute this function more than once at a time
		/*
		if (isRunning){
			Alert alt = new Alert ("Tranquilidad txo!", "Estamos procesando los datos, ¡Amparo!", null, AlertType.INFO);
			Display.getDisplay(mdl).setCurrent(alt);
			return ;
		}
		isRunning = true;
		*/

		ContDebug += 1;

		int suma=0;
		String str;
		dado1 = getNumber ();
		dado2 = getNumber ();
		suma = dado1 + dado2;
		CleanForm (fTirada);
		ContDebug += 1;
		fTirada.setTitle ("Tu tirada es");
		ContDebug += 1;
	        fTirada.removeCommand(cmdTirarAbelardo);
		ContDebug += 1;
		fTirada.removeCommand(cmdTirar);
		ContDebug += 1;
		fTirada.removeCommand(cmdTaparPasar);
		ContDebug += 1;
		if (showImages){
			//fTirada.append (new ImageItem("", imgCached[dado1-1], 3, ""));
			//fTirada.append (new ImageItem("", imgCached[dado2-1], 3, ""));
			fTirada.append (imgCached[dado1-1]);
			fTirada.append (imgCached[dado2-1]);
		}
		ContDebug += 1;
		
		str = new StringBuffer().append("Dado1: ").append(dado1).append("\nDado2: ").append(dado2).append("\n\n").toString();
		ContDebug += 1;
	
		if (dado1 != dado2){
			str = new StringBuffer().append(str).append("Total: ").append(suma).append("\n\n").toString();
		}
		else{
			if ((dado1 == 6 && dado2 == 6) || (dado2 == 6 && dado1 == 6)){
				fTirada.setTitle("REGATAAAA!!");
			}
			else{
				fTirada.setTitle("Parejas");
			}
			pares_intentos = 2;
		}
		ContDebug += 1;
	
		if ((dado1 == 1 && dado2 == 2) 
				|| (dado1 == 2 && dado2 == 1)
				|| (dado1 == 5 && dado2 == 6)
				|| (dado1 == 6 && dado2 == 5))
		{
			fTirada.setTitle("Kinito");
			
			
			mostrar_form_kinito();
			return;
		}
		else		
		if ((dado1 == 1 && dado2 == 3) || (dado2 == 1 && dado1 == 3)){ // 1 y 3 beben tres
			str = new StringBuffer().append(str).append("¡1 y 3 beben tres!").toString();
			fTirada.setTitle("1 y 3 beben tres");
			fTirada.addCommand(cmdTirar);
		}
		else
		if ((dado1 == 1 && dado2 == 4) || (dado1 == 4 && dado2 == 1)){ // Abelardo
			str = new StringBuffer().append(str).append("¡Abelardo!").toString();
			fTirada.setTitle("Abelardo");
		        fTirada.addCommand(cmdTirarAbelardo);
		}
		else{
	        	fTirada.removeCommand(cmdTirarAbelardo);
			fTirada.removeCommand(cmdTirar);
		
		        fTirada.addCommand(cmdTaparPasar);
		}
		ContDebug += 1;
		//if (showImages){
		//	fTirada.append (imgCached[dado1-1]);
		//	fTirada.append (imgCached[dado2-1]);
			
			// little cache system
			// XXX: Use *always* StringBuffer to save memory
			//String strAltText = new StringBuffer().append(dado1).append("").append(dado2).toString();
			//System.out.println("tirada(): " + strAltText);
			
			/*
			if (!isImageCached(strAltText)){
				System.out.println("tirada(): " + strAltText);
				//img = getImage(dado1, dado2);
				imgItem = new ImageItem ("", scaleFastImage(getImage(dado1, dado2), 30, 30, false), Item.LAYOUT_CENTER, strAltText);
				//setImageCached (imgItem);
				fTirada.append (imgItem);
				imgItem = null;
				img = null;
			}
			else{
				ImageItem img_t = getImageCached (strAltText);
				System.out.println("ImageCached: " + imgCachedCounter + " - " + img_t.getImage().getWidth());
				fTirada.append (img_t.getImage());
				img_t = null;
			}
			*/
			//strAltText = null;
		//}
		if (!showImages){
			//fTirada.delete (0);
			fTirada.append (str);
		}
		ContDebug += 1;
		// Debug de capacidades del dispositivo
	
		//String strColor = (Display.getDisplay(mdl).isColor()) ? "Soporta Colores\n" : "No soporta colores\n";
		//int nColor = Display.getDisplay(mdl).numColors();
		//String str_prueba = "Soporta color: " + strColor + "\nColores: " + nColor;
		//fTirada.append (str_prueba);
		//fTirada.append ("Memoria libre: " + Runtime.getRuntime().freeMemory());
		
		Display.getDisplay (mdl).setCurrent (fTirada);
		//str = null;
		//fTirada = null;
	    }
	    catch (java.lang.NullPointerException e){
		//System.out.println("ERROR Tirada NULL: " + e.toString());
		//isRunning = false;
		ShowAlert ("Null Exception: tirada(" + ContDebug + ")");
	    }
    }

    public void mostrar_form_kinito ()
    {
	    //cleanMemory ();
	try{
		kino_dados = 0; // por defecto tiramos con los 2 dados: "0" dos dados, "1" dado1 y "2" dado 2
		kino_intentos = 0;
		kino_vasos=1;
        
		CleanForm (formKinito);
		formKinito.setTitle("Kinito! suerte!");
		if (useImages){
			formKinito.append (imgCached[dado1-1]);
			formKinito.append (imgCached[dado2-1]);
		}
		else{
			formKinito.append (new StringBuffer().append(dado2).append(" - ").append(dado2).toString());
		}
		formKinito.append ("Tienes 3 tiradas.\nPares repite tirada.\n");
       		Display.getDisplay(mdl).setCurrent(formKinito);
	    }
	    catch (java.lang.IllegalStateException e){
		//mostrar_form_kinito();	
		ShowAlert ("Illegal state Exception");
	}
	catch (java.lang.NullPointerException e){
		//System.out.println("mostrar_form_kinito()");
		ShowAlert ("Null Exception: mostrar_form_kinito()");
	}
    }

    public void mostrar_opciones ()
    {
		//cleanMemory ();
	try{
		List lstOptions = new List ("Selecciona tu opción", 0);
		lstOptions.addCommand (cmd_lev);
		lstOptions.addCommand (cmdTirar);
		lstOptions.setCommandListener (cmdli);
		Display.getDisplay(mdl).setCurrent(lstOptions);

		lstOptions = null;
	}
	catch (java.lang.NullPointerException e){
		//System.out.println("mostrar_opciones()");
		ShowAlert ("Null Exception: mostrar_opciones()");
	}
    }

    public void CleanForm (Form frm){
	    frm.setTitle("");
	    for (int i = frm.size() - 1;i >= 0;i--){
		    frm.delete(i);
		    //System.out.println ("eliminando [" + i + "]");
	    }
    }

    public Image getImage(int num) throws java.io.IOException
    {
	    Image img = Image.createImage (new StringBuffer().append(strThemePrefix).append(num).append(".jpg").toString());
	    return img;
    }
    
    public Image getImage(int num1, int num2) throws java.io.IOException
    {
	    String str = new StringBuffer().append(strThemePrefix).append(num1).append(num2).append(".jpg").toString();

	    // Esto nos permite ahorrar en imágenes, por ahora. Reducimos en la mitad el tamaño.
	    if ((num1 == 1 && num2 == 2) || 
			    (num1 == 1 && num2 == 3) ||
			    (num1 == 1 && num2 == 4) ||
			    (num1 == 1 && num2 == 5) ||
			    (num1 == 1 && num2 == 6)){
	    	str = new StringBuffer().append(strThemePrefix).append(num2).append(num1).append(".jpg").toString();
		//System.out.println ("Shift Dados: " + num1 + " - " + num2);
	    }
	    else if ((num1 == 2 && num2 == 3) || 
			    (num1 == 2 && num2 == 4) ||
			    (num1 == 2 && num2 == 5) ||
			    (num1 == 2 && num2 == 6)){
	    	//str = strThemePrefix + num2 + num1 + ".jpg";
	    	str = new StringBuffer().append(strThemePrefix).append(num2).append(num1).append(".jpg").toString();
		//System.out.println ("Shift Dados: " + num1 + " - " + num2);
	    }
	    else if ((num1 == 3 && num2 == 4) || 
			    (num1 == 3 && num2 == 5) ||
			    (num1 == 3 && num2 == 6)){
	    	str = new StringBuffer().append(strThemePrefix).append(num2).append(num1).append(".jpg").toString();
		//System.out.println ("Shift Dados: " + num1 + " - " + num2);
	    }
	    else if ((num1 == 4 && num2 == 5) || 
			    (num1 == 4 && num2 == 6)){
	    	str = new StringBuffer().append(strThemePrefix).append(num2).append(num1).append(".jpg").toString();
		//System.out.println ("Shift Dados: " + num1 + " - " + num2);
	    }
	    else if ((num1 == 5 && num2 == 6)){
	    	str = new StringBuffer().append(strThemePrefix).append(num2).append(num1).append(".jpg").toString();
		//System.out.println ("Shift Dados: " + num1 + " - " + num2);
	    }
	    Image img = Image.createImage (str) ;

	    return img;
    }
    


	// code taken from:
	// http://cvs.sourceforge.net/viewcvs.py/kobjects/utils4me/src/org/kobjects/lcdui/Scale/Image.java?rev=HEAD&content-type=text/vnd.viewcvs-markup

    
	public static Image scaleFastImage (Image src, int dstW, int dstH, boolean percent) {
		int srcW = src.getWidth();
		int srcH = src.getHeight();
		if (percent){
			srcW = Math.abs((src.getWidth() * dstW) / 100); // get the percent to scale
			srcH = Math.abs((src.getHeight() * dstH) / 100); // get the percent to scale
		}
	
		Image tmp = Image.createImage(dstW, srcH);
		Graphics g = tmp.getGraphics();
		
		int delta = (srcW << 16) / dstW;
		int pos = delta/2;
		
		for (int x = 0; x < dstW; x++) {
			g.setClip(x, 0, 1, srcH);
			g.drawImage(src, x - (pos >> 16), 0, Graphics.LEFT | Graphics.TOP);
			pos += delta;
		}
		
		Image dst = Image.createImage(dstW, dstH);
		g = dst.getGraphics();
		
		delta = (srcH << 16) / dstH;
		pos = delta/2;
		
		for (int y = 0; y < dstH; y++) {
			g.setClip(0, y, dstW, 1);
			g.drawImage(tmp, 0, y - (pos >> 16), Graphics.LEFT | Graphics.TOP);
			pos += delta;	
		}
		
		return dst;		
	}

	// obtiene los números aletorios de los dados

	public int getNumber ()
	{
		int dado=0;
		dado = rnd.nextInt();
		dado = Math.abs(dado);
		dado = dado % 6;
		dado += 1;
		
		return dado;
	}
/*
	public ImageItem getImageCached (String str)
	{
		
		try{
			ImageItem imgItem_temp;
			for (int i=0;i <= imgCachedTotal;i++)
			{
				if (imgCached.elementAt(i) == null) break;
				imgItem_temp = (ImageItem) imgCached.elementAt(i);
				if (str.compareTo(imgItem_temp.getAltText()) == 0){
					System.out.println("Extrayendo de la caché de imágenes la \"" + i + "\" - " + str);
					return imgItem_temp;
				}
			}
			System.out.println("getImageCached(): " + str);
			imgItem_temp = null;

		}
		catch (java.lang.NullPointerException e){
			System.out.println("getImgCached(): " + e.getMessage());
		}
	    	catch (java.lang.OutOfMemoryError e){
		    // what we should do in this case? show this alert or just change to "text mode"?
		    Alert alt = new Alert ("I can't load the Image, No memory man!");
		    alt.setType(AlertType.ERROR);
		    alt.addCommand (cmdVolver);
		    alt.setCommandListener (cmdli);
		    Display.getDisplay(mdl).setCurrent(alt);
	    	}
		return null;
		
	}

	public void setImageCached (ImageItem imgItem)
	{
		try{
			if (imgCachedCounter >= imgCachedTotal) 
				return ;

			System.out.println("setImgCached(): " + imgCachedCounter);
			//imgCached[imgCachedCounter] = imgItem;
			imgCached.addElement((Object)imgItem);
			imgCachedCounter++;
			for (int i=0;i <= imgCachedTotal;i++){
				if (imgCached.elementAt(i) != null)
					System.out.println("setImageCached(): " + imgCachedCounter);
			}
		}
		catch (java.lang.ArrayIndexOutOfBoundsException ao){
		}
		catch (java.lang.NullPointerException e){
			System.out.println("setImgCached(): " + e.getMessage());
		}
	    	catch (java.lang.OutOfMemoryError e){
		    // what we should do in this case? show this alert or just change to "text mode"?
		    Alert alt = new Alert ("I can't load the Image, No memory man!");
		    alt.setType(AlertType.ERROR);
		    alt.addCommand (cmdVolver);
		    alt.setCommandListener (cmdli);
		    Display.getDisplay(mdl).setCurrent(alt);
	    	}
	}

	public boolean isImageCached (String num)
	{
		// FIXME: while debugging the game for fix a problem with mem massive consumption, disable imgcached
		
		String numRev = new StringBuffer (num).reverse().toString();
		System.out.println("isImageCached()num: " + imgCached.size() + " - " + imgCached.capacity() + " - " + numRev);
		try{
			for (int i=0;i <= imgCachedTotal;i++)
			{
				//if (imgCached.elementAt(i) == null) break;
				//System.out.println ("imgCached[" + i + "]: " + imgCached[i].getAltText() + " - LengthCached: " + imgCached[i].getAltText().length() + " - LengthPassed: " + num.length());
				ImageItem img = (ImageItem)imgCached.elementAt(i);
				if (num.compareTo(img.getAltText()) == 0 || numRev.compareTo(img.getAltText()) == 0){
					System.out.println("isImageCached(): YES - " + num + " - Alt: " + img.getAltText());
					return true;
				}
				else{
					System.out.println("isImageCached(): NO - " + num + " - Alt: " + img.getAltText() + " -Rev: " + numRev);
				}
			}
			//return false;	
		}
		catch (java.lang.NullPointerException e){
			System.out.println("isImgCached(): " + e.getMessage());
		}
	    	catch (java.lang.OutOfMemoryError e){
		    // what we should do in this case? show this alert or just change to "text mode"?
		    Alert alt = new Alert ("I can't load the Image, No memory no fun!" + e.getMessage());
		    alt.setType(AlertType.ERROR);
		    alt.addCommand (cmdVolver);
		    alt.setCommandListener (cmdli);
		    Display.getDisplay(mdl).setCurrent(alt);
	    	}
		catch (java.lang.ArrayIndexOutOfBoundsException ao){
			return false;
		}
		return false;
	}
*/
	public void cleanMemory ()
	{
		// System.gc();
		Runtime.getRuntime().gc();
	}

	public final void loadImages ()
	{
		try{
			if (Display.getDisplay(mdl).isColor()){
				imgCached = new Image[6];
				// LAYOUTS: 0=DEFAULT, 1=LEFT, 2=RIGHT, 3=CENTER
				imgCached[0] = Image.createImage(getImage(1));
				imgCached[1] = Image.createImage(getImage(2));
				imgCached[2] = Image.createImage(getImage(3));
				imgCached[3] = Image.createImage(getImage(4));
				imgCached[4] = Image.createImage(getImage(5));
				imgCached[5] = Image.createImage(getImage(6));
			}
		}
		catch (java.lang.NullPointerException e){
			ShowAlert ("Null Exception: loadImages()");
		}
		catch (java.io.IOException e){
			ShowAlert ("IOException");
		}
	}

	public void ShowAlert (String strError)
	{
		Alert alt = new Alert ("Error", strError, null, AlertType.ERROR);
       		Display.getDisplay(mdl).setCurrent(alt);
	}
}


