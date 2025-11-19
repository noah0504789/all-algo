import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String[] input;
    private static int n, m, ans;
    private static char[][] arr;
    //private static int[][] dp;
    private static int[][] vis;
    private static BitSet tail;
    private static Map<Character, int[]> dir = Map.of(
        'U', new int[]{-1, 0}, 
        'D', new int[]{1, 0},
        'L', new int[]{0, -1},
        'R', new int[]{0, 1}
    );
    
    public static void main(String... args) throws IOException {
        input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        
        arr = new char[n][m];
        for (int i = 0; i < n; i++) arr[i] = br.readLine().toCharArray();
        
        vis = new int[n][m];
        tail = new BitSet();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                tail.clear();
                dfs(r, c, 0);
            }
        }

        System.out.print(ans);
    }
    
    private static void dfs(int r, int c, int cnt) {
        if (r < 0 || r >= n || c < 0 || c >= m || vis[r][c] == 2) {
            memo();
            ans += cnt;
            return;
        }
        if (vis[r][c] == 1) return;
        vis[r][c] = 1;
        tail.set(key(r, c));
        
        char ch = arr[r][c];
        int[] d = dir.get(ch);
        int nr = r + d[0], nc = c + d[1];
        
        dfs(nr, nc, cnt+1);
    }
    
    private static int key(int r, int c) {
        return r*m+c;
    }
    
    private static int[] point(int key) {
        return new int[]{key/m, key%m};
    }
    
    private static void memo() { 
        for (int k = tail.nextSetBit(0); k >= 0; k = tail.nextSetBit(k+1)) {
            int[] c = point(k);
            vis[c[0]][c[1]] = 2;
        }
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       

        while (c <= ' ') c = System.in.read();
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
}