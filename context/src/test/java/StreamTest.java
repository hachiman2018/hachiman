import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ljp
 */
public class StreamTest {



    @Test
    public void test() {
        List<Integer> list = List.of(1, 2, 3);
        List<Integer> collect = list.stream().filter(o ->  {
            System.out.println("filter ==" + o);
            return o >= 2;
        }).map(o -> {
            System.out.println("map ==" + o);
            return o * 2;
        }).toList();

        System.out.println(collect);
    }
}
