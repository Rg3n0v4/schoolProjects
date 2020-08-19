/*queue.h*/

//
// Raphael Genova
// U. of Illinois, Chicago
// CS 341, Spring 2020
// HW #04: Building an STL compliant container
//

#include <iterator>
#pragma once

class queue
{
private:
	int * arr;
	int front;
	int back;
	int size;

public:
	// You'll need to add an iterator
	// I'm inheriting from std::iterator here to add the typedefs commented below so std::sort will compile, but it isn't strictly necessary.
	class iterator : public std::iterator<std::random_access_iterator_tag, int, int>
	{
	public:
		// These typedefs are the things introduced by the inheritance from std::iterator, that std::sort fails to compile without
		typedef iterator self_type;
		typedef int value_type;
		typedef int& reference;
		typedef int* pointer;
		typedef std::random_access_iterator_tag iterator_category;
		typedef int difference_type;
		
		// location of the iterator in the queue
		int location; // this is a pointer to the object; acts as a pointer to traverse through your queue; points to the index in the queue
		
		// reference to the queue so you have access to the current front, back, and size/capacity
		queue * obj;
		
		// You'll need to provide a constructor for begin() and end()
		iterator(queue* q, int x); //returns the object and returns the element right before begin
		
		// operator++ is required to move the iterator to the next location
		iterator& operator++();
		
		// operator-- required by std::sort, moving the iterator to the previous element
		iterator& operator--();
		
		// operator* required to derefence the iterator and get at the value being referred to.
		int& operator*();
		
		// required by std::sort, to tell whether two iterators point to the same location, and when end is reached
		bool operator==(const iterator other);
		
		// required by std::sort, to tell whether two iterators do not point to the same location
		bool operator!=(const iterator other);
		
		// required by std::sort, to traverse the structure more efficiently
		int operator-(const iterator& other) const;
		
		// required by std::sort, to traverse the structure more efficiently
		iterator operator-(int n);
		// required by std::sort, to traverse the structure more efficiently
		iterator operator+(int n);
		// required by std::sort, to identify which iterator is earlier in the structure, so smaller items can be swapped earlier
		bool operator<(const iterator other);
	};

	// Default constructor
	//
	//  Builds a queue containing room for 50 elements on the heap
	queue();

	// Parametrized constructor
	//
	//  Builds a queue containing room for sz elements on the heap
	queue(int sz);

	// operator[]
	//
	//  Allows the user to extract a value in the queue
	//  This value can be read and written, and is not removed from the queue
	//  The index parameter specifies how far into the queue to go to find the element.
	//		index 0 returns the first element of the queue
	//		A negative index, or an index greater than the current length of the queue should throw an exception
	int& operator[](int index);

	// push(int)
	//
	//  Adds a new value to the queue, after the current last element.
	//  The value parameter is used to create the new last element.
	//	If the queue is full, throw an exception and do not modify the queue.
	void push(int value);

	// pop()
	//
	//  Removes the element at the current front of the queue, making the second element the new first.
	//  The value of the old first element should be returned.
	//	If the queue is empty, throw an exception and do not modify the queue.
	int pop();

	// empty()
	//
	//  Returns whether the queue is currently empty.
	//  Returns true if the queue currently contains no elements.
	//  Returns false if the queue currently contains at least one element.
	bool empty();

	// begin()
	//
	//  Returns a queue iterator pointing to the first element of the queue.
	iterator begin();

	// end()
	//
	//  Returns a queue iterator pointing "after" the first element of the queue.
	iterator end();
};


