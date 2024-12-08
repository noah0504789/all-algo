import java.util.*;

class Solution {
    private Set<Integer> my, draws;
    private boolean flag;
    private int[] cards;
    private int n, idx, ans, target;
    
    public int solution(int coin, int[] cards) {
        n = cards.length;
        my = new HashSet();
        draws = new HashSet();
        
        for (int i = 0; i < n/3; i++) my.add(cards[i]);
        idx = n/3;
        ans = 1;
        target = n+1;
                        
        while (idx < n) {            
            flag = false;
            
            //draws.clear();
            draws.add(cards[idx]);
            draws.add(cards[idx+1]);
            
            idx += 2;
            
            for (int i : my) {
                if (my.contains(target-i)) {
                    my.remove(i);
                    my.remove(target-i);
                    
                    flag = true;
                    
                    break;
                }
            }
            
            if (!flag && coin >= 1) {
                for (int i : my) {
                    if (draws.contains(target-i)) {
                        my.remove(i);
                        draws.remove(target-i);

                        flag = true;
                        coin--;
                        
                        break;
                    }
                }
            }
            
            if (!flag && coin >= 2) {
                for (int i : draws) {
                    if (draws.contains(target-i)) {
                        draws.remove(i);
                        draws.remove(target-i);

                        flag = true;
                        coin-=2;
                        
                        break;
                    }
                }
            }
            
            if (flag) ans++;
            else break;
        }
        
        return ans;
    }
        
}
