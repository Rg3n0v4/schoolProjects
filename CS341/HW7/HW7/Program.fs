#light

module hw07

//
// explode:
//
// Given a string s, explodes the string into a list of characters.
// Example: explode "apple" => ['a';'p';'p';'l';'e']
//
let explode s =
  Seq.toList s

//
// implode
//
// The opposite of explode --- given a list of characters, returns
// the list as a string.  Example: implode ['t';'h';'e'] => "the"
//
let implode L =
  let sb = System.Text.StringBuilder()
  L |> List.iter (fun c -> ignore (sb.Append (c:char)))
  sb.ToString()

//gets the length
let rec length L =
    match L with
        | [] -> 0
        | e::rest -> 1 + length rest

let rec numSpaces N =
    match N with
        | [] -> 0
        | e::tail -> if(e=' ') then 
                        1 + numSpaces tail
                     else 
                        numSpaces tail
let rec numCommas N =
    match N with
        | [] -> 0
        | e::tail -> if(e=',') then 
                        1 + numCommas tail
                     else 
                        numCommas tail
let rec numPeriods N =
    match N with
        | [] -> 0
        | e::tail -> if(e='.') then 
                        1 + numPeriods tail
                     else 
                        numPeriods tail

let rec _Acronym C =
    match C with
    | e::rest -> if e = ' ' then
                    let x = List.head rest
                    if x <> ' ' then
                        x::(_Acronym rest)
                    else
                        _Acronym rest
                 else
                    _Acronym rest
    | _ -> []
                 

let Acronym A =
    match A with
    | e::rest -> if e <> ' ' then
                    e::(_Acronym rest)
                 else
                    _Acronym rest
    | _ -> []

[<EntryPoint>]
let main argv =
    printf "input> "
    let input = System.Console.ReadLine()
    //
    let L = explode input
    //printfn "%A" L
    //
    // 1. length of string
    //
    let len = length L

    printfn "Length of total string = %A" len
    //
    // 2. # of spaces, commas, periods
    // 
    let spaces = numSpaces L
    printfn "Spaces = %A" spaces
    let commas = numCommas L
    printfn "Commas = %A" commas
    let periods = numPeriods L
    printfn "Periods = %A" periods
    //
    // 4. produce the list of characters in the acronym
    // 
    let L2 = Acronym L
    //
    // 5. implode and output new string
    //
    let s = implode L2
    printfn "Acronym = %A" s
    //
    0
