public enum PomodoroPhase {
    WORK("Time to focus!", 25 * 60),
    SHORT_BREAK("Take a quick break!", 5 * 60),
    LONG_BREAK("Great job! Enjoy a long rest.", 15 * 60),
    STOPPED("Pomodoro stopped.", 0);

    private final String message;
    private final int defaultSeconds;

    PomodoroPhase(String message, int defaultSeconds) {
        this.message = message;
        this.defaultSeconds = defaultSeconds;
    }

    public String getMessage() {
        return message;
    }

    public int getDefaultSeconds() {
        return defaultSeconds;
    }


}
