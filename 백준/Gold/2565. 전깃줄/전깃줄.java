import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, len;
    private static int[] tails;
    private static Node[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new Node[n];
        for (int i = 0; i < n; i++) arr[i] = new Node(readInt(), readInt());
        
        Arrays.sort(arr);
        
        tails = new int[n];        
        len = 0;
        for (int i = 0; i < n; i++) {
            int v = arr[i].val;
            int pos = lowerBound(len, v);
            tails[pos] = v;
            if (pos == len) len++;
        }

        System.out.print(n-len);
    }
    
    private static int lowerBound(int r, int x) {
        int l = 0;
        while (l < r) {
            int m = (l+r)>>>1;
            if (tails[m]>= x) r = m;
            else l = m+1;
        }
        return l;
    }
    
    static class Node implements Comparable<Node> {
        int idx, val;
        Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
        
        public int compareTo(Node o) {
            return this.idx - o.idx;
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