#include <stdio.h>

int main (int argc, char** argv)
{
 int val;

 /* prompt the user for input */
 printf ("Enter in a list of numbers ito be stored in a dynamic array.\n");
 printf ("End the list with the terminal value of -999\n");
 
 /* loop until the user enters -999 */
 scanf ("%d", &val);
 while (val != -999)
   {
    /* store the value into an array */

    /* get next value */
    scanf("%d", &val);
   }

 /* call function to make a copy of the array of values */

 /* call function to sort one of the arrays */

 /* loop until the user enters -999 */
 printf ("Enter in a list of numbers to use for searching.  \n");
 printf ("End the list with a terminal value of -999\n");
 scanf ("%d", &val);
 while (val != -999)
   {
    /* call function to use the value in a linear search on the unsorted array */

    /* print out info about the linear search results  */

    /* call function to use the value in a binary search on the sorted array */

    /* print out info about the binary search results  */

    /* get next value */
    scanf("%d", &val);
   }


 printf ("Good bye\n");
 return 0;
} 
