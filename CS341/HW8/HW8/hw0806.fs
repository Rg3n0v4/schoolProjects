module hw0806

//
// howManySatisfyBothHO P1 P2 L
//
// Given two predicate functions 
// (functions which take a single element of the type in L and return a boolean)
// and a list of elements of the type input to the functions:
// Count the number of elements which satisfy both predicate functions.
// 
// Example: howManySatisfyBothHO (fun x -> x >5) (fun y -> y%2=0) [1; 2; 5; 6; 11; 18] => 2
//  In this case 6 and 18 are the values satisfying both, 
//  since 5,6,7,8 pass while 1 and 2 fail the first, and 2,6,18 pass while 1,5,11 fail the second
//  The intersection of these two sets is 6 and 18, 
//  and since there are two elements satisfying both predicate functions the function returns 2
// 
// NOTE: Write this using a higher-order approach, in 
// particular using List.map and List.sum, or List.filter
// and List.length.  Do not use other List functions.
// Do not change the API of the original howManySatisfyBothHO function.
// 
let howManySatisfyBothHO P1 P2 L = 
    let approach = List.filter (fun x -> x > P1 && x%P2 = 0) L
    List.length approach
