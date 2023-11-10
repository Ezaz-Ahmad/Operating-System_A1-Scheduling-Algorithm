import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class SPN extends Algorithm  {

    public SPN(ArrayList<Process> procList, int dispTime) {
        super(procList, dispTime);
    }

    @Override
    public void run() {

        System.out.println("\nSPN:");

        int time = 0;

        LinkedList<Process> readyQueue = new LinkedList<>();
        LinkedList<Process> finishedQueue = new LinkedList<>();

        do {
            // Moveing processes from processList to readyQueue based on arrival time
            ArrayList<Process> toBeRemoved = new ArrayList<>();
            for (Process p : processList)
            {
                if (p.getArrivalTime() <= time)
                {
                    readyQueue.add(p);
                    toBeRemoved.add(p);
                }
            }
            processList.removeAll(toBeRemoved);

            // Sorting the ready queue based on the service time
            readyQueue.sort(Comparator.comparingInt(Process::getServiceTime));

            if (!readyQueue.isEmpty())
            {
                Process p = readyQueue.poll();

                p.setTime(dispatcherTime + time);
                p.setWaitingTime(p.getTime() - p.getArrivalTime());
                p.setTurnAroundTime(p.getWaitingTime() + p.getServiceTime());
                time += p.getServiceTime() + dispatcherTime;
                finishedQueue.add(p);
            }
            else
            {
                time++; // If no processes are available in the ready queue, it simply increase the time
            }
        }
        while (!processList.isEmpty() || !readyQueue.isEmpty());


        for (Process p : finishedQueue)
        {
            System.out.println("T" + p.getTime() + ": " + p.getId() + "(" + p.getPriority() + ")");
        }

        processList.addAll(finishedQueue);
    }
}
