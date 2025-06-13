package ait.numbers.model;

import java.util.Arrays;

public abstract class GroupSum implements Runnable {
    protected int[][] numberGroups;
    protected int rowIndex;
    protected int rowSum;

    public GroupSum(int[][] numberGroups) {
        this.numberGroups = numberGroups;
    }

    // Constructor for task creation
    public GroupSum(int[][] numberGroups, int rowIndex) {
        this.numberGroups = numberGroups;
        this.rowIndex = rowIndex;
    }

    public abstract int computeSum();

    //Getter for getting row sum result
    public int getRowSum() {
        return rowSum;
    }

    @Override
    public void run() {
        rowSum = Arrays.stream(numberGroups[rowIndex]).sum();
    }
}
