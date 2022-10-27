import java.util.ArrayList;

public class OrderQueue {
    private final Work work = new Work();
    private final ArrayList<Order> orderQueue = new ArrayList<>();

    public Work getWork() {
        return work;
    }

    public void start (){
        addOrder();
        startQueue();
    }

    public void addOrder(){
        if (orderQueue.size() == 0 ){
            orderQueue.add(work.getList().get(work.getListIndex()));
            for (int i = work.getListIndex() + 1; i< work.getList().size(); i++){
                if (work.getList().get(work.getListIndex()).getEntranceTime() == work.getList().get(i).getEntranceTime()){
                    orderQueue.add(work.getList().get(i));
                } else {
                    break;
                }
            }
        }
    }

    public void startQueue(){
        while (orderQueue.size() > 0) {
            work.startWork(orderQueue.get(getMinDuration()));
            orderQueue.remove(orderQueue.get(getMinDuration()));
            for (int i = work.getListIndex() + 1; i< work.getList().size(); i++) {
                if (work.getFinishedTime()  >= work.getList().get(i).getEntranceTime() && !work.getList().get(i).isDone() && !orderQueue.contains(work.getList().get(i))) {
                    orderQueue.add(work.getList().get(i));
                } else if (work.getFinishedTime()   < work.getList().get(i).getEntranceTime()) {
                    break;
                }
            }
        }
        work.setListIndex(work.findListIndex());
    }

    public int getMinDuration(){
        int min = 0;
        for (int i = 1; i< orderQueue.size(); i++){
            if (orderQueue.get(min).getDuration()> orderQueue.get(i).getDuration()){
                min = i;
            }
        }
        return min;
    }
}