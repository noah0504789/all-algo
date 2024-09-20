import java.io.*;
import java.util.*;

public class Main {
    private static Homework[] homeworks;
    private static boolean[] days;
    private static int n, ans = 0;

    public static void main(String... args) throws IOException {
        n = readInt();
        homeworks = new Homework[n];
        
        days = new boolean[1001];
        
        for (int i = 0; i < n; i++) homeworks[i] = new Homework(readInt(), readInt());
        
        Arrays.sort(homeworks);
        
        for (Homework homework : homeworks) {
            for (int i = homework.d; i > 0; i--) {
                if (days[i]) continue;
                
                days[i] = true;
                ans+=homework.reword;
                break;
            }
        }
        
        System.out.print(ans);
    }
        
    static class Homework implements Comparable<Homework> { 
        final int d, reword;
        
        Homework (int d, int reword) {
            this.d = d;
            this.reword = reword;
        }
        
        public int compareTo(Homework other) {
//            if (other.reword == this.reword) return Integer.compare(other.d, this.d);
            
            return Integer.compare(other.reword, this.reword);
        }      
    }

    private static int readInt() throws IOException {
        int result = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();

        while (c >= '0' && c <= '9') {
            result *= 10;
            result += c - '0';
            c = System.in.read();
        }

        return result;
    }
}
