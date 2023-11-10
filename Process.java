public class Process {
    private int lastExecutionTime;
    private int lastInterruptTime = 0;
    private int time;
    private int currentServiceTime;
    private int waitingTime;
    private int turnAroundTime;
    private String id;
    private int arrivalTime;
    private int serviceTime;
    private int priority;

    public Process()
    {
        this.id = "";
        this.arrivalTime = 0;
        this.serviceTime = 0;
        this.priority = 0;
    }

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public int getArrivalTime()
    {
        return arrivalTime;
    }
    public void setArrivalTime(int arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }
    public int getServiceTime() {
        return serviceTime;
    }
    public void setServiceTime(int serviceTime)
    {
        this.serviceTime = serviceTime;
    }
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public void setTime(int time)
    {
        this.time = time;
    }
    public int getTime()
    {
        return time;
    }

    public int getWaitingTime()
    {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime)
    {
        this.waitingTime = waitingTime;
    }
    public int getCurrentServiceTime()
    {
        return currentServiceTime;
    }

    public void setCurrentServiceTime(int currentServiceTime)
    {
        this.currentServiceTime = currentServiceTime;
    }
    public int getTurnAroundTime()
    {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime)
    {
        this.turnAroundTime = turnAroundTime;
    }



    public void reset()
    {
        this.time = 0;
        this.waitingTime = 0;
        this.turnAroundTime = 0;
        this.currentServiceTime = 0;
        this.lastExecutionTime = 0;
        this.lastInterruptTime = 0;

    }
}