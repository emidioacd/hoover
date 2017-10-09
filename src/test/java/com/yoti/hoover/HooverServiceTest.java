package com.yoti.hoover;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yoti.hoover.service.IHooverService;
import com.yoti.hoover.web.model.HooverRequest;
import com.yoti.hoover.web.model.HooverResponse;
import com.yoti.hoover.web.model.HooverResponseError;
import com.yoti.hoover.web.model.IHooverResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HooverServiceTest {

	@Autowired
	private IHooverService service;

	@Test
	public void testOk() {
		HooverRequest request = new HooverRequest();

		request.setRoomSize(new int[] { 5, 5 });
		request.setCoords(new int[] { 1, 2 });

		List<int[]> patches = new ArrayList<>();

		patches.add(new int[] { 1, 0 });
		patches.add(new int[] { 2, 2 });
		patches.add(new int[] { 2, 3 });
		
		request.setPatches(patches);

		request.setInstructions("NNESEESWNWW");

		IHooverResponse response = service.hoover(request);

		assertTrue(response instanceof HooverResponse);

		HooverResponse successResponse = (HooverResponse) response;
		assertEquals(successResponse.getCoords()[0], 1);
		assertEquals(successResponse.getCoords()[1], 3);
		assertEquals(successResponse.getPatches(), 1);
	}

	@Test
	public void testNoDirtyPatches() {
		HooverRequest request = new HooverRequest();

		request.setRoomSize(new int[] { 5, 5 });
		request.setCoords(new int[] { 1, 2 });

		List<int[]> patches = new ArrayList<>();

		request.setPatches(patches);

		request.setInstructions("NNESEESWNWW");

		IHooverResponse response = service.hoover(request);

		assertTrue(response instanceof HooverResponse);

		HooverResponse successResponse = (HooverResponse) response;
		assertEquals(successResponse.getCoords()[0], 1);
		assertEquals(successResponse.getCoords()[1], 3);
		assertEquals(successResponse.getPatches(), 0);
	}

	@Test
	public void testNoCoordsError() {
		HooverRequest request = new HooverRequest();

		request.setRoomSize(new int[] { 5, 5 });

		List<int[]> patches = new ArrayList<>();

		request.setPatches(patches);

		request.setInstructions("NNESEESWNWW");

		IHooverResponse response = service.hoover(request);

		assertTrue(response instanceof HooverResponseError);

		HooverResponseError errorResponse = (HooverResponseError) response;
		assertTrue(errorResponse.getError().contains("Unexpected Request Format"));
		assertTrue(errorResponse.getError().contains("coords"));
	}
	
	@Test
	public void testNoInstructionsError() {
		HooverRequest request = new HooverRequest();

		request.setRoomSize(new int[] { 5, 5 });
		request.setCoords(new int[] { 1, 2 });

		List<int[]> patches = new ArrayList<>();

		request.setPatches(patches);

		IHooverResponse response = service.hoover(request);

		assertTrue(response instanceof HooverResponseError);

		HooverResponseError errorResponse = (HooverResponseError) response;
		assertTrue(errorResponse.getError().contains("Unexpected Request Format"));
		assertTrue(errorResponse.getError().contains("instructions"));
	}
	
	@Test
	public void testNoRoomSizeError() {
		HooverRequest request = new HooverRequest();

		request.setCoords(new int[] { 1, 2 });

		List<int[]> patches = new ArrayList<>();

		request.setPatches(patches);

		request.setInstructions("NNESEESWNWW");

		IHooverResponse response = service.hoover(request);

		assertTrue(response instanceof HooverResponseError);

		HooverResponseError errorResponse = (HooverResponseError) response;
		assertTrue(errorResponse.getError().contains("Unexpected Request Format"));
		assertTrue(errorResponse.getError().contains("roomSize"));
	}

	@Test
	public void testNoPatchesFieldError() {
		HooverRequest request = new HooverRequest();

		request.setRoomSize(new int[] { 5, 5 });
		request.setCoords(new int[] { 1, 2 });

		request.setInstructions("NNESEESWNWW");

		IHooverResponse response = service.hoover(request);

		assertTrue(response instanceof HooverResponseError);

		HooverResponseError errorResponse = (HooverResponseError) response;
		assertTrue(errorResponse.getError().contains("Unexpected Request Format"));
		assertTrue(errorResponse.getError().contains("patches"));
	}

	@Test
	public void testEmptyRequestError() {
		IHooverResponse response = service.hoover(null);

		assertTrue(response instanceof HooverResponseError);

		HooverResponseError errorResponse = (HooverResponseError) response;
		assertTrue(errorResponse.getError().contains("Unexpected Request Format"));
	}
}
