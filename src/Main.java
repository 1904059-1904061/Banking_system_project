public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        System.out.printf("Initializing banking system...");

        int totalNumberOfSimulation = 10;
        OperationsQueue operationsQueue = new OperationsQueue();
        Bank bank = new Bank("123", operationsQueue);

        System.out.println("Initializing simulation...");
        Thread simulationThread = new Thread(() -> {
            operationsQueue.addSimulation(totalNumberOfSimulation);
        });
        simulationThread.start();

        System.out.printf("Initializing deposit system...");
        Thread depositThread = new Thread(() -> {
            bank.deposit();
        });
        depositThread.start();
        System.out.println("Completed");

        System.out.printf("Initializing withdraw system...");
        Thread withdrawThread = new Thread(() -> {
            bank.withdraw();
        });
        withdrawThread.start();
        System.out.println("Completed");

        System.out.println("Completed");
    }
}
