import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class A1
{
    public static void main(String[] args)
    {
        // File path
        String filename = args[0];

        // Dispatcher time
        int disp = 0;

        // List of processes
        ArrayList<Process> processes = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename)))
        {
            // Create a new process object
            Process p = new Process();

            // Loop through each line of the file
            do {
                String line = scanner.next();

                // Setting dispatcher time
                if (line.contains("DISP"))
                {
                    disp = scanner.nextInt();
                }
                // Setting process ID
                else if (line.contains("PID"))
                {
                    p.setId(scanner.next());
                }
                // Setting arrival time of the process
                else if (line.contains("ArrTime"))
                {
                    p.setArrivalTime(scanner.nextInt());
                }
                // Setting service time of the process
                else if (line.contains("SrvTime"))
                {
                    p.setServiceTime(scanner.nextInt());
                }
                // Setting priority of the process
                else if (line.contains("Priority"))
                {
                    p.setPriority(scanner.nextInt());
                }
                // Adding the process to the list and create a new process object
                else if (line.contains("END"))
                {
                    if (p.getId() != null && !p.getId().isEmpty()) {
                        processes.add(p);
                        p = new Process();
                    }
                }
                // Breaking the loop if end of file
                else if (line.contains("EOF"))
                {
                    break;
                }
            }
            while (scanner.hasNextLine());


            // Running and printing the results of each scheduling algorithm
            // First Come, First Served
            FCFS fcfs = new FCFS(processes, disp);
            fcfs.run();
            fcfs.print();
            double[] fcfsAvgTimes = fcfs.getAverageTimes();

            // Shortest Process Next
            SPN spn = new SPN(processes, disp);
            spn.run();
            spn.print();
            double[] spnAvgTimes = spn.getAverageTimes();

            // Priority Preemptive
            PP pp = new PP(processes, disp);
            pp.run();
            pp.print();
            double[] ppAvgTimes = pp.getAverageTimes();

            // Priority Round Robin
            PRR prr = new PRR(processes, disp);
            prr.run();
            prr.print();
            double[] prrAvgTimes = prr.getAverageTimes();

            //Showing output for Summery
            System.out.printf("\nSummary:");
            System.out.printf("\nAlgorithm     Average Turnaround Time   Average Waiting Time\n");
            System.out.printf("FCFS            %.2f                     %.2f\n", fcfsAvgTimes[0], fcfsAvgTimes[1]);
            System.out.printf("SPN             %.2f                     %.2f\n", spnAvgTimes[0], spnAvgTimes[1]);
            System.out.printf("PP              %.2f                     %.2f\n", ppAvgTimes[0], ppAvgTimes[1]);
            System.out.printf("PRR             %.2f                     %.2f\n", prrAvgTimes[0], prrAvgTimes[1]);

        }
        //just incase file doesnot load up then this will ru
        catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + filename);
        }
    }
}
