package problem.misc;

/**
 * An experiment is being conducted in a lab. To ensure accuracy, there are two
 * sensors collecting data simultaneously. You are given two arrays sensor1 and
 * sensor2, where sensor1[i] and sensor2[i] are the ith data points collected by
 * the two sensors.
 * 
 * However, this type of sensor has a chance of being defective, which causes
 * exactly one data point to be dropped. After the data is dropped, all the data
 * points to the right of the dropped data are shifted one place to the left,
 * and the last data point is replaced with some random value. It is guaranteed
 * that this random value will not be equal to the dropped value.
 * 
 * For example, if the correct data is [1,2,3,4,5] and 3 is dropped, the sensor
 * could return [1,2,4,5,7] (the last position can be any value, not just 7). We
 * know that there is a defect in at most one of the sensors. Return the sensor
 * number (1 or 2) with the defect. If there is no defect in either sensor or if
 * it is impossible to determine the defective sensor, return -1.
 * 
 * IDEA:
 * 
 * 1. consider one sensor to be faulty, and the other not, calculate the number of drops and positions
 * 
 *
 */
public class Solution1826 {
	
	/**
	 *  Hypothesis: sensor1 is failing
	 *  Try to investigate this case, and return -1 in the 1st elem of result if some discrepancy found,
	 *  or 0 plus pointers if all ok 
	 */
	static int[] check(int[] sensor1, int[] sensor2) {
	       int i1 = 0, i2 = 0, n = sensor1.length;
	        
	        // finding the first miss match index
	        while(i1 < n){
	            if(sensor1[i1] == sensor2[i2]){
	                i2++;
	                i1++;
	            }else{
	                break;
	            }
	        }
	        // if the first miss match index is the last one, then we cannot say anything 
	        if(i1 == n - 1 || i1 == n){
	            return new int[] {-1, i1, i2};
	        }
	        
	        // else difference has been found somewhere in the middle
	        // drop the difference
	        i1 ++;
	        while(i1 < n){
	            if(sensor1[i1] != sensor2[i2]){
	                break;
	            }
	            
	            i1++;
	            i2++;
	        }
	        return new int[] {0, i1, i2};
	}
	
	public int badSensor(int[] sensor1, int[] sensor2) {
		int n = sensor1.length;
		int[] res1 = check(sensor1, sensor2);
		int[] res2 = check(sensor2, sensor1);
		if (res1[0] == -1 || res1[0] == -1) {
			return -1;
		}
		if (res1[0] == 0 && res2[0] == 0 && res1[1] == n && res2[1] == n ) {
			return -1;
		}
		return res1[1] == n ? 2 : 1;
	}
}
