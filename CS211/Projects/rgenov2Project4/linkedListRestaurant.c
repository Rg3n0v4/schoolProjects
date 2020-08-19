#include "restaurantHeader.h"

void addToList(Node** list, char* customerName, int burgerOrder, int saladOrder, status status, boolean debugMode) 
//adds new Node to the end of the linked list when "a" and "c" commands are pressed
{
    Node *new = (Node *) malloc(sizeof(Node)); //creates room in memory for the customer

    //creates customer
	new->name = customerName;
	new->numBurgers = burgerOrder;
    new->numSalads = saladOrder;

    //checks if customer is waiting or if they are called
	if(status == waiting)
    {
		new->restaurantStatus = waiting;
    }
	else
    {
		new->restaurantStatus = called;
    }

	new->pNext = NULL;
	
	//check if list is empty
	if(*list == NULL)
    {
		*list = new; //set the new Node as the head
		return;
	}
	//list not empty
	else
    {
		Node *temp = *list; //used to traverse
		while(temp->pNext != NULL) //travers until you reach the end of the list
        {
            //for debugMode
            if(debugMode == TRUE)
            {
                char *stat;
                if(temp->restaurantStatus == waiting)
                {
                    stat = "Present";
                }
                else
                {
                    stat = "Not Present";
                }
                printf("Name: %s Burgers: %d Salads: %d Status: %s\n", temp->name, temp->numBurgers, temp->numSalads, stat);
            }
			temp = temp->pNext;
        }
		temp->pNext = new; //set the back to the new Node
		return;
	}
	
	return;
}

boolean doesNameExist(Node** list, char* customerName, boolean debugMode) 
//indicates whether a name already exists in the linked list. Used when "a", "c", "w", "t", "l" commands are given as input
{
    //check if list is empty
    if(*list ==  NULL)
    {
        return FALSE;
    }

    Node* temp = *list;//temporary pointer to make sure the actual list isn't lost/changed when you traverse through
    while(temp->pNext != NULL) //traveres through the whole list 
    {
        //for debugMode
		if(debugMode == TRUE)
        {
			char *stat;
			if(temp->restaurantStatus == waiting)
            {
				stat = "Present";
            }
			else
            {
				stat = "Not Present";
            }
			printf("Name: %s Burgers: %d Salads: %d Status: %s\n", temp->name, temp->numBurgers, temp->numSalads, stat);
		}
        if(strcmp(temp->name,customerName) == 0) //if the name is found then return 1 (or true)
        {
            return TRUE;
        }
        temp = temp->pNext;
    }

    return FALSE; //return false if the name doesn't exist

}

boolean updateStatus(Node** list, char* customerName, boolean debugMode) 
//in-restaurant status when a call-ahead order arrives at the restaurant. Used when "q" command is given as input
{
    //check if list is empty
    if(*list ==  NULL)
    {
        return FALSE;
    }

    Node* temp = *list;
    //traverses through the whole list
    while(temp != NULL)
    {
        //for debugMode
		if(debugMode == TRUE)
        {
			char *stat;
			if(temp->restaurantStatus == waiting)
            {
				stat = "Present";
            }
			else
            {
				stat = "Not Present";
            }
			printf("Name: %s Burgers: %d Salads: %d Status: %s\n", temp->name, temp->numBurgers, temp->numSalads, stat);
		}
        //for just the program running normally

		if(strcmp(temp->name, customerName) == 0)
        {
			if(temp->restaurantStatus == called)
            {
                temp->restaurantStatus = waiting;
				return TRUE;
            }
			return FALSE;
		}
		temp = temp->pNext;
	}
	return FALSE;

}

