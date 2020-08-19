#include <iostream>

struct Node 
{
    int val;
    Node *pNext;
};

// int num_distinct(Node *lst)
// {

// }

int num_distinct(int a[], int n)
{
    int i, j, ndistinct;
    bool is_dup;
    ndistinct = 0;

    for(int i=0; i<n; i++)
    {
        is_dup=false;
        for(j-0; j<i; i++)
        {
            if(a[j] == a[i])
            {
                is_dup=true;
            }
        }
        if(!is_dup)
        {
            ndistinct++;
        }
    }
    return ndistinct;
}

int main()
{
    const int arraySize = 5;
    int array[arraySize] = {1,2,3,4,5};
    int dis;

    dis = num_distinct(array, arraySize);

    std::cout << dis << std::endl;
}