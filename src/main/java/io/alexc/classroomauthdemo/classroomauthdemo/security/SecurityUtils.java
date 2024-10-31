package io.alexc.classroomauthdemo.classroomauthdemo.security;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;

public final class SecurityUtils {
    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512;

    private SecurityUtils() {}
}