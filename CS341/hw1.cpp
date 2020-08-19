#define CATCH_CONFIG_MAIN
#include "catch.hpp"

//from slides in class (Lecture 1 slide 6)
class Student
{
    string Name;
    int Midterm;
    int Final;

    Student(string name, int midterm, int final)
        : Name(name), Midterm(midterm), Final(final)
    { }

    double ExamAvg()
    {
        return (this->Midterm + this->Final) / 2.0;
    }
}

SCENARIO("Testing ExamAvg()", "[ExamAvg]")
{
    GIVEN("midterm and final scores") 
    {
        double avg; //for storing the average between the midterm and final scores
        double value; //for storing the difference between the midterm and final scores

        WHEN("midterm is 0 and final is 0") //case 1
        {
            avg = ExamAvg(0,0);
            value = 0 - avg;

            THEN("the average needs to be in between -0.00001 and 0.00001")
            {
                REQUIRE(value >= -0.00001 && value <= 0.00001);
            }
        }
        WHEN("midterm is 1 and final is 1") //case 2
        {
            avg = ExamAvg(1,1);
            value = 1 - avg;

            THEN("the average needs to be in between 0.99999 and 1.00001")
            {
                REQUIRE(value >= 0.99999 && value <= 1.00001);
            }
        }
        WHEN("midterm is 10 and final is 10") //case 3
        {
            avg = ExamAvg(10,10);
            value = 10 - avg;

            THEN("the average needs to be in between 9.99999 and 10.00001")
            {
                REQUIRE(value >= 9.99999 && value <= 10.00001);
            }
        }
        WHEN("midterm is 20 and final is 20") //case 4
        {
            avg = ExamAvg(20,20);
            value = 20 - avg;

            THEN("the average needs to be in between 19.99999 and 20.00001")
            {
                REQUIRE(value >= 19.99999 && value <= 20.00001);
            }
        }
        WHEN("midterm is 30 and final is 30") //case 5
        {
            avg = ExamAvg(30,30);
            value = 30 - avg;

            THEN("the average needs to be in between 29.99999 and 30.00001")
            {
                REQUIRE(value >= 29.99999 && value <= 30.00001);
            }
        }
    }
}

SCENARIO("testing FinalAvg()", "[FinalAvg]")
{
    GIVEN("a vector with student's final exam scores")
    {
        double avg; //average between the final exam scores
        vector<Student> student; //turning the Student object into a vector called student
        double value; //for getting the difference

        WHEN("the vector doesn't contain anything") //vector is empty
        {
            avg = FinalAvg(student);

            THEN("the vector returns 0.0 as the average") //this is because the average of 0 is 0
            {
                REQUIRE(value >= -0.00001 && value <= 0.00001);
            }
        }
        WHEN("the vector size is 1 with FinalAvg of 100") //vector contains 1 Student
        {
            Student Hong( 100, 100);
            student.push_back(Hong);
            value =  FinalAvg(student);

            THEN("the final average should be between 99.99990 and 100.00001")
            {
                REQUIRE(value >= 99.99990 && value <= 100.00001);
            }
        }
        WHEN("the vector size is 4 with FinalAvg of 101.5") //vector contains 1 Student
        {
            Student Tejas( 100, 98); // average = 99
            student.push_back(Tejas);
            Student Venky( 88, 82); // average = 85
            student.push_back(Venky);
            Student Kaiser( 60, 62); // average = 61
            student.push_back(Kaiser);
            value =  FinalAvg(student);

            THEN("the final average should be between 101.49999 and 101.50001")
            {
                REQUIRE(value >= 101.49999 && value <= 101.50001);
            }
        }
    }
}