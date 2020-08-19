




/* THIS CONTAINS EVERYTHING MUST SEPARATE LATER*/





#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

typedef enum {FALSE = 0, TRUE, NO = 0, YES} boolean;
typedef enum {waiting = 0, called= 1} status;

typedef struct Node{
    char* name;
    int numBurgers;
    int numSalads;
    status restaurantStatus;
    struct Node* pNext;
}Node;

//MY CODE BEGINS

//MY CODE ENDS

//GIVEN CODE BEGINS


//GIVEN CODE ENDS

