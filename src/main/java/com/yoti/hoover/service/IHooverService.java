package com.yoti.hoover.service;

import org.springframework.stereotype.Service;

import com.yoti.hoover.web.model.HooverRequest;
import com.yoti.hoover.web.model.IHooverResponse;

@Service
public interface IHooverService {

	public IHooverResponse hoover(HooverRequest hooveringRequest);
}
