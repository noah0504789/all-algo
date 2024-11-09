import java.io.*;
import java.util.*;

public class Main {
    private static PriorityQueue<Node> pq;
    private static Node cur;
    private static int[][] board;
    private static int n, ans, r, c;

    public static void main(String... args) throws IOException {
        n = readInt();
        
        board = new int[n][n];

        pq = new PriorityQueue<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                board[r][c] = readInt();
                if (r == n-1) pq.offer(new Node(board[r][c], r, c));
            }            
        }

        while (n-- > 0) {
            cur = pq.poll();
            
            ans = cur.val;
            r = cur.r;
            c = cur.c;
            
            if (r-1 < 0) continue;
            
            pq.offer(new Node(board[r-1][c], r-1, c));
        }
        
        System.out.print(ans);
    }
    
    static class Node implements Comparable<Node> {
        final int val, r, c;
        
        Node(int val, int r, int c) {
            this.val = val;
            this.r = r;
            this.c = c;
        }
        
        public int compareTo(Node o) {
            return Integer.compare(o.val, this.val);
        }
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        boolean negative = false;
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
