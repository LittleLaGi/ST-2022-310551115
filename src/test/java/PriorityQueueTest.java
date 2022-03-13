import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 5, 2}, new int[]{5, 2, 1}),
                Arguments.of(new int[]{0, 4, 0, 9}, new int[]{9, 0, 4, 0}),
                Arguments.of(new int[]{-1, -8, -2, -1}, new int[]{1, -2, -1, -1}),
                Arguments.of(new int[]{5, 5, 5}, new int[]{5, 5 ,6}),
                Arguments.of(new int[]{-3, 9, 0}, new int[]{-3, 0, 3})
        );
    }

    @ParameterizedTest(name = "#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] test_array, int[] correct_array) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int i = 0; i < test_array.length; ++i)
            queue.add(test_array[i]);

        int[] result = new int[test_array.length];
        for (int i = 0; i < test_array.length; ++i)
            result[i] = queue.poll();

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void Test_Exception_DoesNotHaveNext(){
        assertThrows(NoSuchElementException.class, ()->{
            PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
            Iterator<Integer> iterator = queue.iterator();
            iterator.next();
        });
    }

    @Test
    public void Test_Exception_IllegalArgument(){
        assertThrows(IllegalArgumentException.class, ()->{
            PriorityQueue<Integer> queue = new PriorityQueue<Integer>(0);
        });
    }

    @Test
    public void Test_Exception_NullPointer(){
        assertThrows(NullPointerException.class, ()->{
            PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
            Integer i = null;
            queue.add(i);
        });
    }

}
