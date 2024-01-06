package problem.easy;

public class Solution1176 {
	
	public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int n = calories.length;
        int[] sum = new int[n];
        sum[0] = calories[0];
        for (int i = 1; i < n; i++){
            sum[i] = sum[i-1] + calories[i];
        }
        int total = 0;
        for (int i = 0; i <= n-k; i++){
            int curSum = i == 0 ? sum[i + k - 1] : sum[i + k - 1] - sum[i-1];
            if ( curSum < lower){
                total --;
            }else if (curSum > upper){
                total ++;
            }
        }
        
        return total;
    }
}
