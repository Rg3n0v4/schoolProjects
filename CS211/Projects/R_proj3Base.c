#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct mazeStruct
{
 char** arr;  /* allows for a maze of size 30x30 plus outer walls */
 int width, length;
 int xstart, ystart;
 int xend, yend;
 int **visited;
} maze;

typedef struct Node
{
  int xpos, ypos; 	
  struct Node *next;
}node;

int const TRUE = 1;
int const FALSE = 0;
int DebugMode = 0;

//intializing all functions
//void readFile(FILE *src,int *width, int *length, int *xstart, int *ystart, int *xend, int*yend);
//void validMove(int width, int length, int xstart, int ystart, int xend, int yend);
//void printMazeInfo(int width, int length, int xstart, int ystart, int xend, int yend);
//void initializeMaze(maze *maze, int width, int length, int xstart, int ystart, int xend, int yend);
//void markBlocked(FILE *src, maze *maze, int *xpos, int *ypos, int xstart, int ystart, int xend, int yend, int width, int length);
//void printMaze(maze *maze);
//void push(node **head, int xpos, int ypos);
//void pop(node **head, int debugMode);
//int checkEmpty(node *head);
//void displayStack(node *head);
//void freeMaze(maze *maze);



void readFile(FILE *src, int *width, int *length, int *xstart, int *ystart, int *xend, int*yend)
{
	//check width and length
	while(fscanf (src, "%d %d", &*width, &*length) != EOF)
	{
		if(*width > 0 && *length > 0)
		{
			break;
		}
		else{
			printf("Invalid Maze Size: %d, %d\n", *width, *length);
			*width = -1;
			*length = -1;
		}
	}
	//check xstart and ystart
	while(fscanf (src, "%d %d", &*xstart, &*ystart) != EOF){
		if(*xstart <= *width && *ystart <= *length)
			break;
		else{
			printf("Invalid Maze Start Positions: %d, %d\n", *xstart, *ystart);
			*xstart = -1;
			*ystart = -1;
		}
	}
	//check xend and yend
	while(fscanf (src, "%d %d", &*xend, &*yend) != EOF){
		if(*xend <= *width && *yend <= *length)
			break;
		else{
			printf("Invalid Maze End Positions: %d, %d\n", *xend, *yend);
			*xend = -1;
			*yend = -1;
		}
	}
}//end readFile(...)

void validMove(int width, int length, int xstart, int ystart, int xend, int yend)
{
	//check width and length
	if(width == -1 || length == -1)
	{
		printf("\nReached end of file without finding valid size, start, and end\n");
		exit(1);
	}
	//check xstart and ystart
	if(xstart == -1 || ystart == -1)
	{
		printf("\nReached end of file without finding valid size, start, and end\n");
		exit(1);
	}
	//check xend and yend
	if(xend == -1 || yend == -1)
	{
		printf("\nReached end of file without finding valid size, start, and end\n");
		exit(1);
	}
}//end validMove(...)

void printMazeInfo(int width, int length, int xstart, int ystart, int xend, int yend)
{
	printf ("\nsize: %d, %d\n", width, length);
	printf ("start: %d, %d\n", xstart, ystart);
	printf ("end: %d, %d\n\n", xend, yend);
}//end printMazeInfo(...)

void initializeMaze(maze *maze, int width, int length, int xstart, int ystart, int xend, int yend)
{
	int i, j;
	
	//initialize maze size, start, and end positions
	maze->width = width;
	maze->length = length;
	maze->xstart = xstart;
	maze->ystart = ystart;
	maze->xend = xend;
	maze->yend = yend;
	
	//allocate arr and visited arrays based on width and length
	maze->arr = (char **) malloc(sizeof(char *) * (width + 2));
	maze->visited = (int **) malloc(sizeof(int *) * (width + 2));
	for(i = 0; i < width + 2; i++)
	{
		maze->arr[i] = (char *) malloc(sizeof(char) * (length + 2));
		maze->visited[i] = (int *) malloc(sizeof(int) * (length + 2));
	}
		
	/* initialize the maze to empty and visited to UNVISITED*/
	for (i = 0; i < maze->width + 2; i++)
	{
		for (j = 0; j < maze->length + 2; j++)
		{
			maze->arr[i][j] = '.';
			maze->visited[i][j] = 0;
		}
	}
		
	/* mark the borders of the maze with *'s and blocked positions as VISITED*/
	for (i=  0; i < maze->width + 2; i++)
	{
		maze->arr[i][0] = '*';
		maze->arr[i][maze->length + 1] = '*';
		maze->visited[i][0] = 1;
		maze->visited[i][maze->length + 1] = 1;
	}
	for (i = 0; i < maze->length + 2; i++)
	{
		maze->arr[0][i] = '*';
		maze->arr[maze->width + 1][i] = '*';
		maze->visited[0][i] = 1;
		maze->visited[maze->width + 1][i] = 1;
	}	
	/* mark the starting and ending positions in the maze */
	maze->arr[maze->xstart][maze->ystart] = 'S';
	maze->arr[maze->xend][maze->yend] = 'E';
	
	/* mark the start position as visited */
	maze->visited[maze->xstart][maze->ystart] = 1;
}//end initializeMaze(...)

