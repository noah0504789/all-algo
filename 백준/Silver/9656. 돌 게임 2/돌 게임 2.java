import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    
    public static void main(String... args) throws IOException {
        n = readInt();

        System.out.print(n%2 == 1 ? "CY" : "SK");
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       

        while (c <= ' ') c = System.in.read();
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return negative ? -r : r;
    }
}