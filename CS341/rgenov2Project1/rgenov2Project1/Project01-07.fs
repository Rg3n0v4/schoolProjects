module Project01_07

//
// reduce F L
//
// reduces L to a single value by applying function F
// 
// Examples: reduce (fun x y -> x+y) []             => Unhandled Exception: System.ArgumentException: The input list was empty.
//           reduce (fun x y -> x*y) [23; 4]        => 92
//           reduce (fun x y -> x+y) [23; 43; -60]  => 6 
//           reduce (fun x y -> if x > y then x else y) 
//                  ['c'; 'a'; 'n'; 'a'; 'd'; 'a']  => 'n' 
// 
// You may not call List.reduce directly in your solution.
// 
// For more information on the behavior of this function you can visit https://msdn.microsoft.com/visualfsharpdocs/conceptual/list.reduce%5b%27t%5d-function-%5bfsharp%5d
// 
let reduce F L =
    List.head L      //   TO BE IMPLEMENTED

//[<EntryPoint>]
let main argv =
    let red1 = reduce (fun x y -> x&&y) [false]
    if red1 = false then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let red2 = reduce (fun x y -> x*y) [23; 4]
    if red2 = 92 then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let red3 = reduce (fun x y -> x+y) [23; 43; -60]
    if red3 = 6 then
        printfn "Passed!"
    else
        printfn "Failed!"

    let input4 = ['c'; 'a'; 'n'; 'a'; 'd'; 'a']
    let red4 = reduce (fun x y -> if x > y then x else y) input4
    if red4 = 'n' then
        printfn "Passed!"
    else
        printfn "Failed!"

    0 // return an integer exit code
