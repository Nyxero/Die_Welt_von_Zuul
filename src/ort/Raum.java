package ort;
import java.util.HashMap;
import java.util.LinkedList;

import gegenstand.Gegenstand;

/**
 * Diese Klasse modelliert R�ume in der Welt von Zuul.
 * 
 * Ein "Raum" repr�sentiert einen Ort in der virtuellen Landschaft des Spiels.
 * Ein Raum ist mit anderen R�umen �ber Ausg�nge verbunden. M�gliche Ausg�nge
 * liegen im Norden, Osten, S�den und Westen. F�r jede Richtung h�lt ein Raum
 * eine Referenz auf den benachbarten Raum.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Raum {
	private String beschreibung;
	private HashMap<String, Raum> ausgaenge = new HashMap<String, Raum>();
	private LinkedList<Gegenstand> gegenstaende = new LinkedList<Gegenstand>();

	/**
	 * Erzeuge einen Raum mit einer Beschreibung. Ein Raum hat anfangs keine
	 * Ausg�nge.
	 * 
	 * @param beschreibung
	 *            enth�lt eine Beschreibung in der Form "in einer K�che" oder
	 *            "auf einem Sportplatz".
	 */
	public Raum(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * Definiere die Ausg�nge dieses Raums. Jede Richtung f�hrt entweder in
	 * einen anderen Raum oder ist 'null' (kein Ausgang).
	 * 
	 * @param norden
	 *            Der Nordeingang.
	 * @param osten
	 *            Der Osteingang.
	 * @param sueden
	 *            Der S�deingang.
	 * @param westen
	 *            Der Westeingang.
	 */
	public void setzeAusgang(String richtung, Raum raum) {
		ausgaenge.put(richtung, raum);
	}

	public Raum getAusgang(String richtung) {
		return ausgaenge.get(richtung);
	}

	public String ausgaengeToString() {
		StringBuilder sb = new StringBuilder("");
		for (String key : ausgaenge.keySet()) {
			sb.append(key);
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public void gegenstandAblegen(Gegenstand gegenstand) {
		gegenstaende.add(gegenstand);
	}
	
	public Gegenstand gegenstandAufheben(String name) {
		for (Gegenstand gs : gegenstaende) {
			if (gs.getName().equalsIgnoreCase(name)) {
				gegenstaende.remove(gs);
				return gs;
			}
		}
		return null;
	}
	
	public Gegenstand getGegenstand(String name) {
		for (Gegenstand gs : gegenstaende) {
			if (gs.getName().equalsIgnoreCase(name)) {
				return gs;
			}
		}
		return null;
	}
	
	public String gegenstaendeToString() {
		StringBuilder sb = new StringBuilder("");
		for (Gegenstand gegenstand : gegenstaende) {
			sb.append(gegenstand.getName());
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public String getLongDesciption() {
		StringBuilder sb = new StringBuilder("");
		sb.append("Sie sind ");
		sb.append(gibBeschreibung());
		sb.append(System.getProperty("line.separator"));
		sb.append("Gegenst�nde: ");
		sb.append(gegenstaendeToString());
		sb.append(System.getProperty("line.separator"));
		sb.append("Ausg�nge: ");
		sb.append(ausgaengeToString());
		return sb.toString();
	}

	/**
	 * @return Die Beschreibung dieses Raums.
	 */
	public String gibBeschreibung() {
		return beschreibung;
	}
}