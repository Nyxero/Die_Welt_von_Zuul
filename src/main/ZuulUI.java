package main;

import java.util.HashMap;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ZuulUI extends Application {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	private Canvas can;

	Game game;

	private GraphicsContext gc;
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();

	private Timeline tl;

	private void draw() {
		game.update(keys);
	}

	@Override
	public void init() throws Exception {
		tl = new Timeline(new KeyFrame(Duration.millis(1000 / 60), e -> {
			draw();
		}));
		tl.setCycleCount(Animation.INDEFINITE);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();
		Scene scene = new Scene(root, 800, 800);

		stage.setTitle("Die Welt von Zuul");

		can = new Canvas(scene.getWidth(), scene.getHeight());
		gc = can.getGraphicsContext2D();

		root.getChildren().add(can);
		// root.setStyle("-fx-background-color: #000000");

		scene.setOnMousePressed(e -> {
			MouseButton mb = e.getButton();
			if (mb == MouseButton.PRIMARY) {
				keys.put(KeyCode.F20, true);
				game.processCommand(KeyCode.F20);
			}
			if (mb == MouseButton.SECONDARY) {
				keys.put(KeyCode.F21, true);
				game.processCommand(KeyCode.F21);
			}
		});

		scene.setOnMouseReleased(e -> {
			MouseButton mb = e.getButton();
			if (mb == MouseButton.PRIMARY) {
				keys.remove(KeyCode.F20);
			}
			if (mb == MouseButton.SECONDARY) {
				keys.remove(KeyCode.F21);
			}
		});

		scene.setOnKeyPressed(e -> {
			keys.put(e.getCode(), true);
			game.processCommand(e.getCode());
		});

		scene.setOnKeyReleased(e -> {
			keys.remove(e.getCode());
		});

		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();

		game = new Game(gc);
		tl.play();
	}
}