import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;;
public class OperationsQueue {
    private final List<Integer> operations = new ArrayList<>();
    private final List<Integer> arr = new ArrayList<>(Arrays.asList(74,-50,83,-82,-65,90,33,60,33,58));

    public synchronized void addSimulation(int totalSimulation) {
        // Add 50 random numbers in the operations list. The number will be range from -100 to 100. It cannot be zero.
        // int comp = 0; //operation add kora shesh kina bujhar jnno
        for (int i=0;i<10;i++){
            operations.add(arr.get(i));
            System.out.println(i + " New operation added: "+ arr.get(i));
            //  for(int j=0;j < operations.size();j++)
        //   System.out.println("Operation q : "+ operations.get(j));    
        // for (int i = 0; i < totalSimulation; i++) {
        //     int random = (int) (Math.random() * 200) - 100;
        //     while(random==0){
        //         random = (int) (Math.random() * 200) - 100;
        //     }
        //     operations.add(random);
        //     System.out.println(i + ". New operation added: " + random);
            // add small delay to simulate the time taken for a new customer to arrive
            try {
                Thread.sleep((int) (Math.random() * 80));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // operations.add(-9999);
    }
    public synchronized void add(int amount) {
        operations.add(amount);
        notifyAll();
    }
    // public synchronized int check_operation(){
    //     if (operations.isEmpty())
    //      return 1;
    //     else return 0; 
    // }
    public synchronized int getNextItem() {
        // add a small delay to simulate the time taken to get the next operation.
        while(operations.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return operations.remove(0);
    }
}
