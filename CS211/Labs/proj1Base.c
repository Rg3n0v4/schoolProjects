#include <stdio.h>
#include <stdlib.h>

//makes copy of a certain array
void makeArrayCopy(int fromArray[], int toArray[], int size);

//sorts the array in ascending order
void sortAscending(int arr[], int size);

//perfomrs linear search and returns two values:
//1. the index where the value was found or -1 if not found
//2. number of comparisons needed to determine if/where the value is located    
int linSearch(int arr[], int size, int target, int* numComparisons);

//performs binary search and returns two values: 
//1. the index where the value was found or -1 if not found
//2. number of comparisons needed to determine if/where the value is located  
int binSearch(int arr[], int low, int size, int target, int* numComparisons);

int main (int argc, char** argv)
{
 int val;
 int *darr;
 int allocated = 1;
 darr = (int *) malloc(allocated*sizeof(int));

 /* prompt the user for input */
 printf ("Enter in a list of numbers ito be stored in a dynamic array.\n");
 printf ("End the list with the terminal value of -999\n");

 int counter = 0;

 /* loop until the user enters -999 */
 scanf ("%d", &val);
 while (val != -999)
  {
    /* store the value into an array */
    if(counter == allocated)
    {
      int *temp = darr; //makes sure to keep the old array
      darr = (int*) malloc((allocated + 100) * sizeof(int)); //gives more space for new array
      int i=0;
      for(i = 0; i < allocated; i++)
      {
        darr[i] = temp[i];
      }
      free(temp); //discards the old array because it has already been added into the new array
      allocated = allocated+100; //incereases value for future increase in array size;
    }
    darr[counter] = val;
    counter++; //keeps track as to how many integers were created
    /* get next value */
    scanf("%d", &val);
  }

  int *copy_darr; //new array that will hold all values
  copy_darr = (int *) malloc(allocated * sizeof(int)); //allocating memeory for new array to store copied values

 /* call function to make a copy of the array of values */
 makeArrayCopy(darr, copy_darr, counter);

 /* call function to sort one of the arrays */
 sortAscending(copy_darr, counter);

  int numCompares =0;
  //int* pNumCompares = &numCompares;
 /* loop until the user enters -999 */
 printf ("Enter in a list of numbers to use for searching.  \n");
 printf ("End the list with a terminal value of -999\n");
 scanf ("%d", &val);

 while (val != -999)
   {
    /* call function to use the value in a linear search on the unsorted array */

    /* print out info about the linear search results  */
    printf ("Linear search value: %d ", val);
    printf("index: %d ", linSearch(darr, counter, val, &numCompares));
    printf("numCompares: %d\n", numCompares);

    /* call function to use the value in a binary search on the sorted array */

    /* print out info about the binary search results  */
    printf("Binary search value: %d ", val);
    printf("index: %d ", binSearch(copy_darr, 0, counter-1, val, &numCompares));
    printf("numCompares: %d\n\n", numCompares);

    /* get next value */
    scanf("%d", &val);
   }


 printf ("Good bye\n");
 return 0;
} 

//makes copy of a certain array
void makeArrayCopy(int fromArray[], int toArray[], int size)
{
  int i;
  for(i = 0; i < size; i++) 
  {
      toArray[i] = fromArray[i];
  }
  return;
}

//sorts the array in ascending order
void sortAscending(int arr[], int size)
{
  int i, j;
  for (i = 0; i < size; ++i) 
  {
      for (j = i + 1; j < size; ++j)
      {
          if (arr[i] > arr[j]) 
          {
              int a =  arr[i];
              arr[i] = arr[j];
              arr[j] = a;
          }

      }

  }
}

//performs linear search and returns two values:
//1. the index where the value was found or -1 if not found
//2. number of comparisons needed to determine if/where the value is located    
int linSearch(int arr[], int size, int target, int* numComparisons)
{
  int i;
  *numComparisons = 0;
  for(i = 0; i < size; i++)
  {
    (*numComparisons)++;
    if(arr[i] == target)
    {
      return i;
    }
  }
  return -1;

}

//performs binary search and returns two values: 
//1. the index where the value was found or -1 if not found
//2. number of comparisons needed to determine if/where the value is located  
int binSearch(int arr[], int low, int size, int target, int* numComparisons)
{
    *numComparisons = 0;    
  while (low <= size) 
  { 
        int m = low + (size - low) / 2; 

        (*numComparisons)++;
        // Check if x is present at mid 
        if (arr[m] == target) 
            return m; 
  
        // If x greater, ignore left half 
        if (arr[m] < target) 
            low = m + 1; 
  
        // If x is smaller, ignore right half 
        else
            size = m - 1; 
    } 
  
    // if we reach here, then element was 
    // not present 
    return -1; 
}
