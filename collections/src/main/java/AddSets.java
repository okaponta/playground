import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddSets {
    Set<String> addSets_1(Set<String> set1, Set<String> set2) {
        return Stream.concat(
                        set1.stream(),
                        set2.stream())
                .collect(Collectors.toSet());
    }

    Set<String> addSets_2(Set<String> set1, Set<String> set2) {
        return Sets.newHashSet(Iterables.concat(set1, set2));
    }

    Set<String> addSets_3(Set<String> set1, Set<String> set2) {
        return Stream.of(set1, set2)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    Set<String> addSets_4(Set<String> set1, Set<String> set2) {
        Set<String> result = new HashSet<>();
        result.addAll(set1);
        result.addAll(set2);
        return result;
    }
}
