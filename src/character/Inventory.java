package character;

import java.util.LinkedList;

import item.Item;
import main.IShowable;

/**
 * Klasse um das Inventar eines Spielers darzustellen
 */
public class Inventory implements IShowable {
	private int currentUsedSpace;
	private LinkedList<Item> items = new LinkedList<Item>();
	private int maxSpace;

	/**
	 * Standardkonstruktor
	 */
	public Inventory() {
		maxSpace = 5;
		currentUsedSpace = 0;
	}

	/**
	 * Konstruktor mit der Eingabem�glichkeit f�r maximale Pl�tze und Items direkt
	 * beim Erstellen des Inventars
	 * 
	 * @param maxSpace
	 *            Maximale inventarpl�tze
	 * @param items
	 *            Items die sich von Beginn an im Inventar befinden sollen
	 */
	Inventory(int maxSpace, LinkedList<Item> items) {
		this.maxSpace = maxSpace;
		currentUsedSpace = 0;
		addItems(items);
	}

	/**
	 * Konstruktor mit der Eingabem�glichkeit f�r Items direkt beim Erstellen des
	 * Inventars
	 * 
	 * @param items
	 *            Items die sich von Beginn an im Inventar befinden sollen
	 */
	Inventory(LinkedList<Item> items) {
		this.maxSpace = 10;
		currentUsedSpace = 0;
		addItems(items);
	}

	/**
	 * Item zum Inventar hinzuf�gen
	 * 
	 * @param item
	 *            Item das hinzugef�gt werden soll
	 */
	public void addItem(Item item) {
		if (currentUsedSpace + 1 != maxSpace) {
			items.add(item);
		}
	}

	/**
	 * Mehrere Items gleichzeitig zum Inventar hinzuf�gen
	 * 
	 * @param items
	 *            Items die hinzugef�gt werden sollen
	 */
	public void addItems(LinkedList<Item> items) {
		if (items != null) {
			for (Item item : items) {
				addItem(item);
			}
		}
	}

	/**
	 * Item anhand des Namens zur�ckgeliefert bekommen
	 * 
	 * @param itemName
	 *            Name des Items
	 * @return Item mit dem Namen
	 */
	public Item getFirstItemByName(String itemName) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Alle Items als Liste erhalten
	 * 
	 * @return Alle Items im Inventar
	 */
	public LinkedList<Item> getItems() {
		return items;
	}

	/**
	 * Item anhand des Namens L�schen
	 * 
	 * @param itemName
	 *            Name des Items
	 */
	public void removeFirstItemByName(String itemName) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				items.remove(item);
			}
		}
	}

	/**
	 * Item anhand des Idexes L�schen
	 * 
	 * @param itemIndex
	 *            Index des Items
	 */
	public void removeItemByIndex(int itemIndex) {
		if (items.get(itemIndex) != null) {
			items.remove(itemIndex);
		}
	}

	/**
	 * Inventar anzeigen lassen
	 */
	@Override
	public void show() {
		int dist = 10;
		for (Item item : items) {
			item.showAt(dist, 750);
			dist += item.getWidth() + 10;
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Item item : items) {
			stringBuilder.append(item.toString());
			stringBuilder.append("\r\n");
		}
		return stringBuilder.toString();
	}
}
