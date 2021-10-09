import com.google.common.collect.Sets;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


class AddSetsTest {

    @Nested
    class method_test {
        private final Set<String> set1 = Sets.newHashSet("e1", "e2", "e3");
        private final Set<String> set2 = Sets.newHashSet("e3", "e4", "e5");

        @Test
        void addSets_1() {
            final Set<String> actual = AddSets.addSets_1(set1, set2);
            assertResult(actual);
        }

        @Test
        void addSets_2() {
            final Set<String> actual = AddSets.addSets_2(set1, set2);
            assertResult(actual);
        }

        @Test
        void addSets_3() {
            final Set<String> actual = AddSets.addSets_3(set1, set2);
            assertResult(actual);
        }

        @Test
        void addSets_4() {
            final Set<String> actual = AddSets.addSets_4(set1, set2);
            assertResult(actual);
        }

        private void assertResult(Set<String> actual) {
            assertThat(actual).hasSize(5)
                    .contains("e1")
                    .contains("e2")
                    .contains("e3")
                    .contains("e4")
                    .contains("e5");
        }
    }

    @Test
    void method_speed() {
        long onlyNew = 0;
        long addSet1 = 0;
        long addSet2 = 0;
        long addSet3 = 0;
        long addSet4 = 0;
        long[] onlyNewTimes = new long[1000];
        long[] addSet1Times = new long[1000];
        long[] addSet2Times = new long[1000];
        long[] addSet3Times = new long[1000];
        long[] addSet4Times = new long[1000];

        for (int i = 0; i < 1000; i++) {
            Random rnd = new Random();
            Set<String> set1 = new HashSet<>();
            Set<String> set2 = new HashSet<>();
            IntStream.rangeClosed(0, 100)
                    .mapToObj(j -> String.valueOf(rnd.nextInt(1000)))
                    .forEach(set1::add);
            IntStream.rangeClosed(0, 100)
                    .mapToObj(j -> String.valueOf(rnd.nextInt(1000)))
                    .forEach(set2::add);
            long onlyNewTime = measureTime(e -> new HashSet<>());
            long addSet1Time = measureTime(e -> AddSets.addSets_1(set1, set2));
            long addSet2Time = measureTime(e -> AddSets.addSets_2(set1, set2));
            long addSet3Time = measureTime(e -> AddSets.addSets_3(set1, set2));
            long addSet4Time = measureTime(e -> AddSets.addSets_4(set1, set2));
            onlyNew += onlyNewTime;
            addSet1 += addSet1Time;
            addSet2 += addSet2Time;
            addSet3 += addSet3Time;
            addSet4 += addSet4Time;
            onlyNewTimes[i] = onlyNewTime;
            addSet1Times[i] = addSet1Time;
            addSet2Times[i] = addSet2Time;
            addSet3Times[i] = addSet3Time;
            addSet4Times[i] = addSet4Time;
        }
        System.out.println("Average");
        System.out.println(onlyNew / 1000000 + "ns");
        System.out.println(addSet1 / 1000000 + "ns");
        System.out.println(addSet2 / 1000000 + "ns");
        System.out.println(addSet3 / 1000000 + "ns");
        System.out.println(addSet4 / 1000000 + "ns");
        System.out.println("Median");
        System.out.println(calcMedian(onlyNewTimes) / 1000 + "ns");
        System.out.println(calcMedian(addSet1Times) / 1000 + "ns");
        System.out.println(calcMedian(addSet2Times) / 1000 + "ns");
        System.out.println(calcMedian(addSet3Times) / 1000 + "ns");
        System.out.println(calcMedian(addSet4Times) / 1000 + "ns");
    }

    private long measureTime(IntConsumer consumer) {
        long tic = System.nanoTime();
        IntStream.rangeClosed(0, 1000).forEach(consumer);
        long toc = System.nanoTime();
        return toc - tic;
    }

    private long calcMedian(long[] arr) {
        Arrays.sort(arr);
        int len = arr.length;
        if (len % 2 == 0) {
            return (arr[len / 2] + arr[(len / 2) - 1]) / 2;
        }
        return arr[(len / 2) - 1];
    }

}