#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <string>

struct MyNode2
{
  std::string fname;
  MyNode2* next;

};

class LinkedList 
{
  private:
  
  MyNode2* head;

  public:

  void insert(std::string fname)
  {
    MyNode2* newNode = new MyNode2;
    newNode->next = head;
    newNode->fname = fname;
    head = newNode;
  } 

  bool search(std::string x)  
{  
    MyNode2* current = head;  
    while (current != NULL)  
    {  
        if (current->fname == x)  
            return true;  
        current = current->next;  
    }  
    return false;  
} 
};

class MyNode
{
  private:
    int islandNum; //the island number that a certain island can access
    MyNode* next; //points to the next island
  public:
    //constructor
    MyNode()
    {
      islandNum = -1; //just to initialize it
      next = NULL;
    }
    
    //returns island number
    int getIslandNum()
    {
      return islandNum;
    }
    //sets island number
    void setIslandNum(int num)
    {
      islandNum = num;
    }

    //returns the next node
    MyNode* getNext()
    {
      return next;
    }
    void setNext(MyNode* nextNode)
    {
      next = nextNode;
    }
};

class MyList
{
  private:
    MyNode* head;
  public:
    //constructor
    MyList()
    {
      head = NULL;
    }

    //inserting values onto the list
    void insertValue(int addIsland)
    {
      //if the list is empty
      if(head == NULL)
      {
        head = new MyNode();
        head->setIslandNum(addIsland);
        head->setNext(NULL);
      }
      //if the list isn't empty
      else if(head->getNext() == NULL)//only one thing on the list
      {
        MyNode* newIsland = new MyNode();
        newIsland->setIslandNum(addIsland);
        newIsland->setNext(NULL);
        head->setNext(newIsland);
      }
      else
      {
        MyNode* temp = head;
        while(temp->getNext() != NULL)//go to the end of the list
        {
          temp = temp->getNext();
        }
        //adds on a new node
        MyNode* newIsland = new MyNode();
        newIsland->setIslandNum(addIsland);
        newIsland->setNext(NULL);
        temp->setNext(newIsland);
      }

    }

    //deletes a value on the list
    void deleteValue(int islandNum)
    {
      //if the first value on the list needs to be deleted change the head of 
      //the list to be the second node on the list 
      if(head->getIslandNum() == islandNum)
      {
        MyNode* temp = head->getNext();//so that the rest of the list isn't lost when head is deleted
        head->setNext(head->getNext());
        free(head);
        head = temp;
        return;
      }
      
      //if the last value on the list needs to be deleted, change the second to last node to null
      MyNode* lastNode = head;
      while(lastNode->getNext()->getNext() != NULL)
      {
        lastNode = lastNode->getNext();
      }
      if(lastNode->getNext()->getIslandNum() == islandNum)
      {
        MyNode* deleteNode = lastNode->getNext();
        lastNode->setNext(NULL);
        free(deleteNode);
        return;
      }

      //if the value that needs to be deleted is in the middle of the list
      MyNode* currentNode = head;
      while(currentNode->getNext()->getIslandNum() != islandNum && currentNode->getNext() != NULL)
      {
        currentNode = currentNode->getNext();
      }
      if(currentNode->getNext()->getIslandNum() == islandNum)
      {
        MyNode* prev = currentNode; //set to the node before the deleted node
        MyNode* temp = currentNode->getNext()->getNext(); //so that the half of the list that is after the deleted node isn't lost
        currentNode = currentNode->getNext(); //set to the deleted node
        currentNode->setNext(NULL); //makes the deleted node go to null
        prev->setNext(temp);//connects the node before the deleted node to the node after the deleted node
        free(currentNode);
      }

    }

    //gets the current number of islands are in the list
    int getNumberOfCurrentValues()
    {
      int counter = 0;
      MyNode* temp = head;
      
      while(temp != NULL)
      {
        counter++;
        temp = temp->getNext();
      }

      return counter;
    }

    //gets the nth value 
    MyNode* getNthValue(int nthValue)
    {
      int position = 0;
      MyNode* hd = head;
      while (hd != NULL)
      {
        if(position == nthValue)
        {
          return hd;
        }
        position++;
        hd = hd->getNext();
      }
    }

};

class Island
{
  private:
    int islandNum; //REMEMBER THAT THIS IS AN INDEX NOT AN ACTUAL ISLAND NUM
    bool isVisited;
    MyList list; //no need to make a setter because it's already made in the MyList object
  public:
    //constuctor
    Island();
    //getter and setter for isVisited variable
    bool getIsVisited();

    void setIsVisited(bool visit);

    //getter and setter for islandNum
    int getIslandNum();
    
    void setIslandNum(int num);

    MyList* getList();

    void printList();

}; 

class ArchipelagoExpedition
{
 private:
   Island* arr; //dynamic array of islands 
   int numIslands;
   LinkedList L;
  
 public:
  
 // Use a constructor to initialize the Data Members for your expedition
 ArchipelagoExpedition()
 {
  numIslands = 10;
  arr = new Island[numIslands];
  for(int i = 0; i < numIslands; i++)
  {
    arr[i].setIslandNum(i); //this sets up the island number (index number NOT ACTUAL ISLAND NUM)
  }
 }
  
  
 // The main loop for reading in input
 void processCommandLoop (FILE* inFile)
 {
  char  buffer[300];
  char* input;

  input = fgets ( buffer, 300, inFile );   // get a line of input
    
  // loop until all lines are read from the input
  while (input != NULL)
  {
    // process each line of input using the strtok functions 
    char* command;
    command = strtok (input , " \n\t");

    printf ("*%s*\n", command);
    
    if ( command == NULL )
      printf ("Blank Line\n");
 
    else if ( strcmp (command, "q") == 0) 
      exit(1);
     
    else if ( strcmp (command, "?") == 0) 
      showCommands();
     
    else if ( strcmp (command, "t") == 0) 
      doTravel();
     
    else if ( strcmp (command, "r") == 0) 
      doResize();

    else if ( strcmp (command, "i") == 0) 
      doInsert();

    else if ( strcmp (command, "d") == 0) 
      doDelete();

    else if ( strcmp (command, "l") == 0) 
      doList();

    else if ( strcmp (command, "f") == 0) 
      doFile();

    else if ( strcmp (command, "#") == 0) 
      ;
     
    else
      printf ("Command is not known: %s\n", command);
     
    input = fgets ( buffer, 300, inFile );   // get the next line of input

  }
 }
 
