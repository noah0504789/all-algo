import java.util.*;

class Solution {
    String numbers;
    BitSet visited, checked;
    int answer, size, num;
    boolean[] notPrimes;
    
    public int solution(String numbers) {
        this.numbers = numbers;
        answer = 0;
        size = numbers.length();
        visited = new BitSet();
        checked = new BitSet();
        
        notPrimes = new boolean[(int) Math.pow(10, size)];
        notPrimes[0] = notPrimes[1] = true;        
        
        for (int i = 2; i * i <= notPrimes.length; i++) {
            if (notPrimes[i]) continue;
            
            for (int j = i+i; j < notPrimes.length; j += i) notPrimes[j] = true;
        }
        
        for (int k = 1; k <= size; k++) perm(k, 0);
        
        return answer;
    }        
    
    private void perm(int k, int depth) {
        if (depth == k) {
            // System.out.println("cand: " + num);
            if (isPrime(num) && !checked.get(num)) {
                // System.out.println("num: " + num);
                checked.set(num);
                answer++;
            }
            return;
        }
        
        for (int i = 0; i < size; i++) {
            if (visited.get(i)) continue;
            
            int curNum = Integer.parseInt(numbers.substring(i, i+1)) * (int) Math.pow(10, depth);
            
            visited.set(i);
            num += curNum;
            
            perm(k, depth+1);
            
            visited.clear(i);
            num -= curNum;
        }
    }
    
    private boolean isPrime(int num) {
        //if (num <= 3) return true;
        
        return !notPrimes[num];        
    }
}