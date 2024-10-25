import java.io.*;
import java.util.*;

public class Main {    
    private static StringBuilder sb;
    private static Queue<Node> queue;
    private static PriorityQueue<Integer> maxQueue;
    private static Node curNode;
    private static int tc, n, m;

    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        queue = new ArrayDeque<>();
        maxQueue = new PriorityQueue<>(Collections.reverseOrder());

        tc = readInt();

        for (int i = 0; i < tc; i++) {
            n = readInt();
            m = readInt();

            queue.clear();
            maxQueue.clear();

            int priority = 0;
            for (int j = 0; j < n; j++) {
                priority = readInt();
                
                queue.offer(new Node(j, priority));
                maxQueue.offer(priority);
            }

            int cnt = 0;
            while (!queue.isEmpty()) {
                curNode = queue.poll();
                
                if (curNode.w == maxQueue.peek()) {
                    maxQueue.poll();
                    cnt++;

                    if (curNode.idx == m) break;
                } else {
                    queue.offer(curNode);
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.print(sb);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }

    static class Node {
        final int idx, w;

        Node(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }
    }
}