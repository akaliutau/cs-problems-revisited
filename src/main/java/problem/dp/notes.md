Dynamic Programming
====================

DP is just an optimization of brute force approach, with memoization to speed up the process of calculations

In any case the stages in solving problem must be as following ones:

## Bottom-Up

0. Start with BF in order to determine parameter space - which parameters must be mutated in order to calculate all possibilities?

1. Identify parameter space, it's dimensionality. It could be the size of input, some other param which 
   correlates with increasing complexity of the problem. How should param space relate to problem's question?  

2. Identify edge cases with one parameter only ( i is 0 or 1, linear case)

3. Define the state (function) - should answer the question

4. Identify relations between states with increase of one or more parameters

5. Implement the DP algorithm


Complexity of DP solution - the number of all possible tuples of parameters which we have to check

## Top-Down

If min/max must be defined during calculations:

1. Start from BF

2. During construction of solution determine the very first step - the recursive function
   all the rest parts will be sub-problems, reflecting the first step

3. Define _recursive function_ and memoization data structure

## Note about memo data structure:
 
Memoize the state, which is fully defined by mutated parameters in the recursive function

Do not use backtracking with memoization, as it could filter out optimal solution (memoization itself caches repetitions)

