import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static StringTokenizer st;
    private static StringBuilder sb;
    
    private static Map<String, Integer> map;
    private static String type, name;
    private static long ans;
    private static int tc, n;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        map = new HashMap<>();
        tc = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < tc; i++) {
            n = Integer.parseInt(br.readLine());
            
            map.clear();
            
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());                        
            
                name = st.nextToken();
                type = st.nextToken();
            
                map.put(type, map.getOrDefault(type, 0)+1);                    
            }
            
            ans = 1;
            
            for (int cnt : map.values()) ans *= (cnt+1);
            
            sb.append(ans-1).append("\n");
        }

        System.out.print(sb);
    }
}