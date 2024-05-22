package homework2;

public class ProcessQueue {
    public Process[] pq;
    public int length;

    public ProcessQueue(ProcessQueue other) {
        this.length = other.length;
        this.pq = new Process[other.pq.length];
        for (int i = 1; i <= other.length; i++) {
            this.pq[i] = other.pq[i];
        }
    }

    public ProcessQueue() {
        this.length = 0;
        this.pq = new Process[2];
    }

    public Process peekNextProcess() {
        return pq[1];
    }

    public void addProcess(Process process) {
        if (pq.length == length + 1) {
            resize(2 * pq.length);
        }

        pq[++length] = process;
        swim(length);

    }

    public Process runNextProcess() {
        if (isEmpty()) {
            return null;
        }

        Process min = pq[1];
        swap(1, length--);
        pq[length + 1] = null;

        if (length > 0 && length == pq.length / 4) {
            resize(pq.length / 2);
        }

        sink(1);
        return min;
    }


    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void swap(int a, int b) {
        Process temp = pq[a];
        pq[a] = pq[b];
        pq[b] = temp;
    }

    private boolean less(int a, int b) {
        return pq[a].compareTo(pq[b]) < 0;
    }

    private void resize(int capacity) {
        Process[] temp = new Process[capacity];
        for (int i = 1; i <= length; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    private void sink(int k) {
        while (k * 2 < length) {
            int leftChild = 2 * k;
            int rightChild = 2 * k + 1;

            int swapPos = less(leftChild, rightChild) ? leftChild : rightChild;

//            if (rightChild <= length)
//                swapPos = less(leftChild, rightChild) ? leftChild : rightChild;

            if (less(leftChild, k) || less(rightChild, k)) {
                swap(k, swapPos);
            }
            k = swapPos;
        }
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }


    private static Process[] clonePQ(ProcessQueue origin) {
        Process[] temp = new Process[origin.pq.length];
        for (int i = 1; i < origin.pq.length; i++) {
            temp[i] = origin.pq[i];
        }
        return temp;
    }

    public static Process popCurrentProcess(int time, ProcessQueue origin) {
        ProcessQueue copy = new ProcessQueue(origin);

        Process result = copy.runNextProcess();

        while (!(result.getArrivalTime() <= time && result.getBurstTime() > 0)) {
            result = copy.runNextProcess();
            if (result == null) {
                return null;
            }
        }

        return result;
    }

    public int decrementBurstTime(Process process, ProcessQueue origin) {
        Process[] copy = clonePQ(origin);

        for (int index = 1; index <= copy.length; index++) {
            Process result = copy[index];

            if (result.equals(process)) {
                pq[index].decrementBurstTime();
                return index;
            }
        }

        return 0;
    }

    public boolean finishBurst(int index) {
        return pq[index].getBurstTime() == 0;
    }

    public void deleteNode(int index) {
        if (index < 1 || index > length) {
            System.out.println("Invalid node index.");
            return;
        }
        pq[index] = pq[length];
        pq[length] = null;
        length--;

        sink(index);
    }
}
