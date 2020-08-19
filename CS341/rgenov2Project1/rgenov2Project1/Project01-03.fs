module Project01_03

//
// min L
//
// Returns minimum element of L
// 
// Examples: min []          => raises an exception (Unhandled Exception: System.ArgumentException: The input sequence was empty.)
//           min [-2; 4]     => -2
//           min [34]        => 34
//           min [10; 9; 9; 101] => 9 
//           min ['d', 'r', 'b'] => b
//
// You may not call List.min directly in your solution.
// For more information on the behavior of this function you can visit https://msdn.microsoft.com/visualfsharpdocs/conceptual/list.min%5b%27t%5d-function-%5bfsharp%5d
// 
// 
let min L =
    List.head L    // TO BE IMPLEMENTED

//[<EntryPoint>]
let main argv =
    let min2 = min [-2; 4]
    if min2 = -2 then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let min3 = min [34]
    if min3 = 34 then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let min4 = min [10; 9; 9; 101]
    if min4 = 9 then
        printfn "Passed!"
    else
        printfn "Failed!"  

    let min5 = min ['d'; 'r'; 'b'] 
    if min5 = 'b' then
        printfn "Passed!"
    else
        printfn "Failed!" 
    
    0 // return an integer exit code
