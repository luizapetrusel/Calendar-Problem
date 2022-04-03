import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TimeFinder {
    private final List<Person> persons;

    public TimeFinder(List<Person> persons) {
        this.persons = persons;
    }

    public void find() {
        //sort bound hours and get the maximum one
        Collections.sort(persons, (person1, person2) -> person2.getBounds().getFromHour() - person1.getBounds().getFromHour());
        int inTimeHour = persons.get(0).getBounds().getFromHour();
        int inTimeMinutes = persons.get(0).getBounds().getFromMin();

        //sort bound hours and get the minimum one
        Collections.sort(persons, (person1, person2) -> person1.getBounds().getToHour() - person2.getBounds().getToHour());
        int endTimeHour = persons.get(0).getBounds().getToHour();
        int endTimeMinutes = persons.get(0).getBounds().getToMin();

        //marking the map with free time
        Meeting availableInterval = new Meeting(inTimeHour, inTimeMinutes, endTimeHour, endTimeMinutes);
        Map<String, Character> map = new TreeMap<>();
        updateSchedule(availableInterval, map, 'F');

        //marking the map with busy times
        List<Meeting> allBusyIntervals = persons.stream()
                .flatMap(m -> m.getMeetingList().stream())
                .collect(Collectors.toList());
        Collections.sort(allBusyIntervals, (person1, person2) -> person1.getFromHour() - person2.getFromHour());
        updateScheduleMap(allBusyIntervals, map, 'B');

        printFreeTime(map);
    }

    private void updateScheduleMap(List<Meeting> busyIntervals, Map<String, Character> map, char marker) {

        for (Meeting interval : busyIntervals) {
            updateSchedule(interval, map, marker);
        }
    }

    private void updateSchedule(Meeting interval, Map<String, Character> map, char marker) {

        int startTimeHour = interval.getFromHour();
        int startTimeMinutes = interval.getFromMin();

        int startTimeInMinutes = getTimeInMinutes(startTimeHour, startTimeMinutes);
        int endTimeInMinutes = getTimeInMinutes(interval.getToHour(), interval.getToMin());

        for (int i = 0; i < (endTimeInMinutes - startTimeInMinutes); i++) {

            String key = getKey(startTimeHour, startTimeMinutes);

            if (marker == 'B' && map.get(key) != null) {
                map.put(key, marker);
            } else if (marker == 'F' && map.get(key) == null) {
                map.put(key, marker);
            }

            ++startTimeMinutes;

            if (startTimeMinutes == 60) {
                startTimeMinutes = 0;
                ++startTimeHour;
            }
        }
    }

    private int getTimeInMinutes(int hour, int minutes) {
        return (hour * 60) + minutes;
    }

    public String getKey(int hour, int minutes) {

        StringBuilder stringBuilder = new StringBuilder();
        String hourString = hour + "";
        String minutesString = minutes + "";

        if (String.valueOf(hour).length() == 1) {
            hourString = "0" + hour;
        }

        if (String.valueOf(minutes).length() == 1) {
            minutesString = "0" + minutes;
        }

        stringBuilder.append(hourString).append(":").append(minutesString);
        return stringBuilder.toString();
    }

    private void printFreeTime(Map<String, Character> map) {
        boolean timeStarted = false;
        boolean timeEnded = false;

        for (String k : map.keySet()) {

            if (map.get(k) == 'F' && timeStarted == false) {
                System.out.print("[" + k);
                timeStarted = true;
                timeEnded = false;
            }

            if (map.get(k) == 'B' && timeStarted == true && timeEnded == false) {
                System.out.println(" , " + k + "]");
                timeEnded = true;
                timeStarted = false;
            }
        }
    }
}

