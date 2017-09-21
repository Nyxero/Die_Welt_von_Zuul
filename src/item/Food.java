package item;

import character.Character;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Die hier beschreibene Klasse definiert ein eine Spezialisierung von Item mit
 * dem Namen Food. Es repr�sentiert ein essbares Objekt.
 *
 */
public class Food extends Item {
	private int changeHealthPoints;

	/**
	 * Konstruktor von Food.
	 * 
	 * @param name
	 *            Name von dem Essen.
	 * @param description
	 *            Beschreibung von dem Essen
	 * @param price
	 *            Wird ben�tigt f�r eine sp�tere Erweiterung.
	 * @param image
	 *            Stellt die visualisierung dar.
	 * @param x
	 *            X Koordinaten
	 * @param y
	 *            Y Koordinaten
	 * @param graphicsContext
	 *            Wird ben�tigt um das Objekt in das JavaFX Fenster zu Zeichnen.
	 * @param changeHealthPoints
	 *            Beschreibt den Integer Wert wie die Lebenspunkte eines Charakters
	 *            ge�ndert werden sollen.
	 */
	public Food(String name, String description, int price, Image image, int x, int y, GraphicsContext graphicsContext,
			int changeHealthPoints) {
		super(name, description, price, image, new Point2D(x, y), graphicsContext);
		this.changeHealthPoints = changeHealthPoints;
	}

	/**
	 * Diese Methode beeinflusst die Leben eines Charakters. Ob positiv oder negativ
	 * h�ngt von dem Inhalt von "changeHealthPoints" ab.
	 */
	@Override
	public void use(Character character) {
		int newHealthPoints = (character.getHP() + changeHealthPoints);
		character.setHP(newHealthPoints);
	}
}
