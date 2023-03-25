package com.jspapps.droneapp.application.util;

import java.util.UUID;

public class RandomUUIDGenerator {

    public static String generateRandomUUIDString() {
        UUID dynamicId =  UUID.randomUUID();
        String id = dynamicId.toString().replaceAll("-", "");
        return id.substring(0, Math.min(id.length(), 20));
    }
}
