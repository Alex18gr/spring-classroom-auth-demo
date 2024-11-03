package io.alexc.classroomauthdemo.classroomauthdemo.web.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Object to return as body in JWT Authentication.
 */

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken {

    private String idToken;

}
