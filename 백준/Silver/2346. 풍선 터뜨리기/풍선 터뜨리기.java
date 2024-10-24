import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;
    private static LinkedList<Node> queue;
    private static int n;

    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        queue = new LinkedList<>();

        n = readInt();

        Node newNode = null;

        for (int i = 1; i <= n; i++) {
            newNode = new Node(readInt(), i);

            if (!queue.isEmpty()) {
                newNode.prev = queue.peekLast();
                newNode.prev.next = newNode;
            }

            queue.offer(newNode);
        }

        queue.peekFirst().prev = queue.peekLast();
        queue.peekLast().next = queue.peekFirst();

        Node curNode = queue.peekFirst();
        int steps = 0;

        while (true) {
            curNode.isDeleted = true;
            steps = curNode.data;
            
            sb.append(curNode.idx).append(" ");

            queue.remove(curNode);

            if (queue.isEmpty()) break;

            while (steps != 0) {
                if (steps > 0) {
                    do curNode = curNode.next;
                    while (curNode.isDeleted);
                    steps--;
                } else {
                    do curNode = curNode.prev;
                    while (curNode.isDeleted);
                    steps++;
                }
            }
        }

        System.out.print(sb);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();

        boolean negative = false;
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }

        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return negative ? -r : r;
    }

    static class Node {
        final int data, idx;
        Node prev, next;
        boolean isDeleted;

        Node(int data, int idx) {
            this.data = data;
            this.idx = idx;
            this.isDeleted = false;
        }
    }
}