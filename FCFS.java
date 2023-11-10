import java.util.ArrayList;
import java.util.Comparator;

public class FCFS extends Algorithm {

    public FCFS(ArrayList<Process> procList, int dispTime) {
        super(procList, dispTime);

        // Sorting processes by their arrival time
        processList.sort(Comparator.comparingInt(Process::getArrivalTime));
    }

    @Override
    public void run() {

        System.out.println("\nFCFS:");

        int time = 0;

        // Processing each process
        for (Process proc : processList) {
            proc.setTime(dispatcherTime + time);
            proc.setWaitingTime(proc.getTime() - proc.getArrivalTime());
            proc.setTurnAroundTime(proc.getWaitingTime() + proc.getServiceTime());
            time += proc.getServiceTime() + dispatcherTime;
        }

        // Printing the schedule
        for (Process proc : processList) {
            System.out.println("T" + proc.getTime() + ": " + proc.getId() + "(" + proc.getPriority() + ")");
        }
    }
}
