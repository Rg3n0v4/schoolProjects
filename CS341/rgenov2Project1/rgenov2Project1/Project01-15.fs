module Project01_15

//
// slice L start stop
//
// Returns a slice of the list with the specified starting and ending indices (start inclusive and end non-inclusive)
// This function creates a list containing the copied values from the input list between the starting and ending index
// 
// Examples: 
//          slice [1; 2; 3; 4; 5] 0 0 => []
//          slice [1; 2; 3; 4; 5] 0 1 => [1]
//          slice [1; 2; 3; 4; 5] 1 4 => [2; 3; 4]
//          slice [1; 2; 3; 4; 5] 0 5 => [1; 2; 3; 4; 5]
//          slice [1; 2; 3; 4; 5; 6; 7; 8; 9; 10] 6 2 => []
//        

let rec slice_create beginL x l stop =
    if beginL = stop then 
        x
    else 
        slice_create (beginL+1) (x @ [List.head l]) (List.tail l) stop 
        //recurse through and increment beginL, add on the head of the list of l, keep passing l, and keep giving stop
   
let rec slice_help currIndex l start stop =
    if currIndex <> start then 
        slice_help (currIndex+1) (List.tail l) start stop 
        //recurse throigh and increment currIndex,
    else
        slice_create currIndex [] l stop //goes into another helper function for list creation

let slice L start stop =
    if ((start=0 && stop=0) || (start > stop)) then 
        []
    else
        slice_help 0 L start stop 


[<EntryPoint>]
let main argv =
    let t1 = slice [1; 2; 3; 4; 5] 0 0
    if t1 = [] then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let t2 = slice [1; 2; 3; 4; 5] 0 1
    if t2 = [1] then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let t3 = slice [1; 2; 3; 4; 5] 1 4
    if t3 = [2; 3; 4] then
        printfn "Passed!"
    else
        printfn "Failed!"     
        
    let t4 = slice [1; 2; 3; 4; 5] 0 5
    if t4 = [1; 2; 3; 4; 5] then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let t5 = slice [1; 2; 3; 4; 5; 6; 7; 8; 9; 10] 6 2
    if t5 = [] then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    0 // return an integer exit code
