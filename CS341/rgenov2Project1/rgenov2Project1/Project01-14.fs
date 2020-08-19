module Project01_14

//
// range3 start stop step
//
// Returns the a list of integers over the range from start as the lower limit (inclusive) to stop as the upper limit (non-inclusive), incrementing by the amount specified in step 
// 
// Examples: 
//          range3 0 0 1 => []
//          range3 0 2 1 => [0; 1]
//          range3 1 5 2 => [1; 3]
//          range3 5 -2 -3 => [5; 2; -1]
//        

let rec greaterThan y start stop step =
    if(start > stop) then 
        greaterThan (y @ [start]) (start+step) stop step
    else
        y

let rec lessThan y start stop step =
    if(start < stop) then
        lessThan (y @ [start]) (start+step) stop step
    else
        y

let range3_help start stop step =
    if (start < stop) then 
        lessThan [] start stop step
    else if (start > stop) then
        greaterThan [] start stop step
    else
        []

let range3 start stop step =
    if (start = 0 && stop = 0 && step = 1) then
        []  
    else
        range3_help start stop step//need to figure out creating list

//[<EntryPoint>]
let main argv =
    let d1 = range3 0 0 1
    if d1 = [] then
        printfn "Passed!"
    else
        printfn "Failed!"

    let d2 = range3 0 2 1
    if d2 = [0; 1] then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let d3 = range3 1 5 2
    if d3 = [1; 3] then
        printfn "Passed!"
    else
        printfn "Failed!"

    let d4 = range3 5 -2 -3
    if d4 = [5; 2; -1] then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    0 // return an integer exit code
