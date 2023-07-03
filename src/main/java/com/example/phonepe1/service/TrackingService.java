package com.example.phonepe1.service;

import com.example.phonepe1.model.Entity;

import java.util.List;

public interface TrackingService {

    public void startTracking(Entity entity);

    public void stopTracking(Integer id);

    public int getCounts(List<String> tags);
}
