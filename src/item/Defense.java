package item;

import character.Character;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Diese Klasse deffiniert eine spezialisierung von Item.
 * Das hier beschriebene Objekt stellt ein R�stungsgegenstand dar.
 */
public class Defense extends Item {
	private double armor;
	private EnumDefense defenseType;	// Wird werdendet, um die berschiedenen Typen von R�stungsgegenst�nden zu unterscheiden. 

	/**
	 * Der Kontrtuktor von ein R�stungsgegenstandes.
	 * @param name = Der Name der R�stung als String.
	 * @param description = Die Beschreibung der R�stung als String. 
	 * @param price = Der Preis der R�stung als Integer (wird f�r eine sp�ter angedachte Erweiterung ben�tigt).
	 * @param defenseType = Beschreibt den Typen der R�stung anhand von EnumDefense.
	 * @param image = Das aussehen der R�stung.
	 * @param x = ben�tigt f�r die Positionierung der R�stung.
	 * @param y = ben�tigt f�r die Positionierung der R�stung.
	 * @param graphicsContext = Wird f�r die Implementation von dem Bild in das JavaFX Fenster ben�tigt.
	 * @param armor = Der R�stungswert von der R�stung.
	 */
	public Defense(String name, String description, int price, EnumDefense defenseType, Image image, int x, int y,
			GraphicsContext graphicsContext, int armor) {
		super(name, description, price, image, new Point2D(x, y), graphicsContext);
		this.armor = armor;
		this.defenseType = defenseType;
	}

	public double getArmor() {
		return armor;
	}

	public int getDamage() {
		return 0;
	}

	public EnumDefense getDefenseType() {
		return defenseType;
	}

	/**
	 * R�stet die R�stung aus.
	 */
	@Override
	public void use(Character character) {
		character.equipItem(this);
	}
}
