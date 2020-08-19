#include "queue.h"
#include <iostream>
using namespace std;

queue::queue()
{
	arr = new int[50];//<-number of elements in queue by default
	front = -1; //<- initialization of the beginning of the queue
	back = -1;  //<- initialization of the back of the queue
	size = 0; //<- size of the queue
	
}
queue::queue(int sz)
{
	arr = new int[sz];//<-number of elements in queue by default
	front = -1; //<- initialization of the beginning of the queue
	back = -1;  //<- initialization of the back of the queue
	size = 0; //<- size of the actual queue
}


int& queue::operator[](int index)
{
	if(index == back || index == front)
	//if it's the beginning or ending of the queue they are looking for
	{
		return arr[index];
	}
	else if(index >= size || index < 0)
	//if the index they are looking for is out of range of the array/queue
	{
		throw out_of_range("queue::operator[]");
	}
	else
	//if index is in the middle
	{
		int beginIndex = front; //so that the it's a "starting point"
		int arrCounter = index+1; //to keep track of how many elements beginIndex needs to go "up" by in the queue
		
		while(arrCounter != 0 && beginIndex != back)
		{
			if(beginIndex == size) //incase it's at the back of the memory
			{
				beginIndex = 0;//set it to the beginning of arr
			}
			else
			{
				beginIndex++; //have it "increment" through the queue
				arrCounter--; 
			}
		}
		return arr[beginIndex]; //returns where index is in the arr
	}
	
	
}

void queue::push(int value)
{
	if(front == -1)//if the queue is just being made
	{
		front = 0;
		back = 0;
		size = 1; //there is one element in the array
		arr[back] = value; //puts the value at the back of the queue
	}
	else//the array is made
	{
		if((front == 0 && back == size-1) || (front != 0 && back == front -1))// if the array is full
		{
			throw out_of_range("queue::push[]");
		}
		else
		{
			if(back == size-1)
			{
				back = 0; //to "wrap" around
				arr[back] = value; //puts the value at the back of the queue
			}
			else
			{
				back++; //increase the back of the queue
				arr[back] = value; //puts the value at the back of the queue
			}
		}
	}
}

int queue::pop()
{
	int ogBegin = arr[front];
	if(!empty())
	{
		if(front == back) //if there's only one element
		{
			front = -1;
			back = -1;
			size = 0;
		}
		else
		{
			if(front == size-1) //if front is at the back of the array itself
			{
				front = 0; //to "wrap" around
			}
			else
			{
				front++; //so that the "beginning" of the queue is different
			}
			
		}

		size--;
		return ogBegin; //returns original "beginning" of queue before it was changed
	}
	else
	{
		throw out_of_range("queue::pop[]");
	}
}

bool queue::empty()
{
	if(size != 0) 
	//if the array contains at least 1 variable
	{
		return false;
	}
	else
	//if the array is empty
	{
		return true;
	}
}

//hw5 below

queue::iterator queue::begin()
{
	return queue::iterator(this, front-1);
}

queue::iterator queue::end()
{
	return queue::iterator(this, back+1);
}

queue::iterator::iterator(queue* q, int x)
{
	iterator::location = x; //where the user wants to be at 
	iterator::obj = q;//the object itself is given by the user
}

int& queue::iterator::operator*()
{
	int x = obj->arr[location];
	return x; //returns the location the user is at currently in the queue
}

queue::iterator& queue::iterator::operator++()
{
	//you're supposed to increment the pointer by 1
	//increment location in the array
	//DO NOT CHANGE FRONT AND BACK
	//make a wrap a round if location == back then set it to the front

	//if the location is at the end of the size BUT NOT THE END of the queue and assuming the back is 
	//towards the front of the queue
	if(location != obj->back && location == obj->size-1 && location > obj->back)
	{
		location = 0; //"wrap around" 
		//goes to the begining of the array queue but not necessarily the front of the queue
	}
	//just increment normally as long as location isn't the same as the size of the queue
	else if(location != obj->back && location < obj->size-1 && (location > obj->back || location < obj->back))
	{
		location++;
	}
	else //if location == obj->size then the location is something that the queue can't reach
	{
		throw out_of_range("queue::iterator& queue::iterator::operator++()");
	}
	return *this;
}

queue::iterator& queue::iterator::operator--()
{
	//if the location is at the front the queue 
	if(location != obj->front && location == 0 && location < obj->front)
	{
		location = obj->size-1; //"wrap around" 
	}
	//just decrement normally as long as location isn't 0
	else if(location != obj->front && location != 0 && (location > obj->front || location < obj->front))
	{
		location--;
	}
	else //if location == obj->size then the location is something that the queue can't reach
	{
		throw out_of_range("queue::iterator& queue::iterator::operator--()");
	}
	return *this;
}

bool queue::iterator::operator!=(const queue::iterator other)
{
	//other is referring to a whole NEW object
	//its a pointer 
	// this function checks 
	//depends on the object if they are pointing to the same object

	//queue is 5
	//Case 1: there's one queue, location points index 4 and lets say the same queue is also pointing to the index 4 return false
	//Case 2: if location in queue 1 is pointing index 4 and location in queue 2 is pointing to index 4 should return true

	if(location == other.location && obj == other.obj)
	//same location AND same object return false
	{
		return false;
	}
	else if(location == other.location && obj != other.obj)
	//same location BUT different obj then returns true
	{
		return true;
	}
	else if(location != other.location && obj == other.obj)
	//different location BUT other is referring to the same obj
	{
		return true;
	}
}

bool queue::iterator::operator==(const queue::iterator other)
{
	//opposite of what is in !=
	if(location == other.location && obj == other.obj)
	//same location AND same object return true
	{
		return true;
	}
	else if(location == other.location && obj != other.obj)
	//same location BUT different obj then returns false
	{
		return false;
	}
	else if(location != other.location && obj == other.obj)
	//different location BUT other is referring to the same obj
	{
		return false;
	}
}


int queue::iterator::operator-(const queue::iterator& other) const
{
	//computes the distance between the 2 pointers
	//the variables are the same but they are different objects
	//it MAYBE the same queue and it MAYBE different queues

	return other.location - location;
}

queue::iterator queue::iterator::operator-(int n)
{
	//it basically needs to return the value 
	//make a copy of the current queue
	//move back n times 
	//consider wrap around

	for(int i = 0; i < n; i++)
	{
		operator--();
	}
	return iterator(*this);
}

queue::iterator queue::iterator::operator+(int n)
{
	//same as operator- but moves forward n times
	//consider wrap around
	for(int i = 0; i < n; i++)
	{
		operator++();
	}
	return iterator(*this);
}

//									pointer to a location
bool queue::iterator::operator<(const queue::iterator other)
{
	//distance going forwards, based on the other location they give you

	if (other.location - obj->front > location - obj->front)
    {
        return true;
    }
	return false;
}

