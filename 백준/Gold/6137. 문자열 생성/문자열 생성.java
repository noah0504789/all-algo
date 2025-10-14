import java.io.*;
import java.util.*;

public class Main {
        
    private static BufferedReader br;
    private static StringBuilder sb;
    private static int n;
    private static char[] arr;
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        arr = new char[n];
        for (int i = 0; i < n; i++) arr[i] = br.readLine().charAt(0);
        
        int l = 0, r = n-1;
        int printed = 0;
        while (l <= r) {
            boolean takeLeft;
            if (arr[l] < arr[r]) takeLeft = true;
            else if (arr[l] > arr[r]) takeLeft = false;
            else {                
                int i = l, j = r;
                while (i < j && arr[i] == arr[j]) {i++; j--;}
                    
                takeLeft = (i>=j) || (arr[i] <= arr[j]);
            }
            
            sb.append(takeLeft ? arr[l++] : arr[r--]);
            printed++;
            
            if (printed % 80 == 0) sb.append("\n");
        }

        System.out.print(sb);
    }
}