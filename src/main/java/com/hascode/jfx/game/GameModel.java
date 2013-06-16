package com.hascode.jfx.game;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

public class GameModel {
	private final int INITIAL_BLOCKS_HORIZONTAL = 10;
	private final int INITIAL_BLOCKS_VERTICAL = 5;
	private final int INITIAL_AMOUNT_BLOCKS = getInitialBlocksHorizontal()
			* getInitialBlocksVertical();

	private final DoubleProperty ballX = new SimpleDoubleProperty();
	private final DoubleProperty ballY = new SimpleDoubleProperty();
	private final DoubleProperty paddleX = new SimpleDoubleProperty();
	private final BooleanProperty gameStopped = new SimpleBooleanProperty();
	private final BooleanProperty gameLost = new SimpleBooleanProperty(false);
	private final BooleanProperty gameWon = new SimpleBooleanProperty(false);
	private final DoubleProperty boxesLeft = new SimpleDoubleProperty(
			getInitialAmountBlocks());

	private boolean movingDown = true;
	private boolean movingRight = true;
	private double movingSpeed = 1.0;
	private double paddleDragX = 0.0;
	private double paddleTranslateX = 0.0;

	private final ObservableList<ImageView> boxes = FXCollections
			.observableArrayList();

	public double getPaddleTranslateX() {
		return paddleTranslateX;
	}

	public double getPaddleDragX() {
		return paddleDragX;
	}

	public BooleanProperty getGameStopped() {
		return gameStopped;
	}

	public void setPaddleTranslateX(final double d) {
		this.paddleTranslateX = d;

	}

	public void setPaddleDragX(final double d) {
		this.paddleDragX = d;
	}

	public DoubleProperty getPaddleX() {
		return paddleX;
	}

	public int getInitialBlocksVertical() {
		return INITIAL_BLOCKS_VERTICAL;
	}

	public int getInitialBlocksHorizontal() {
		return INITIAL_BLOCKS_HORIZONTAL;
	}

	public ObservableList<ImageView> getBoxes() {
		return boxes;
	}

	public void reset() {
		getBoxesLeft().set(getInitialAmountBlocks());
		for (ImageView r : boxes) {
			r.setVisible(true);
		}
		setMovingSpeed(1.0);
		setMovingDown(true);
		setMovingRight(true);
		getBallX().setValue(250);
		getBallY().setValue(350);
		paddleX.setValue(175);
		gameStopped.set(true);
		getGameLost().set(false);
		getGameWon().set(false);
	}

	public DoubleProperty getBallX() {
		return ballX;
	}

	public DoubleProperty getBallY() {
		return ballY;
	}

	public BooleanProperty getGameLost() {
		return gameLost;
	}

	public BooleanProperty getGameWon() {
		return gameWon;
	}

	public DoubleProperty getBoxesLeft() {
		return boxesLeft;
	}

	public int getInitialAmountBlocks() {
		return INITIAL_AMOUNT_BLOCKS;
	}

	public void incrementSpeed() {
		if (getMovingSpeed() <= 6)
			setMovingSpeed(getMovingSpeed() + getMovingSpeed() * 0.5);
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public double getMovingSpeed() {
		return movingSpeed;
	}

	public void setMovingSpeed(double movingSpeed) {
		this.movingSpeed = movingSpeed;
	}

}
