import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, h, cnt, curCnt, prevCnt;
    private static int[][] dir = {
        {0, 1, 0}, {1, 0, 0}, {-1, 0, 0}, {0, -1, 0},
        {0, 0, 1}, {0, 0, -1}
    };
    private static int[][][] arr;
    private static boolean flag;
    //private static BitSet visited;
    private static Queue<Integer> curQ, nextQ;
    
    public static void main(String... args) throws IOException {
        m = readInt();
        n = readInt();        
        h = readInt();
                
        flag = true;
        //visited = new BitSet();
        
        curQ = new LinkedList<>();
        nextQ = new LinkedList<>();
        
        arr = new int[n][m][h];
        for (int i = 0; i < h; i++) {
            for (int r= 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    arr[r][c][i] = readInt();
                    if (arr[r][c][i] == 0) {
                        flag = false; 
                        cnt++;
                    } else if (arr[r][c][i] == 1) {
                        cnt++;
                        int k = key(r, c, i);                        
                        curQ.offer(k);
                    }
                }
            }
        }
        
        if (flag) {
            System.out.print(0);
            return;
        }
               
        System.out.print(bfs());
    }
    
    private static int bfs() {
        int ans = 0, sum = 0;
        
        while (true) {
            sum += curQ.size();
        
            while (!curQ.isEmpty()) {
                int[] cur = point(curQ.poll());
                int h_ = cur[0], r = cur[1], c = cur[2];

                for (int[] d : dir) {
                    int nr = r + d[0], nc = c + d[1], nh = h_+d[2];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || nh < 0 || nh >= h) continue;
                    if (arr[nr][nc][nh] == -1 || arr[nr][nc][nh] == 1) continue;

                    int nk = key(nr, nc, nh);
                    arr[nr][nc][nh] = 1;
                    nextQ.offer(nk);                
                }
            }
            
            if (nextQ.isEmpty()) break;

            curQ.addAll(nextQ);
            nextQ.clear();
            
            ans++;
        }

        return sum == cnt ? ans : -1;
    }
    
    private static int key(int r, int c, int h) {
        return n*m*h+r*m+c;
    }
    
    private static int[] point(int key) {
        int tmp = key % (n*m);
        return new int[]{key/(n*m), tmp/m, tmp%m};
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