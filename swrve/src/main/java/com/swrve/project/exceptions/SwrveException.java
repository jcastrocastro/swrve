package com.swrve.project.exceptions;

/**
 * @author Raul Castro
 *
 * Note: I'm not happy using exit code. It's a legacy from C/C++. Most applications should avoid using it and
 * find other suitable designs for interactions with external applications.
 *      However it will be worst cluttering the code with many System.exit all over the code
 */
public class SwrveException extends Exception {

    public SwrveException(String message, Throwable ex) {
        super(message, ex);
        System.exit(1);
    }

    public SwrveException(String message) {
        super(message);
        System.exit(1);
    }
}
