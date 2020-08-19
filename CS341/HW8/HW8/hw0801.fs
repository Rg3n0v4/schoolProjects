module hw0801

//
// scrabbleCharToPointTR L
//
// Given a list L of characters, create a list of integers by getting the point value of each of the characters in list L
// Example: scrabbleCharToPointTR ['S'; 'C'; 'R'; 'A'; 'B'; 'B'; 'L'; 'E'] => [1; 3; 1; 1; 3; 3; 1; 1]
// 
// NOTE: write a tail-recursive version using a helper function.
// Do not change the API of the original scrabbleCharToPointTR function.
// 

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


let scrabbleCharToPointTR L = 
    scrabbleCharToPointTR_help L []


// Here are the point values of each letter in the game of Scrabble
// Use pattern matching against constant values (without the WHEN keyword) to decide the amount to add and make the recursive call
// A	1
// B	3
// C	3
// D	2
// E	1
// F	4
// G	2
// H	4
// I	1
// J	8
// K	5
// L	1
// M	3
// N	1
// O	1
// P	3
// Q	10
// R	1
// S	1
// T	1
// U	1
// V	4
// W	4
// X	8
// Y	4
// Z	10
