public class CurrentTime {
    private int days = 0;
    private int minutes = 540;

    public CurrentTime() {
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return "Time{" +
                "days=" + days +
                ", minutes=" + minutes +
                '}';
    }
}
