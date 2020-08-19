module Project01_02

//
// max L
//
// Returns maximum element of L
// 
// Examples: max []          => raises an exception (Unhandled Exception: System.ArgumentException: The input sequence was empty.)
//           max [-2; 4]     => 4
//           max [34]        => 34
//           max [10; 10; 9] => 10
//           max ['a'; 'e'; 'c'] => e
// 
// You may not call List.max directly in your solution.
// For more information on the behavior of this function you can visit https://msdn.microsoft.com/visualfsharpdocs/conceptual/list.max%5b%27t%5d-function-%5bfsharp%5d
// 
// 

let rec maxHelper l largest =
    match l with 
    | [] -> largest
    | e::rest when largest < e -> maxHelper rest e
    | e::rest -> maxHelper rest largest


let max L =
    maxHelper L

[<EntryPoint>]
let main argv =
    let max2 = max [-2; 4]
    if max2 = 4 then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let max3 = max [34]
    if max3 = 34 then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let max4 = max [10; 10; 9]
    if max4 = 10 then
        printfn "Passed!"
    else
        printfn "Failed!" 
        
    let max5 = max ['a'; 'e'; 'c']
    if max5 = 'e' then
        printfn "Passed!"
    else
        printfn "Failed!"
    
    0 // return an integer exit code
