public interface PomodoroListener {
    void onTick(PomodoroPhase phase, int remainingSeconds, String formattedTime);
    void onPhaseChange(PomodoroPhase newPhase, String announcement);
    void onComplete();
}
