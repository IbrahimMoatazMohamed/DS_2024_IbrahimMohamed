package homework2;

import java.util.ArrayList;
import java.util.HashMap;

public class Scheduler {

    public static void scheduleAndRun(ArrayList<Process> processes) {
        ProcessQueue queue = new ProcessQueue();
        for (Process process : processes) {
            queue.addProcess(process);
        }
        HashMap<String, ProcessStats> finishTime = new HashMap<>();

        int time = 0;
        while (!queue.isEmpty()) {
            Process currentProcess = ProcessQueue.popCurrentProcess(time, queue);
            if (currentProcess != null) {
                String processName = currentProcess.getName();

                if (!finishTime.containsKey(processName)) {
                    finishTime.put(processName, new ProcessStats(currentProcess.getArrivalTime(), currentProcess.getBurstTime()));
                }

                int index = queue.decrementBurstTime(currentProcess, queue);
                if (queue.finishBurst(index)) {
                    queue.deleteNode(index);

                    finishTime.get(processName).setFinishTime(time + 1);
                }

                printTime(time, processName);
            }
            if (currentProcess == null) {
                printTime(time, "no process");
            }

            time++;
        }
        System.out.println("Total time: " + time);

        float sum = 0;
        for (ProcessStats state : finishTime.values()) {
            sum += state.getWaitTime();
        }
        System.out.println("Average waiting time: " + sum / finishTime.size());
    }

    private static void printTime(int time, String processName) {
        System.out.print("t: " + time);
        if (time < 10) {
            System.out.print("   ");
        } else {
            System.out.print("  ");
        }
        System.out.println("| " + processName);
    }

    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<>();

//      1
//        processes.add(new Process("P1", 1, 4, 0));
//        processes.add(new Process("P2", 2, 3, 0));
//        processes.add(new Process("P3", 1, 7, 6));
//        processes.add(new Process("P4", 3, 4, 11));
//        processes.add(new Process("P5", 2, 2, 12));

//      2
//        processes.add(new Process("P1", 5, 4, 0));
//        processes.add(new Process("P2", 4, 3, 1));
//        processes.add(new Process("P3", 3, 1, 2));
//        processes.add(new Process("P4", 2, 5, 3));
//        processes.add(new Process("P5", 2, 2, 4));

//      3
//        processes.add(new Process("P1", 3, 3, 0));
//        processes.add(new Process("P2", 2, 4, 1));
//        processes.add(new Process("P3", 4, 6, 2));
//        processes.add(new Process("P4", 6, 4, 3));
//        processes.add(new Process("P5", 10, 2, 5));

//      4
//        processes.add(new Process("P1", 2, 1, 0));
//        processes.add(new Process("P2", 6, 7, 1));
//        processes.add(new Process("P3", 3, 3, 2));
//        processes.add(new Process("P4", 5, 6, 3));
//        processes.add(new Process("P5", 4, 5, 4));
//        processes.add(new Process("P6", 10, 15, 5));
//        processes.add(new Process("P7", 9, 8, 15));

//      5
        processes.add(new Process("P1", 2, 4, 1));
        processes.add(new Process("P2", 1, 1, 2));
        processes.add(new Process("P3", 3, 2, 8));

        scheduleAndRun(processes);
    }
}

class ProcessStats {
    private int finishTime;
    private int arrivalTime;
    private int burstTime;

    public ProcessStats(int arrivalTime, int burstTime) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getWaitTime() {
        return finishTime - arrivalTime - burstTime;
    }
}
