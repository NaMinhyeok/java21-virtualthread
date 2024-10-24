package minhyeok.javathread;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadRunner {
    public static void main(String[] args) {
        threadRun();
    }

    public static void run() {
        Thread.startVirtualThread(() -> {
            System.out.println("start 가상 스레드입니다.");
        });

        Runnable runnable = () -> {
            System.out.println("runnable 가상 스레드입니다.");
        };

        Thread virtualThread = Thread.ofVirtual().start(runnable);

        Thread.Builder builder = Thread.ofVirtual().name("JVM-Thread");
        Thread virtualThread2 = builder.start(runnable);

        System.out.println("버츄얼 스레드인지 확인 " + virtualThread.isVirtual());

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 3; i++) {
                executorService.submit(runnable);
            }
        }

    }

    public static void threadRun() {
        new Thread(() -> {
            System.out.println("start 플랫폼 스레드입니다.");
        }).start();

        Runnable runnable = () -> {
            System.out.println("runnable 플랫폼 스레드입니다.");
        };

        Thread platformThread = new Thread(runnable);
        platformThread.start();

        Thread platformThread2 = new Thread(runnable);
        platformThread2.setName("JVM-Thread");
        platformThread2.start();

        System.out.println("플랫폼 스레드인지 확인 " + !platformThread.isVirtual());

        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
            for (int i = 0; i < 3; i++) {
                executorService.submit(runnable);
            }
        }
    }

    public static void virtualThreadRun() {
        while (true) {
            long start = System.currentTimeMillis();
            try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                for (int i = 0; i < 100_000; i++) {
                    executor.submit(() -> {
                        Thread.sleep(Duration.ofSeconds(2));
                        return null;
                    });
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("가상 스레드 실행 시간: " + (end - start) + "ms");
        }
    }

    public static void platformThreadRun() {
        while (true) {
            long start = System.currentTimeMillis();
            try (var executor = Executors.newFixedThreadPool(100)) {
                for (int i = 0; i < 100_0; i++) {
                    executor.submit(() -> {
                        Thread.sleep(Duration.ofSeconds(2));
                        return null;
                    });
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("플랫폼 스레드 실행 시간: " + (end - start) + "ms");
        }
    }
}
