package problem.string;

/**
 * 
 * Order and str are strings composed of lowercase letters. In order, no letter
 * occurs more than once.
 * 
 * order was sorted in some custom order previously. We want to permute the
 * characters of str so that they match the order that order was sorted. More
 * specifically, if x occurs before y in order, then x should occur before y in
 * the returned string.
 * 
 * Return any permutation of str (as a string) that satisfies this property.
 * 
 * Example: Input: 
 * order = "cba" 
 * str = "abcd" 
 * 
 * Output: "cbad"
 * 
 * IDEA:
 * use frequency map which should serve as map + statistics structure
 *  
 * 
 * Algorithm:
 * 1. Calculate frequency for initial string
 * 2. use this frequency map to add all symbols from the original string 
 * 3. attach all unmapped symbols to the tail
 *  
 */
public class Solution791 {

	public String customSortString(String order, String str) {
        int mapped[] = new int[26];
        for(char i : str.toCharArray()){
            mapped[i - 'a'] ++;
        }
        
        StringBuilder res = new StringBuilder();
        for(char c : order.toCharArray()){
            while(mapped[c - 'a'] > 0){// we have the character 'c' in original string - add THEM ALL to resulting string 
                res.append(c);
				mapped[c - 'a'] --;
            }
        }
        
        for(char c : str.toCharArray()) {// add all the rest symbols which we do not have the mapping for
            if(mapped[c - 'a'] != 0) res.append(c);
        }
         
        return res.toString();
    }

}
