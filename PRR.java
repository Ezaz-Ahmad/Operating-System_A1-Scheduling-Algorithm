import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class PRR extends Algorithm {
    private final int HIGH_PRIORITY_QUANTUM = 4;
    private final int LOW_PRIORITY_QUANTUM = 2;

    private int dispatcherTime;

    public PRR(ArrayList<Process> procList, int dispTime)
    {
        super(procList, dispTime);
        this.dispatcherTime = dispTime;
    }

    private int getQuantum(Process proc)
    {
        return (proc.getPriority() <= 2) ? HIGH_PRIORITY_QUANTUM : LOW_PRIORITY_QUANTUM;
    }

    @Override
    public void run() {
        System.out.println("\nPRR:");

        processList.sort(Comparator.comparingInt(Process::getArrivalTime));

        LinkedList<Process> readyQueue = new LinkedList<>();
        LinkedList<Process> completedProc = new LinkedList<>();
        Process currentProc = null;

        int time = 0;
        int quantumCounter = 0;

        while (!processList.isEmpty() || currentProc != null || !readyQueue.isEmpty()) {
            while (!processList.isEmpty() && processList.get(0).getArrivalTime() <= time) {
                readyQueue.add(processList.remove(0));
            }

            if (currentProc == null || quantumCounter == 0) {
                if (currentProc != null && quantumCounter == 0) {
                    readyQueue.add(currentProc);
                }

                time += dispatcherTime;

                currentProc = readyQueue.poll();

                if (currentProc != null) {
                    quantumCounter = getQuantum(currentProc);
                    currentProc.setTime(time);
                    System.out.println("T" + time + ": " + currentProc.getId() + "(" + currentProc.getPriority() + ")");
                }
            }

            time++;
            quantumCounter--;

            if (currentProc != null) {
                currentProc.setCurrentServiceTime(currentProc.getCurrentServiceTime() + 1);

                if (currentProc.getCurrentServiceTime() == currentProc.getServiceTime()) {
                    currentProc.setTurnAroundTime(time - currentProc.getArrivalTime());
                    currentProc.setWaitingTime(currentProc.getTurnAroundTime() - currentProc.getServiceTime());

                    completedProc.add(currentProc);
                    currentProc = null;
                    quantumCounter = 0;
                }
            }
        }

        processList.addAll(completedProc);
    }
}