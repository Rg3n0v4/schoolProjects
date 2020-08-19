// Learn more about F# at http://fsharp.org
// See the 'F# Tutorial' project for more help.

let rec length L =
    match L with
    | [] -> 0
    | e::rest -> 1 + length rest
 
let rec sum S = 
    match S with
    | head :: tail -> head + sum tail
    | [] -> 0
        
let rec average L T = 
    float T /  float L
    

[<EntryPoint>]
let main argv =
    printf "filename> "
    let filename = System.Console.ReadLine()
    let data_array = System.IO.File.ReadAllLines(filename)
    let data_list= Array.toList data_array
    //
    // convert strings to integers:
    //
    let values = List.map (fun s -> int s) data_list
    printfn "%A" values
    //
    let len = length values
    printfn "%A" len
    //
    let total = sum values
    printfn "%A" total
    //
    let avg = average len total
    printfn "%A" avg

    0
