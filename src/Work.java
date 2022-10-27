import java.util.ArrayList;

public class Work {
    private CurrentTime currentTime = new CurrentTime();
    private ArrayList<Order> list = new ArrayList<>();
    private int finishedTime = 0;
    private int waitingTime = 0;
    private int avgWaitingTime = 0;
    private int maxWaitingTime = 0;
    private int listIndex = 0;

    public int getListIndex() {
        return listIndex;
    }
    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }
    public ArrayList<Order> getList() {
        return list;
    }
    public int getFinishedTime() {
        return finishedTime;
    }
    public int getMaxWaitingTime() {
        return maxWaitingTime;
    }
    public int getAvgWaitingTime () {
        return avgWaitingTime / list.size();
    }

    public void setAvgWaitingTime (int avgWaitingTime) {
        this.avgWaitingTime = avgWaitingTime;
    }

    public void startWork(Order order){
        findWaitingTime(order);
        makeOrder(order);

        if (maxWaitingTime < order.getWaitingTime()) maxWaitingTime = order.getWaitingTime();
        avgWaitingTime +=order.getWaitingTime();
    }

    public void findWaitingTime(Order order){
        currentTime.setDays(order.getEntranceTime() / 1440);
        currentTime.setMinutes(order.getEntranceTime() - currentTime.getDays() * 1440);
        int atWork = order.getDuration();
        waitingTime = 0;
        while (atWork > 0) {
            if (currentTime.getMinutes() >= 540 && currentTime.getMinutes() <= 1020) {
                int temp = atWork;
                atWork = atWork - 1020 + currentTime.getMinutes();
                if (atWork < 0) {
                    currentTime.setMinutes(currentTime.getMinutes() + temp);
                    waitingTime += temp;
                } else {
                    waitingTime += 1020 - currentTime.getMinutes() + 960;
                    currentTime.setMinutes(540);
                    currentTime.setDays(currentTime.getDays() + 1);

                }
            } else {
                if (currentTime.getMinutes() > 1020 && currentTime.getMinutes() <= 1440) {
                    waitingTime += 1440 - currentTime.getMinutes() + 540;
                    currentTime.setMinutes(540);
                    currentTime.setDays(currentTime.getDays() + 1);
                } else {
                    waitingTime += 540 - currentTime.getMinutes();
                    currentTime.setMinutes(540);
                }
            }
        }
    }

    public void makeOrder (Order order){
        if (order.getEntranceTime() > finishedTime){
            order.setWaitingTime(waitingTime);
            order.setFinishedTime(order.getEntranceTime() + waitingTime);
        } else {
            order.setWaitingTime(finishedTime - order.getEntranceTime() + waitingTime);
            order.setFinishedTime(order.getWaitingTime() + order.getEntranceTime());
        }
        order.setDone(true);
        finishedTime = order.getFinishedTime();
    }

    public int findListIndex() {
        for (int i = 0; i< list.size(); i++){
            if (!list.get(i).isDone()){
                return i;
            }
        }
        return -1;
    }
}
