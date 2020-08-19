#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <string>

#include "proj6Header.h"
    
    Island::Island() //construcor   
    {
      islandNum = -1; //just to initalize it
      isVisited = false; //the island hasn't been visited
    }

    //getter and setter for isVisited variable
    bool Island::getIsVisited()
    {
      return isVisited;
    }

    void Island::setIsVisited(bool visit)
    {
      isVisited = visit;
    }

    //getter and setter for islandNum
    int Island::getIslandNum()
    {
      return islandNum;
    }
    
    void Island::setIslandNum(int num)
    {
      islandNum = num;
    }

    MyList* Island::getList()
    {
      return &list;
    }

    void Island::printList()
    {
      printf("Island #%d can be reached by: ", islandNum+1);
      for(int j = 0; j < list.getNumberOfCurrentValues(); j++)
      {
        printf("%d, ",list.getNthValue(j)->getIslandNum());
      }
      printf("\n");
    }