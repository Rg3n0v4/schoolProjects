#include "queue.cpp"
#include <iostream>
using namespace std;

int main()
{
    queue q;
    q.push(10);
    q.push(20);
    q.push(30);
    
    for (int i : q)
    {
        std::cout << i << std::endl;
    }
    return 0;
}


//Wayne's test cases
// int main()
// {
//     //Assuming you constructed default correctly
//     queue q(10);
    
//     // //Testing empty() function
//     // std::cout << "Testing empty() function." << std::endl;
//     // bool b = q.empty();
//     // if(!b){
//     //     std::cout << "empty function wrong" << std::endl;
//     // }
    
//     // std::cout << "Testing pop when no elements are present. Error message should occur." << std::endl;
//     // q.pop();
    
//     // std::cout << "Testing [] when no element are present. Error message should occur." << std::endl;
//     // q[0];
    
//     // std::cout << "Testing when popping last element out and trying to pop again after. Error Message should occur" << std::endl;
//     // q.push(0);
//     // q.pop();
//     // q.pop();
    
//     //Setting up queue
//     q.push(0);
//     q.push(1);
//     q.push(2);
//     q.push(3);
//     q.push(4);
//     q.push(5);
//     q.push(6);
//     q.push(7);
//     q.push(8);
//     q.push(9);
    
//     // std::cout << "Testing negative number []. Error should occur." << std:: endl;
//     // q[-1];
    
//     // std::cout << "Testing push function when queue is full and begin = 0. Error message should occur." << std::endl;
//     // q.push(20);
    
//     // std::cout << "Testing bracket notation out of range. Error message should occur." << std::endl;
//     // q[10];
    
    
    
//     //Normal
//     // std::cout << "Testing when begin starts at 0 and end ends at 9. Finding arr[0] should give 0. Output: "
//     //     << q[0] << std::endl;
//     // std::cout << "Testing when begin starts at 0 and end ends at 9. Finding arr[5] should give 6. Output: "
//     //     << q[5] << std::endl;
//     // std::cout << "Testing when begin starts at 0 and end ends at 9. Finding arr[9] should give 9. Output: "
//     //     << q[9] << std::endl;
    
//     q.pop();
//     q.pop();
//     q.pop();
//     q.pop();
//     q.pop();
//     q.push(0);
//     q.push(1);
//     q.push(2);
//     q.push(3);
//     q.push(4);
    
//     // std::cout << "Testing push function when queue is full and begin != 0. Error message should occur." << std::endl;
//     // q.push(20);
    
//     //Testing when begin doesn't start at 0
//     std::cout << "Testing when begin starts at 5 and end ends at 4. Finding arr[3] should give 8. Output: "
//         << q[3] << std::endl;
//     // std::cout << "Testing when begin starts at 5 and end ends at 4. Finding arr[7] should give 2. Output: "
//     // << q[7] << std::endl;
    
//     // std::cout << "Testing popping a value and printing out correct returned value. Output should be 5. Output: " << q.pop() << std::endl;
//     return 0;
// }