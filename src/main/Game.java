package main;

import java.util.HashMap;

import character.Player;
import item.Item;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import location.Worldmap;

/**
 * Dies ist die Hauptklasse der Welt von Zuul.
 */

public class Game {
	private HashMap<KeyCode, Runnable> actions = new HashMap<KeyCode, Runnable>();
	private GraphicsContext gc;
	private Worldmap land;
	private Player player;
	private TextBox textbox;
	private ZuulUI zuulUI;

	/**
	 * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
	 */
	public Game(GraphicsContext gc, ZuulUI zuulUI) {
		land = new Worldmap(gc);
		land.createRooms();
		player = new Player("Dave", "Ich liebe dich", land.getStartpoint(), 400, 400,
				Usefull.linkToImage("/Bilder/Dave.png"), gc, null);
		this.gc = gc;
		textbox = TextBox.newTextBox();
		this.zuulUI = zuulUI;

		setActions();
		outputWelcomeText();
	}

	/**
	 * Die Hauptmethode zum Spielen. Sie wird jeden Frame aufgerufen.
	 * 
	 * @param keys
	 *            Eine Liste aller Tasten die momentan gedr�ckt sind
	 */
	public void update(HashMap<KeyCode, Boolean> keys) {
		// Jeden key durchgehen und versuchen f�r ihn eine Aktion auszuf�hren
		for (KeyCode key : keys.keySet()) {
			try {
				actions.get(key).run();
			} catch (Exception ex) {
			}
		}

		// Alles auf dem Screen entfernen
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

		// Raum in dem der Spieler sich befindet anzeigen und seine Elemente Updaten
		player.getRoom().show();
		player.getRoom().update(player);

		// Leichte Anpassung der Spielerposition um besser damit arbeiten zu k�nnen
		Point2D pos = player.getPosition();
		pos = new Point2D(pos.getX() - player.getWidth() / 2, pos.getY() - player.getHeight() / 2);

		// Spieler und Textbox anzeigen
		player.show();
		textbox.show();
	}

	/**
	 * Einen Begr��ungstext f�r den Spieler ausgeben.
	 */
	private void outputWelcomeText() {
		textbox.addText("Willkommen zu Zuul!");
		textbox.addText("Tippen sie 'help', wenn Sie Hilfe brauchen.");
		textbox.addText();
		textbox.addText("Seltsame Ereignisse haben ihre Schatten vorausgeworfen." + System.getProperty("line.separator")
				+ "�ber Nacht fiel der Goldpreis auf 3 US-Dollar pro Feinunze und die Menscheit strebte nach einen neuen Wertanlage:"
				+ System.getProperty("line.separator") + "Lutetium!" + System.getProperty("line.separator")
				+ "Niemand wei�, was dann geschah. Vielleicht gruben wir zu tief, vielleicht lie�en wir uns auf falsche G�tzen ein,"
				+ System.getProperty("line.separator") + "fest steht, dass das Ende der Welt �ber uns kam."
				+ System.getProperty("line.separator")
				+ "Nur hatten die Propheten keine Ahnung, wie seltsam das Ende werden w�rde."
				+ System.getProperty("line.separator")
				+ "Jetzt stehst du alleine vor der Universit�t. In der Ferne schnurrt eine Katze."
				+ System.getProperty("line.separator") + "");
		System.out.println();
		// System.out.println(spieler.getAktuellerRaum().getLongDesciption());
	}

	/**
	 * Gib Hilfsinformationen aus.
	 */
	private void printHelp() {
		textbox.addText("Sie haben sich verlaufen. Sie sind allein.");
		textbox.addText("Sie irren auf dem Unigel�nde herum.");
	}

	/**
	 * Verarbeite einen gegebenen Befehl (f�hre ihn aus).
	 * 
	 * @param key
	 *            Eine Taste die Gedr�ckt wurde
	 */
	public void processCommand(KeyCode key) {
		switch (key) {
		case H:
			printHelp();
			break;
		case E:
			player.interagieren();
			break;
		case F20:
			int dist = 10;
			for (Item item : player.getItems()) {
				if (Usefull.intersects(zuulUI.getMouseX(), zuulUI.getMouseY(), 0, 0, item.getX(), item.getY(),
						item.getWidth(), item.getHeight())) {
					player.getRoom().addItem(player.dropItem(item.getName()));
					break;
				}
				dist += item.getWidth() + 10;
			}
			player.attack();
			break;
		default:
		}
	}

	/**
	 * Bestimmen welche Aktionen jeden Frame in dem eine Bestimmte Taste gedr�ckt
	 * ist ausgef�hrt werden sollen
	 */
	private void setActions() {
		actions.put(KeyCode.W, () -> {
			player.move(KeyCode.W);
		});

		actions.put(KeyCode.A, () -> {
			player.move(KeyCode.A);
		});

		actions.put(KeyCode.S, () -> {
			player.move(KeyCode.S);
		});

		actions.put(KeyCode.D, () -> {
			player.move(KeyCode.D);
		});
	}

}