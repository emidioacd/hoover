package com.yoti.hoover.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yoti.hoover.exception.InvalidRequestException;
import com.yoti.hoover.model.Position;
import com.yoti.hoover.model.Robot;
import com.yoti.hoover.model.RobotHoover;
import com.yoti.hoover.model.Room;
import com.yoti.hoover.utils.HooverConstants;
import com.yoti.hoover.web.model.HooverRequest;
import com.yoti.hoover.web.model.HooverResponse;
import com.yoti.hoover.web.model.HooverResponseError;
import com.yoti.hoover.web.model.IHooverResponse;

@Service
public class HooverService implements IHooverService {

	public IHooverResponse hoover(HooverRequest hooverRequest) {
		IHooverResponse response = null;
		try {
			if (hooverRequest == null) {
				throw new InvalidRequestException("request");
			}

			Room room = getRoom(hooverRequest);

			List<Position> patches = getPatches(hooverRequest);
			patches.forEach(patch -> {
				room.setDirtyPatch(patch);
			});

			Robot robot = getRobot(hooverRequest);

			List<Character> instructions = getInstructions(hooverRequest);

			robot.setInstructions(instructions);

			RobotHoover robotHoover = new RobotHoover(room, robot);

			int cleanPaches = robotHoover.startHoovering();

			response = new HooverResponse(new Position(robot.getX(), robot.getY()), cleanPaches);
		} catch (InvalidRequestException e) {
			response = new HooverResponseError("Unexpected Request Format: " + (e.getMessage()));
		}
		return response;
	}

	private List<Position> getPatches(HooverRequest hooverRequest) throws InvalidRequestException {
		List<Position> patches = new LinkedList<>();

		List<int[]> patchesFromRequest = hooverRequest.getPatches();

		if (patchesFromRequest == null) {
			throw new InvalidRequestException("patches");
		}

		for (int[] patch : patchesFromRequest) {
			if (patch == null || patch.length != 2) {
				throw new InvalidRequestException("patches");
			}

			patches.add(new Position(patch[0], patch[1]));
		}
		return patches;
	}

	private List<Character> getInstructions(HooverRequest hooverRequest) throws InvalidRequestException {
		String instructions = hooverRequest.getInstructions();

		if (instructions == null || !instructions.matches(HooverConstants.INSTRUCTIONS_REGEX)) {
			throw new InvalidRequestException("instructions");
		}

		return instructions.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
	}

	private Robot getRobot(HooverRequest hooverRequest) throws InvalidRequestException {
		int[] coords = hooverRequest.getCoords();

		if (coords == null || coords.length != 2) {
			throw new InvalidRequestException("coords");
		}

		return new Robot(coords[0], coords[1]);
	}

	private Room getRoom(HooverRequest hooverRequest) throws InvalidRequestException {
		int[] roomSize = hooverRequest.getRoomSize();

		if (roomSize == null || roomSize.length != 2) {
			throw new InvalidRequestException("roomSize");
		}

		return new Room(roomSize[0], roomSize[1]);
	}

}
