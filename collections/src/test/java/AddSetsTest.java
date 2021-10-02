import com.google.common.collect.Sets;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
            assertThat(actual).hasSize(5)
                    .contains("e1")
                    .contains("e2")
                    .contains("e3")
                    .contains("e4")
                    .contains("e5");
        }

        @Test
        void addSets_2() {
            final Set<String> actual = AddSets.addSets_2(set1, set2);
            assertThat(actual).hasSize(5)
                    .contains("e1")
                    .contains("e2")
                    .contains("e3")
                    .contains("e4")
                    .contains("e5");
        }

        @Test
        void addSets_3() {
            final Set<String> actual = AddSets.addSets_3(set1, set2);
            assertThat(actual).hasSize(5)
                    .contains("e1")
                    .contains("e2")
                    .contains("e3")
                    .contains("e4")
                    .contains("e5");
        }

        @Test
        void addSets_4() {
            final Set<String> actual = AddSets.addSets_4(set1, set2);
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
            onlyNew += measureTime(e -> new HashSet<>());
            addSet1 += measureTime(e -> AddSets.addSets_1(set1, set2));
            addSet2 += measureTime(e -> AddSets.addSets_2(set1, set2));
            addSet3 += measureTime(e -> AddSets.addSets_3(set1, set2));
            addSet4 += measureTime(e -> AddSets.addSets_4(set1, set2));
        }
        System.out.println(onlyNew / 1000000 + "ns");
        System.out.println(addSet1 / 1000000 + "ns");
        System.out.println(addSet2 / 1000000 + "ns");
        System.out.println(addSet3 / 1000000 + "ns");
        System.out.println(addSet4 / 1000000 + "ns");
    }

    private long measureTime(IntConsumer consumer) {
        long tic = System.nanoTime();
        IntStream.rangeClosed(0, 1000).forEach(consumer);
        long toc = System.nanoTime();
        return toc - tic;
    }
}