void markBlocked(FILE *src, maze *maze, int *xpos, int *ypos, int xstart, int ystart, int xend, int yend, int width, int length)
{
	//loop to accept blocked positions
	while (fscanf (src, "%d %d", &*xpos, &*ypos) != EOF)
	{
		//check if blocking start position
 		if(*xpos == xstart && *ypos == ystart)
		 {
			printf("Invalid Blocking Start Position: %d, %d\n", *xpos, *ypos);
			continue;
		}
		//check if blocking end position
		if(*xpos == xend && *ypos == yend)
		{
			printf("Invalid Blocking End Position: %d, %d\n", *xpos, *ypos);
			continue; 
		}
		//check if blocking outside of maze
		if(*xpos > width || *ypos > length)
		{
			printf("Invalid Blocking Outside Of Maze: %d, %d\n", *xpos, *ypos);
			continue;
		}
		//check if blocking non-positive index of array
		if(*xpos < 1 || *ypos < 1)
		{
			printf("Invalid Blocking Of Maze: %d, %d\n", *xpos, *ypos);
			continue;
		}
		maze->arr[*xpos][*ypos] = '*';
		maze->visited[*xpos][*ypos] = 1;
	}
}//end markBlocked(...)

void push(node **head, int xpos, int ypos)
{
	//create new node
	node *temp = (node*) malloc(sizeof(node));
	//set node values
	temp->xpos = xpos;
	temp->ypos = ypos;
	//set next pointer
	temp->next = *head;
	//set new head
	*head = temp;
}//end push(...)

void pop(node **head, int debugMode)
{
	//printf("\nPOPPED\n");
	//set temp head
	node *temp = *head;
	if(debugMode == TRUE)
	{
		printf("Popping off node: (%d, %d)\n", temp->xpos, temp->ypos);
	}
	//check to see if list is empty
	if(temp != NULL)
	{
		//remove from list
		*head = temp->next;
		free(temp);
		return;
	}
	//empty
	printf("\nCannot pop from empty node\n");
	return;
}//end pop(...)

int checkEmpty(node *head)
{
	if(head == NULL)
	{
		return 1;
	}
	return 0;
}//end checkEmpty(...)

void displayStack(node *head)
{
	if(head->next != NULL)
	{
		displayStack(head->next);
	}
	printf("(%d,%d)\n", head->xpos, head->ypos);
}//end displayStack(...)

void freeMaze(maze *maze)
{
	int i;
	for(i = 0; i < maze->width + 2; i++)
	{
	free(maze->arr[i]);
	free(maze->visited[i]);
	}
	free(maze->arr);
	free(maze->visited);
}//end freeMaze(...)

void depthFirstSearch(maze *maze, node** head)
{
	push(head, maze->xstart, maze->ystart);
	while(*head != NULL && ((*head)->xpos != maze->xend || (*head)->ypos != maze->yend))
	{
		if((*head)->xpos == maze->xend && (*head)->ypos == maze->yend)
		{
			break;
		}
		if(maze->visited[(*head)->xpos-1][(*head)->ypos] == 0)//top
		{
			maze->visited[(*head)->xpos-1][(*head)->ypos] = 1;
			push(head, (*head)->xpos-1, (*head)->ypos);
		}
		else if(maze->visited[(*head)->xpos][(*head)->ypos-1] == 0)//left
		{
			maze->visited[(*head)->xpos][(*head)->ypos-1] = 1;
			push(head, (*head)->xpos, (*head)->ypos-1);
		}
		else if(maze->visited[(*head)->xpos][(*head)->ypos+1] == 0)//right
		{
			maze->visited[(*head)->xpos][(*head)->ypos+1] = 1;
			push(head, (*head)->xpos, (*head)->ypos+1);
		}
		else if(maze->visited[(*head)->xpos+1][(*head)->ypos] == 0)//down
		{
			maze->visited[(*head)->xpos+1][(*head)->ypos] = 1;
			push(head, (*head)->xpos+1, (*head)->ypos);
		}
		else
		{
			pop(head, DebugMode);
		}
	}
	
	if(*head == NULL)
	{
		printf("Maze has no solution");
	}
    else
    {
	    displayStack(*head);
    }
	
}//end depthFirstSearch

int main (int argc, char **argv)
{
  //intializing all variables to be used

  maze m1; //intialize the maze
  //variables for maze
  int xpos;
  int ypos;
  int width = -1;
  int length = -1;
  int xstart = -1;
  int ystart = -1;
  int xend = -1;
  int yend = -1;  
  int i;
  int j; //for loops
  node *nodeHead = NULL;

  FILE *src;
  
  for(i = 0; i< argc; i++)
  {
    if(strcmp(argv[i], "-d")==0)
    {
      DebugMode = TRUE;
    }
  }

  /* verify the proper number of command line arguments were given */
  if(argc != 2) 
  {
     printf("Usage: %s <input file name>\n", argv[0]);
     exit(-1);
  }
   
  /* Try to open the input file. */
  if ( ( src = fopen( argv[1], "r" )) == NULL )
  {
    printf ( "Can't open input file: %s", argv[1] );
    exit(-1);
  }

  	/* read in the size, starting and ending positions in the maze */
	readFile(src, &width, &length, &xstart, &ystart, &xend, &yend);

	/*check to see if values are valid */
	validMove(width, length, xstart, ystart, xend, yend);

	/*initialize maze*/
	initializeMaze(&m1, width, length, xstart, ystart, xend, yend);

	/* mark the blocked positions in the maze with *'s */
	markBlocked(src, &m1, &xpos, &ypos, xstart, ystart, xend, yend, width, length);

	/* print them out to verify the input */	
	printMazeInfo(width, length, xstart, ystart, xend, yend);

  /* print out the initial maze */
  for (i = 0; i < m1.width+2; i++)
  {
    for (j = 0; j < m1.length+2; j++)
    {
      printf ("%c", m1.arr[i][j]);
    }
    printf("\n");
  }
  
  depthFirstSearch(&m1, &nodeHead);

}//end of main()
