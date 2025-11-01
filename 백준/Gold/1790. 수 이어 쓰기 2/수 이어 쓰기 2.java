import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int n, k, length;
    
    public static void main(String... args) throws IOException {
        String input[] = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);
        
        int sum = 0, base = 1;
        for (int i = 1; i < 9; i++, base *= 10) {
            int cnt = base * 9;
            if (sum + cnt * i < k) {
                sum += cnt * i;
                continue;
            }
            
            int num = base + (k-sum-1)/i;
            if (num > n) System.out.print(-1);
            else System.out.print(String.valueOf(num).charAt((k-sum-1)%i));
            return;
        }
        
        System.out.print(k-sum <= 9 ? (k==sum?1:0):-1);
    }
}