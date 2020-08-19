module Project01_08

//
// fold F accumulator L
//
// Applies a function f to each element of the collection, threading an accumulator argument through the computation.
//
// Returns a value 
// 
// Examples: 
//          fold (fun x y -> x&&y) true [] => true
//          fold (fun x y -> x+(string y)) "Hello " ['W';'o';'r';'l';'d'] => "Hello World"
//          fold (fun x y -> x+y) -60 [23; 43; 6] => 12
//          fold (fun x y -> x*y) 1 [23; 5; 80] => 9200
//          
// You may not call List.fold directly in your solution.
// 
// For more information visit https://msdn.microsoft.com/visualfsharpdocs/conceptual/list.fold%5b%27t%2c%27state%5d-function-%5bfsharp%5d
// 

let rec fold F start L = 
    match F with
    | [] -> start
    | hd::tl -> fold F (F start hd) tl

//[<EntryPoint>]
let main argv =
    let f1 = fold (fun x y -> x&&y) true []
    if f1 = true then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let f2 = fold (fun x y -> x+(string y)) "Hello " ['W';'o';'r';'l';'d']
    if f2 = "Hello World" then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    let f3 = fold (fun x y -> x+y) -60 [23; 43; 6]
    if f3 = 12 then
        printfn "Passed!"
    else
        printfn "Failed!"

    let f4 = fold (fun x y -> x*y) 2 [23; 5; 80]
    if f4 = 9200 then
        printfn "Passed!"
    else
        printfn "Failed!"
        
    0 // return an integer exit code
