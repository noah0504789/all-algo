import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;
    private static int[] pre, preIdx, post;
    private static int n, idx;

    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        n = readInt();        

        pre = new int[n];
        preIdx = new int[n+1];
        for (int i = 0; i < n; i++) preIdx[pre[i] = readInt()] = i;
        
        post = new int[n];
        for (int i = 0; i < n; i++) post[i] = readInt();
        
        preOrder(0, n-1, 0, n-1);

        System.out.print(sb);
    }
    
    public static void preOrder(int is, int ie, int ps, int pe) {
        if (is > ie || ps > pe) return;
        
        int root = post[pe];
        
        sb.append(root).append(" ");
        
        int rootIdx = preIdx[root], len = rootIdx - is;
                
        preOrder(is, rootIdx-1, ps, ps + len - 1);
        preOrder(rootIdx+1, ie, ps + len, pe - 1);
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
