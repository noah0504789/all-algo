import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static List<Integer> list = new ArrayList<>();;
    private static int[] primes;
    private static boolean[] nonPrime;
    private static int n, ans;

    public static void main(String... args) {
        n = sc.nextInt();
        ans = 0;

        nonPrime = new boolean[n+1];

        for (int i = 2; i*i <= n; i++) {
            if (nonPrime[i]) continue;

            for (int j = i+i; j <= n; j+=i) nonPrime[j] = true;
        }

        for (int i = 2; i <= n; i++) {
            if (!nonPrime[i]) list.add(i);
        }

        // primes = list.stream().mapToInt(Integer::intValue).toArray();

        int start = 0, end = 0, sum = 0;
        while (true) {                        
            if (sum>=n) {
                sum -= list.get(start);
                start++;    
            } else if (end == list.size()) {
                break;
            } else {
                sum += list.get(end);
                end++;
            }         
            
            if (sum == n) ans++;
        }

        System.out.println(ans);
    }

}