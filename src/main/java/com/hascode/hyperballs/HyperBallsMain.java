package com.hascode.hyperballs;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.HyperlinkBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HyperBallsMain extends Application {
	private final DoubleProperty ballX = new SimpleDoubleProperty();
	private final DoubleProperty ballY = new SimpleDoubleProperty();
	private final DoubleProperty paddleX = new SimpleDoubleProperty();
	private final BooleanProperty gameStopped = new SimpleBooleanProperty();
	private final BooleanProperty gameLost = new SimpleBooleanProperty(false);
	private boolean movingDown = true;
	private boolean movingRight = true;
	private double movingSpeed = 1.0;
	private double paddleDragX = 0.0;
	private double paddleTranslateX = 0.0;

	private final List<Rectangle> boxes = new ArrayList<>();

	private final Circle ball = CircleBuilder.create().radius(10.0)
			.fill(Color.BLACK).build();

	private final Rectangle borderTop = RectangleBuilder.create().x(0).y(0)
			.width(500).height(2).build();

	private final Rectangle borderBottom = RectangleBuilder.create().x(0)
			.y(492).width(500).height(8).build();

	private final Rectangle borderLeft = RectangleBuilder.create().x(0).y(0)
			.width(2).height(500).build();

	private final Rectangle borderRight = RectangleBuilder.create().x(498).y(0)
			.width(2).height(500).build();

	private final Rectangle paddle = RectangleBuilder.create().x(200).y(460)
			.width(150).height(15).fill(Color.BLACK).cursor(Cursor.HAND)
			.onMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(final MouseEvent evt) {
					paddleTranslateX = paddle.getTranslateX() + 150;
					paddleDragX = evt.getSceneX();
				}
			}).onMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(final MouseEvent evt) {
					double x = paddleTranslateX + evt.getSceneX() - paddleDragX;
					paddleX.setValue(x);
				}
			}).build();

	private final Text infotext = TextBuilder.create()
			.text("Press 'Start' to play!").font(Font.font("Arial", 16.0))
			.layoutX(540).layoutY(50).build();

	private final Text gameOverText = TextBuilder.create().text("Game Over")
			.font(Font.font("Arial", 40.0)).fill(Color.RED).layoutX(150)
			.layoutY(250).build();

	private final Button startButton = ButtonBuilder.create().text("Start")
			.layoutX(540).layoutY(100)
			.onAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(final ActionEvent evt) {
					initGame();
					gameStopped.set(false);
					heartbeat.playFromStart();
				}
			}).build();

	private final Hyperlink link = HyperlinkBuilder.create()
			.text("www.hascode.com").layoutX(540).layoutY(480).build();

	private final Group area = GroupBuilder
			.create()
			.focusTraversable(true)
			.children(ball, borderTop, borderBottom, borderLeft, borderRight,
					startButton, infotext, link, paddle, gameOverText).build();

	private final EventHandler<ActionEvent> pulseEvent = new EventHandler<ActionEvent>() {
		@Override
		public void handle(final ActionEvent evt) {
			checkCollisions();
			double x = movingRight ? movingSpeed : -movingSpeed;
			double y = movingDown ? movingSpeed : -movingSpeed;
			ballX.set(ballX.get() + x);
			ballY.set(ballY.get() + y);
		}
	};

	private final Timeline heartbeat = TimelineBuilder.create()
			.keyFrames(new KeyFrame(new Duration(10.0), pulseEvent))
			.cycleCount(Timeline.INDEFINITE).build();

	@Override
	public void start(final Stage stage) throws Exception {
		initGui(stage);
		initGame();
	}

	protected void checkCollisions() {
		checkBoxCollisions();
		if (ball.intersects(paddle.getBoundsInLocal())) {
			incrementSpeed();
			movingDown = false;
		}
		if (ball.intersects(borderTop.getBoundsInLocal())) {
			incrementSpeed();
			movingDown = true;
		}
		if (ball.intersects(borderBottom.getBoundsInLocal())) {
			gameStopped.set(true);
			gameLost.set(true);
		}
		if (ball.intersects(borderLeft.getBoundsInLocal())) {
			incrementSpeed();
			movingRight = true;
		}
		if (ball.intersects(borderRight.getBoundsInLocal())) {
			incrementSpeed();
			movingRight = false;
		}
		if (paddle.intersects(borderRight.getBoundsInLocal())) {
			paddleX.set(350);
		}
		if (paddle.intersects(borderLeft.getBoundsInLocal())) {
			paddleX.set(0);
		}
	}

	private void checkBoxCollisions() {
		for (Rectangle r : boxes) {
			if (ball.intersects(r.getBoundsInLocal())) {
				area.getChildren().remove(r);
			}
		}
	}

	private void incrementSpeed() {
		if (movingSpeed <= 4)
			movingSpeed += movingSpeed * 0.25;
	}

	private void initGame() {
		movingSpeed = 1.0;
		movingDown = true;
		movingRight = true;
		ballX.setValue(250);
		ballY.setValue(350);
		paddleX.setValue(175);
		startButton.disableProperty().bind(gameStopped.not());
		ball.centerXProperty().bind(ballX);
		ball.centerYProperty().bind(ballY);
		paddle.xProperty().bind(paddleX);
		gameStopped.set(true);
		gameLost.set(false);
		gameOverText.visibleProperty().bind(gameLost);
		area.requestFocus();
	}

	private void initBoxes() {
		int startX = 25;
		int padX = 2;
		int startY = 50;
		int padY = 2;
		for (int v = 1; v <= 4; v++) {
			for (int h = 1; h <= 20; h++) {
				int x = startX + (h * 20) + padX;
				int y = startY + (v * 20) + padY;
				Rectangle r = RectangleBuilder.create().height(20).width(20)
						.fill(Color.BISQUE).layoutX(x).layoutY(y).build();
				boxes.add(r);
				area.getChildren().add(r);
			}
		}
	}

	private void initGui(final Stage stage) {
		Scene scene = SceneBuilder.create().width(700).height(500)
				.fill(Color.GRAY).root(area).build();
		initBoxes();
		stage.setScene(scene);
		stage.setTitle("HyperBalls");
		stage.show();
	}

	public static void main(final String... args) {
		Application.launch(args);
	}

}
