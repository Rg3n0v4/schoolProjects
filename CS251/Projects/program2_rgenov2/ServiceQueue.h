
#ifndef _SERVICE_QUEUE_H
#define _SERVICE_QUEUE_H

#include <iostream>
#include <vector>
#include <utility>



class ServiceQueue {

private:
	struct buzzer
	{
		int isBeingUsed; //will be 0 for not being used and 1 for is being used
		int id; //buzzer number
		buzzer* pPrev;//points to the buzzer in front of it
		buzzer* pNext; //points to the buzzer behind it
	};

	struct list
	{
		buzzer* front; //a person knows who's in front of them
		buzzer* back; //a person knows who's behind them
		int lineLength; //the number of people waiting in line
	};

	list queue;
	list bin;

	std::vector<buzzer*> buzz_bucket; 

	/** Your private data members here!
	*   (you should have NO PUBLIC data members!
	*
	* Nested private types also go here.
	* For example, if your design needs some kind of
	*   structure, you would specify it here.
	*/
	

public:

	/**
	 * Constructor
	 * Description: intializes an empty service queue.
	 *
	 * RUNTIME REQUIREMENT: O(1)
	 *
		 * TODO
	 */
	ServiceQueue()
	{
		queue = list();
		queue.front = nullptr;
		queue.back = nullptr;
		queue.lineLength = 0;

		bin = list();
		bin.front = nullptr;
		bin.back = nullptr;
		bin.lineLength = 0;
	}

	/**
	 * Destructor
	 * Description:  deallocates all memory assciated
	 *   with service queue
	 *
	 * RUNTIME REQUIREMENT:  O(N_b) where N_b is the number of buzzer
	 *	IDs that have been used during the lifetime of the
	 *	service queue; in general, at any particular instant
	 *	the actual queue length may be less than N_b.
	 *
	 *	[See discussion of "re-using buzzers" below]
	 *
		 * TODO
	 */
	~ServiceQueue() {

	}

	/**
	 * Function: snapshot()
		 * param:  buzzers is an integer vector passed by ref
	 * Description:  populates buzzers vector with a "snapshot"
		 *               of the queue as a sequence of buzzer IDs
		 *
	 *
	 * RUNTIME REQUIREMENT:  O(N)  (where N is the current queue
	 *		length).
	 */
	void snapshot(std::vector<int> &buzzers)
	{
		buzzers.clear();   // you don't know the history of the 
						   //   buzzers vector, so we had better
						   //   clear it first.
		buzzer* curr = queue.front; //current buzzer in the list to be printed out
		if (curr == nullptr)
		{
			return;
		}
		while (curr != nullptr)
		{
			buzzers.push_back(curr->id);
			curr = curr->pNext;
		}
	}

	/**
	 * Function: length()
	 * Description:  returns the current number of
	 *    entries in the queue.
	 *
	 * RUNTIME REQUIREMENT:  O(1)
	 */
	int  length()
	{
		if (queue.lineLength != 0)
		{
			return queue.lineLength;
		}

		return 0;   // placeholder

	}

