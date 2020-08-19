


Name: Raphael Genova

-----------------------------------------------

Please confirm that you compiled your solution with test cases exercising ALL
functions using g++ -std=c++11.  Confirm this statement by typing YES below.
(If you did not do this, compilation errors may result in an overall grade of
zero!)

YES




Describe what augmentations to the bst data structures you made to complete the 
project -- i.e., what types / data members did you change and why?


I changed the way bst_node was created. I added in a leftVal and a rightVal to
make sure that the height of both the left and right subtrees can be taken into account
for when I size balance the tree. 




-----------------------------------------------
Which functions did you need to modify as a result of the augmentations from the previous
question?  


I had to adjust the _insert() function's inner workings because the tree needed to be
balanced along the way for all input values. 







-----------------------------------------------
For each function from the previous question, how did you ensure that the (asymptotic) runtime 
remained the same?


I ensured the runtime by having the function follow back the insertion path it took for inserting
a new node. 





-----------------------------------------------
For each of the assigned functions, tell us how far you got using the choices 0-5 and
answer the given questions.  


0:  didn't get started
1:  started, but far from working
2:  have implementation but only works for simple cases
3:  finished and I think it works most of the time but not sure the runtime req is met. 
4:  finished and I think it works most of the time and I'm sure my design leads to 
       the correct runtime (even if I still have a few bugs).
5:  finished and confident on correctness and runtime requirements


to_vector level of completion:  _____5______  


-----------------------------------------------
get_ith level of completion:  _____5______  

    How did you ensure O(h) runtime?

    ANSWER: I ensured O(h) runtime by having the currentIndex be changed every time the while loop was going down the tree.
-----------------------------------------------
position_of level of completion:  _____5______  

    How did you ensure O(h) runtime?

    ANSWER: I ensured O(h) runtime by having the currentIndex be changed every time the while loop was going down the tree.
-----------------------------------------------
num_geq level of completion:  ____5_______  

    How did you ensure O(h) runtime?

    ANSWER: I ensured O(h) runtime by having the while loop loop through the whole binary search tree until it reached the end of the tree.

-----------------------------------------------
num_leq level of completion:  _____5_______

    How did you ensure O(h) runtime?

    ANSWER: I ensured O(h) runtime by having the while loop loop through the whole binary search tree until it reached the end of the tree.

-----------------------------------------------
num_range level of completion:  ____5________

    How did you ensure O(h) runtime?

    ANSWER: I ensured O(h) runtime by uitilzing num_leq() twice.

-----------------------------------------------
extract_range level of completion:  _____5_______

    How did you ensure O(h+k) runtime?

    ANSWER: I ensured O(h+k) runtime by having the while loop go through the height of the binary tree and change between the left and right 
    nodes based on if the the value of the current node was less than the given minimum or greater than the maximum.

Implementation of size-balanced criteria according to 
the given guidelines:

    Level of completion: _____3______











