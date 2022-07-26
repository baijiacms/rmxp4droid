package com.music.farng.mp3;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This is the exception that is always generated by any class in these packages.
 *
 * @author Eric Farng
 * @version $Revision: 1.1 $
 */
public class TagException extends Exception {

    /**
     * Creates a new TagException object.
     */
    public TagException() {
        super();
    }

    /**
     * Creates a new TagException object.
     */
    public TagException(final Throwable exception) {
        super(exception);
    }

    /**
     * Creates a new TagException object.
     *
     * @param message the detail message.
     */
    public TagException(final String message) {
        super(message);
    }

    /**
     * Creates a new TagException object.
     */
    public TagException(final String message, final Throwable exception) {
        super(message, exception);
    }

    private void writeObject(final ObjectOutputStream out) {
        throw new UnsupportedOperationException("Cannot write to Output Stream: " + out.toString());
    }

    private void readObject(final ObjectInputStream in) {
        throw new UnsupportedOperationException("Cannot read from Input Stream: " + in.toString());
    }
}