#include <iostream>

struct Node 
{
    int val;
    Node *pNext;
};

int num_distinct(Node *lst)
{
    bool duplicate;
    int distinct = 0;
    while(lst->pNext != nullptr)
    {
        duplicate = false;
        while(lst->pNext != NULL)
        {
            if(lst->val == lst->pNext->val)
            {
                duplicate = true;
            }
        }
        if(duplicate == false)
        {
            distinct++;
        }
        lst = lst->pNext;
    }
    return distinct;

}

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

void push(Node** head_ref, int new_data) 
{ 
    /* allocate node */
    Node* new_node = new Node;
  
    /* put in the data  */
    new_node->val  = new_data; 
  
    /* link the old list off the new node */
    new_node->pNext = (*head_ref); 
  
    /* move the head to point to the new node */
    (*head_ref)    = new_node; 
} 

int main()
{
    std::cout << "HELLO WORLD";
    const int arraySize = 5;
    int array[arraySize] = {1,2,3,4,5};
    int dis;
    int list_dis;
    Node *head = new Node;
    head->val = 1;
    head->pNext = nullptr;
    push(&head, 2);
    push(&head, 3);
    push(&head, 2);
    push(&head, 1);
    

    dis = num_distinct(array, arraySize);
    list_dis = num_distinct(head);

    std::cout << "Output for array: " << dis << std::endl;
    std::cout << "Output for linked list: " << list_dis << std::endl;

    int pause;
    std::cin >> pause;
    return 0;
}