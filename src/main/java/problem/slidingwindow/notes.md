Sliding window
===============

Sliding window (aka Dynamic window) is a part of sequence defined by 2 pointers

When designing the algorithm:

1) use 2 pointers and collect all possible statistics about the content of this window
2) update statistics on adding/removing elements
3) use statistics to find the max/min of parameters, including the geometry (ie. the window itself) 