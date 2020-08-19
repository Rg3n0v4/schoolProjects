module hw0802

//
// scrabbleCharToPointHO L
//
// Given a list L of characters, create a list of integers by getting the point value of each of the characters in list L
// let pointValues = [('A',1); ('B',3); ('C',3); ('D',2); ('E',1); ('F',4); ('G',2); ('H',4); ('I',1); ('J',8); ('K', 5); ('L',1); ('M',3); ('N',1); ('O',1); ('P',3); ('Q',10); ('R',1); ('S',1); ('T',1); ('U',1); ('V',4); ('W',4); ('X',8); ('Y',4); ('Z',10)]
// scrabbleCharToPointHO ['S'; 'C'; 'R'; 'A'; 'B'; 'B'; 'L'; 'E'] pointValues => [1; 3; 1; 1; 3; 3; 1; 1]
// 
// NOTE: Write a higher order version of scrabbleCharToPoint by mapping the helper function scrabbleLookupValue over L;
// scrabbleLookupValue can be written using regular recursion, since PointValues has a maximum length of 26 elements
// do not change the API of the original scrabbleCharToPointHO function.
// 
let rec scrabbleLookupValue letter pointValues= 
    match pointValues with
    | [] -> 0
    | (a,b)::tl when a = letter -> b;
    | _::tl -> scrabbleLookupValue letter tl //loops back around if the second case doesn't match
    


let scrabbleCharToPointHO pointValues L = 
    List.map (scrabbleLookupValue pointValues) L 

// pointValues = [('A',1); ('B',3); ('C',3); ('D',2); ('E',1); ('F',4); ('G',2); ('H',4); ('I',1); ('J',8); ('K', 5); ('L',1); ('M',3); ('N',1); ('O',1); ('P',3); ('Q',10); ('R',1); ('S',1); ('T',1); ('U',1); ('V',4); ('W',4); ('X',8); ('Y',4); ('Z',10)]
// the letter may also be the blank character ' ', any letter passed to the function not in the list should return a point value of 0 points