import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br;
    private static int n, m, p = 1_000_000_007;
    private static int[] dr = {1,-1,0,0}, dc = {0,0,1,-1};
    private static char[][] arr;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        
        arr = new char[n][m];        
        for (int r = 0; r < n; r++) arr[r] = br.readLine().toCharArray();
        
        long k = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                char ch = arr[r][c];
                boolean ok = true;
                for (int t = 0; t < 4; t++) {
                    int nr = r+dr[t], nc = c + dc[t];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                    if (arr[nr][nc] != ch) {ok=false; break;}
                }
                if (ok) k++;
            }
        }

        System.out.print(pow(2, k));
    }
    
    private static long pow(long a, long b) {
        long res = 1;
        while (b>0) {
            if((b&1)!=0) {
                res*=a;
                res%=p;
            }
            a*=a;
            a%=p;
            b>>=1;
        }
        return res;
    }
}