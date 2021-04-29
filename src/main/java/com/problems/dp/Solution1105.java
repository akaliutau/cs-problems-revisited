package com.problems.dp;

/**
 * We have a sequence of books: the i-th book has thickness books[i][0] and
 * height books[i][1].
 * 
 * We want to place these books in order onto bookcase shelves that have total
 * width shelf_width.
 * 
 * We choose some of the books to place on this shelf (such that the sum of
 * their thickness is <= shelf_width), then build another level of shelf of the
 * bookcase so that the total height of the bookcase has increased by the
 * maximum height of the books we just put down. We repeat this process until
 * there are no more books to place.
 * 
 * Note again that at each step of the above process, the order of the books we
 * place is the same order as the given sequence of books. For example, if we
 * have an ordered list of 5 books, we might place the first and second book
 * onto the first shelf, the third book on the second shelf, and the fourth and
 * fifth book on the last shelf.
 * 
 * Return the minimum possible height that the total bookshelf can be after
 * placing shelves in this manner.
 * 
 * Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
 * Output: 6
 * 
 * Explanation: The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
 * Notice that book number 2 does not have to be on the first shelf.
 * 
 * IDEA: 1) add books until possible tracking for each book the state of the
 * shelf 2) when the shelf is full, true to put some books from the 1st shelf to
 * the 2nd one if this helps to decrease the height 3) update the total height
 * and repeat
 * 
 * 
 * State diagrammer:
 * 
 * 
 * [1,1],[2,3],  h = 3 
 * [2,3]         h = 3
 * 
 * [1,1],        h = 1 
 * [2,3],[2,3]   h = 3
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
 * 2) execute the back-processing for the deep up to shelfWidth
 *
 */
public class Solution1105 {

	public int minHeightShelves(int[][] books, int shelfWidth) {
		int n = books.length;

		int[] dp = new int[n];

		dp[0] = books[0][1];
		for (int i = 1; i < n; i++) {
			int runningShelfWidth = books[i][0];
			int shelfheight = books[i][1]; 
			dp[i] = dp[i - 1] + shelfheight;
			for (int j = i - 1; j >= 0 && books[j][0] + runningShelfWidth <= shelfWidth; j--) {// try to re-shuffle books up to the depth -shelfWidth
				runningShelfWidth += books[j][0];
				shelfheight = Math.max(shelfheight, books[j][1]);
                if (j == 0){// nowhere to go
                   dp[i] = shelfheight;
                }else{
				   dp[i] = Math.min(dp[i], dp[j - 1] + shelfheight);
                }
			}
		}
		return dp[n - 1];
	}

}
