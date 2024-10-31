package io.alexc.classroomauthdemo.classroomauthdemo.error;

public class UserNotActivatedException extends RuntimeException {

    public UserNotActivatedException() {
        super();
    }

    public UserNotActivatedException(final String message) {
        super(message);
    }

}
