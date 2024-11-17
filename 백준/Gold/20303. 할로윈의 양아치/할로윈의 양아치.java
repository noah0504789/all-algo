import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static Queue<Integer> queue;
    private static Node[] friends;
    private static BitSet friendV, allV;
    private static int[] candies, dp;
    private static int n, m, k, src, dest, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();
        k = readInt();

        candies = new int[n+1];
        for (int i = 1; i <= n; i++) candies[i] = readInt();

        friends = new Node[n+1];
        for (int i = 1; i <= m; i++) {
            src = readInt();
            dest = readInt();

            friends[src] = new Node(dest, friends[src]);
            friends[dest] = new Node(src, friends[dest]);
        }

        queue = new ArrayDeque<>();
        friendV = new BitSet();
        allV = new BitSet();
        dp = new int[k];

        for (int i = 1; i <= n; i++) {
            if (allV.get(i)) continue;

            int sum = bfs(i);
            int friendCnt = friendV.cardinality();

            allV.or(friendV);

            for (int j = k-1; j >= friendCnt; j--) dp[j] = Math.max(dp[j], dp[j-friendCnt] + sum);
        }

        ans = 0;

        for (int i = 0; i < k; i++) ans = Math.max(ans, dp[i]);

        bw.write(ans+"");

        bw.flush();
    }

    public static int bfs(int num) {
        int sum = 0;

        friendV.clear();
        friendV.set(num);

        queue.offer(num);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            sum += candies[cur];

            for (Node friend = friends[cur]; friend != null; friend = friend.next) {
                int next = friend.num;
                if (friendV.get(next)) continue;

                friendV.set(next);

                queue.offer(next);
            }
        }

        return sum;
    }

    static class Node {
        final int num;
        final Node next;

        Node(int num, Node next) {
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
