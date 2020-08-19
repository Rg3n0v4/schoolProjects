module Project01_09

//
// flatten L
//
// Flatten a list of lists to a single list
//
// Returns list 
// 
// Examples: 
//          flatten [[]] => []
//          flatten [[1]] => [1]
//          flatten [[1; 2]; [2; 3; 4]] => [1; 2; 2; 3; 4]
//			flatten [['o'; 'n']; [' ']; ['w'; 'i'; 'n'; 'g'; 's']] => ['o'; 'n'; ' '; 'w'; 'i'; 'n'; 'g'; 's']
// 
// You may not call List.concat or similar functions directly in your solution.
// 
// For more information visit https://msdn.microsoft.com/visualfsharpdocs/conceptual/list.concat%5b%27t%5d-function-%5bfsharp%5d
// 
let rec flatten_help start list = 
    match list with
    | [] -> start
    | hd::tl -> flatten_help (start@hd) tl

let flatten L = 
    match L with
    | [] -> []
    | hd::tl -> flatten_help hd tl

//[<EntryPoint>]
let main argv =
    let f1 = flatten [[]]
    if f1 = [] then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let f2 = flatten [[1]]
    if f2 = [1] then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let f3 = flatten [[1; 2]; [2; 3; 4]]
    if f3 = [1; 2; 2; 3; 4] then
        printfn "Passed!"
    else
        printfn "Failed!"

    let f3 = flatten [['o'; 'n']; [' ']; ['w'; 'i'; 'n'; 'g'; 's']]
    if f3 = ['o'; 'n'; ' '; 'w'; 'i'; 'n'; 'g'; 's'] then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    0 // return an integer exit code
