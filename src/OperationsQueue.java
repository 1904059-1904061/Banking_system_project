import java.util.List;
import java.util.ArrayList;
// import java.util.Arrays;
public class OperationsQueue {
    private final List<Integer> operations = new ArrayList<>();
    // private final List<Integer> arr = new ArrayList<>(Arrays.asList(74,-50,83,-82,-65,90,33,60,33,58)); use this list for testing 
    public  int counter=0;
    public synchronized void addSimulation(int totalSimulation) {    
        for (int i = 0; i < totalSimulation; i++) {
            int random = (int) (Math.random() * 200) - 100;
            while(random==0){
                random = (int) (Math.random() * 200) - 100;
            }
            operations.add(random);
            notifyAll();
            System.out.println(i + ". New operation added: " + random);
            try {
                Thread.sleep((int) (Math.random() * 80));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void add(int amount) {
        operations.add(amount);
        counter++;
        notifyAll();
    }
    public synchronized int getNextItem() {
        // add a small delay to simulate the time taken to get the next operation.
        while(operations.isEmpty()) {
            try {
                if(operations.isEmpty()==true && counter == 0){
                    return -9999;
                }
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter--;
        return operations.remove(0);
    }
}
