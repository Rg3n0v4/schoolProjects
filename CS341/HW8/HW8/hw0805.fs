module hw0805

open hw08

//
// howManySatisfyBothTR P1 P2 L
//
// Given two predicate functions 
// (functions which take a single element of the type in L and return a boolean)
// and a list of elements of the type input to the functions:
// Count the number of elements which satisfy both predicate functions.
// 
// Example: howManySatisfyBothTR (fun x -> x >5) (fun y -> y%2=0) [1; 2; 5; 6; 11; 18] => 2
//  In this case 6 and 18 are the values satisfying both, 
//  since 5,6,7,8 pass while 1 and 2 fail the first, and 2,6,18 pass while 1,5,11 fail the second
//  The intersection of these two sets is 6 and 18, 
//  and since there are two elements satisfying both predicate functions the function returns 2
// 
// NOTE: Write a tail-recursive version using a helper function.
// Do not change the API of the original howManySatisfyBothTR function.
// 

let rec howManySatisfyBothTR_help p1 p2 l curr = 
    match l with 
    | [] -> curr
    | e::rest when (e > p1) && (e%2 = 0) -> howManySatisfyBothTR_help p1 p2 rest (curr+1)
    | _::rest -> howManySatisfyBothTR_help p1 p2 rest curr

let howManySatisfyBothTR P1 P2 L = 
    howManySatisfyBothTR_help P1 P2 L 0


