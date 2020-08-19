module hw08

let rec scrabbleCharToPointTR_help L x = 
    match L with
    | [] -> List.rev x
    | 'A'::tl -> scrabbleCharToPointTR_help tl (1::x)
    | 'B'::tl -> scrabbleCharToPointTR_help tl (3::x)
    | 'C'::tl -> scrabbleCharToPointTR_help tl (3::x)
    | 'D'::tl -> scrabbleCharToPointTR_help tl (2::x)
    | 'E'::tl -> scrabbleCharToPointTR_help tl (1::x)
    | 'F'::tl -> scrabbleCharToPointTR_help tl (4::x)
    | 'G'::tl -> scrabbleCharToPointTR_help tl (2::x)
    | 'H'::tl -> scrabbleCharToPointTR_help tl (4::x)
    | 'I'::tl -> scrabbleCharToPointTR_help tl (1::x)
    | 'J'::tl -> scrabbleCharToPointTR_help tl (8::x)
    | 'K'::tl -> scrabbleCharToPointTR_help tl (5::x)
    | 'L'::tl -> scrabbleCharToPointTR_help tl (1::x)
    | 'M'::tl -> scrabbleCharToPointTR_help tl (3::x)
    | 'N'::tl -> scrabbleCharToPointTR_help tl (1::x)
    | 'O'::tl -> scrabbleCharToPointTR_help tl (1::x)
    | 'P'::tl -> scrabbleCharToPointTR_help tl (3::x)
    | 'Q'::tl -> scrabbleCharToPointTR_help tl (10::x)
    | 'R'::tl -> scrabbleCharToPointTR_help tl (1::x) 
    | 'S'::tl -> scrabbleCharToPointTR_help tl (1::x)
    | 'T'::tl -> scrabbleCharToPointTR_help tl (1::x)
    | 'U'::tl -> scrabbleCharToPointTR_help tl (1::x)
    | 'V'::tl -> scrabbleCharToPointTR_help tl (4::x)
    | 'W'::tl -> scrabbleCharToPointTR_help tl (4::x)
    | 'X'::tl -> scrabbleCharToPointTR_help tl (8::x)
    | 'Y'::tl -> scrabbleCharToPointTR_help tl (4::x)
    | 'Z'::tl -> scrabbleCharToPointTR_help tl (10::x)
    | _::tl -> scrabbleCharToPointTR_help tl (0::x)

let rec scrabbleLookupValue pointValues letter= 
    match pointValues with
    | [] -> 0
    | (a,b)::tl when a = letter -> b;
    | _::tl -> scrabbleLookupValue tl letter //loops back around if the second case doesn't match
    

let scrabbleCharToPointHO L pointValues = 
    List.map (scrabbleLookupValue pointValues) L

let rec sumTripleWordScrabbleTR_help l x =
    match l with
    | [] -> x+50
    | e::rest -> (sumTripleWordScrabbleTR_help rest ((e*3)+x)

let sumTripleWordScrabbleTR L = 
    sumTripleWordScrabbleTR_help L 0

let sumTripleWordScrabbleHO L = 
    let sumTripleWordScrabbleHO_help = List.map (fun x -> 3*x) L 
    List.sum L + 50


let rec howManySatisfyBothTR_help p1 p2 l curr = 
    match l with 
    | [] -> curr
    | e::rest when (e > p1) && (e%2 = 0) -> howManySatisfyBothTR_help p1 p2 rest (curr+1)
    | _::rest -> howManySatisfyBothTR_help p1 p2 rest curr

let howManySatisfyBothTR P1 P2 L = 
    howManySatisfyBothTR_help P1 P2 L 0

let howManySatisfyBothHO P1 P2 L = 
    let approach = List.filter (fun x -> x > P1 && x%P2 = 0) L
    List.length approach
