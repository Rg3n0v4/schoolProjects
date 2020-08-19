﻿//
// F# program to analyze payroll data.
//
// << Raphael Genova >>
// U. of Illinois, Chicago
// CS 341, Spring 2020
// Project #02
//

module Project02

open System.Net


//
// doubleOrNothing
//
// Given a string containing a double numeric value
// or being an empty string
// returns the double equivalent, with the empty string
// treated as the value 0.0
//
let doubleOrNothing s = 
    match s with
    | "" -> 0.0
    | x -> double x

//
// ParseCSVLine and ParseCSVDatabase
//
// Given a sequence of strings representing payroll data, 
// parses the strings and returns a list of tuples.  Each
// sub-list denotes one employee.  Example:
//
//[ ("JESSE A", "ACOSTA", "POLICE OFFICER", "POLICE", "Salary", 0.0, 93354.0, 0.0); ... ]
//
// The values are first name, last name, occupation,
// department, salary type, hours per week, annual salary,
// and hourly wage. 
// Depending on the salary type, 
// either hours per week and hourly wage 
// or annual salary will be filled in with 0.0,
// since the field is empty in the csv
//
// First Name, Last Name, Occupation, Dept Name, Fulltime or Part time, Typical Hours, Annual Salary, Hourly Rate
let ParseCSVLine (line:string) = 
    let tokens = line.Split(',')
    let listOfValues = Array.toList tokens
    //match listOfValues with  // alternative implmentation to remove warnings
    //| fName::lName::occupation::department::salaryType
    //    ::hoursPerWeek::annualSalary::hourlyWage::[] -> (fName,lName,occupation,department,salaryType,
    //                                                      (doubleOrNothing hoursPerWeek),
    //                                                      (doubleOrNothing annualSalary),
    //                                                      (doubleOrNothing hourlyWage)
    //                                                    )
    //| _ -> failwith "Insufficient values in line of csv file"

    let fName::lName::Occupation::Department::SalaryType::HoursPerWeek::AnnualSalary::HourlyWage::[] = listOfValues
    (fName,lName,Occupation,Department,SalaryType,
      (doubleOrNothing HoursPerWeek),
      (doubleOrNothing AnnualSalary),
      (doubleOrNothing HourlyWage)
    )

let rec ParseCSVDatabase lines = 
    let employees = Seq.map ParseCSVLine lines
    //printfn "%A" employees
    Seq.toList employees


// Returns whether the employee is salaried
//
// Example: on Input of
// ("JESSE A", "ACOSTA", "POLICE OFFICER", "POLICE", "Salary", 0.0, 93354.0, 0.0)
// Returns
// true
//
// Example: on Input of
// ("JOHN R", "KEATING", "FOREMAN OF ELECTRICAL MECHANICS", "GENERAL SERVICES",  "Hourly", 40.0, 0.0, 52.35)
// Returns
// false 
//
let isSalary employee =
    let (fName,lName,Occupation,Department,SalaryType,
         HoursPerWeek, AnnualSalary, HourlyWage
        ) = employee
    if SalaryType = "Salary" then 
        true
    else
        false

// Returns whether the employee is paid hourly, not salaried
//
// Example: on Input of
// ("JESSE A", "ACOSTA", "POLICE OFFICER", "POLICE", "Salary", 0.0, 93354.0, 0.0)
// Returns
// false
//
// Example: on Input of
// ("JOHN R", "KEATING", "FOREMAN OF ELECTRICAL MECHANICS", "GENERAL SERVICES",  "Hourly", 40.0, 0.0, 52.35)
// Returns
// true 
//
let isHourly employee =
    let (fName, lName, Occupation, Department, SalaryType,
            HoursPerWeek, AnnualSalary, HourlyWage
        ) = employee 
    if SalaryType = "Hourly" then
        true
    else
        false

