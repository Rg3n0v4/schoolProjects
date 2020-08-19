/**************************************************************/
/*                                                            */
/*  The Code below this point should NOT need to be modified  */
/*  for this program.   If you feel you must modify the code  */
/*  below this point, you are probably trying to solve a      */
/*  more difficult problem that you are being asked to solve  */
/*                                                            */
/**************************************************************/

#include <cstdio>
#include <cstring>
#include <cctype>
#include <iostream>

using namespace std;

// Enumarated Type specifying all of the Tokens
enum TokenType{
  ERROR, OPERATOR, VALUE, EOLN, QUIT, HELP, EOFILE
};

void printCommands()
{
 printf ("The commands for this program are:\n\n");
 printf ("q - to quit the program\n");
 printf ("? - to list the accepted commands\n");
 printf ("or any infix mathematical expression using operators of (), *, /, +, -\n");
}



// Class to hold the Token information
class Token
{
  private:
    TokenType type;
    char      op;       // using '$' as undefined/error
    int       val;      // using -999 as undefined/error

  public:

  // Default to initialize to the ERROR TokenType
  Token()
  {
    type = ERROR;
    op = '$';
    val = -999;
  }

  // Initialize to a specific TokenType
  Token (TokenType t)
  {
    type = t;
    op = '$';
    val = -999;
  }

  // Set to a specific TokenType
  void setToType(TokenType t)
  {
    type = t;
    op = '$';
    val = -999;
  }

  // Set to a OPERATOR TokenType with specific operator value
  void setToOperator(char c)
  {
    type = OPERATOR;
    op = c;
    val = -999;
  }

  // Set to a VALUE TokenType with a specific numeric value
  void setToValue(int v)
  {
    type = VALUE;
    op = '$';
    val = v;
  }

  // return true if the Current Token is of the given TokenType
  bool equalsType(TokenType t)
  {
    if (type == t)
      return true;
    else
      return false;
  }

  // return true if the Current Token is of the OPERATOR TokenType
  //     and contains the given operator character
  bool equalsOperator(char c)
  {
    if (type == OPERATOR && op == c)
      return true;
    else
      return false;
  }

  // Return the Operator for the current Token
  //   verify the current Token is of the OPERATOR TokenType
  char getOperator ()
  {
    if (type == OPERATOR)
      return op;
    else
      return '$';   // using $ to indicate an error value
  }

  // Return the Value for the current Token
  //   verify the current token is of the value TokenType
  int getValue()
  {
    if (type == VALUE)
      return val;
    else
      return -999;  // using -999 to indicate an error value
  }
};


class TokenReader
{
  private:
     char inputline[300];  // this assumes that all input lines are 300 characters or less in length
     bool needline;
     int pos;


  public:

  // initialize the TokenReader class to read from Standard Input
  TokenReader()
  {
    // set to read from Standard Input
    inputline[0] = '\0';
    pos = 0;
    needline = true;
  }

  // Force the next getNextToken to read in a line of input
  void clearToEoln()
  {
    needline = true;
  }

  // Return the next Token from the input line
  Token getNextToken()
  {
    char* endCheck;

    //printf ("getToken %d, %d, %s\n", pos, needline, inputline);

    // get a new line of input from user
    if (needline)
    {
      endCheck = fgets ( inputline, 300, stdin);

      if (endCheck == NULL )
      {
        printf ("Error in reading");
        Token t(EOFILE);
        return t;
      }

      for (int i = 0 ; i < strlen(inputline) ; i++)
          if ('\n' == inputline[i])
              inputline[i] = ' ';
      strcat (inputline , " ");    // add a space at end to help deal with digit calculation
      needline = false;
      pos = 0;
    }

    // skip over any white space characters in the beginning of the input
    while ( pos < strlen(inputline) && isspace(inputline[pos]) )
    {
      pos++;
    }

    // check for the end of the current line of input
    if (pos >= strlen(inputline))
    { // at end of line
      needline = true;
      Token t(EOLN);
      return t;
    }

    // Get the next character from the input line
    char ch = inputline[pos]; pos++;

    // check if 'q' or 'Q' was entered ==> QUIT Token
    if ( 'q' == ch || 'Q' == ch )
    {
      return Token (QUIT);
    }

    // check if "?" was entered ==> HELP Token
    if ( '?' == ch )
    {
      return Token (HELP);
    }

    // check for Operator values of: + - * / ( )  ==> OPERATOR Token
    if ( ('+' == ch) || ('-' == ch) || ('*' == ch) ||
      ('/' == ch) || ('(' == ch) || (')' == ch) )
    {
      Token t;
      t.setToOperator( ch );
      return t;
    }

    // check for a number  ==> VALUE Token
    if (isdigit(ch))
    {
      int number = int (ch) - int('0');  // subtract ascii value of ch from ascii value of '0'
      ch = inputline[pos]; pos++;
      while (isdigit(ch))
      {
        number = number * 10 + int (ch) - int('0');
        ch = inputline[pos]; pos++;
      }
      pos--; // since number calcuation check one character after the last digit
      Token t;
      t.setToValue( number );
      return t;
    }

    // Input in not valid if code get to this part ==> ERROR Token
    return Token (ERROR);
  }

};

