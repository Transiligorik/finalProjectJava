package our.project.util;

import our.project.model.HasFieldForEvenSort;
import our.project.util.sort.ComparableTimSort;
import our.project.util.sort.TimSort;


import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

public final class SortUtil {
    private SortUtil() {}

    public static <T> void sort(List<T> list) {
        Object[] array = list.toArray();
        ComparableTimSort.sort(array, 0, array.length, null, 0, 0);
        setValuesFromArray(list, array);
    }

    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
        Object[] array = list.toArray();
        TimSort.sort(array, 0, array.length, (Comparator) comparator, null, 0, 0);
        setValuesFromArray(list, array);
    }

    public static <T extends HasFieldForEvenSort> void sortByOneFieldEven(List<T> list) {
        Map<Integer, T> map = list.stream()
                .filter(o -> o.getFieldForEvenSort() % 2 != 0)
                .collect(Collectors.toMap(list::indexOf, o -> o ));
        list.removeIf(t -> t.getFieldForEvenSort() % 2 != 0);
        sort(list, Comparator.comparingLong(HasFieldForEvenSort::getFieldForEvenSort));
        map.forEach(list::add);
    }

    private static <T> void setValuesFromArray(List<T> list, Object[] array) {
        ListIterator<T> i = list.listIterator();
        for (Object e : array) {
            i.next();
            i.set((T) e);
        }
    }
}
