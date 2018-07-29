/**
 * Created by fahad on 7/29/2018.
 */

import java.util.*;

public class Hashing {

    public static void main(String[] args) throws Exception {

    }


    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int max = 0;
        for(int i = 0; i < nums.length; i ++){
            if(nums[i] == 0){
                count--;
            } else count++;
            if(count == 0){
                max = Math.max(max, i + 1);
            } else {
                if(map.containsKey(count)){
                    int index = map.get(count);
                    max = Math.max(max, i - index);
                } else{
                    map.put(count, i);
                }
            }
        }
        return max;
    }

    public boolean isAnagram(String s, String t) {
        int[] S = new int[256];
        int[] T = new int[256];
        if (s.length() != t.length()) return false;

        for (int i = 0, l = s.length(); i < l; i++) {
            S[s.charAt(i)]++;
        }

        for (int i = 0, l = t.length(); i < l; i++) {
            T[t.charAt(i)]++;
        }

        for (int i = 0; i < 256; i++) {
            if (S[i] != T[i]) return false;
        }

        return true;
    }

    public int findPairs(int[] nums, int k) {

       Map<Integer, Integer> map = new HashMap<>();
       int count = 0;
        if (nums.length == 0 || k < 0) return 0;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                if (entry.getValue() > 1)
                    count++;
            } else {
                if (map.containsKey(entry.getKey() + k))
                    count++;
            }
        }
        return count;
    }

    public String customSortString(String S, String T) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < T.length(); i ++){
            if(!map.containsKey(T.charAt(i))){
                map.put(T.charAt(i), 1);
            } else{
                map.put(T.charAt(i), map.get(T.charAt(i)) + 1);
            }
        }
        StringBuilder result = new StringBuilder();
        for(char c : S.toCharArray()){
            if(map.containsKey(c)){
                int count = map.remove(c);
                for(int i = 0; i < count; i ++){
                    result.append(c);
                }
            }
        }
        for(char c : map.keySet()){
            int count = map.get(c);
            for(int i = 0; i < count; i ++){
                result.append(c);
            }
        }
        return result.toString();
    }

    public static List<Integer> findAnagrams(String s, String p) {
        int[] TC = new int[256];
        int[] PC = new int[256];

        List<Integer> result = new ArrayList<>();
        int pLen = p.length();
        if (pLen > s.length()) return result;
        Arrays.fill(TC, 0);
        Arrays.fill(PC, 0);
        for (int i = 0; i < pLen; i++) {
            TC[s.charAt(i)]++;
            PC[p.charAt(i)]++;
        }

        int i = pLen;
        for (int l = s.length(); i < l; i++) {
            if (compare())
                result.add(i - pLen);

            TC[s.charAt(i)]++;
            TC[s.charAt(i - pLen)]--;
        }
        if (compare())
            result.add(i - pLen);

        return result;
    }

    private static boolean compare() {
        int[] TC = new int[256];
        int[] PC = new int[256];
        
        for (int i = 0; i < 256; i++) {
            if (TC[i] != PC[i])
                return false;
        }
        return true;
    }
}
