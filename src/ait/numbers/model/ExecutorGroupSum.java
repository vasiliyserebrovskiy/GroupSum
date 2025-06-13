package ait.numbers.model;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorGroupSum extends GroupSum{
    public ExecutorGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        // TODO ExecutorGroupSum
        int rows = numberGroups.length;

        GroupSum[] tasks = new GroupSum[rows];

        // Create tasks
        for (int i = 0; i < rows; i++) {
            tasks[i] = new ThreadGroupSum(numberGroups, i);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < rows; i++) {
            executorService.execute(tasks[i]);
        }

        executorService.shutdown();

        //waiting for calculation
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        return Arrays.stream(tasks).mapToInt(GroupSum::getRowSum).sum();
    }
}
