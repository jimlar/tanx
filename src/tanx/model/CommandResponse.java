package tanx.model;

import java.io.Serializable;

public class CommandResponse implements Serializable {
    private Throwable exception;

    public CommandResponse() {
        this(null);
    }

    public CommandResponse(Throwable exception) {
        this.exception = exception;
    }

    public boolean wasSuccessful() {
        return exception == null;
    }

    public Throwable getException() {
        return exception;
    }
}
