import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PriorityQueue {
    private List<Integer> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
        heap.add(0); // Index 0 is not used
    }

    private int parent(int i) {
        return i / 2;
    }

    private int leftChild(int i) {
        return 2 * i;
    }

    private int rightChild(int i) {
        return 2 * i + 1;
    }

    private void heapifyUp(int i) {
        while (i > 1 && heap.get(i) < heap.get(parent(i))) {
            int temp = heap.get(i);
            heap.set(i, heap.get(parent(i)));
            heap.set(parent(i), temp);
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left <= heap.size() - 1 && heap.get(left) < heap.get(smallest)) {
            smallest = left;
        }

        if (right <= heap.size() - 1 && heap.get(right) < heap.get(smallest)) {
            smallest = right;
        }

        if (smallest != i) {
            int temp = heap.get(i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, temp);
            heapifyDown(smallest);
        }
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int deleteMin() {
        if (heap.size() == 1) {
            throw new IllegalStateException("Priority queue is empty");
        }

        int min = heap.get(1);
        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapifyDown(1);
        return min;
    }

    public void printPriorityQueue() {
        for (int i = 1; i < heap.size(); i++) {
            System.out.print(heap.get(i) + " ");
        }
        System.out.println();
    }
}

public class PriorityQueueApp {
    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Insert");
            System.out.println("2. Delete Min");
            System.out.println("3. Print Priority Queue");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter priority value: ");
                    int value = scanner.nextInt();
                    pq.insert(value);
                    break;
                case 2:
                    int min = pq.deleteMin();
                    System.out.println("Deleted minimum priority value: " + min);
                    break;
                case 3:
                    pq.printPriorityQueue();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
