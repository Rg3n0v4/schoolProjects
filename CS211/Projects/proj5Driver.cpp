#include "proj5Header.h"

bool debugMode = false;

int main(int argc, char *argv[])
{
    /***************************************************************/
    /* Add code for checking command line arguments for debug mode */
    for(int i = 0; i< argc; i++)
    {
        if(strcmp(argv[i], "-d")==0)
        {
            debugMode = true;
        }
    }

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
      if(debugMode == true)
      {
        printf ("Val: %d, ", inputToken.getValue() );
      }

      valueStack.push(inputToken); //pushes the inputToken onto the valueStack 
    }

    /* The expression contains an OPERATOR */
    else if (inputToken.equalsType(OPERATOR))
      {
       /* make this a debugMode statement */
       if(debugMode == true)
       {
        printf ("OP: %c, ", inputToken.getOperator() );
       }
       
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
            operatorStack.pop();

            int v2 = valueStack.top().getValue();
            valueStack.pop();

            int v1 = valueStack.top().getValue();
            valueStack.pop();

            Token newToken =*  new Token();
            int v3;
            v3 = popAndEval( v1, op, v2);
            
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
