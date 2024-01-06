DFS
======

Main idea - traverse through all variants one by one, has strong relation to Backtracking

Best problems to apply this approach to should have the following features:

1. there must be multiple ways to solve problem, and it is necessary to find the _optimal_ one

2. the whole space of variants can be represented by the tree of possibilities

3. every path can be _segmented_


Algorithm:

1. make one step

2. check the condition to terminate path building (at least one of the following):

  2.1 target has been reached
    
  2.2 constraint violation
  
3. if this point has been reached, choose in a consistent way how to lay out the next segment of path; 
   note it in seen map or similar data structure to avoid infinite loop
   
4. make recursive call

5. clear up seen map to restore initial state 
