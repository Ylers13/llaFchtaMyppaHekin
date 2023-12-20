package picture;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

public class TimerExample {
    private static final int TIMER_DELAY = 3000; // 5 seconds
    private static final int COUNT_DOWN_LATCH = 3; // 3 count down

    public static void main(String[] args) throws InterruptedException {
        TimerExample example = new TimerExample();

       // for (int i = 0; i < 3; i++) {
            example.startTimer();
            example.waitUntilTimerEnds();
        example.continueProgram();
        System.out.println("jiasf");
        System.out.println("gogogogo");
       // }
    }

    private Timer timer;
    private CountDownLatch countDownLatch;

    public TimerExample() {
        timer = new Timer();
        countDownLatch = new CountDownLatch(COUNT_DOWN_LATCH);
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("count qian:"+countDownLatch.getCount());
                countDownLatch.countDown(); // Count down latch every time the timer runs
                System.out.println("count hou:"+countDownLatch.getCount());
                System.out.println("Timer task executed.");
            }
        }, TIMER_DELAY, TIMER_DELAY); // Schedule the timer to run every 5 seconds (5000 milliseconds)
    }

    private void waitUntilTimerEnds() throws InterruptedException {
        countDownLatch.await(); // Wait until the count down latch reaches zero
    }

    private void continueProgram() {
        System.out.println("Timer ended. Continuing the program...");
        timer.cancel();
    }
}