// Returns the full name of the employee
//
// Example: on Input of
// ("JESSE A", "ACOSTA", "POLICE OFFICER", "POLICE", "Salary", 0.0, 93354.0, 0.0)
// Returns
// "JESSE A ACOSTA"
//
// Example: on Input of
// ("JOHN R", "KEATING", "FOREMAN OF ELECTRICAL MECHANICS", "GENERAL SERVICES",  "Hourly", 40.0, 0.0, 52.35)
// Returns
// "JOHN R KEATING"
//
let getName employee =
    let (fName,lName,Occupation,Department,SalaryType,
         HoursPerWeek, AnnualSalary, HourlyWage
        ) = employee
    fName + " " + lName

// Returns name of the department the employee belongs to
//
// Example: on Input of
// ("JESSE A", "ACOSTA", "POLICE OFFICER", "POLICE", "Salary", 0.0, 93354.0, 0.0)
// Returns
// "POLICE"
//
// Example: on Input of
// ("JOHN R", "KEATING", "FOREMAN OF ELECTRICAL MECHANICS", "GENERAL SERVICES",  "Hourly", 40.0, 0.0, 52.35)
// Returns
// "GENERAL SERVICES" 
//        
let getDepartment employee =
    let (fName,lName,Occupation,Department,SalaryType,
         HoursPerWeek, AnnualSalary, HourlyWage
        ) = employee
    Department

//
//The function calcSalary calculates the annual salary, 
// either by directly returning the recorded annual salary 
// or calculating it by finding the average weekly salary 
// by multiplying the hours per week by hourly wage, 
// and then multiplying that by 52 to get the average 
// annual salary for that hourly worker.    
//
// Example: on Input of
// ("JESSE A", "ACOSTA", "POLICE OFFICER", "POLICE", "Salary", 0.0, 93354.0, 0.0)
// Returns
// 93354.0
//
// Example: on Input of
// ("JOHN R", "KEATING", "FOREMAN OF ELECTRICAL MECHANICS", "GENERAL SERVICES",  "Hourly", 40.0, 0.0, 52.35)
// Returns
// 52.0*40.0*52.35 = 108888.0 
//
let calcSalary employee =
    let (fName, lName, Occupation, Department, SalaryType, 
         HoursPerWeek, AnnualSalary, HourlyWage
        ) = employee
    if(SalaryType = "Hourly") then
        double (52.0 * HoursPerWeek * HourlyWage)
    else
        double AnnualSalary


// The function getNumberOfEmployees returns the number of employees in the data set.
let rec getNumberOfEmployees allData =
    List.length allData

// The function getNumberOfSalariedEmployees returns the number of employees who have an annual salary in the data set.
let rec getNumberOfSalariedEmployees allData =
   match allData with
   | [] -> 0
   | hd::tl when (isSalary hd) = true -> 1 + getNumberOfSalariedEmployees tl
   | _::tl -> getNumberOfSalariedEmployees tl

// The function getNumberOfSalariedEmployees returns the number of employees who are paid hourly in the data set.
let rec getNumberOfHourlyEmployees allData =
   match allData with
   | [] -> 0
   | hd::tl when (isHourly hd) -> 1 + getNumberOfHourlyEmployees tl
   | _::tl -> getNumberOfHourlyEmployees tl

// The function findHighestPaidEmployee returns the name and salary
// of the highest paid employee.
// Use the computed salary for hourly employees.
let rec findHighestPaidEmployee allData =
    let result = List.maxBy(fun x -> calcSalary x) allData
    (getName result, calcSalary result)

// The function findHighestPaidEmployee returns the salary
// of the highest paid employee within a specific department.
// Use the computed salary for hourly employees.
let rec findHighestPaidEmployeeInDept allData deptName =
    let highest = List.maxBy( fun x -> calcSalary x ) (List.filter(fun x -> (getDepartment x) = deptName ) allData)
    (calcSalary highest)

// The function getAverageSalary calculates
// the average of the computed salary field.
let rec getAverageSalary allData =
    List.averageBy(fun x -> calcSalary x) allData

// The function getAverageSalary calculates 
// the average of the computed salary field for a specific department.
let rec getAverageSalaryInDept allData deptName =
    List.averageBy(fun x -> calcSalary x) (List.filter(fun x -> (getDepartment x) = deptName ) allData)

