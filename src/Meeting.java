public class Meeting {
    private int fromHour, fromMin, toHour, toMin;

    public Meeting(int fromHour, int fromMin, int toHour, int toMin) {
        this.fromHour = fromHour;
        this.toHour = toHour;
        this.fromMin = fromMin;
        this.toMin = toMin;
    }


    public int getFromHour() {
        return fromHour;
    }

    public int getFromMin() {
        return fromMin;
    }

    public int getToHour() {
        return toHour;
    }

    public int getToMin() {
        return toMin;
    }
}
