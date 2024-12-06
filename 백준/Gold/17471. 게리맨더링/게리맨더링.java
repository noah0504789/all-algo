import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int INF = Integer.MAX_VALUE;
    private static Set<Integer> groupA, groupB;
    private static Node[] connected;
    private static Queue<Integer> queue;
    private static BitSet visited;
    private static int[] people;
    private static int n, total, neighCnt, ans, aStart, bStart;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        people = new int[n+1];
        total = 0;
        for (int i = 1; i <= n; i++) {
            people[i] = readInt();
            total += people[i];
        }

        connected = new Node[n+1];
        for (int src = 1; src <= n; src++) {
            neighCnt = readInt();

            while (neighCnt-- > 0) connected[src] = new Node(readInt(), connected[src]);
        }

        ans = INF;
        queue = new ArrayDeque<>();
        visited = new BitSet();

        groupA = new HashSet<>();
        groupB = new HashSet<>();

        for (int i = 1; i < (1<<n); i++) check(i);

        bw.write(ans != INF ? ans+"" : "-1");
        bw.flush();
    }

    private static void check(int bitmask) {
        groupA.clear();
        groupB.clear();

        for (int i = 0; i < n; i++) {
            if ((bitmask & (1 << i)) != 0) {
                aStart = i+1;
                groupA.add(i + 1);
            } else {
                bStart = i+1;
                groupB.add(i + 1);
            }
        }

        if (groupA.isEmpty() || groupB.isEmpty()) return;
        if (!isConnected(groupA, aStart)) return;
        if (!isConnected(groupB, bStart)) return;

        int sumA = groupA.stream().mapToInt(i -> people[i]).sum();
        int sumB = total - sumA;

        ans = Math.min(ans, Math.abs(sumA - sumB));
    }

    private static boolean isConnected(Set<Integer> group, int start) {
        queue.clear();
        visited.clear();

        queue.offer(start);
        visited.set(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Node neigh = connected[cur]; neigh != null; neigh = neigh.next) {
                int neighNum = neigh.num;
                if (!group.contains(neighNum)) continue;
                if (visited.get(neighNum)) continue;

                visited.set(neighNum);
                queue.offer(neighNum);
            }
        }

        return visited.cardinality() == group.size();
    }

    static class Node {
        final int num;
        Node next;

        public Node(int num, Node next) {
            this.num = num;
            this.next = next;
        }
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
}
