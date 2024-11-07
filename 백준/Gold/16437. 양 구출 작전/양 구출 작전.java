import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static StringTokenizer st;

    private static List<List<Integer>> list;
    private static char[] types;
    private static char type;
    private static int[] counts;
    private static int n, cnt, parent, ans, curCnt;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) list.add(new ArrayList<>());
        
        types = new char[n+1];
        counts = new int[n+1];

        for (int i = 2; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            type = st.nextToken().charAt(0);
            cnt = Integer.parseInt(st.nextToken());
            parent = Integer.parseInt(st.nextToken());
                        
            types[i] = type;
            counts[i] = cnt;
            
            list.get(parent).add(i);
        }
        
        System.out.print(dfs(1));
    }

    public static long dfs(int node) {
        long sum = 0;
        
        for (int child : list.get(node)) sum += dfs(child);
        
        if (types[node] == 'S') return sum + counts[node];
        
        return sum - counts[node] > 0 ? sum - counts[node] : 0;
    }
}