import java.util.Timer;
import java.util.TimerTask;
public class PomodoroManager {
    private PomodoroPhase currentPhase = PomodoroPhase.STOPPED;
    private int remainingSeconds = 0;
    private int completedWorkSessions = 0;
    private boolean isRunning = false;
    private Timer timer;
    private PomodoroListener listener;

    public PomodoroManager(PomodoroListener listener) {
        this.listener = listener;
    }

    public void setListener(PomodoroListener listener) {
        this.listener = listener;
    }
    public synchronized void start() {
        if (isRunning) return;

        if (currentPhase == PomodoroPhase.STOPPED) {
            startPhase(PomodoroPhase.WORK);
        } else {
            resumeTimer();
        }
    }

    private void resumeTimer() {
        isRunning = true;
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        }, 1000, 1000);
    }

    private synchronized void tick() {
        if (!isRunning) return;

        if (remainingSeconds > 0) {
            remainingSeconds--;
            if (listener != null) {
                listener.onTick(currentPhase, remainingSeconds, formatTime(remainingSeconds));
            } else {
                handlePhaseTransition();
            }
        }
    }

    public static String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void handlePhaseTransition() {
        pause();
        if (currentPhase == PomodoroPhase.WORK) {
            completedWorkSessions++;
            if (completedWorkSessions % 4 == 0) {
                startPhase(PomodoroPhase.LONG_BREAK);
            } else {
                startPhase(PomodoroPhase.SHORT_BREAK);
            }
        } else {
            startPhase(PomodoroPhase.WORK);
        }
    }


    public synchronized void pause() {
        if (!isRunning) return;
        isRunning = false;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
    public synchronized void stop() {
        pause();
        currentPhase = PomodoroPhase.STOPPED;
        remainingSeconds = 0;
        if (listener != null) {
            listener.onPhaseChange(PomodoroPhase.STOPPED, PomodoroPhase.STOPPED.getMessage());
        }
    }

    public synchronized void toggle() {
        if (isRunning) {
            pause();
        } else {
            start();
        }
    }
    private void startPhase(PomodoroPhase phase) {
        this.currentPhase = phase;
        this.remainingSeconds = phase.getDefaultSeconds();
        if (listener != null) {
            listener.onPhaseChange(phase, phase.getMessage());
        }
        resumeTimer();
    }
}
