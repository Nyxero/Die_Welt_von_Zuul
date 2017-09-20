package location;

import java.util.ArrayList;
import java.util.Random;

import character.Enemy;
import item.Crafting;
import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.Usefull;
import main.ZuulUI;

public class Worldmap {
	private Room startpoint = null;
	private ArrayList<Room> raeume = new ArrayList<Room>();
	private GraphicsContext gc;

	public Worldmap(GraphicsContext gc) {
		this.gc = gc;
	}
	
	

	/**
	 * Erzeuge alle R�ume und verbinde ihre Ausg�nge miteinander.
	 */
	public void raeumeAnlegen() {
		Room draussen, cafeteria;// , hoersaal, cafeteria, labor, buero, keller, abstellkammer;

		draussen = new Room("Haupteingang der Universit�t" + System.getProperty("line.separator")
				+ "Das Gel�nde ist verlassen, in dieser dunklen Nacht. Die Ger�chte besagen in den Tiefen dieses Ortes w�rde die"
				+ System.getProperty("line.separator")
				+ "Menschheit antworten finden. Vielleicht sogar Erl�sung. Bist du deswegen hier?", this,
				new Image(ZuulUI.class.getResourceAsStream("/Bilder/Draussen.png")), gc);
		cafeteria = new Room("test", this, Usefull.linkToImage("/Bilder/Cafeteria.png"), gc);
		raeume.add(draussen);
		startpoint = draussen;
		
		Item ente;
		
		ente = new Crafting("Ente", "I A", 5, Usefull.linkToImage("/Bilder/Ente.png"), 10, 200, gc, true);
		Enemy monster = new Enemy("Waldo", "Ein super toller Gegner", draussen, 700, 700, Usefull.linkToImage("/Bilder/Monster.png"), gc, null);
		
		draussen.addItem(ente);
		draussen.setzeGegner(monster);
		draussen.setzeAusgang(Usefull.linkToImage("/Bilder/tuer1.png"), gc, new Point2D(0, 400), new Point2D(700, 400), cafeteria);
		/*
		 * Gegenstand kanninchen, lachs; Landscape panther, goldteller; ArrayList<Raum>
		 * destination = new ArrayList<Raum>(); HashMap<LandscapeResponse, String>
		 * landscapeResponse = new HashMap<LandscapeResponse, String>();
		 * ArrayList<Runnable> execute = new ArrayList<Runnable>(); NPC schnurrkatze;
		 * 
		 * // die R�ume erzeugen
		 * 
		 * draussen = new Raum("Haupteingang der Universit�t" +
		 * System.getProperty("line.separator") +
		 * "Das Gel�nde ist verlassen, in dieser dunklen Nacht. Die Ger�chte besagen in den Tiefen dieses Ortes w�rde die"
		 * + System.getProperty("line.separator") +
		 * "Menschheit antworten finden. Vielleicht sogar Erl�sung. Bist du deswegen hier?"
		 * , this); raeume.add(draussen); startpoint = draussen; hoersaal = new
		 * Raum("in einem Vorlesungssaal", this); raeume.add(hoersaal); cafeteria = new
		 * Raum("in der Cafeteria der Uni", this); raeume.add(cafeteria); labor = new
		 * Raum("in einem Rechnerraum", this); raeume.add(labor); buero = new
		 * Raum("im Verwaltungsb�ro der Informatik", this); raeume.add(buero); keller =
		 * new Raum("im Keller des Rechenzentrums", this); raeume.add(keller);
		 * abstellkammer = new Raum("Die Abstellkammer", this);
		 * raeume.add(abstellkammer);
		 * 
		 * // die Ausg�nge initialisieren /*draussen.setzeAusgang("east", hoersaal);
		 * draussen.setzeAusgang("south", labor); draussen.setzeAusgang("west",
		 * cafeteria); hoersaal.setzeAusgang("west", draussen);
		 * cafeteria.setzeAusgang("east", draussen); labor.setzeAusgang("north",
		 * draussen); labor.setzeAusgang("east", buero); buero.setzeAusgang("west",
		 * labor); buero.setzeAusgang("down", keller); keller.setzeAusgang("up", buero);
		 * keller.setzeAusgang("east", abstellkammer);
		 * abstellkammer.setzeAusgang("west", keller);
		 * 
		 * // Gegenst�nde kanninchen = new Crafting("Kanninchen",
		 * "Ein wei�es Kanninchen", 5, 1000); lachs = new Crafting("Lachs",
		 * "Herrlicher, roter Lachs", 2, 50);
		 * 
		 * // Gegenst�nde ablegen cafeteria.gegenstandAblegen(kanninchen);
		 * 
		 * // Landschaft initialisieren
		 * landscapeResponse.put(LandscapeResponse.USE_RESPONSE,
		 * "Du streichelst den Panther!");
		 * landscapeResponse.put(LandscapeResponse.ENTER_RESPONSE,
		 * "Ein Panther springt aus der Dunkelheit, packt dich im Nacken und verschleppt dich in einen anderen Raum!"
		 * ); landscapeResponse.put(LandscapeResponse.REMOVE_RESPONSE,
		 * "Ein Panther springt aus der Dunkelheit und faucht. Dein wei�es Kanninchen hoppelt davon und der Panther jagt ihm nach!"
		 * ); destination.add(draussen); destination.add(cafeteria);
		 * destination.add(hoersaal); destination.add(labor); destination.add(buero);
		 * destination.add(keller); panther = new Teleporter("Panther",
		 * "Eine schwarze Raubkatze", landscapeResponse, "Kanninchen", destination);
		 * 
		 * execute.add(() -> System.out.println("Es schnurrt!")); goldteller = new
		 * Sammler("Goldteller", "Ein goldener Teller", landscapeResponse, "Kanninchen",
		 * execute); // Landschaft setzen abstellkammer.landschaftBauen(panther);
		 * draussen.landschaftBauen(goldteller);
		 */

		// NPC initalisieren
		// schnurrkatze = new NPC("Schnurrkatze", 100, draussen, 10, 10, null);
		// schnurrkatze.setText("Die schwarze Katze schnurrt. Und spricht. Die
		// Menschheit musste sich schnell an derlei Dinge gew�hnen." +
		// System.getProperty("line.separator") +
		// "'Lachs', maunzt sie." + System.getProperty("line.separator") +
		// "'Der Mensch m�ge mir Lachs aus der Cafeteria bringen, damit ich ihm ein
		// Geheimnis verrate." + System.getProperty("line.separator") +
		// "Lege er es auf meinen Teller. Nun hinfort!'" +
		// System.getProperty("line.separator"));
		// NPC setzen
		// draussen.setzeNPC(schnurrkatze);
		// keller.setzeGegner(new Gegner("Blubb",10, keller, 10, 10, null));
		/*
		 * Raum draussen, hoersaal, cafeteria, labor, buero, keller, abstellkammer;
		 * Gegenstand regenschirm, tasse, messer, erlenmeyerkolben, ventilator,
		 * peitsche, muffin, kanninchen; Landscape panther; HashMap<LandscapeResponse,
		 * String> landscapeResponse = new HashMap<LandscapeResponse, String>();
		 * 
		 * // die R�ume erzeugen draussen = new
		 * Raum("vor dem Haupteingang der Universit�t", this); raeume.add(draussen);
		 * startpoint = draussen; hoersaal = new Raum("in einem Vorlesungssaal", this);
		 * raeume.add(hoersaal); cafeteria = new Raum("in der Cafeteria der Uni", this);
		 * raeume.add(cafeteria); labor = new Raum("in einem Rechnerraum", this);
		 * raeume.add(labor); buero = new Raum("im Verwaltungsb�ro der Informatik",
		 * this); raeume.add(buero); keller = new Raum("im Keller des Rechenzentrums",
		 * this); raeume.add(keller); abstellkammer = new Raum("Die Abstellkammer",
		 * this); raeume.add(abstellkammer);
		 * 
		 * regenschirm = new Waffe("Schirmy", "Ein pinker Regenschirm", 5, 10); tasse =
		 * new Crafting("Tasse", "Auf Ihr Steht: '#1 Dad'", 2); messer = new
		 * Waffe("Messer", "Es hat 'Made with Kinderarbeit' aufgedruckt", 1, 50);
		 * erlenmeyerkolben = new Crafting("Erlenmeyerkolben",
		 * "Die Fl�ssigkeit darin riecht Alkoholisch", 3); ventilator = new
		 * Crafting("Ventilator", "F�r die schwitzige Jahreszeit", 30); peitsche = new
		 * Waffe("Peitsche", "Sie hat 'BDSM' eingraviert", 10, 20); muffin = new
		 * Nahrung("Muffin", "Er glitzert :O", 3); muffin.setEssbar(true); kanninchen =
		 * new Crafting("Kanninchen", "Ein wei�es Kanninchen", 5);
		 * 
		 * landscapeResponse.put(LandscapeResponse.USE_RESPONSE,
		 * "Du streichelst den Panther!");
		 * landscapeResponse.put(LandscapeResponse.ENTER_RESPONSE,
		 * "Ein Panther springt aus der Dunkelheit, packt dich im Nacken und verschleppt dich in einen anderen Raum!"
		 * ); landscapeResponse.put(LandscapeResponse.REMOVE_RESPONSE,
		 * "Ein Panther springt aus der Dunkelheit und faucht. Dein wei�es Kanninchen hoppelt davon und der Panther jagt ihm nach!"
		 * ); panther = new TeleporterTrap("Panther", "Eine schwarze Raubkatze",
		 * landscapeResponse, "Kanninchen");
		 * 
		 * // die Ausg�nge initialisieren draussen.setzeAusgang("east", hoersaal);
		 * draussen.setzeAusgang("south", labor); draussen.setzeAusgang("west",
		 * cafeteria); draussen.gegenstandAblegen(regenschirm);
		 * 
		 * hoersaal.setzeAusgang("west", draussen); hoersaal.gegenstandAblegen(tasse);
		 * 
		 * cafeteria.setzeAusgang("east", draussen);
		 * cafeteria.gegenstandAblegen(messer);
		 * 
		 * labor.setzeAusgang("north", draussen); labor.setzeAusgang("east", buero);
		 * labor.setzeAusgang("down", keller);
		 * labor.gegenstandAblegen(erlenmeyerkolben); labor.gegenstandAblegen(muffin);
		 * labor.gegenstandAblegen(kanninchen);
		 * 
		 * buero.setzeAusgang("west", labor); buero.gegenstandAblegen(ventilator);
		 * 
		 * keller.setzeAusgang("up", labor); keller.setzeAusgang("east", abstellkammer);
		 * keller.gegenstandAblegen(peitsche); keller.setzeGegner(new Gegner("Blubb"
		 * ,10, keller, null));
		 * 
		 * abstellkammer.setzeAusgang("west", keller);
		 * abstellkammer.landschaftBauen(panther);
		 */
	}

	public Room getStartpoint() {
		return startpoint;
	}

	public Room getRandomRoom() {
		Random randomNumber = new Random();
		int roomNumber = randomNumber.nextInt(raeume.size());

		return raeume.get(roomNumber);
	}
}
