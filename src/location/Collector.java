package location;

import java.util.ArrayList;
import java.util.HashMap;

import character.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Definiert Objekte der Klasse Collector, die Items vom Spieler annehmen und
 * eine Reaktion ausf�hren
 */
public class Collector extends Landscape {
	private int amount;
	private ArrayList<Runnable> execute;
	private String key;
	private int maxAmount;

	/**
	 * 
	 * @param name
	 *            Name des Objekts
	 * @param beschreibung
	 *            Beschreibung des Objekts
	 * @param image
	 *            Bild des Objekts in der 2D-Welt
	 * @param x
	 *            Position des Objekts in der 2D-Welt x-Achse
	 * @param y
	 *            Position des Objekts in der 2D-Welt y-Achse
	 * @param gc
	 *            Grafischer Kontext zum Darstellen des Objekts
	 * @param landscapeResponse
	 *            Liste, die die Enums m�glicher Interaktionen und die dazugeh�rigen
	 *            Texte speichert
	 * @param key
	 *            Name des Items, welches der Collector annimmt
	 * @param amount
	 *            Menge der bisher gesammelten Items (f�r k�nftige Erweiterungen
	 *            m�glicherweise nicht immer 0 bei Erstellung des Objekts)
	 * @param maxAmount
	 *            Zielwert f�r das Sammeln von Items
	 * @param execute
	 *            Liste von Befehlen, die in Worldmap �bergeben wird
	 */
	public Collector(String name, String beschreibung, Image image, int x, int y, GraphicsContext gc,
			HashMap<LandscapeResponse, String> landscapeResponse, String key, int amount, int maxAmount,
			ArrayList<Runnable> execute) {
		super(name, beschreibung, image, x, y, gc, landscapeResponse);
		this.key = key;
		this.amount = amount;
		this.maxAmount = maxAmount;
		this.execute = execute;
	}

	@Override
	public void onEnterRoom(Player spieler) {

	}

	@Override
	/**
	 * @param Spieler
	 *            Spieler, der mit dem Objekt interagiert
	 */
	public void onUse(Player player) {
		/*
		 * Wenn der Spieler das gesuchte Item nicht tr�gt, aber noch gesammelt wird,
		 * gebe Text aus
		 */
		if (player.getItem(key) == null && amount < maxAmount) {
			textbox.addText(getResponse(LandscapeResponse.USE_RESPONSE));
		}

		/*
		 * Wenn das Sammeln abgeschlosen ist, tue nichts
		 */
		else if (amount >= maxAmount) {

		}

		/*
		 * Wenn Spieler das Objekt tr�gt und mit dieser Abgabe das Ziel noch nicht
		 * erreicht ist, gib Item ab und Text aus
		 */
		else if (amount + 1 < maxAmount) {
			player.dropItem(key);
			amount += 1;
			textbox.addText(getResponse(LandscapeResponse.COLLECT_RESPONSE));
			/*
			 * Wenn Spieler das Objekt tr�gt und mit dieser Abgabe das Ziel erreich ist, gib
			 * Item ab, gib Text aus, f�hre Befehle aus
			 */
		} else if (amount + 1 == maxAmount) {
			player.dropItem(key);
			amount += 1;
			textbox.addText(getResponse(LandscapeResponse.COLLECTFINISH_RESPONSE));

			for (Runnable runnable : execute) {
				runnable.run();
			}
		}
	}

	public ArrayList<Runnable> getExecute() {
		return execute;
	}

	/**
	 * Klont die �bergebene ArrayList
	 * 
	 * @param execute
	 *            Liste aus Worlmap, die als Zwischenspeicher f�r Befehle dient, bis
	 *            diese hier �bergeben werden
	 */
	public void setExecute(ArrayList<Runnable> execute) {
		this.execute = new ArrayList<Runnable>(execute);
	}

}
