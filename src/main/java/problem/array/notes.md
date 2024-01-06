Arrays
========

1. When dealing with arrays, first of all try to analyze the state of all variables during array traversing.
   a) use case: sum of elements - track the running sum
   b) intervals: track the moment of their starting, ending

 sum1 = [id1, id2, id3]      <-- arrays with feature 'uniques numbers <= k'
 sum2 = [id2, id3]           <-- arrays with feature 'uniques numbers < k'
 sum1 - sum2 = [id1, id2, id3] - [id2, id3] = [id1]    <-- arrays with feature 'uniques numbers == k' 
