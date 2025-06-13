package ait.numbers.test;

import ait.numbers.model.GroupSum;

public class GroupSumPerfomanceTest {
    private String name;
    private GroupSum groupSum;

    public GroupSumPerfomanceTest(String name, GroupSum groupSum) {
        this.name = name;
        this.groupSum = groupSum;
    }

    public void runTest() {
        long t1 = System.currentTimeMillis();
        int sum = groupSum.computeSum();
        long t2 = System.currentTimeMillis();
        System.out.println("test name: " + name + ", duration: " + (t2 - t1) + ", sum = " + sum);
    }
}
