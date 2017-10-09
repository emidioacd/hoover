package com.yoti.hoover;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.yoti.hoover.model.Position;
import com.yoti.hoover.model.Robot;
import com.yoti.hoover.model.RobotHoover;
import com.yoti.hoover.model.Room;

@SpringBootTest
public class HooverTest {

	@Test
	public void testOk() {
		Room room = new Room(5, 5);

		room.setDirtyPatch(new Position(1, 0));
		room.setDirtyPatch(new Position(2, 2));
		room.setDirtyPatch(new Position(2, 3));

		Robot robot = new Robot(1, 2);
		List<Character> instructions = new LinkedList<>(
				Arrays.asList('N', 'N', 'E', 'S', 'E', 'E', 'S', 'W', 'N', 'W', 'W'));
		robot.setInstructions(instructions);

		RobotHoover hoover = new RobotHoover(room, robot);

		hoover.startHoovering();

		assertEquals(hoover.getRobot().getX(), 1);
		assertEquals(hoover.getRobot().getY(), 3);

		assertEquals(hoover.getCleanPatchesCount(), 1);
	}

	@Test
	public void testLongRoom() {
		Room room = new Room(11, 5);

		room.setDirtyPatch(new Position(1, 0));
		room.setDirtyPatch(new Position(2, 2));
		room.setDirtyPatch(new Position(2, 3));
		room.setDirtyPatch(new Position(7, 3));

		Robot robot = new Robot(1, 2);
		List<Character> instructions = new LinkedList<>(Arrays.asList('E', 'N', 'E', 'E', 'E', 'E'));
		robot.setInstructions(instructions);

		RobotHoover hoover = new RobotHoover(room, robot);

		hoover.startHoovering();

		assertEquals(hoover.getRobot().getX(), 6);
		assertEquals(hoover.getRobot().getY(), 3);

		assertEquals(hoover.getCleanPatchesCount(), 2);
	}

	@Test
	public void testCleanAllPatches() {
		Room room = new Room(5, 5);

		room.setDirtyPatch(new Position(1, 0));
		room.setDirtyPatch(new Position(2, 2));
		room.setDirtyPatch(new Position(2, 3));

		Robot robot = new Robot(1, 2);
		List<Character> instructions = new LinkedList<>(Arrays.asList('S', 'S', 'E', 'N', 'N', 'N'));
		robot.setInstructions(instructions);

		RobotHoover hoover = new RobotHoover(room, robot);

		hoover.startHoovering();

		assertEquals(hoover.getRobot().getX(), 2);
		assertEquals(hoover.getRobot().getY(), 3);

		assertEquals(hoover.getCleanPatchesCount(), 3);
	}

	@Test
	public void testNoDirtyPatches() {
		Room room = new Room(5, 5);

		Robot robot = new Robot(1, 2);
		List<Character> instructions = new LinkedList<>(
				Arrays.asList('N', 'N', 'E', 'S', 'E', 'E', 'S', 'W', 'N', 'W', 'W'));
		robot.setInstructions(instructions);

		RobotHoover hoover = new RobotHoover(room, robot);

		hoover.startHoovering();

		assertEquals(hoover.getRobot().getX(), 1);
		assertEquals(hoover.getRobot().getY(), 3);

		assertEquals(hoover.getCleanPatchesCount(), 0);
	}

	@Test
	public void testDrivingIntoWall() {
		Room room = new Room(5, 5);

		room.setDirtyPatch(new Position(1, 0));
		room.setDirtyPatch(new Position(2, 2));
		room.setDirtyPatch(new Position(2, 3));
		Robot robot = new Robot(1, 2);
		// NNNNS - go into wall and back to the same position
		List<Character> instructions = new LinkedList<>(
				Arrays.asList('N', 'N', 'E', 'S', 'E', 'N', 'N', 'N', 'S', 'E', 'S', 'W', 'N', 'W', 'W'));
		robot.setInstructions(instructions);

		RobotHoover hoover = new RobotHoover(room, robot);

		hoover.startHoovering();

		assertEquals(hoover.getRobot().getX(), 1);
		assertEquals(hoover.getRobot().getY(), 3);
		assertEquals(hoover.getCleanPatchesCount(), 1);
	}

}
