package io.alexc.classroomauthdemo.classroomauthdemo.error;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {
        super();
    }

    public StudentNotFoundException(Integer id) {
        super("Student with id = " + id + " not found.");
    }

    public StudentNotFoundException(Integer id, Integer classroomId) {
        super("Student with id = " + id + " not found in classroom with id = " + classroomId);
    }

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
}
