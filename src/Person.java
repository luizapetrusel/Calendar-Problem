import java.util.List;

public class Person {
    private Meeting bounds;
    private List<Meeting> meetingList;

    public Person(Meeting bounds, List meetingList) {
        this.bounds = bounds;
        this.meetingList = meetingList;
    }


    public Meeting getBounds() {
        return bounds;
    }

    public List<Meeting> getMeetingList() {
        return meetingList;
    }
}