void processExpression (Token inputToken, TokenReader *tr);

class Stack {
    private:
      int usedSize;
      int maxSize;
      Token* arr;

    public: 

    void init()//  initialize the stack for use THIS MAY BE A CONSTRUCTOR
    {
      arr =  new Token[2];
      maxSize = 2;
      usedSize = -1;
    }
    bool isEmpty ()   // return true if the stack has no members
    {
      return usedSize == -1;
    }
    void push (Token data) //add the data to the top of stack; grow dynamic array if needed
    {
      //if the list is full then allocate more memory
      if(usedSize == maxSize)
      {
        Token* temp = arr;
        arr = new Token[maxSize+2];
        for(int i = 0; i < usedSize; i++)
        {
          arr[i] = temp[i];
        }

        delete[] arr;
        maxSize += 2;
      }
      arr[++usedSize] = data;
      if(arr[usedSize].equalsType(OPERATOR))
      {
        cout << "Inserting: " << arr[usedSize].getOperator() << endl;
      }
      else
      {
        cout << "Inserting: " << arr[usedSize].getValue() << endl;
      }
      
    }
    Token top ( ) // return the data value on the top of the stack
    {
      if(!isEmpty())
      {
        return arr[usedSize];
      }
      else
      {
        cout<<"exiting"<<endl;
        //exit(EXIT_FAILURE);
      }
      
      
    }
    void pop ( )// remove the data value from the top of the stack
    {
      usedSize--;
    }
    void reset ( )// “clear” the stack to remove any values it contains 
    {
      delete[] arr;
      Token* newArr = new Token[2];
      arr = newArr;
      usedSize = -1;
      maxSize = 2;
    }
};



    /**************************************************************/
    /*                                                            */
    /*  The Code above this point should NOT need to be modified  */
    /*  for this program.   If you feel you must modify the code  */
    /*  below this point, you are probably trying to solve a      */
    /*  more difficult problem that you are being asked to solve  */
    /*                                                            */
    /**************************************************************/


int main(int argc, char *argv[])
{
    /***************************************************************/
    /* Add code for checking command line arguments for debug mode */


    Token inputToken;
    TokenReader tr;

    printf ("Starting Expression Evaluation Program\n\n");
    printf ("Enter Expression: ");


    inputToken = tr.getNextToken ();

    while (inputToken.equalsType(QUIT) == false)
    {
      /* check first Token on Line of input */
      if(inputToken.equalsType(HELP))
      {
       printCommands();
       tr.clearToEoln();
      }
      else if(inputToken.equalsType(ERROR))
      {
       printf ("Invalid Input - For a list of valid commands, type ?\n");
       tr.clearToEoln();
      }
      else if(inputToken.equalsType(EOLN))
      {
       printf ("Blank Line - Do Nothing\n");
       /* blank line - do nothing */
      }
      else
      {
       processExpression(inputToken, &tr);
      }

      printf ("\nEnter Expression: ");
      inputToken = tr.getNextToken ();
    }

  printf ("Quitting Program\n");
  return 1;
}

//adds or subtracts based on the op
int popAndEval(int value1, Token op, int value2)
{
  if(op.getOperator() == '+')
  {
    return value1 + value2;
  }

  if(op.getOperator() == '-')
  {
    return value1 - value2;
  }
  
  if(op.getOperator() == '*')
  {
    return value1 * value2;
  }

  if(op.getOperator() == '/')
  {
    return value1 / value2;
  }
}


