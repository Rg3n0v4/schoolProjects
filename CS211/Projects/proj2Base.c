#include <stdio.h>
#include <stdlib.h>

#define TRUE 1
#define FALSE 0

int DebugMode;

typedef struct Node{
	int size;		//current size of the dynamic array
	int top;		//the top of the stack
	char *arrPtr;	//pointer to the dynamic array that holds the stack
}Node;

void init(Node* data);
int is_empty(Node* data); //checks if the stack is empty
void push(Node* data, int value, int debugMode); //for adding on an element to the stack
int pop(Node* data, int debugMode); //for popping an element off the stack
char top(Node* data); //returns the top of the stack
void clear(Node* data); //resets the stack 

int main (int argc, char** argv)
{
  
 char input[301];

 Node stack;
 init(&stack);

  DebugMode = FALSE;
  int i;
  for(i = 0; i< argc; i++)
  {
    if(strcmp(argv[i], "-d")==0)
    {
      DebugMode = TRUE;
    }
  }

 /* set up an infinite loop */
 while (1)
 {
   char firstPart, secondPart;
   int j, completePair = 1;

   /* get line of input from standard input */
   printf ("\nEnter input to check or q to quit\n");
   fgets(input, 300, stdin);
 
   /* remove the newline character from the input */
   int i = 0;
   while (input[i] != '\n' && input[i] != '\0')
   {
      i++;
   }//end of while(input[i] != '\n' ...)
   input[i] = '\0';

  /* check if user enter q or Q to quit program */
   if ( (strcmp (input, "q") == 0) || (strcmp (input, "Q") == 0) )
   {
     break;
   }

   printf ("%s\n", input);

   /* run the algorithm to determine is input is completePair */
   //if there is an open parenthesis/bracket/brace then added it onto the stack
   i = 0;
   int xit = 0;
   while(input[i] != '\0')
   {
    if(input[i] == '(' || 
       input[i] == '{' ||
       input[i] == '[' ||
       input[i] == '<')
    {
      push(&stack, input[i], DebugMode);
    }
    if(input[i] == ')' || 
       input[i] == '}' || 
       input[i] == ']' || 
       input[i] == '>')
    {
      //change firstPart based on user input
      if(input[i] == ')')
      {
        firstPart = '(';
      }
      else if(input[i] == '}')
      {
        firstPart = '{';
      }
      else if(input[i] == ']')
      {
        firstPart = '[';
      }
      else if(input[i] == '>')
      {
        firstPart = '<';
      }
      
      //if firstPart is at top of stack, pop the stack and continue
      if(top(&stack) == firstPart)
      {
        //printf("POP\n");
        pop(&stack, DebugMode);
        //displayStack(&stack);
        i++;
        continue;
      }
      //if firstPart is not at the top of stack, NOT completePair
      else
      {
          if(is_empty(&stack) == 0)
          {
            if(top(&stack) == '(')
            {
              secondPart = ')';
            }
            else if(top(&stack) == '{')
            {
              secondPart = '}';
            }
            else if(top(&stack) == '[')
            {
              secondPart = ']';
            }
            else if(top(&stack) == '<')
            {
              secondPart = '>';
            }
            
            for(j = 0; j < i; j++)
            {
              printf(" ");
            }
            printf("^ expecting %c\n\n", secondPart);
            completePair = 0;
            xit = 1;
            break;
         }
          else if(is_empty(&stack) == 1)
          {
            for(j = 0; j < i; j++)
            {
              printf(" ");
            }
            printf("^ missing %c\n\n", firstPart);
            completePair = 0;
            xit = 1;
            break;
          }
      } 
    }//if(input[i] == ')' ...)
    i++;
  }//while([i] !=\0)
  if(is_empty(&stack) == 0 && xit == 0)
  {
    char topOfStack;
    if(top(&stack) == '(')
    {
      topOfStack = ')';
    }
    else if(top(&stack) == '[')
    {
      topOfStack = ']';
    }
    else if(top(&stack) == '{')
    {
      topOfStack = '}';
    }
    else if(top(&stack) == '<')
    {
      topOfStack = '>';
    }

    for(j = 0; j < i; j++)
    {
      printf(" ");
    }

    printf("^ missing %c", topOfStack);
  }
 }

 printf ("\nGoodbye\n");
 return 0;
}

void init(Node* data) //initalizes stack values to all be 0
{
  data->size = 2;
  data->top =  0;
  char *arr = (char *) malloc(sizeof(char)* (data->size));
  data->arrPtr = arr;
}
int is_empty(Node* data) //checks if the stack is empty
{
  if(data->top == 0)
  {
    return 1;
  }
  return 0;
}
void push(Node* data, int value, int DebugMode) //for adding on an element to the stack
{
  if(DebugMode == TRUE)
  {
    printf("\nDebug mode: Push \n");
  }
  //checks if the stack is full
  //if it is allocate more memory
  if(data->top == data->size)
  {
		int i;
		//grow the array
		char *temp;
    temp = data->arrPtr; //makes sure that the old array isn't lost
    data->arrPtr = (char *) malloc((sizeof(char) * (data->size)) + 2);  //allocate the larger array space

      for(i = 0; i < data->size; i++) //loop to copy values to larger array space
      {
        data->arrPtr[i] = temp[i];
      }
				
			if(DebugMode == TRUE)
      {
				printf("\nNumber of values copied: %d", i);
      }
      
      free(temp);	//deallocates memory
      //data->arrPtr = temp; 	//set "permanent" array identifier to point to larger space
      

			if(DebugMode == TRUE)
      {
				printf("\nOld Size: %d", data->size);
      }
            data->size += 2; //update size to reflect larger array space
			if(DebugMode == TRUE)
      {
				printf("\nNew Size: %d", data->size);
      }
      
	}
  //add value to the stack array
	data->arrPtr[data->top] = value;
	if(DebugMode == TRUE)
  {
		printf("\nPushed to data: %c", data->arrPtr[data->top]);
  }
	
	//increment top-of-stack value
	data->top++;
	if(DebugMode == TRUE)
		printf("\nNew top value: %d", data->top);

}
int pop(Node* data, int DebugMode) //for popping an element off the stack
{
    if(DebugMode == TRUE){
      printf("\nDEBUG MODE: POP\n");
      printf("\nPopped from data: %c", data->arrPtr[data->top]);
    }
    
    //decrement top-of-stack value
    data->top--;
    if(DebugMode == TRUE)
      printf("\nNew top value: %d", data->top);
    
    //"remove" top stack value
    data->arrPtr[data->top] = 0;
}
char top(Node* data) //returns the top of the stack
{
  return data->arrPtr[data->top - 1];
}
void clear(Node* data) //resets the stack 
{
  data->size = 2;
  data->top =  0;
  char *arr = (char *) malloc(sizeof(char)* (data->size));
  data->arrPtr = arr;
}
