import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static StringTokenizer st;

    private static List<List<Integer>> list;
    private static char[] types;
    private static char type;
    private static long[] dp;
    private static int[] counts;
    private static int n, cnt, parent, ans, curCnt;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) list.add(new ArrayList<>());
        
        types = new char[n+1];
        counts = new int[n+1];
        dp = new long[n+1];

        for (int i = 2; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            type = st.nextToken().charAt(0);
            cnt = Integer.parseInt(st.nextToken());
            parent = Integer.parseInt(st.nextToken());
                        
            types[i] = type;
            counts[i] = cnt;
            dp[i] = type == 'S' ? cnt : -cnt;
            
            list.get(parent).add(i);
        }                
        
        dfs(1, -1);
        
        System.out.print(dp[1]);
    }

    public static void dfs(int node, int parent) {
        for (int child : list.get(node)) dfs(child, node);
        
        if (parent == -1) return;        
        if (dp[node] <= 0) return;
        
        dp[parent] += dp[node];
    }
}