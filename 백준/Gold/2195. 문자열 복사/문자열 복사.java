import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;

    private static StringBuilder sb; 
    private static String s, p;
    private static int idx, ans;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        s = br.readLine();
        p = br.readLine();
        
        idx = 0;
        ans = 1;
        
        for (int i = 0; i < p.length(); i++) {
            if (s.indexOf(p.substring(idx, i+1)) != -1) continue;
            
            ans++;
            idx = i;
        }
        
        System.out.print(ans);
    }
}