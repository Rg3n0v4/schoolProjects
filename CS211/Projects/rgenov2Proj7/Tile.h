#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <string>
#include <iostream>
#include <fstream>

/*
  Types of Tiles:
    -Generic (just stand on)
    -Chest (contains gold and should increment goldCollected variable in Player)
    -Door (access to other Room)
    -Wall (if the player ends up hitting a wall, they are just sent back to where they previously were)
*/
class Tile
{
  private:
  //x and y coordinates for where the tile is in the maze
  int x;
  int y;

  char tileType;
  //G = generic tile
  //C = chest
  //D = door
  //* = wall

  //checking if the Tile has been visited
  bool isVisited;

  public:
  //constructor
  Tile();
  //getter and setter for x and y coordinates
  int getX();
  void setX(int val);

  int getY();
  void setY(int val);

  //getter and setter for titleType
  char getType();
  void setType(char type);

  //getter and setter for isVisited
  bool getVisited();
  void setVisited(bool v);

};

class Chest: public Tile
{
  private:
  int numGold;

  public:

  //constructor
  Chest(int xCoor, int yCoor)
  {
    setX(xCoor);
    setY(yCoor);
    setType('C');
    numGold = 0; //initialization
  }

  //getter and setter for numGold
  int getGold()
  {
    return numGold;
  }
  void setGold(int val)
  {
    numGold = val;
  }
};

class Door: public Tile
{
  public:
  Door(int xCoor, int yCoor)
  {
    setX(xCoor);
    setY(yCoor);
    setType('D');
  }

};

class Wall: public Tile
{
  public:
  Wall()
  {
    setType('*');
  }
};
