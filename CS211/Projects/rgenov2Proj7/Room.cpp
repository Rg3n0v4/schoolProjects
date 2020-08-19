#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <string>
#include <iostream>
#include <fstream>
#include "Room.h"

using namespace std;

class Room
{
  private:
  int numRows;
  int numColumns;
  Tile*** roomMap;

  public:
  Room::Room(int rows, int columns)
  {
    //initializes everything
    numRows = rows + 2;
    numColumns = columns + 2;
    roomMap = new Tile**[rows];
    Room* nextRoom = NULL;

    for(int i = 0 ; i < numRows; i++)
    {
      roomMap[i] = new Tile*[numColumns];
    }

    //Defines all tiles as Tiles
    for(int j = 0; j < numRows; j++)
    {
      for(int m = 0; m < numColumns; m++)
      {
        roomMap[j][m] = new Tile();
      }
    }

    for(int n = 0; n < numColumns; n++)
    {
      roomMap[0][n] = new Wall();
      roomMap[numRows - 1][n] = new Wall();
    }

    for(int k = 0; k < numRows; k++)
    {
      roomMap[k][0] = new Wall();
      roomMap[k][numColumns - 1] = new Wall();
    }
  }

  
  //prints out the room
  void Room::printMap()
  {
    for(int i = 0; i < numRows; i++)
    {
      for(int j = 0; j < numColumns; j++)
      {
        cout << roomMap[i][j]->getType();
      }
      cout << endl;
    }
  }

  Tile*** Room::getMap()
  {
    return roomMap;
  }

  void Room::setMap(Tile*** newMap)
  {
    roomMap = newMap;
  }

  //getters and setters for width and length
  int Room::getRows()
  {
    return numRows;
  }
  void Room::setRows(int val)
  {
    numRows = val;
  }

  int Room::getColumns()
  {
    return numColumns;
  }
  void Room::setColumns(int val)
  {
    numColumns = val;
  }
  

  //create a door in the maze
  void Room::createDoor(LinkedList* listOfMazes, bool& exitExists)
  {
    if(exitExists == false)
    {
      char* s = strtok (NULL, " \n\t");
      int xCoor = atoi(s);
      s = strtok (NULL, " \n\t");
      int yCoor = atoi(s);
      roomMap[xCoor][yCoor] = new Door(xCoor, yCoor);
      char* fname = strtok (NULL, " \n\t");
      if ( fname == NULL )
      {
        printf ("Filename expected\n");
        return;
      }
      listOfMazes->insert(fname);
      cout<<"File being inserted is: "<<fname<<endl;
    }
    else
    {
      char* s = strtok (NULL, " \n\t");
      int xCoor = atoi(s);
      s = strtok (NULL, " \n\t");
      int yCoor = atoi(s);
      roomMap[xCoor][yCoor] = new Door(xCoor, yCoor);
      roomMap[xCoor][yCoor]->setType('E');
    }
  }

  //creates obstacle in the maze
  void Room::createObstacle()
  {
    char* s = strtok (NULL, " \n\t");
    int xCoor = atoi(s);
    s = strtok (NULL, " \n\t");
    int yCoor = atoi(s);
    roomMap[xCoor][yCoor] = new Wall();
  }

  //creates chest
  void Room::createChest()
  {
    char* s = strtok (NULL, " \n\t");
    int xCoor = atoi(s);
    s = strtok (NULL, " \n\t");
    int yCoor = atoi(s);
    s = strtok (NULL, " \n\t");
    int chestValue = atoi(s);
    Chest* newChest = new Chest(xCoor, yCoor);
    newChest->setGold(chestValue);
    roomMap[xCoor][yCoor] = newChest;
  }

  //setUp player's location in maze
  void Room::setPlayerLoc()
  {
    char* s = strtok (NULL, " \n\t");
    int xCoor = atoi(s);
    s = strtok (NULL, " \n\t");
    int yCoor = atoi(s);
    roomMap[xCoor][yCoor]->setType('P');
    roomMap[xCoor][yCoor]->setVisited(true);
  }

  //checking if the player has a valid move
  bool Room::validMove(int destination_col, int destination_row)
  {
    char desitination_tile = roomMap[destination_row][destination_col]->getType();
    if(desitination_tile != '*')
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  //setUp player's location
  void Room::playerMovement(int playerX, int playerY, Player originalPlayer)
  {
    roomMap[originalPlayer.getX()][originalPlayer.getY()]->setType(' ');
    // originalPlayer->setX(playerX);
    // originalPlayer->setY(playerY);
    roomMap[playerX][playerY]->setType('P');
    roomMap[playerX][playerY]->setVisited(true);
  }
};
