package location;

import java.util.ArrayList;
import java.util.HashMap;

import character.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Collector extends Landscape {
	private String key;
	private int amount;
	private int maxAmount;
	private ArrayList<Runnable> execute;

	public Collector(String name, String beschreibung, Image image, int x, int y, GraphicsContext gc,
			HashMap<LandscapeResponse, String> landscapeResponse, String key, int amount, int maxAmount, ArrayList<Runnable> execute) {
		super(name, beschreibung, image, x, y, gc, landscapeResponse);
		this.key = key;
		this.amount = amount;
		this.execute = execute;
	}

	public void onUse(Player spieler) {
		if (spieler.getGegenstand(key) == null) {
			tb.addText(getResponse(LandscapeResponse.USE_RESPONSE));
		}

		else if (amount + 1 < maxAmount){
			spieler.dropItem(key);
			amount += 1;
			tb.addText(getResponse(LandscapeResponse.COLLECT_RESPONSE));
		}
		else if (amount + 1 == maxAmount) {
			spieler.dropItem(key);
			amount += 1;
			System.out.println(getResponse(LandscapeResponse.COLLECTFINISH_RESPONSE));

			for (Runnable runnable : execute) {
				runnable.run();
			}
		}
	}

	public void onEnterRoom(Player spieler) {

	}

}
