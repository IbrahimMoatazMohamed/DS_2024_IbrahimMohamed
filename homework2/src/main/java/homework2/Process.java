package homework2;

public class Process implements Comparable<Process> {
    private String name;
    private int priority;
    private int burstTime;
    private int arrivalTime;

    public Process(String name, int priority, int burstTime, int arrivalTime) {
        this.name = name;
        this.priority = priority;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String getName() {
        return name;
    }

    public void decrementBurstTime() {
        this.burstTime--;
    }

    @Override
    public int compareTo(Process other) {
        int cmpPriority = Integer.compare(priority, other.priority);

        if (cmpPriority != 0)
            return cmpPriority;
        else
            return arrivalTime - other.arrivalTime;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Process other = (Process) obj;
        return name.equals(other.getName())
                && priority == other.priority
                && arrivalTime == other.arrivalTime;
    }
}
