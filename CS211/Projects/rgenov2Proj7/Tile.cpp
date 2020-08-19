#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <string>
#include <iostream>
#include <fstream>
#include "Tile.h"

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
  Tile::Tile()
  {
    tileType = ' ';
    isVisited = false;
  }

  //getter and setter for x and y coordinates
  int Tile::getX()
  {
    return x;
  }
  void Tile::setX(int val)
  {
    x = val;
  }

  int Tile::getY()
  {
    return y;
  }
  void Tile::setY(int val)
  {
    y = val;
  }

  //getter and setter for titleType
  char Tile::getType()
  {
    return tileType;
  }
  void Tile::setType(char type)
  {
    tileType = type;
  }

  //getter and setter for isVisited
  bool Tile::getVisited()
  {
    return isVisited;
  }
  void Tile::setVisited(bool v)
  {
    isVisited = v;
  }

};
