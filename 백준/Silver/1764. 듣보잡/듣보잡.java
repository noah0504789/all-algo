import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Set<String> set = new HashSet();
    private static PriorityQueue<String> pq = new PriorityQueue();
    private static int n, m;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        n = readInt();
        m = readInt();
                
        for (int i = 0; i < n; i++) set.add(br.readLine());
        
        for (int i = 0; i < m; i++) {
            String name = br.readLine();
            if (set.contains(name)) pq.offer(name);
        }
        
        sb.append(pq.size()).append("\n");              
        
        while (!pq.isEmpty()) sb.append(pq.poll()).append("\n");              

        System.out.print(sb);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       

        while (c <= ' ') c = System.in.read();

        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
}
