public class Order {
    private final int entranceTime;
    private final int duration;
    private int waitingTime;
    private int finishedTime;
    private boolean isDone;

    public Order(int entranceTime, int duration, boolean isDone) {
        this.entranceTime = entranceTime;
        this.duration = duration;
        this.isDone = isDone;
        waitingTime = 0;
    }

    public int getEntranceTime() {
        return entranceTime;
    }

    public int getDuration() {
        return duration;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getFinishedTime() {
        return finishedTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setFinishedTime(int finishedTime) {
        this.finishedTime = finishedTime;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return entranceTime + " " + duration + " " + waitingTime + " " + finishedTime + " " + isDone;
    }
}
