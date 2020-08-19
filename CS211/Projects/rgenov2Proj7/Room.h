#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <string>
#include <iostream>
#include <fstream>
#include "Tile.h"
#include "Player.h"
using namespace std;

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

class Room
{
  private:
  int numRows;
  int numColumns;
  Tile*** roomMap;

  public:
  Room(int rows, int columns);
  
  //prints out the room
  void printMap();

  //getter and setter for roomMap
  Tile*** getMap();
  void setMap(Tile*** newMap);

  //getters and setters for rows and columns
  int getRows();
  void setRows(int val);

  int getColumns();
  void setColumns(int val);
  

  //create a door in the maze
  void createDoor(LinkedList* listOfMazes, bool& exitExists);

  //creates obstacle in the maze
  void createObstacle();

  //creates chest
  void createChest();

  //setUp player's location in maze
  void setPlayerLoc()
  {
    char* s = strtok (NULL, " \n\t");
    int xCoor = atoi(s);
    s = strtok (NULL, " \n\t");
    int yCoor = atoi(s);
    roomMap[xCoor][yCoor]->setType('P');
    roomMap[xCoor][yCoor]->setVisited(true);
  }

  //checking if the player has a valid move
  bool validMove(int destination_col, int destination_row);

  //setUp player's location
  void playerMovement(int playerX, int playerY, Player originalPlayer);
};