void processExpression (Token inputToken, TokenReader *tr)
{
 /**********************************************/
 /* Declare both stack head pointers here      */

  Stack operatorStack =* new Stack(); //for all the math operations of the tokens
  Stack valueStack =* new Stack(); //for all the numbers in the tokens
  operatorStack.init();
  valueStack.init();

 /* Loop until the expression reaches its End */
 while (inputToken.equalsType(EOLN) == false)
   {
    /* The expression contain a VALUE */
    if (inputToken.equalsType(VALUE))
    {
      /* make this a debugMode statement */
      printf ("Val: %d, ", inputToken.getValue() );

      valueStack.push(inputToken); //pushes the inputToken onto the valueStack 
    }

    /* The expression contains an OPERATOR */
    else if (inputToken.equalsType(OPERATOR))
      {
       /* make this a debugMode statement */
       printf ("OP: %c, ", inputToken.getOperator() );
       
       //pushes ( onto the stack if '(' exists
        if(inputToken.getOperator() == '(')
        {
          operatorStack.push(inputToken);
        }
       
       //pushes + or - onto the stack if '+' or '-' exists 
        if(inputToken.getOperator() == '+' || inputToken.getOperator() == '-')
        {
          while(operatorStack.isEmpty() == false &&
                (operatorStack.top().getOperator() == '+' || operatorStack.top().getOperator() == '-' || 
                 operatorStack.top().getOperator() == '*' || operatorStack.top().getOperator() == '/'))
          {
            Token op = operatorStack.top();
            operatorStack.pop();

            int v2 = valueStack.top().getValue();
            valueStack.pop();

            int v1 = valueStack.top().getValue();
            valueStack.pop();

            Token sumOrDiff =*  new Token();
            int v3 = popAndEval( v1, op, v2);
            sumOrDiff.setToValue(v3);
            valueStack.push(sumOrDiff);
            cout << "Result " << v3 << endl;
          }
          operatorStack.push(inputToken);
        }
       
       //pushes * or / onto the stack if '*' or '/' if they exist
        if(inputToken.getOperator() == '*' || inputToken.getOperator() == '/')
        {
          while(operatorStack.isEmpty() == false && 
                (operatorStack.top().getOperator() == '*' || operatorStack.top().getOperator() == '/'))
          {
            Token op = operatorStack.top();
            operatorStack.pop();

            int v2 = valueStack.top().getValue();
            valueStack.pop();

            int v1 = valueStack.top().getValue();
            valueStack.pop();

            Token prodOrQuo =*  new Token();
            int v3 = popAndEval( v1, op, v2);
            prodOrQuo.setToValue(v3);
            valueStack.push(prodOrQuo);
            cout << "Result " << v3 << endl;
          }
          operatorStack.push(inputToken);
        }

       //pushes ) onto the stack if ')' exists
        if(inputToken.getOperator() == ')')
        {
          while(operatorStack.isEmpty() == false && operatorStack.top().getOperator() != '(')
          {
            Token op = operatorStack.top();
            cout<<"op: " << op.getOperator() << endl;
            operatorStack.pop();

            int v2 = valueStack.top().getValue();
            valueStack.pop();

            int v1 = valueStack.top().getValue();
            valueStack.pop();

            Token newToken =*  new Token();
            int v3;
            v3 = popAndEval( v1, op, v2);
            cout << "v3: " << v3 << endl;
            
            newToken.setToValue(v3);
            valueStack.push(newToken);
          }
          //check if operatorStack is empty
          if(operatorStack.isEmpty() == true)
          {
            cout << "ERROR -- operatorStack is empty" << endl;
          }
          else
          {
            operatorStack.pop();
          }
        }//end of if statement finding )
        
      }//end of else if statement inputToken.getOperator()

    /* get next token from input */
    inputToken = tr->getNextToken ();
   }

 /* The expression has reached its end */

while(operatorStack.isEmpty() == false)
    {
      Token op = operatorStack.top();
      operatorStack.pop();

      int v2 = valueStack.top().getValue();
      valueStack.pop();

      int v1 = valueStack.top().getValue();
      valueStack.pop();

      int v3;
      if(op.getOperator() == '+' || op.getOperator() == '-')
      {
        Token sumOrDiff =*  new Token();
        v3 = popAndEval( v1, op, v2);
        sumOrDiff.setToValue(v3);
        valueStack.push(sumOrDiff);
      }
      if(op.getOperator() == '*' || op.getOperator() == '/')
      {
        Token prodOrQuo =*  new Token();
        v3 = popAndEval( v1, op, v2);
        prodOrQuo.setToValue(v3);
        valueStack.push(prodOrQuo);
      }
      Token temp = valueStack.top();
      valueStack.pop();
      cout << "Result: " << temp.getValue() << endl;
    }


 printf ("\n");
}

