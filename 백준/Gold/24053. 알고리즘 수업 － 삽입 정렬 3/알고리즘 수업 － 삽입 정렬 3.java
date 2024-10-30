import java.io.*;
import java.util.*;

public class Main {
    private static int[] a, b;
    private static int n;

    public static void main(String... args) throws IOException {
        n = readInt();
        
        a = new int[n];
        for (int i = 0; i < n; i++) a[i] = readInt();
        
        b = new int[n];
        for (int i = 0; i < n; i++) b[i] = readInt();
        
        if (Arrays.equals(a, b)) {
            System.out.print(1);
            return;
        }
        
        for (int i = 0; i < n; i++) {
            int j = i - 1;
            int newItem = a[i];
            
            while (j >= 0 && a[j] > newItem) {
                a[j+1] = a[j];
                if (a[i] == b[i] && Arrays.equals(a, b)) {
                    System.out.print(1);
                    return;
                }
                j--;
            }
            
            a[j+1] = newItem;
            if (a[i] == b[i] && Arrays.equals(a, b)) {
                System.out.print(1);
                return;
            }            
        }

        System.out.print(0);
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
