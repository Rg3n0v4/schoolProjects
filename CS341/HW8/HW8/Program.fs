// Learn more about F# at http://fsharp.org
// See the 'F# Tutorial' project for more help.
open hw08
[<EntryPoint>]
let main argv = 
    let hw0801 = scrabbleCharToPointTR ['S'; 'C'; 'R'; 'A'; 'B'; 'B'; 'L'; 'E']
        if hw0801 = [1; 3; 1; 1; 3; 3; 1; 1] then
            printfn "hw0801 pass on small list"
        else
            printfn "hw0801 fail on small list"
        
    let pointValues = [('A',1); ('B',3); ('C',3); ('D',2); ('E',1); ('F',4); ('G',2); ('H',4); ('I',1); ('J',8); ('K', 5); ('L',1); ('M',3); ('N',1); ('O',1); ('P',3); ('Q',10); ('R',1); ('S',1); ('T',1); ('U',1); ('V',4); ('W',4); ('X',8); ('Y',4); ('Z',10)]
    
    let hw0802 = scrabbleCharToPointHO ['S'; 'C'; 'R'; 'A'; 'B'; 'B'; 'L'; 'E'] pointValues
        if hw0802 = [1; 3; 1; 1; 3; 3; 1; 1] then
            printfn "hw0802 pass on small list"
        else
            printfn "hw0802 fail on small list"
        
    let hw0803 = sumTripleWordScrabbleTR [1; 3; 1; 1; 3; 3; 1; 1]
    if hw0803 = 92 then
        printfn "hw0803 pass on small list"
    else
        printfn "hw0803 fail on small list"
        
    let hw0804 = sumTripleWordScrabbleHO [1; 3; 1; 1; 3; 3; 1; 1]
    if hw0804 = 92 then
        printfn "hw0804 pass on small list"
    else
        printfn "hw0804 fail on small list"
        
    let hw0805 = howManySatisfyBothTR (fun x -> x >5) (fun y -> y%2=0) [1; 2; 5; 6; 11; 18]
    if hw0805 = 2 then
        printfn "hw0805 pass on small list"
    else
        printfn "hw0805 fail on small list"
        
    let hw0806 = howManySatisfyBothHO (fun x -> x >5) (fun y -> y%2=0) [1; 2; 5; 6; 11; 18]
    if hw0806 = 2 then
        printfn "hw0806 pass on small list"
    else
        printfn "hw0806 fail on small list"
    0 // return an integer exit code
