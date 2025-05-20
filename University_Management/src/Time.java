package University_Management.src;

public class Time {
    int hour;
    int minute;
    int second;

    public Time() {
    }

    public Time(int hour, int minute, int second) {
        setTime(hour, minute, second);
    }

    public Time setTime(int hour, int minute, int second) {
        setHour(hour);
        setMinute(minute);
        setSecond(second);
        return this;
    }

    public int getHour() {
        return hour;
    }

    public Time setHour(int hour) {
        this.hour = ((hour >= 0 && hour < 24) ? hour : 0);
        return this;
    }

    public int getMinute() {
        return minute;
    }

    public Time setMinute(int minute) {
        this.minute = ((minute >= 0 && minute < 60) ? minute : 0);
        return this;
    }

    public int getSecond() {
        return second;
    }

    public Time setSecond(int second) {
        this.second = ((second >= 0 && second < 60) ? second : 0);
        return this;
    }

    @Override
    public String toString() {
        int display = (hour == 0 || hour == 12) ? 12 : hour % 12;
        String amPm = (hour < 12) ? " AM" : " PM";
        return display +
                ":" + (minute < 10 ? "0" : "") + minute +
                ":" + (second < 10 ? "0" : "") + second +
                amPm;
    }
}
