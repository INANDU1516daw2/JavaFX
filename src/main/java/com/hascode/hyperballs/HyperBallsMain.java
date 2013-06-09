package com.hascode.hyperballs;

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
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradientBuilder;
import javafx.scene.paint.Stop;
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
	private final Circle ball = CircleBuilder.create().radius(10.0)
			.fill(Color.AZURE).build();

	private final Rectangle borderTop = RectangleBuilder.create().x(0).y(0)
			.width(500).height(4).build();

	private final Rectangle borderBottom = RectangleBuilder.create().x(0)
			.y(496).width(500).height(4).build();

	private final Rectangle borderLeft = RectangleBuilder.create().x(0).y(0)
			.width(4).height(500).build();

	private final Rectangle borderRight = RectangleBuilder.create().x(496).y(0)
			.width(4).height(500).build();

	private final Text infotext = TextBuilder.create()
			.text("Press 'Start' to play!").font(Font.font("Arial", 16.0))
			.layoutX(540).layoutY(50).build();

	private final Button startButton = ButtonBuilder.create().text("Start")
			.layoutX(540).layoutY(100)
			.onAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(final ActionEvent evt) {
					gameStopped.set(false);
					heartbeat.playFromStart();
				}
			}).build();

	private final Group area = GroupBuilder
			.create()
			.focusTraversable(true)
			.children(ball, borderTop, borderBottom, borderLeft, borderRight,
					startButton, infotext).build();

	private final DoubleProperty ballX = new SimpleDoubleProperty();
	private final DoubleProperty ballY = new SimpleDoubleProperty();
	private final BooleanProperty gameStopped = new SimpleBooleanProperty();
	private boolean movingDown = true;
	private boolean movingRight = true;
	private double movingSpeed = 1.0;

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
		if (ball.intersects(borderTop.getBoundsInLocal())) {
			incrementSpeed();
			movingDown = true;
		}
		if (ball.intersects(borderBottom.getBoundsInLocal())) {
			incrementSpeed();
			movingDown = false;
		}
		if (ball.intersects(borderLeft.getBoundsInLocal())) {
			incrementSpeed();
			movingRight = true;
		}
		if (ball.intersects(borderRight.getBoundsInLocal())) {
			incrementSpeed();
			movingRight = false;
		}
	}

	private void incrementSpeed() {
		if (movingSpeed <= 5)
			movingSpeed += movingSpeed * 0.25;
	}

	private void initGame() {
		movingSpeed = 1.0;
		movingDown = true;
		movingRight = true;
		ballX.setValue(250);
		ballY.setValue(150);
		startButton.disableProperty().bind(gameStopped.not());
		ball.centerXProperty().bind(ballX);
		ball.centerYProperty().bind(ballY);
		gameStopped.set(true);
		area.requestFocus();
	}

	private void initGui(final Stage stage) {
		Scene scene = SceneBuilder
				.create()
				.width(700)
				.height(500)
				.fill(LinearGradientBuilder
						.create()
						.startX(0.0)
						.startY(0.0)
						.endX(0.0)
						.endY(1.0)
						.stops(new Stop(0.0, Color.BLACK),
								new Stop(0.0, Color.GRAY)).build()).root(area)
				.build();
		stage.setScene(scene);
		stage.setTitle("HyperBalls");
		stage.show();
	}

	public static void main(final String... args) {
		Application.launch(args);
	}

}
