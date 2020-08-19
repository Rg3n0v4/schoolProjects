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

/*all functions below are in linkedListRestaurant.c*/
void addToList(Node** list, char* customerName, int burgerOrder, int saladOrder, status status, boolean debugMode); 
//adds new node to the end of the linked list when "a" and "c" commands are pressed

boolean doesNameExist(Node** list, char* customerName, boolean debugMode);
//indicates whether a name already exists in the linked list. Used when "a", "c", "w", "t", "l" commands are given as input

boolean updateStatus(Node** list, char* customerName, boolean debugMode);
//in-restaurant status when a call-ahead order arrives at the restaurant. Used when "q" command is given as input

char* retrieveAndRemove (Node** list, int burgers, int salads, boolean debugMode);
//find the first in-restaurant orderthat matches the orderprepared for pick upat the counter. This group is to be removed from the linked list.  This is to be used when the rcommand is givenas input.

int countOrdersAhead (Node** list, char* nameOfCustomer, boolean debugMode);
//This operation is to return the number of orders waiting ahead of anorderwith a specific name.  This is to be used when the lcommand is given as input.

void displayWaitingTime(Node** list, char* nameOfCustomer, boolean debugMode);
//This operation is to return the estimated waiting time for the specific name. 

void displayOrdersAhead(Node** list, char* nameOfCustomer);
//traverses through the whole list until a specific name is reached

void displayListInformation(Node** list);
//traverses through the entire list from beginning to end. Displays node's name, order details and in restaurant status

/*all functions below are in linkedListInteraction.c*/
void doAdd (Node** lst, boolean debugMode);
void doCallAhead (Node** lst, boolean debugMode);
void doWaiting (Node** lst, boolean debugMode);
void doRetrieve (Node** list, boolean debugMode);
void doList (Node** list, boolean debugMode);
void doDisplay (Node** list);
void doEstimateTime(Node** list, boolean debugMode);

/*all functions below are in userRestaurantDriver.c*/
char *getName();
void clearToEoln();
void printCommands();
int getNextNWSChar ();
