module Project01_05

//
// map F L
//
// Maps function F to L - Returns list produced by mapping function F over L
// 
// Examples: map (fun x -> x + 1) []                  => []
//           map (fun x -> x + 1) [23]                => [24]
//           map (fun x -> x - 1) [23; 43]            => [22; 42]
//           map (fun x -> x - 1) [23; 43]            => [22; 42]
//           map (fun i -> (char i)) [99; 97; 115; 116] => ['c';'a';'s';'t']
//           map (fun c -> (char ((int c)+1))) ['a';'b';'c']  => ['b';'c';'d']
// 
// You may not call List.map directly in your solution.
// 
// For more information on the behavior of this function you can visit https://msdn.microsoft.com/visualfsharpdocs/conceptual/list.map%5b%27t%2c%27u%5d-function-%5bfsharp%5d
// 
let map F L =
    []     // TO BE IMPLEMENTED

//[<EntryPoint>]
let main argv =
    let map1 = map (fun x -> x + 1) []
    if map1 = [] then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let map2 = map (fun x -> x + 1) [23]
    if map2 = [24] then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let map3 = map (fun x -> x - 1) [23; 43]
    if map3 = [22; 42] then
        printfn "Passed!"
    else
        printfn "Failed!"     

    let input4 = [99; 97; 115; 116]
    let map4 = map (fun x -> (char x)) input4
    if map4 = ['c';'a';'s';'t'] then
        printfn "Passed!"
    else
        printfn "Failed!"

    let input5 = ['a'; 'b'; 'c']
    let map5 = map (fun x -> (char ((int x)+1))) input5
    if map5 = ['b';'c';'d'] then
        printfn "Passed!"
    else
        printfn "Failed!"     
    
    0 // return an integer exit code
