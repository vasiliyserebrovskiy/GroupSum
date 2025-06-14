package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.Arrays;

public class ThreadGroupSum extends GroupSum {
    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {

        int rows = numberGroups.length;

        OneGroupSum[] tasks = new OneGroupSum[rows];

        // Create tasks
        for (int i = 0; i < rows; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]);
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

        return Arrays.stream(tasks).mapToInt(OneGroupSum::getSum).sum();
    }
}
