#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <string>
#include <iostream>
#include <fstream>
#include "Player.h"


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
  Player::Player()
  {
    goldCollected = 0; //the player doesn't have any gold
    x = -1; //intialization of x
    y = -1; //initalization of y
  }

  //goldCollected getter and incrementer
  int Player::getGold()
  {
    return goldCollected;
  }
  void Player::incrementGold(int num)
  {
    goldCollected += num;
  }

  //X coordinate getter and setter
  int Player::getX()
  {
    return x;
  }
  void Player::setX(int num)
  {
    x = num;
  }

  //Y coordinate getter and setter
  int Player::getY()
  {
    return y;
  }
  void Player::setY(int num)
  {
    y = num;
  }

  //getter and setter for tilesVisited
  int Player::getTilesVisited()
  {
    return numOfTiles;
  }
  void Player::setTilesVisited(int v)
  {
    numOfTiles = v;
  }
};