// Searches through the data set to generate the list of all unique department names.
let getUniqueDepartmentNames allData =
    allData
    |> List.map getDepartment
    |> List.distinct
    |> List.sort
    //without piping
    //let allDepts = List.map getDepartment allData
    //let uniqueDepts = List.distinct allDepts
    //uniqueDepts


// The function howManyEmployeesInEachDepartment computes the number of employees in every department.  
// This function should return a list of tuples, pairs between the department name and number of employees. 
//[("No Department",0)]
let rec howManyEmployeesInEachDepartment_help m allData deptNames =
    match deptNames with
    | [] -> List.rev m
    | e::rest -> let y = List.filter(fun m -> (getDepartment m) = e) allData
                 let deptLength = List.length y
                 howManyEmployeesInEachDepartment_help ((e, deptLength) :: m) allData rest
    
let rec howManyEmployeesInEachDepartment allData deptNames =
    howManyEmployeesInEachDepartment_help [] allData deptNames
    


 //The function findTotalSalaryByDepartment computes the overall annual salary budget for every department. 
 //The calculated salary should include the average annual salary for hourly employees.
 //This function should return a list of tuples, pairs between the department name and total annual salary. 
 //[("No Department",0.0)]
let findTotalSalaryByDepartment_help dept allData =
    let z = List.filter(fun y -> (getDepartment y) = dept) allData
    (dept, (List.sumBy(fun m -> calcSalary m) z))

let rec findTotalSalaryByDepartment allData deptNames =
    List.map(fun x -> findTotalSalaryByDepartment_help x allData) deptNames 

        
// The function findHighestPaidDeptOverall returns the name and total annual salary
// of the department with the largest overall annual salary paid to employees in that department. 
// The calculated salary should include the average annual salary for hourly employees.
// This function should return a single tuple, containing the department name and total annual salary.
//[("No Department",0.0)]
let findHighestPaidDeptOverall_help dept allData =
    List.sumBy((fun m -> calcSalary m)) (List.filter(fun y-> (getDepartment y) = dept) allData)

let rec findHighestPaidDeptOverall allData deptNames =
    List.max (List.map(fun x -> findHighestPaidDeptOverall_help x allData) deptNames )

// The function withinSalaryRange returns the number of employees whose calculated salary
// is greater than the lower bound and less than or equal to the upper bound.
let rec withinSalaryRange lower upper L =
    match L with 
    | [] -> 0
    | hd::tl when calcSalary(List.head L) > lower && calcSalary(List.head L) <= upper -> 1 + withinSalaryRange lower upper (List.tail L)
    | _:: tl -> withinSalaryRange lower upper (List.tail L)

//
// printOneHistogram
//
// prints one histogram value, the format is
//   label: *****value
// where the # of stars is value / amountPerStar.
//
let printOneHistogram label value amountPerStar =
  // 
  // helper function to print # of stars:
  //
  let rec printstars n = 
    match n with
    | 0 -> ()
    | 1 -> printf "*"
    | _ -> printf "*"
           printstars (n-1)
  //
  // print label, stars, and histogram value:
  //
  printf " %16s: " label    // Enough space for all the departments in the file
  if(value > amountPerStar) then 
    printstars (value / amountPerStar)
    printfn "%A" value
  else
    printfn "%A" value




