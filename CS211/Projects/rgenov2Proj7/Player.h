#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <string>
#include <iostream>
#include <fstream>


class Player
{
  private:
  //number of gold collected from chests
  int goldCollected;

  //number of tiles visited
  int numOfTiles;

  //x and y coordinates for current location
  int x;
  int y;

  public:

  //constructor
  Player();

  //goldCollected getter and incrementer
  int getGold();
  void incrementGold(int num);

  //X coordinate getter and setter
  int getX();
  void setX(int num);

  //Y coordinate getter and setter
  int getY();
  void setY(int num);

  //getter and setter for tilesVisited
  int getTilesVisited();
  void setTilesVisited(int v);
};
