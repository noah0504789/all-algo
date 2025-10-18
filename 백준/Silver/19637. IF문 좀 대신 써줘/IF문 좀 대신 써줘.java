import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br;
    private static StringBuilder sb;
    private static int n, m, val;
    private static String[] inputs, names;
    private static int[] limits;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        inputs = br.readLine().split(" ");
                
        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);
        names = new String[n];
        limits = new int[n];
        for (int i = 0; i < n; i++) {
            inputs = br.readLine().split(" ");
            names[i] = inputs[0];
            limits[i] = Integer.parseInt(inputs[1]);
        }
        
        while (m-- > 0) {
            val = Integer.parseInt(br.readLine());
            sb.append(names[lowerBound(val)]).append("\n");
        }

        System.out.print(sb);
    }
    
    private static int lowerBound(int target) {
        int l = 0, r = n;
        while (l < r) {
            int m = (l+r)>>>1;
            if (limits[m] >= target) r = m;
            else l = m+1;
        }
        return l;
    }
}