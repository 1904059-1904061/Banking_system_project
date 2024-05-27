
public class Bank {
    String accountNumber;

    OperationsQueue operationsQueue;

    int balance = 0;

    public Bank(String accountNumber, OperationsQueue operationsQueue) {
        this.accountNumber = accountNumber;
        this.operationsQueue = operationsQueue;
    }
    // A deposit function that will run in parallel on a separate thread. It will be a loop where in each iteration, it read the amount from the operationQueue and deposit the amount.
    public synchronized void deposit() {
        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        while (true) {
            int amount = operationsQueue.getNextItem();
            int f1=0;
            while(operationsQueue.check_operation()==1 ) {
                
                try{ 
                   wait();
                   if (operationsQueue.check_operation()==1){
                    f1=1; 
                    break;
                   }
                }catch(InterruptedException e){
                }
            }
            if(f1==1)
              break;
            if (amount>0) {
                balance =  balance + amount;
                System.out.println("Deposited: " + amount + " Balance: " + balance);
            }
            else{
                operationsQueue.add(amount);
                System.out.println("operation added back from deposit function"+amount);
            }

        }
    }

    // A withdraw function that will run in parallel on a separate thread. It will be a loop where in each iteration, it read the amount from the operationQueue and withdraw the amount.
    public synchronized void withdraw() {
        while (true) {
            int amount = operationsQueue.getNextItem();
            int f2=0;
            while(operationsQueue.check_operation()==1) {
                
                try{ 
                   wait();
                   if(operationsQueue.check_operation()==1){
                    f2=1; 
                    break;
                   }
                }catch(InterruptedException e){
                }
            }
            if(f2==1)
             break;
            if(balance+amount<0){

                System.out.println("Not enough balance to withdraw "+amount);
                continue;
            }

            if (amount<0) {
                balance =  balance + amount;
                System.out.println("Withdrawn: " + amount + " Balance: " + balance);
            }
            else{
                operationsQueue.add(amount);
                System.out.println("operation added back from withdraw "+amount);
            }

        }
    }
}
