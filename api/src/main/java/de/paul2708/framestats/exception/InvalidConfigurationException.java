package de.paul2708.framestats.exception;

/**
 * This exception is thrown, if the {@link de.paul2708.framestats.configuration.TableConfiguration} is invalid.
 *
 * @author Paul2708
 */
public final class InvalidConfigurationException extends IllegalArgumentException {

    /**
     * Create a new invalid configuration exception.
     *
     * @param message message that contains the reason why the config is invalid
     */
    public InvalidConfigurationException(String message) {
        super(message);
    }
}