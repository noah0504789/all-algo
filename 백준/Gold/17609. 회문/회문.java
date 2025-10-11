import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static BufferedReader br;
    private static final int P = 0, S = 1, N = 2;
    private static int tc;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        br = new BufferedReader(new InputStreamReader(System.in));
        
        tc = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < tc; i++) {
            sb.append(solution(br.readLine())).append("\n");
        }

        System.out.print(sb);
    }
    
    private static int solution(String s) {
        int l = 0, r = s.length()-1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                if (isPal(s, l+1, r) || isPal(s, l, r-1)) return S; 
                return N;
            }
        }
        
        return P;
    }
    
    private static boolean isPal(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        
        return true;
    }
}