 void showCommands()
 {
   printf ("The commands for this project are:\n");
   printf ("  q \n");
   printf ("  ? \n");
   printf ("  # \n");
   printf ("  t <int1> <int2> \n");
   printf ("  r <int> \n");
   printf ("  i <int1> <int2> \n");
   printf ("  d <int1> <int2> \n");
   printf ("  l \n");
   printf ("  f <filename> \n");
 }
 
 void doTravel()
 {
   int val1 = 0;
   int val2 = 0;

   // get an integer value from the input
   char* next = strtok (NULL, " \n\t");
   if ( next == NULL )
   {
     printf ("Integer value expected\n");
     return;
   }
   val1 = atoi ( next );
   if ( val1 == 0 && strcmp (next, "0") != 0)
   {
     printf ("Integer value expected\n");
     return;
   }
   if(val1 > numIslands) 
   //if the island that they put in can't be reached due to resize
   {
     printf("Integer value can't be reached");
     return;
   }
   
   // get another integer value from the input
   next = strtok (NULL, " \n\t");
   if ( next == NULL )
   {
     printf ("Integer value expected\n");
     return;
   }
   val2 = atoi ( next );
   if ( val2 == 0 && strcmp (next, "0") != 0)
   {
     printf ("Integer value expected\n");
     return;
   }
   
   if(val2 > numIslands) 
   //if the island that they put in can't be reached due to resize
   {
     printf("Integer value can't be reached");
     return;
   }
   
   printf ("Performing the Travel Command from %d to %d\n",
            val1, val2);
   depthFirstSeachHelper(val1, val2);
 }
 
 void doResize()
 {
   char* s = strtok (NULL, " \n\t");
  int val1 = atoi(s);
   
  printf ("Performing the Resize Command with %d\n", val1 );
 
  Island* temp = arr;
  numIslands = val1;
  arr = new Island[numIslands];

  for(int i = 0; i < numIslands; i++)
  {
    arr[i].setIslandNum(i); //this sets up the island number (index number NOT ACTUAL ISLAND NUM)
  }
 }
 
 //insert the edge to indicate a ferry ride from island<int1>to island<int2>
 void doInsert()
 {
   //take in the input island and the connecting island on add onto the list
   char* s = strtok (NULL, " \n\t");
   int islandNum = atoi(s);
   s = strtok (NULL, " \n\t");
   int island2Num = atoi(s);
   if(islandNum > numIslands || island2Num > numIslands) // if any of the inputs goes over the number of islands that there can be
   {
     printf("Island inserted is bigger than number of current islands in place. Must resize islands in order to add.\n");
     return;
   }
   else
   {
    arr[islandNum-1].getList()->insertValue(island2Num);
   }
 }
 
 //removes the ferry ride from Island<int1> to Island<int2>
 void doDelete()
 {
   char* s = strtok (NULL, " \n\t");
   int islandNum = atoi(s);
   s = strtok (NULL, " \n\t");
   int island2Num = atoi(s);
   arr[islandNum-1].getList()->deleteValue(island2Num);
 }
 
 //lists all the items 
 void doList()
 {
   for(int i = 0; i < numIslands; i++)
   {
     arr[i].printList();
   }
 }
 
 void doFile()
 {
   // get a filename from the input
   char* fname = strtok (NULL, " \n\t");
   if ( fname == NULL )
   {
     printf ("Filename expected\n");
     return;
   }
   
   printf ("Performing the File command with file: %s\n", fname);
   
   // next steps:  (if any step fails: print an error message and return ) 
   //  1. verify the file name is not currently in use
   //  2. open the file using fopen creating a new instance of FILE*
   //  3. recursively call processCommandLoop() with this new instance of FILE* as the parameter
   //  4. close the file when processCommandLoop() returns

   //char* otherName = strtok(NULL, " \n\t");
   if(L.search(fname))
   return;
   FILE* file = fopen(fname, "r");
   L.insert(fname);
   //if(fname == any other ones) return;
   processCommandLoop(file);
 }

  //x is the island you start at 
  //y is the island you are looking for 
 void depthFirstSeachHelper(int x, int y)
 {
   for(int i = 0; i < numIslands; i++)
   {
     arr[i].setIsVisited(false);
   }
   if(dfs(x,y) == true)
   {
     printf("You can get from island %d to island %d in one or more ferry rides\n", x, y);
   }
   else 
   {
     printf("You can NOT get from island %d to island %d in one or more ferry rides\n", x, y);
   }
 }
 
 //x is the island you start at
 //y is the island you are looking for
  bool dfs(int x, int y)
  {
    //wayne's way

    for(int i = 0; i < arr[x-1].getList()->getNumberOfCurrentValues(); i++)
    {
      int island = arr[x-1].getList()->getNthValue(i)->getIslandNum();
      if(island == y)
      {
        return true;
      }

      if(arr[island-1].getIsVisited()== false)
      {
        arr[island-1].setIsVisited(true);

        if(dfs(island, y) == true)
        {
          return true;
        }
      }
    }
    return false;
  }
};