	/**
	 * Function: give_buzzer()
		 * Return:   buzzer-ID (integer) assigned to the new customer.
	 * Description:  This is the "enqueue" operation.  For us
	 *    a "buzzer" is represented by an integer (starting
	 *    from zero).  The function selects an available buzzer
	 *    and places a new entry at the end of the service queue
	 *    with the selected buzer-ID.
	 *    This buzzer ID is returned.
	 *    The assigned buzzer-ID is a non-negative integer
	 *    with the following properties:
	 *
	 *       (1) the buzzer (really it's ID) is not currently
	 *         taken -- i.e., not in the queue.  (It
	 *         may have been in the queue at some previous
	 *         time -- i.e., buzzer can be re-used).
	 *	  This makes sense:  you can't give the same
	 *	  buzzer to two people!
	 *
	 *       (2) Reusable Buzzers:  A re-usable buzzer is
	 *	  a buzzer that _was_ in the queue at some previous
	 *	  time, but currently is not.
	 *
		 *         REQUIREMENT:  If there is one or more reusable
		 *         buzzer, you MUST return one of them; furthermore,
		 *         it must be the buzzer that became reusable most
		 *         MOST RECENTLY.
	 *
	 *       (3) if there are no previously-used / reusable buzzers,
		 *         the SMALLEST possible buzzer-ID is used (retrieved from
		 *         inventory).
	 *	    Properties in this situation (where N is the current
	 *	      queue length):
	 *
	 *		- The largest buzzer-ID used so far is N-1
	 *
	 *		- All buzzer-IDs in {0..N-1} are in the queue
	 *			(in some order).
	 *
	 *		- The next buzzer-ID (from the basement) is N.
	 *
	 *    In other words, you can always get more buzzers (from
	 *    the basement or something), but you don't fetch an
	 *    additional buzzer unless you have to (i.e., no reusable buzzers).
	 *
	 * Comments/Reminders:
	 *
	 *	Rule (3) implies that when we start from an empty queue,
	 *	the first buzzer-ID will be 0 (zero).
	 *
	 *	Rule (2) does NOT require that the _minimum_ reuseable
	 *	buzzer-ID be used.  If there are multiple reuseable buzzers,
	 *	any one of them will do.
	 *
	 *	Note the following property:  if there are no re-useable
	 *	buzzers, the queue contains all buzzers in {0..N-1} where
	 *       N is the current queue length (of course, the buzzer IDs
	 *	may be in any order.)
	 *
	 * RUNTIME REQUIREMENT:  O(1)  ON AVERAGE or "AMORTIZED"
	 *          In other words, if there have been M calls to
	 *		give_buzzer, the total time taken for those
	 *		M calls is O(M).
	 *
	 *		An individual call may therefore not be O(1) so long
	 *		as when taken as a whole they average constant time.
	 *
	 */
	int  give_buzzer()
	{
		if (queue.front != nullptr)
			//if the queue is not empty which sends buzzer to the back of the queue
		{
			buzzer* newBuzzer;
			if (bin.front == nullptr)
			{
				newBuzzer = new buzzer;
				newBuzzer->id = buzz_bucket.size();
				newBuzzer->pPrev = queue.back;
				newBuzzer->pNext = nullptr;
				buzz_bucket.push_back(newBuzzer);
			}
			else
			{
				newBuzzer = bin.front; //adds a "new" buzzer onto the list
				bin.front = newBuzzer->pNext;
				newBuzzer->pNext = nullptr;
				newBuzzer->pPrev = queue.back;
				bin.lineLength--;
			}
			
			newBuzzer->isBeingUsed = 1;
			queue.back->pNext = newBuzzer;
			queue.back = newBuzzer;
			queue.lineLength++;
			
		}
		else //queue IS empty
		{
			buzzer* newBuzzer;
			if (bin.front == nullptr)//if the bin has nothing in the front
			{
				newBuzzer = new buzzer;
				newBuzzer->id = buzz_bucket.size();
				newBuzzer->pPrev = nullptr;
				newBuzzer->pNext = nullptr;
				buzz_bucket.push_back(newBuzzer);
			}
			else //reuses a buzzer already present in the bin
			{
				newBuzzer = bin.front; //adds a "new" buzzer onto the list
				bin.front = newBuzzer->pNext;
				newBuzzer->pNext = nullptr;
				newBuzzer->pPrev = nullptr;
				bin.lineLength--;
			}

			newBuzzer->isBeingUsed = 1;
			queue.back = newBuzzer;
			queue.front = newBuzzer;
			queue.lineLength++;
		}
		
		return queue.back->id;  // placeholder
	}

	/**
	 * function: seat()
	 * description:  if the queue is non-empty, it removes the first
	 *	 entry from (front of queue) and returns the
	 *	 buzzer ID.
	 *	 Note that the returned buzzer can now be re-used.
	 *
	 *	 If the queue is empty (nobody to seat), -1 is returned to
	 *	 indicate this fact.
		 *
		 * Returns:  buzzer ID of dequeued customer, or -1 if queue is empty.
	 *
	 * RUNTIME REQUIREMENT:  O(1)
	 */
	int seat()
	{
		if (queue.front == nullptr) //if the queue is empty 
		{
			return -1;
		}
		else //if queue is not empty
		{
			buzzer* temp = queue.front;
			queue.front = temp->pNext;

			if (queue.front != nullptr) //if there IS something in the queue
			{
				temp->pNext->pPrev = nullptr;
			}
			else //if there is ONLY ONE element in the queue
			{
				queue.back = nullptr;
			}

			if (bin.front == nullptr) //if bin IS empty
			{
				bin.front = temp;
				temp->pPrev = nullptr;
				temp->pNext = nullptr;
			}
			else //if the bin ISN'T EMPTY
			{
				temp->pNext = bin.front;
				bin.front = temp;
				temp->pPrev = nullptr;
			}

			temp->isBeingUsed = 1;
			queue.lineLength--;
			bin.lineLength++;
			return temp->id;
		}
	}


