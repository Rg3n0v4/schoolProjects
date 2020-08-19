module Project01_13

//
// range2 start stop
//
// Returns a list of integers over the range from start as the lower limit (inclusive) to stop as the upper limit (non-inclusive) 
// 
// Examples: 
//          range2 0 0 => []
//          range2 0 1 => [0]
//          range2 1 5 => [1; 2; 3; 4]
//          range2 -2 3 => [-2; -1; 0; 1; 2]
//          

let rec range2_help x start stop =
    if (start = stop) then
        x
    else
        range2_help (x@[start]) (start+1) stop

let range2 start stop  =
    if (start = 0 && stop = 0) then 
        []
    else
        range2_help [] start stop


[<EntryPoint>]
let main argv =
    let d1 = range2 0 0
    if d1 = [] then
        printfn "Passed!"
    else
        printfn "Failed!"

    let d2 = range2 0 1 
    if d2 = [0] then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let d3 = range2 1 5
    if d3 = [1; 2; 3; 4] then
        printfn "Passed!"
    else
        printfn "Failed!"

    let d4 = range2 -2 3
    if d4 = [-2; -1; 0; 1; 2] then
        printfn "Passed!"
    else
        printfn "Failed!"
                
    0 // return an integer exit code
