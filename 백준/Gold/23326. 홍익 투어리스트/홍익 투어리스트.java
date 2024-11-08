import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;

    private static final int[] DIRS = {1};
    private static Queue<int[]> queue;
    
    private static TreeSet<Integer> tree;
    private static BitSet visited;
    private static boolean[] tours;
    private static int n, q, cur, cmd, tour, move;

    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        n = readInt();
        q = readInt();

        tree = new TreeSet<>();
        
        for (int i = 1; i <= n; i++) {
            if (readInt() == 0) continue;
            
            tree.add(i);
        }

        cur = 1;

        for (int i = 0; i < q; i++) {
            cmd = readInt();

            if (cmd == 1) {
                tour = readInt();
                
                if (tree.contains(tour)) tree.remove(tour);
                else tree.add(tour);
            } else if (cmd == 2) {
                cur += readInt();
                cur %= n;
                
                if (cur == 0) cur = n;
            } else {
                sb.append(getDist()).append("\n");
            }
        }

        System.out.print(sb);
    }

    public static int getDist() {
        if (tree.contains(cur)) return 0;
        if (tree.isEmpty()) return -1;
        
        Integer high = tree.ceiling(cur);
        
        return high != null ? high - cur : n - cur + tree.first();
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
