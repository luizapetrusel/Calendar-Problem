import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //first person calendar
        Meeting bound = new Meeting(9, 00, 20, 00);
        Meeting busy1 = new Meeting(9, 00, 10, 30);
        Meeting busy2 = new Meeting(12, 00, 13, 30);
        Meeting busy3 = new Meeting(16, 00, 18, 30);
        List<Meeting> busyIntervals1 = Arrays.asList(busy1, busy2, busy3);
        Person person1 = new Person(bound, busyIntervals1);

        //second person calendar
        bound = new Meeting(10, 00, 18, 30);
        busy1 = new Meeting(10, 00, 11, 30);
        busy2 = new Meeting(12, 30, 14, 30);
        busy3 = new Meeting(14, 30, 15, 00);
        Meeting busy4 = new Meeting(16, 00, 17, 00);
        List<Meeting> busyIntervals2 = Arrays.asList(busy1, busy2, busy3, busy4);
        Person person2 = new Person(bound, busyIntervals2);

        TimeFinder timeFinder = new TimeFinder(Arrays.asList(person1, person2));
        timeFinder.find();

    }

}
