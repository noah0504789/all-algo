import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static BufferedWriter bw;

    private static StringBuilder sb;
    private static String input, processed;
    private static int[] p;
    private static int size, center, centerIdx, right, max, mirror, start, end;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        input = br.readLine();
        processed = preprocess(input);
        
        size = processed.length();
        p = new int[size];
        
        center = centerIdx = right = max = 0;
        
        for (int i = 0; i < size; i++) {
            mirror = 2 * center - i;
            
            if (right > i) p[i] = Math.min(right - i, p[mirror]);
            
            while (i+p[i]+1 < size && i-p[i]-1 >= 0) {
                if (processed.charAt(i+p[i]+1) != processed.charAt(i-p[i]-1)) break;
                
                p[i]++;
            }
            
            if (i+p[i] > right) {
                center = i;
                right = i + p[i];
            }
            
            if (p[i] > max) {
                max = p[i];
                centerIdx = i;
            }
        }
        
        start = (centerIdx - max) / 2;
        end = start + max;
                
        bw.write(input.substring(start, end).length()+"");
        bw.flush();
    }
    
    private static String preprocess(String s) {
        sb.append('#');
        
        for (char c : s.toCharArray()) sb.append(c).append('#');
        
        return sb.toString();
    }
}