char* retrieveAndRemove (Node** list, int burgers, int salads, boolean debugMode)
// This group is to be removed from the linked list.  Used when the "r" command is givenas input.
{
    Node* temp = *list;
    char* nameToReturn = NULL;

    if(temp == NULL) //if there's nothing in the list
    {
        return nameToReturn;
    }
    if(temp->pNext == NULL) //if it is a singleton
    {
        if(temp->numBurgers <= burgers && temp->numSalads <= salads && temp->restaurantStatus == waiting)
        {
            nameToReturn = (char*) malloc(sizeof(char) * 30);
            strcpy(nameToReturn, temp->name);
            *list = NULL;
            free(temp);
            return nameToReturn;
        }
        else
        {
            return nameToReturn;
        }
    }

    if(temp->numBurgers <= burgers && temp->numSalads <= salads && temp->restaurantStatus == waiting) //if the person infront
    {
        nameToReturn = (char*) malloc(sizeof(char) * 30);
        strcpy(nameToReturn, temp->name);
        *list = NULL;
        free(temp);
        return nameToReturn;
    }

    while(temp->pNext != NULL)//traverse the list
    {
        //for debugMode
		if(debugMode == TRUE)
        {
			char *stat;
			if(temp->restaurantStatus == waiting)
            {
				stat = "Present";
            }
			else
            {
				stat = "Not Present";
            }
			printf("Name: %s Burgers: %d Salads: %d Status: %s\n", temp->name, temp->numBurgers, temp->numSalads, stat);
		}
        if(temp->pNext->numBurgers <= burgers && temp->pNext->numSalads <= salads && 
            temp->pNext->restaurantStatus == waiting) //if the person is found
        {
            nameToReturn = (char*) malloc(sizeof(char) * 30);
            strcpy(nameToReturn, temp->pNext->name);
            Node* tempNext = temp->pNext;
            Node* tempNextx2 = tempNext->pNext;

            temp->pNext = tempNextx2;

            free(tempNext);
            return nameToReturn;
        }
		temp = temp->pNext;
	}
	
	printf("No valid group size for table size\n");
	return nameToReturn;
}
int countOrdersAhead (Node** list, char* nameOfCustomer, boolean debugMode)
//This operation is to return the number of orders waiting ahead of an orderwith a specific name. 
// This is to be used when the "l" command is given as input.
{
    int numAhead = 0;
    if(*list == NULL) //if the list is empty
    {
        return -2;
    }

    Node* temp = *list;
    while(temp != NULL)
    {
        if(debugMode == TRUE)
        {
			char *stat;
			if(temp->restaurantStatus == waiting)
            {
				stat = "Present";
            }
			else
            {
				stat = "Not Present";
            }
			printf("Name: %s Burgers: %d Salads: %d Status: %s\n", temp->name, temp->numBurgers, temp->numSalads, stat);
		}
        if(strcmp(temp->name, nameOfCustomer) == 0)
        {
            return numAhead;
        }
        numAhead++;
        temp = temp->pNext;
    }
    return -1;
}

void displayWaitingTime(Node** list, char* nameOfCustomer, boolean debugMode)
//This operation is to return the estimated waiting time for the specific name. 
{
    int totalBurgers = 0;
    int totalSalads = 0;
    if(*list == NULL)//if there's nothing in the list
    {
        return;
    }
    Node* temp = *list;
    while(temp != NULL && strcmp(temp->name, nameOfCustomer) != 0)
    //loops through to find the name of the customer and until it reaches the end of the list
    {
        //for debugMode
        if(debugMode == TRUE)
        {
			char *stat;
			if(temp->restaurantStatus == waiting)
            {
				stat = "Present";
            }
			else
            {
				stat = "Not Present";
            }
			printf("Name: %s Burgers: %d Salads: %d Status: %s\n", temp->name, temp->numBurgers, temp->numSalads, stat);
		}
        totalBurgers += temp->numBurgers;
        totalSalads += temp->numSalads;
        temp = temp->pNext;
    }
    
    if(temp == NULL)
    {
        printf("Name doesn't exist");
    }
    else
    {
        printf("Total wait time: %d minutes\n",  10*(totalBurgers + temp->numBurgers) + 5*(totalSalads + temp->numSalads));
    }
}

void displayOrdersAhead(Node** list, char* nameOfCustomer)
//traverses through the whole list until a specific name is reached
{
    Node* temp = *list;
    int counter = 0, i = 1;
	char *stat;
	
	if(temp == NULL)//if the list is empty
    {
		printf("List is empty\n");
		return;
	}
	if(strcmp(temp->name, nameOfCustomer) == 0)//if the person is already infront
    {
		printf("First on the list: No groups ahead\n");
		return;
	}
	while(strcmp(temp->name, nameOfCustomer) != 0) 
    //keep looping through until the name of the customer is found
    //while it's looping it prints out all the groups infront it
    {
		printf("Name: %d Burgers: %d Salads: %d Status: %s\n", temp->name, temp->numBurgers, temp->numSalads, temp->restaurantStatus);

		temp = temp->pNext;
	}
	return;
}

void displayListInformation(Node** list)
//traverses through the entire list from beginning to end. Displays Node's name, order details and in restaurant status
{
    if(list == NULL)
    {
        return;
    }

    Node* temp = *list;
    while(temp != NULL)
    {
        printf("Name: %s Burgers: %d Salads: %d Status: %d\n", temp->name, temp->numBurgers, temp->numSalads, temp->restaurantStatus);
        temp = temp->pNext;
    }
}
