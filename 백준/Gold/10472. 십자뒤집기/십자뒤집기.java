import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb;
    private static int tc;
    private static boolean check;
    private static int[] tArr;
    private static int[][] toggled, dir = {
        {0,0},{0, 1},{0, -1},{1, 0},{-1, 0}
    };
    private static char[] cArr;
    private static long[] dp; // i모양일때 최소클릭수    
    private static Queue<int[]> queue;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = Integer.parseInt(br.readLine());
        tArr = new int[tc];
        for (int i = 0; i < tc; i++) {
            int a = 0;
            for (int r = 0; r < 3; r++) {
                cArr = br.readLine().toCharArray();
                for (int c = 0; c < 3; c++) {
                    if (cArr[c] == '*') a |= 1<<(r*3+c);
                }
            }
            
            tArr[i] = a;
        }
        
        toggled = new int[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                int mask = 0;
                
                for (int[] d : dir) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr < 0 || nr >= 3 || nc < 0 || nc >= 3) continue;

                    mask |= 1<<(nr*3+nc);
                }
                
                toggled[r][c] = mask;
            }
        }
        
        dp = new long[1<<9];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        
        queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        bfs();    
        
        for (int s : tArr) sb.append(dp[s] + "\n");

        System.out.print(sb);
    }
    
    private static void bfs() {
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cs = cur[0], cc = cur[1];
            
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    int ns = cs ^ toggled[r][c], nc = cc+1;
                    
                    if (dp[ns] != -1) continue;
                    dp[ns] = nc;
                    
                    queue.offer(new int[]{ns, nc});
                }
            }
        }
    }
    
    private static int click(int shape, int r, int c) {        
        int[][] board = new int[3][3];
        for (int bit = 0; bit < 9; bit++) {
            if ((shape & (1<<bit)) != 0) board[bit/3][bit%3] = 1;
        }
        
        board[r][c] ^= 1;
        
        for (int[] d : dir) {
            int nr = r + d[0], nc = c + d[1];
            if (nr < 0 || nr >= 3 || nc < 0 || nc >= 3) continue;
            
            board[nr][nc] ^= 1;
        }
        
        int ns = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 1) ns |= 1<<(i*3+j);
            }
        }
        
        return ns;
    }
}