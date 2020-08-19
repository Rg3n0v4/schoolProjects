#light

module Project01

//1
let rec length L =
    match L with
    | [] -> 0
    | e::tail -> 1 + length tail

//2
let rec max_help l largest =
    match l with 
    | [] -> largest
    | e::rest when largest < e -> max_help rest e
    | e::rest -> max_help rest largest

let max L =
    max_help L

//3
let rec min_help l smallest =
    match l with 
    | [] -> smallest
    | e::rest when smallest > e -> min_help rest e
    | e::rest -> min_help rest smallest

let min L = 
    min_help L

//4
let rec nth L n = 
    match L, n with
    | [], _ -> 1
    | e::rest, 1 -> e
    | e::rest, _ -> nth rest (n-1)

//5
let rec map_help x f l =
    match l with 
    | [] -> x |> List.rev
    | e::rest -> map_help ((f e)::x) f rest

let map F L = 
    map_help [] F L

//6
let rec iter_help f l =
    match l with
    | [] -> []
    | e::rest -> f e; 
                  iter_help f rest
let iter F L = 
    iter_help F L

//7
let rec reduce_help x f l =
    match l with
    | [] -> x
    | e::rest when List.isEmpty rest -> x
    | e::rest -> reduce_help (f x rest.Head) f rest

let reduce F L =
    let X = List.head L
    reduce_help X F L

//8
let rec fold F start L = 
    match F with
    | [] -> start
    | hd::tl -> fold F (F start hd) tl

//9
let rec flatten_help start list = 
    match list with
    | [] -> start
    | hd::tl -> flatten_help (start@hd) tl //starts concatination

let flatten L = 
    match L with
    | [] -> []
    | hd::tl -> flatten_help hd tl

//10
let rec zip_help L1 L2 =
    match L1 with
    | [] -> []
    | hd::tl -> (hd, List.head L2)::(zip_help tl (List.tail L2)) //starts building the list in (_,_) format

let zip L1 L2 = 
    if (L1 = []) then 
        []
    else
        zip_help  L1 L2
//11
let partOne (a,_) = //isolates the first part of the tuple
    [a]

let partTwo (_,b) = //isolates the second part fo the tuple
    [b]

let rec unzip_help L A B =
    match L with
    | [] -> (A, B) //base case
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

//12
let rec range stop =
    if (stop = 0) then 
        []
    else
        range(stop - 1)@[stop - 1]

//13    
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
//14
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
//15
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
//16
let rec filter F L = 
    match L with
    | [] -> []
    | hd::tl when (F hd) = true -> hd::filter F tl
    | _ -> filter F (List.tail L)

// Learn more about F# at http://fsharp.org
// See the 'F# Tutorial' project for more help.

[<EntryPoint>]
let main argv = 
    printfn "%A" argv
    0 // return an integer exit code
