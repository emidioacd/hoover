package com.yoti.hoover.model;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class RobotHoover {

	private Room room;

	private Robot robot;

	private int cleanPatchesCount;
	
	private BiPredicate<Integer, Integer> greaterCondition = (greater, lower) -> greater > lower;

	/**
	 * Initialises the problem context with the robot and the room to be cleaned
	 * 
	 * @param room
	 * @param robot
	 */
	public RobotHoover(Room room, Robot robot) {
		this.room = room;
		this.robot = robot;
		this.cleanPatchesCount = 0;
	}

	/**
	 * Initiates the cleaning of the room using the robot
	 * 
	 * @return the number of cleaned patches
	 */
	public int startHoovering() {
		List<Character> instructions = robot.getInstructions();

		instructions.forEach(instruction -> moveAndHover(instruction));

		return cleanPatchesCount;
	}

	private void moveAndHover(Character instruction) {
		if (moveRobot(instruction)) {
			cleanPatchesCount += room.clean(robot.getPosition());
		}
	}

	/*
	 * Moves the robot according with the instruction parameter
	 */
	private boolean moveRobot(Character instruction) {
		boolean robotHasMoved = false;
		switch (instruction) {
		case 'N':
			robotHasMoved = moveRobot(room.getHeight() - 1, robot.getY(), greaterCondition, Robot::goNorth);
			break;
		case 'S':
			robotHasMoved = moveRobot(robot.getY(), 0, greaterCondition, Robot::goSouth);
			break;
		case 'E':
			robotHasMoved = moveRobot(room.getWidth() - 1, robot.getX(), greaterCondition, Robot::goEast);
			break;
		case 'W':
			robotHasMoved = moveRobot(robot.getX(), 0, greaterCondition, Robot::goWest);
			break;
		}
		return robotHasMoved;
	}

	/*
	 * Moves the robot if the condition is true, robot move method should be passed
	 * on move
	 */
	private boolean moveRobot(int greater, int lower, BiPredicate<Integer, Integer> condition, Consumer<Robot> move) {
		if (condition.test(greater, lower)) {
			move.accept(robot);
			return true;
		}
		return false;
	}

	public Robot getRobot() {
		return robot;
	}

	public int getCleanPatchesCount() {
		return cleanPatchesCount;
	}

	public Room getRoom() {
		return room;
	}
}
