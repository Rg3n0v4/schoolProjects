#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <string>
#include <iostream>
#include <fstream>
#include "Room.h"
#include "Tile.h"
#include "Player.h"

using namespace std;


//prints out the different commands you can do for this game
void showCommands()
 {
   cout << "The commands for this project are:\n";
   cout << "  q (quit program)\n";
   cout << "  w (move player up)\n";
   cout << "  a (move player left)\n";
   cout << "  s (move player down)\n";
   cout << "  d (move player right)\n";
   cout << "  h (print a list of all commands)\n";
   cout << "  g (player's score)\n";
   cout << "  f (find the shortest path to the door)\n";
 }


int main (int argc, char** argv)
{
  //setting up and reading file and creating maze accordingly
  FILE *src;

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

  /*initializeMaze*/
  char  buffer[300];
  char* input;

  input = fgets ( buffer, 300, src );   // get a line of input
  
  char* firstNum = strtok(input, " /n/t");
  int numOfRows =  atoi(firstNum);
  char* secondNum = strtok(NULL, "/n/t");
  int numOfColumns = atoi(secondNum);

  Room maze(numOfRows, numOfColumns);
  Player player;
  LinkedList listOfMazes;
  bool hasExit = false;

  input = fgets ( buffer, 300, src );
  
  // loop until all lines are read from the input
  while (input != NULL)
  {
    // process each line of input using the strtok functions 
    char* command;
    command = strtok (input , " \n\t");

    printf ("*%s*\n", command);
    
    if ( command == NULL )
      printf ("Blank Line\n");
 
    else if ( strcmp (command, "Q") == 0) //quit
    {
      exit(1);
    }
    else if ( strcmp (command, "S") == 0) //player setUp
    {
      maze.setPlayerLoc();
    }

    else if ( strcmp (command, "O") == 0) //create wall
    {
      maze.createObstacle();
    }
    else if ( strcmp (command, "C") == 0) //create chest
    {
      maze.createChest();
    }
    
    else if ( strcmp (command, "D") == 0) //create door
    {
      maze.createDoor(&listOfMazes, hasExit);
    }

    else if ( strcmp (command, "E") == 0) //create exit
    {
      hasExit = true;
      maze.createDoor(&listOfMazes, hasExit);
    }
    else
    {
      printf ("Command is not known: %s\n", command);
    }
     
    input = fgets ( buffer, 300, src );   // get the next line of input
    maze.printMap();
  }

  char movePlayer;
  bool playerCanMove;
  int playerX;
  int playerY;
  while(movePlayer != 'q')
  {
    playerCanMove = false;
  // process each line of input using the strtok functions 
    cout << "Move Player: ";
    cin >> movePlayer;
    if (movePlayer == 'q') //quit
    {
      exit(1);
    }
    else if ( movePlayer == 'w') //move up
    {
      playerCanMove = maze.validMove(player.getY()-1, player.getX());
      playerX = player.getX();
      playerY = player.getY()-1;
    }
    else if ( movePlayer == 'a') //move left
    {
     playerCanMove = maze.validMove(player.getY(), player.getX()-1);
     playerX = player.getX()-1;
     playerY = player.getY();
    }
    else if ( movePlayer == 's') //move down
    {
      playerCanMove = maze.validMove(player.getY()+1, player.getX());
      playerX = player.getX();
      playerY = player.getY()+1;
    }
    else if ( movePlayer == 'd') //move right
    {
      playerCanMove = maze.validMove(player.getY(), player.getX()+1);
      playerX = player.getX()+1;
      playerY = player.getY();
    }
    else if ( movePlayer == 'g') //print player's score
    {
      player.getGold();
    }

    //changes player's coordinates and where they are on the board
    if(playerCanMove == true)
    {
      maze.playerMovement(playerX, playerY, player);
      player.setX(playerX);
      player.setY(playerY);
      // maze.getMap()[playerX][playerY]->setType('P');
      // maze.getMap()[playerX][playerY]->setVisited(true);
      // player.setTilesVisited(player.getTilesVisited()+1);
    }
    // else if ( strcmp (command, "F") == 0) //find a path to a door using DFS and print path
    //   findPath();
    maze.printMap();
  }
  

  return 0;
}
