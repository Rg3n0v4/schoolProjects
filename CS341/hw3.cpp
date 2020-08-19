#include <iostream>
using namespace std;

class Vector {
public:
    Vector(int s) :elem{new double[s]}, sz{s} { } // constr uct a Vector

    double& operator[](int i)
    {
        if (i<0 || size()<=i)
            throw out_of_range("Vector::operator[]");
        return elem[i];
    }
    
    int size() { return sz; }
private:
    double* elem; // pointer to the elements
    int sz; // the number of elements
};

//for question 1
/*
    The purpose of the parameter i in the double& Vector::operator[]() method is so that the user can put in a certain index
    that they want to access in the elem[] array. Inside the method itself, it checks if the parameter i is not less than 0
    because there's no way to access a negative or decimal array index. The method also checks the parameter i is greater than
    or equal to the size of the array because if the size is bigger than the array, there's no way to access an element that's 
    not in the scope of the array. If the parameter i is the size of the array, assuming the user wants to access the last element
    in the array, then the method is not able to access that element because when accessing the last element in the array, it is
    always the size of the array - 1. 
*/

int main()
{
    Vector x(4);

    cout << x.operator[](2) << endl; //this would print out 0 because at index 2 of the vector x there was nothing initialized or set to any value

    return 0;
}

//for question 2
/*
    What double& does is that it is a reference to a double which means it can change the value of the passed parameter. What the & does is: 
    it dereferences the double variable which means it gets the address the double is in memory so that it can access the variable directly. 
*/

int main2()
{
    Vector y(4);

    y.operator[](2) = 3; //this sets the number 3 to index 2 of Vector y because of the double&
                        //we are able to change the value of index 2 of the Vector y
    cout << y.operator[](2) << endl; //this would print out 3 because at index 2 of the vector y it's value is 3

    return 0;
}

