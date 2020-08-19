module hw0804

//
// sumTripleWordScrabbleHO L
//
// Given a list L of integers, computes the sum of triple the values in L, then add 50 to the result
// Written another way, apply the function (3*x) for each x in L, add 50 to the resulting list, and then sum the values of that combined list.
// Example: sumTripleWordScrabbleHO [1; 3; 1; 1; 3; 3; 1; 1] => 92
// 
// NOTE: Write this using a higher-order approach, in 
// particular using List.map and List.sum.  Do not use
// other List. functions.  Do not write any recursive functions
// in the implementation of this function.
// Do not change the API of the original sumTripleWordScrabbleHO function.
// 
let sumTripleWordScrabbleHO L = 
    let sumTripleWordScrabbleHO_help = List.map (fun x -> 3*x) L 
    List.sum L + 50