	/**
	 * function: kick_out()
	 *
	 * description:  Some times buzzer holders cause trouble and
	 *		a bouncer needs to take back their buzzer and
	 *		tell them to get lost.
	 *
	 *		Specifially:
	 *
	 *		If the buzzer given by the 2nd parameter is
	 *		in the queue, the buzzer is removed (and the
	 *		buzzer can now be re-used) and 1 (one) is
	 *		returned (indicating success).
	 *
	 * Return:  If the buzzer isn't actually currently in the
	 *		queue, the queue is unchanged and false is returned
	 *		(indicating failure).  Otherwise, true is returned.
	 *
	 * RUNTIME REQUIREMENT:  O(1)
	 */
	bool kick_out(int kickBuzzer)
	{
		if (buzz_bucket.size() <= kickBuzzer || buzz_bucket.at(kickBuzzer)->isBeingUsed == 0)
		{
			return false;
		}
		buzzer* temp = buzz_bucket.at(kickBuzzer);
		
		if (temp->pPrev == nullptr)//if it's at the beginning of the list
		{
			seat();
			return true;
		}
		else
		{
			if (temp->pNext == nullptr) //if it's at the end of the list
			{
				buzzer* beforeTemp = temp->pPrev;
				beforeTemp->pNext = nullptr;
				queue.back = beforeTemp;
			}
			else //somewhere in the middle
			{
				buzzer* beforeTemp = temp->pPrev;
				buzzer* afterTemp = temp->pNext;

				beforeTemp->pNext = afterTemp;
				afterTemp->pPrev = beforeTemp;
			}

			if (bin.front == nullptr) //if bin IS empty
			{
				bin.front = temp;
				temp->pPrev = nullptr;
				temp->pNext = nullptr;
			}
			else //if the bin ISN'T EMPTY
			{
				temp->pNext = bin.front;
				bin.front = temp;
				temp->pPrev = nullptr;
			}

			temp->isBeingUsed = 0;
			queue.lineLength--;
			bin.lineLength++;
			
			return true;
		}
	}

	/**
	 * function:  take_bribe()
	 * description:  some people just don't think the rules of everyday
	 *		life apply to them!  They always want to be at
	 *		the front of the line and don't mind bribing
	 *		a bouncer to get there.
	 *
	 *	        In terms of the function:
	 *
	 *		  - if the given buzzer is in the queue, it is
	 *		    moved from its current position to the front
	 *		    of the queue.  1 is returned indicating success
	 *		    of the operation.
	 *		  - if the buzzer is not in the queue, the queue
	 *		    is unchanged and 0 is returned (operation failed).
	 *
	 * Return:  If the buzzer isn't actually currently in the
	 *		queue, the queue is unchanged and false is returned
	 *		(indicating failure).  Otherwise, true is returned.
	 *
	 * RUNTIME REQUIREMENT:  O(1)
	 */
	bool take_bribe(int bribeBuzzer)
	{	
		if (buzz_bucket.size() <= bribeBuzzer || buzz_bucket.at(bribeBuzzer)->isBeingUsed == 0)
		{
			return false;
		}
		
		buzzer* temp = buzz_bucket.at(bribeBuzzer);

		if (temp->pPrev == nullptr)//if the person is already in front
		{
			return true;
		}
		else if(temp->pNext == nullptr)//if the person is at the end of the line 
		{
			buzzer* beforeTemp = temp->pPrev;
			buzzer* originalHeadOfList = queue.front;
			beforeTemp->pNext = nullptr;
			queue.back = beforeTemp;
			queue.front = temp;
			temp->pNext = originalHeadOfList;
			temp->pPrev = nullptr;
		}
		else //if the person is in the middle in the line
		{
			buzzer* beforeTemp = temp->pPrev;
			buzzer* afterTemp = temp->pNext;
			temp->pPrev = nullptr; //because you're being brought to the front of the line
			beforeTemp->pNext = afterTemp;
			afterTemp->pPrev= beforeTemp;
			temp->pNext = queue.front; //makes sure the end of the list isn't lost
			queue.front->pPrev = temp;
			queue.front = temp; //updates temp to be the front of the list
		}
		
		return true;

	}



};   // end ServiceQueue class

#endif

