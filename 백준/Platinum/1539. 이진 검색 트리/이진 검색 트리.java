import java.io.*;
import java.util.*;

public class Main {
    private static TreeSet<Integer> tree;
    private static long ans;
    private static int[] height;
    private static int n;

    public static void main(String... args) throws IOException {
        n = readInt();

        height = new int[n+2];
        
        tree = new TreeSet<>();
        tree.add(0);
        tree.add(n+1);              
        
        ans = 0;

        for (int i = 0; i < n; i++) {
            int node = readInt() + 1;
            
            height[node] = Math.max(height[tree.higher(node)], height[tree.lower(node)]) + 1;
            
            ans += height[node];
            
            tree.add(node);
        }

        System.out.print(ans);
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
