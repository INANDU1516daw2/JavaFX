package com.hascode.jfx.game;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BallGame extends Application {
	private static final String VIEW_GAME = "/view/GameView.fxml";
	private static final String STYLESHEET_FILE = "/stylesheet/style.css";
	public static final Image ICON = new Image(
			SingleClassNoXmlBallGame.class.getResourceAsStream("/image/head.png"));

	@Override
	public void start(final Stage stage) throws Exception {
		initGui(stage);
	}

	private void initGui(final Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(VIEW_GAME));
		Scene scene = SceneBuilder.create().root(root).width(500).height(530)
				.build();
		scene.getStylesheets().add(STYLESHEET_FILE);
		stage.setScene(scene);
		stage.setTitle("hasCode.com - Java FX 2 Ball Game Tutorial");
		stage.getIcons().add(ICON);
		stage.show();
	}

	public static void main(final String... args) {
		Application.launch(args);
	}

}
