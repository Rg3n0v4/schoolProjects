module Project01_10

//
// zip L1 L2
//
// Zip two lists
//
// Returns list of tuples
// 
// Examples: 
//          zip [] [] => []
//          zip [1] [1] => [(1, 1)]
//          zip [1; 2; 40] [3; 56; 6] => [(1, 3); (2, 56); (40, 6)]
//          zip [1; 2; 3] ['a'; 'b'; 'c'] => [(1, 'a'); (2, 'b'); (3, 'c')]
//          
// You may not call List.zip directly in your solution.
// 
// For more information visit https://msdn.microsoft.com/visualfsharpdocs/conceptual/list.zip%5b%27t1%2c%27t2%5d-function-%5bfsharp%5d
// 

let rec zip_help L1 L2 =
    match L1 with
    | [] -> []
    | hd::tl -> (hd, List.head L2)::(zip_help tl (List.tail L2))

let zip L1 L2 = 
    zip_help  L1 L2
    //[(List.head L1, List.head L2)]   // TO BE IMPLEMENTED

//[<EntryPoint>]
let main argv =
    let z1 = zip [] []
    if z1 = [] then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let z2 = zip [1] [1]
    if z2 = [(1,1)] then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let z3 = zip [1; 2; 40] [3; 56; 6]
    if z3 = [(1, 3); (2, 56); (40, 6)] then
        printfn "Passed!"
    else
        printfn "Failed!"     

    let z4 = zip [1; 2; 3] ['a'; 'b'; 'c']
    if z4 = [(1, 'a'); (2, 'b'); (3, 'c')] then
        printfn "Passed!"
    else
        printfn "Failed!"     

    0 // return an integer exit code
