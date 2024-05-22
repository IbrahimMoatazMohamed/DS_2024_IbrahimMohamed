package homework2;

public class ProcessQueue {
    public Process[] pq = new Process[2];
    public int length = 0;

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

            if (less(leftChild, k) || less(rightChild, k)) {
                swap(k, swapPos);
            }
            k = swapPos;
        }
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public static Process popCurrentProcess(int time, ProcessQueue origin) {
//        copy origin to runNextProcess without damage
        ProcessQueue copy = new ProcessQueue();

        copy.length = origin.length;
        copy.pq = new Process[origin.pq.length];
        for (int i = 1; i <= origin.length; i++) {
            copy.pq[i] = origin.pq[i];
        }

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
        for (int index = 1; index <= origin.length; index++) {
            Process result = origin.pq[index];

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
