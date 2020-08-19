module hw0803

//
// sumTripleWordScrabbleTR L
//
// Given a list L of integers, computes the sum of triple the values in L, then add 50 to the result
// Written another way, apply the function (3*x) for each x in L, add 50 to the resulting list, and then sum the values of that combined list.
// Example: sumTripleWordScrabbleTR [1; 3; 1; 1; 3; 3; 1; 1] => 92
// 
// NOTE: Write a tail-recursive version using a helper function.
// Do not change the API of the original sumTripleWordScrabbleTR function.
// 

let rec sumTripleWordScrabbleTR_help l x =
    match l with
    | [] -> x+50
    | e::rest -> (sumTripleWordScrabbleTR_help rest ((e*3)+x)

let sumTripleWordScrabbleTR L = 
    sumTripleWordScrabbleTR_help L 0