import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class PP extends Algorithm {

    private int dispatcherTime;

    public PP(ArrayList<Process> procList, int dispTime) {
        super(procList, dispTime);
        this.dispatcherTime = dispTime;
    }

    @Override
    public void run() {

        System.out.println("\nPP:");

        processList.sort(Comparator.comparingInt(Process::getArrivalTime));

        LinkedList<Process> readyQueue = new LinkedList<>();
        LinkedList<Process> completedProc = new LinkedList<>();
        Process currentProc = null;

        int time = 0;

        while (!processList.isEmpty() || currentProc != null || !readyQueue.isEmpty()) {

            // Transfering processes that have arrived to the ready queue
            while (!processList.isEmpty() && processList.get(0).getArrivalTime() <= time) {
                readyQueue.add(processList.remove(0));
            }

            // Sorting the ready queue based on process priority
            readyQueue.sort(Comparator.comparingInt(Process::getPriority));

            // Checking for preemption or if we need to pick a new process
            if (currentProc == null || (!readyQueue.isEmpty() && readyQueue.getFirst().getPriority() < currentProc.getPriority())) {
                time += dispatcherTime; // Add the dispatcher time when switching or starting

                if (currentProc != null) {
                    readyQueue.add(currentProc);
                }

                currentProc = readyQueue.poll();

                if (currentProc != null) {
                    currentProc.setTime(time);
                    System.out.println("T" + time + ": " + currentProc.getId() + "(" + currentProc.getPriority() + ")");
                }
            }
            // Simuleateing the execution for one time unit
            time++;

            if (currentProc != null) {
                currentProc.setCurrentServiceTime(currentProc.getCurrentServiceTime() + 1);

                // Checking if the current process has finished its execution
                if (currentProc.getCurrentServiceTime() == currentProc.getServiceTime()) {
                    currentProc.setTurnAroundTime(time - currentProc.getArrivalTime());
                    currentProc.setWaitingTime(currentProc.getTurnAroundTime() - currentProc.getServiceTime());

                    completedProc.add(currentProc);
                    currentProc = null; // The process is finished
                }
            }
        }
        // Adding the completed processes back to the original process list
        processList.addAll(completedProc);
    }
}