[<EntryPoint>]
let main argv =
    //
    // input file name, then input employee data and build
    // a list of tuples:
    //
    printf "Enter name of the csv file containing employee data: "

    let filename = System.Console.ReadLine() //"payroll_02.csv" //
    let contents = System.IO.File.ReadLines(filename)
    let data = ParseCSVDatabase contents
    //let data = [List.head data]

    //printfn "This is the data you have loaded."
    //List.iter (printfn "%A") data

    printf "Enter name of the department to be analyzed: "

    let deptname = System.Console.ReadLine()
    let allDepts = getUniqueDepartmentNames data

    let N = getNumberOfEmployees data
    printfn ""
    printfn "# of employees: %A" N
    printfn ""
        

    let deptNs = howManyEmployeesInEachDepartment data allDepts
    let printOutHowMany =  List.find(fun x -> fst x = deptname) deptNs
    printfn "# of employees in %s: %d" deptname (snd printOutHowMany)     // Figure out how to get the number for one department
    printfn ""

    //
    // % of employees salaried:
    //
    let numSalaried = getNumberOfSalariedEmployees data
    let percentSalaried = (double numSalaried) / (double N) * 100.0
    let numHourly = getNumberOfHourlyEmployees data
    let percentHourly = (double numHourly) / (double N) * 100.0

    printfn "%% of employees Salaried: %d (%.2f%%)" numSalaried percentSalaried
    printfn "%% of employees Hourly: %d (%.2f%%)" numHourly percentHourly
    printfn ""

    //
    // average salary:
    //
    let avgSalary = getAverageSalary data
    printfn "Average salary: %.2f" avgSalary
    //printfn ""

    //
    // average salary in department:
    //
    let avgSalary = getAverageSalaryInDept data deptname
    printfn "Average salary in %s: %.2f" deptname avgSalary
    printfn ""
    
    //
    // highest salary:
    //
    let (maxName,maxSalary) = findHighestPaidEmployee data
    printfn "Largest salary: %s paid %.2f annually" maxName maxSalary
    //printfn ""

    //
    // highest salary in department:
    //
    let maxSalary = findHighestPaidEmployeeInDept data deptname
    printfn "Largest salary in %s: %.2f" deptname maxSalary
    printfn ""

    let rec eachDepartment deptName =
        match deptName with
        | [] -> []
        | (a,b)::tl -> printOneHistogram a b 5; eachDepartment tl

    printfn "** Histogram of employees by department (each star represents 5 employees):"    
    eachDepartment deptNs
    printfn ""
    //
    // categorize salaries into 4 groups:
    //   0       < salary <= 60000
    //   60000   < salary <= 90000
    //   90000   < salary <= 120000
    //   120000  < salary <= 10,000,000  // arbitrary upper bound to reuse function
    //
    let count60korless = withinSalaryRange 0.0 60000.0 data
    let percent60korless = (double count60korless) / (double (getNumberOfEmployees data) ) * 100.0

    let count60kto80k = withinSalaryRange 60000.0 80000.0 data
    let percent60kto80k = (double count60kto80k) / (double (getNumberOfEmployees data) ) * 100.0

    let count80kto100k = withinSalaryRange 80000.0 100000.0 data
    let percent80kto100k = (double count80kto100k) / (double (getNumberOfEmployees data) ) * 100.0

    let count100kto120k = withinSalaryRange 100000.0 120000.0 data
    let percent100kto120k = (double count100kto120k) / (double (getNumberOfEmployees data) ) * 100.0

    let countgreater120k = withinSalaryRange 120000.0 10000000.0 data
    let percentgreater120k = (double countgreater120k) / (double (getNumberOfEmployees data) ) * 100.0

    printfn "** Salary Ranges:"
    printfn " 0-60000 : %A (%.2f%%)" count60korless percent60korless
    printfn " 60000-80000 : %A (%.2f%%)" count60kto80k percent60kto80k
    printfn " 80000-100000: %A (%.2f%%)" count80kto100k percent80kto100k
    printfn " 100000-120000: %A (%.2f%%)" count100kto120k percent100kto120k
    printfn " > 120000: %A (%.2f%%)" countgreater120k percentgreater120k
    printfn ""

    printfn "** Histogram of Salary Ranges (each star represents 10 employees):"    
    let salaryGroups = [("<60000", count60korless);("60-80k", count60kto80k);("80-100k", count80kto100k);("100-120k", count100kto120k);(">120000",countgreater120k)]
    
    (printOneHistogram "<60000" count60korless 10)
    (printOneHistogram "60-80k" count60kto80k 10)
    (printOneHistogram "80-100k" count80kto100k 10)
    (printOneHistogram "100-120k" count100kto120k 10)
    (printOneHistogram ">120000" countgreater120k 10)

    0