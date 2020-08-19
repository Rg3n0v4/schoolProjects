module Project01_11

//
// unzip L
//
// Unzip a list of pairs to a pair of lists
//
// Returns tuple of lists
// 
// Examples: 
//          unzip [] => ([], [])
//          unzip [(1, 3); (2, 56); (40, 6)] => ([1; 2; 40], [3; 56; 6])
//          unzip [(1, 'a'); (2, 'b'); (3, 'c')] => ([1; 2; 3], ['a'; 'b'; 'c'])
//
// You may not call List.unzip directly in your solution.
//
// For more information visit https://msdn.microsoft.com/visualfsharpdocs/conceptual/list.unzip%5b%27t1%2c%27t2%5d-function-%5bfsharp%5d
// 

let partOne (a,_) = //isolates the first part of the tuple
    [a]

let partTwo (_,b) = //isolates the second part fo the tuple
    [b]

let rec unzip_help L A B =
    match L with
    | [] -> (A, B) //basecase
    | hd::tl -> let x = partOne hd
                let y = partTwo hd
                unzip_help tl (A @ x) (B @ y) //recurses back around with tl (rest of list) (A@x) (for concatinating the tuple) 
                                                //(B @ y) (same thing with concatinating the tuple)

let unzip L = 
    if( L = []) then 
        ([], [])
    else
        let A = [] //creates an empty list type
        let B = [] //creates another empty list type
        unzip_help L A B

//[<EntryPoint>]
let main argv =
    let u11,u12 = unzip []
    if (u11 = []) && (u12 = []) then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let u21,u22 = unzip [(1, 3); (2, 56); (40, 6)]
    if (u21 = [1; 2; 40]) && (u22 = [3; 56; 6]) then
        printfn "Passed!"
    else
        printfn "Failed!"

    let u31,u32 = unzip [(1, 'a'); (2, 'b'); (3, 'c')]
    if (u31 = [1; 2; 3]) && (u32 = ['a'; 'b'; 'c']) then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    0 // return an integer exit code
