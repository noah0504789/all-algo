import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;

    private static boolean[] visited, finished;
    private static int[] nums;
    private static int tc, n, cnt, next;

    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        tc = readInt();

        for (int i = 0; i < tc; i++) {
            n = readInt();

            visited = new boolean[n+1];
            finished = new boolean[n+1];
            
            nums = new int[n+1];
            
            for (int j = 1; j <= n; j++) nums[j] = readInt();
            
            cnt = 0;

            for (int k = 1; k <= n; k++) {
                if (visited[k]) continue;
                
                dfs(k);
            }

            sb.append(n - cnt).append("\n");
        }

        System.out.print(sb);
    }

    public static void dfs(int start) {
        visited[start] = true;
        next = nums[start];
        
        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) {
            cnt++;
            for (int nxt = next; nxt != start; nxt = nums[nxt]) cnt++;
        }
        
        finished[start] = true;
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
