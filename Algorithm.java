import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public abstract class Algorithm
{
    // Dispatcher time
    int dispatcherTime;

    // List of processes
    LinkedList<Process> processList = new LinkedList<>();

    // Constructor
    public Algorithm(ArrayList<Process> procList, int dispTime)
    {
        this.dispatcherTime = dispTime;
        this.processList.addAll(procList);

        // Reset the process variables
        for (Process process : procList)
        {
            process.reset();
        }
    }

    // Method to print the results
    public void print()
    {
        // Sorting processes by their ID
        processList.sort(Comparator.comparing(Process::getId));
        System.out.println();
        // Printing the header name
        System.out.printf("%-9s %-18s %-10s%n", "Process", "Turnaround Time", "Waiting Time");
        // Printing the results for each process
        for (Process proc : processList)
        {
            System.out.printf("%-10s %-20d %-12d%n", proc.getId(), proc.getTurnAroundTime(), proc.getWaitingTime());
        }
    }
    // Abstract method to be implemented by subclasses
    public abstract void run();

    //Calculation for Summery
    public double[] getAverageTimes() {
        int totalTurnaroundTime = 0;
        int totalWaitingTime = 0;
        int numberOfProcesses = processList.size();

        for (Process p : processList) {
            totalTurnaroundTime += p.getTurnAroundTime();
            totalWaitingTime += p.getWaitingTime();
        }

        double avgTurnaroundTime = (double) totalTurnaroundTime / numberOfProcesses;
        double avgWaitingTime = (double) totalWaitingTime / numberOfProcesses;

        return new double[]{avgTurnaroundTime, avgWaitingTime};
    }

}
