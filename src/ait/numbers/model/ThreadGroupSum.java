package ait.numbers.model;

import java.util.Arrays;

public class ThreadGroupSum extends GroupSum {
    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    public ThreadGroupSum(int[][] numberGroups, int rowIndex) {
        super(numberGroups, rowIndex);
    }

    @Override
    public int computeSum() {
        // TODO ThreadGroupSum
        int rows = numberGroups.length;

        GroupSum[] tasks = new GroupSum[rows];

        // Create tasks
        for (int i = 0; i < rows; i++) {
            tasks[i] = new ThreadGroupSum(numberGroups, i);
        }

        Thread[] threads = new Thread[rows];
        
        //Create and start our threads
        for (int i = 0; i < rows; i++) {
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        // Waiting for all threads
        for (int i = 0; i < rows; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return Arrays.stream(tasks).mapToInt(GroupSum::getRowSum).sum();
    }
}
