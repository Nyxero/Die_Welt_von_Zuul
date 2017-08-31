package ort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import character.Gegner;
import character.Kaufmann;
import character.NPC;
import gegenstand.Crafting;
import gegenstand.Gegenstand;
import gegenstand.Nahrung;
import gegenstand.Waffe;

public class Landkarte {
	private Raum startpoint = null;
	private ArrayList<Raum> raeume = new ArrayList<Raum>();

	public Landkarte() {

	}

	/**
	 * Erzeuge alle R�ume und verbinde ihre Ausg�nge miteinander.
	 */
	public void raeumeAnlegen() {
		Raum draussen, hoersaal, cafeteria, labor, buero, keller, abstellkammer;
		Gegenstand regenschirm, tasse, messer, erlenmeyerkolben, ventilator, peitsche, muffin;
		Landscape panther;

		// die R�ume erzeugen
		draussen = new Raum("vor dem Haupteingang der Universit�t", this);
		raeume.add(draussen);
		startpoint = draussen;
		hoersaal = new Raum("in einem Vorlesungssaal", this);
		raeume.add(hoersaal);
		cafeteria = new Raum("in der Cafeteria der Uni", this);
		raeume.add(cafeteria);
		labor = new Raum("in einem Rechnerraum", this);
		raeume.add(labor);
		buero = new Raum("im Verwaltungsb�ro der Informatik", this);
		raeume.add(buero);
		keller = new Raum("im Keller des Rechenzentrums", this);
		raeume.add(keller);
		abstellkammer = new Raum("Die Abstellkammer", this);
		raeume.add(abstellkammer);

		regenschirm = new Waffe("Schirmy", "Ein pinker Regenschirm", 5, 20, 10);
		tasse = new Crafting("Tasse", "Auf Ihr Steht: '#1 Dad'", 2, 20);
		messer = new Waffe("Messer", "Es hat 'Made with Kinderarbeit' aufgedruckt", 1, 30, 50);
		erlenmeyerkolben = new Crafting("Erlenmeyerkolben", "Die Fl�ssigkeit darin riecht Alkoholisch", 3, 5);
		ventilator = new Crafting("Ventilator", "F�r die schwitzige Jahreszeit", 30, 70);
		peitsche = new Waffe("Peitsche", "Sie hat 'BDSM' eingraviert", 10, 150, 20);
		muffin = new Nahrung("Muffin", "Er glitzert :O", 3, 15);

		panther = new Teleporter("Panther", "Eine schwarze Raubkatze");

		// die Ausg�nge initialisieren
		draussen.setzeAusgang("east", hoersaal);
		draussen.setzeAusgang("south", labor);
		draussen.setzeAusgang("west", cafeteria);
		draussen.gegenstandAblegen(regenschirm);
		NPC np = new NPC("Karsten", 100, draussen, null);
		np.setText("Hi, ich heisse Marvin");
		draussen.setzeNPC(np);

		hoersaal.setzeAusgang("west", draussen);
		hoersaal.gegenstandAblegen(tasse);
		LinkedList<Gegenstand> items = new LinkedList<Gegenstand>();
		items.add(ventilator);
		items.add(tasse);
		Kaufmann kaufmann = new Kaufmann("Walter", 200, hoersaal, items);
		kaufmann.setText("Hallo, ich bin Walter m�chtest du meine Waren begutachten?");
		hoersaal.setzeNPC(kaufmann);

		cafeteria.setzeAusgang("east", draussen);
		cafeteria.gegenstandAblegen(messer);

		labor.setzeAusgang("north", draussen);
		labor.setzeAusgang("east", buero);
		labor.setzeAusgang("down", keller);
		labor.gegenstandAblegen(erlenmeyerkolben);
		labor.gegenstandAblegen(muffin);

		buero.setzeAusgang("west", labor);
		buero.gegenstandAblegen(ventilator);

		keller.setzeAusgang("up", labor);
		keller.setzeAusgang("east", abstellkammer);
		keller.gegenstandAblegen(peitsche);
		keller.setzeGegner(new Gegner("Blubb" ,10, keller, null));

		abstellkammer.setzeAusgang("west", keller);
		abstellkammer.landschaftBauen(panther);
	}

	public Raum getStartpoint() {
		return startpoint;
	}

	public Raum getRandomRoom() {
		Random randomNumber = new Random();
		int roomNumber = randomNumber.nextInt(raeume.size());

		return raeume.get(roomNumber);
	}
}
