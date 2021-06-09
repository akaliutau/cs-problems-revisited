Dynamic Programming
====================

DP is just an optimization of brute force approach, with memoization to speed up the process of calculations

In any case the stages in solving problem must be as following ones:

0. Start with BF in order to determine parameter space - which parameters must be mutated in order to calculate all possibilities?

1. Identify parameter space, it's dimensionality. It could be the size of input, some other param which 
   correlates with increasing complexity of the problem. How should param space relate to problem's question?  

2. Identify edge cases when one parameter is 0 or 1 (linear case)

3. Define the state (function) - should answer the question

4. Identify relations between states with increase of one or more parameters

5. Implement the DP algorithm (Top-Down or Bottom-Up) 


Complexity of DP solution - the number of all possible tuples of parameters which we have to check
