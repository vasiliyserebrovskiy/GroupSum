package ait.numbers.model;

import java.util.Arrays;

public class ParallelStreamGroupSum extends GroupSum{
    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {

//        return Arrays.stream(numberGroups)
//                .parallel()
//                .flatMapToInt(Arrays::stream)
//                .sum();

        return Arrays.stream(numberGroups)
                .parallel()
                .mapToInt(row -> {
                    return Arrays.stream(row).sum();
                })
                .sum();
    }
}
