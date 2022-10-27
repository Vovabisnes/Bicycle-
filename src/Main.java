import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    private static final Work work = new Work();
    private static final OrderQueue queue = new OrderQueue();

    public static void main(String[] args) {
        String fileName = "C:\\fahrradwerkstatt1.txt";
        readFile(fileName);
        startMethodOne();
        System.out.println();
        startMethodTwo();
    }
    public static void readFile(String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String read;
            while((read = reader.readLine()) != null){
                String [] array = read.split(" ");
               queue.getWork().getList().add(new Order(Integer.parseInt(array[0]), Integer.parseInt(array[1]), false));
               work.getList().add(new Order(Integer.parseInt(array[0]), Integer.parseInt(array[1]), false));
            }
        } catch (Exception e){
            System.out.println("File does not exist");
        }
    }

    public static void startMethodOne(){
        for (Order order: work.getList()){
            work.startWork(order);
        }
        System.out.println("Method 1:");
        System.out.println("Avg waiting time is " + work.getAvgWaitingTime() + " minutes");
        System.out.println("Max waiting time is ist " + work.getMaxWaitingTime() + " minutes");
    }

    public static void startMethodTwo(){
        while (queue.getWork().getListIndex() != -1) {
            queue.start();
        }
        System.out.println("Method 2:");
        System.out.println("Avg waiting time is " + queue.getWork().getAvgWaitingTime() + " minutes");
        System.out.println("Max waiting time is " + queue.getWork().getMaxWaitingTime() + " minutes");
    }
}