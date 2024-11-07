import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;

    private static List<Integer>[] graph;
    private static int[] dp;
    private static int n, root, q, src, dest;

    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        n = readInt();
        root = readInt();
        q = readInt();

        graph = new ArrayList[n+1];

        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n-1; i++) {
            src = readInt();
            dest = readInt();

            graph[src].add(dest);
            graph[dest].add(src);
        }

        dp = new int[n+1];

        postOrder(root, -1);

        for (int i = 0; i < q; i++) sb.append(dp[readInt()]).append("\n");

        System.out.print(sb);
    }

    public static int postOrder(int node, int parent) {
        int cnt = 1;

        for (int child : graph[node]) {
            if (child == parent) continue;

            cnt += postOrder(child, node);
        }

        return dp[node] = cnt;
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
