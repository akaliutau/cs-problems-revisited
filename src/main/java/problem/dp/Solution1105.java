package problem.dp;

/**
 * We have a sequence of books: the i-th book has thickness books[i][0] and
 * totalHeight books[i][1].
 * 
 * We want to place these books in order onto bookcase shelves that have total
 * width shelf_width.
 * 
 * We choose some of the books to place on this shelf (such that the sum of
 * their thickness is <= shelf_width), then build another level of shelf of the
 * bookcase so that the total totalHeight of the bookcase has increased by the
 * maximum totalHeight of the books we just put down. We repeat this process until
 * there are no more books to place.
 * 
 * Note again that at each step of the above process, the order of the books we
 * place is the same order as the given sequence of books. For example, if we
 * have an ordered list of 5 books, we might place the first and second book
 * onto the first shelf, the third book on the second shelf, and the fourth and
 * fifth book on the last shelf.
 * 
 * Return the minimum possible totalHeight that the total bookshelf can be after
 * placing shelves in this manner.
 * 
 * Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
 * Output: 6
 * 
 * Explanation: The sum of the totalHeights of the 3 shelves are 1 + 3 + 2 = 6.
 * Notice that book number 2 does not have to be on the first shelf.
 * 
 * IDEA: 
 * approach is the same as in 2-parameter LIS
 * 
 * 1) add books one by one until it's possible tracking after each book the state of the shelf 
 * 
 * 2) when the shelf is full, true to put some books from the 1st shelf to
 * the 2nd one if this helps to decrease the totalHeight 
 * 
 * 3) update the total totalHeight and repeat
 * 
 * 
 * State diagrammer:
 * [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]]
 * 
 * [1,1],[2,3],  h = 3 
 * [2,3]         h = 3
 * 
 * [1,1],        h = 1 
 * [2,3],[2,3]   h = 3 <-- we put some books starting from 1 to the 2nd shelf, and this helped to optimize totalHeight
 * 
 * 
 * [1,1],         h = 1 
 * [2,3],[2,3],   h = 3 
 * [1,1]          h = 1
 * 
 * 
 * [1,1], 
 * [2,3],[2,3], 
 * [1,1],[1,1],[1,1],[1,2]
 * 
 * 
 * IDEA:
 * 1) add book
 * 2) execute the back-processing for the re-shuffle process with depth up to shelfWidth
 *
 */
public class Solution1105 {

	public int minHeightShelves(int[][] books, int shelfWidth) {
		int n = books.length;

		int[] totalHeight = new int[n];

		totalHeight[0] = books[0][1];// put the 1st book on the 1st shelf
		
		// to start shifting process must have at least 2 books
		
		for (int i = 1; i < n; i++) {
			// we are starting from the last book
			int curShelfWidth = books[i][0];
			int curShelfHeight = books[i][1]; 
			totalHeight[i] = totalHeight[i - 1] + curShelfHeight;
			
			for (int j = i - 1; j >= 0; j--) {// try to re-shuffle books up to the depth -shelfWidth, starting from previous book
				int prevBookWidth = books[j][0];
				int prevBookHeight = books[j][1]; 
				if (prevBookWidth + curShelfWidth > shelfWidth) {
					break;
				}
				// take the previous book and put it on the current shelf
				// calculate the width and height of current self after shift
				curShelfWidth += prevBookWidth;
				curShelfHeight = Math.max(curShelfHeight, prevBookHeight);
				int totalPrevHeightWithoutCurrentBook = j == 0 ? 0 : totalHeight[j - 1]; //we shifted ALL books to the current shelf, so total height will coincide with curShelfHeight
				
  			    totalHeight[i] = Math.min(totalHeight[i], totalPrevHeightWithoutCurrentBook + curShelfHeight);
 			}
		}
		return totalHeight[n - 1];
	}

}
