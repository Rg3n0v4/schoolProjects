
#include <string>
#include <iostream>
#include <sstream>
#include <fstream>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

void ssort(std::string &s) {

  /*
     strings are "iterable" objects and so have the begin()
     and end() functions.  These functions (in a pretty
     abstract way) return "iterators" which specify the
     "beginning" and "end" of the associated object).

     Thus, this call is asking the sort function to sort
     the entire string s.
  */
  std::sort(s.begin(), s.end());
}

int main(int argc, char *argv[]){
  /*
     the variable word2freq is an unordered_map
        from strings to integers.
  */
  std::unordered_map<std::string, vector<string>> mapOfWords;
  std::ifstream file;
  std::string word;
  int numOfWords = 0;
  int numOfEqu = 0;
  int sizeOfLargeEqu = 0;
  string keyOfLargEqu = "";

  /*
  *  argv[1] is a C-string which is the filname specified
  *    by the user.  Let's try to open it.
  */

  file.open(argv[1], std::ios::in);

  if(!file.is_open())
  {
    std::cout << "Error: could not open file '" <<
                     argv[1] << "'\n";
    std::cout << "goodbye\n";
    return 1;
  }

  std::cout << "reading input file...\n";

  while(file >> word) 
  { 
    string temp = word;
    ssort(word);
    if(mapOfWords.count(word) == 0)
    {
        numOfEqu++;
    }
    mapOfWords[word].push_back(temp); //key of the unordered map
    if(mapOfWords[word].size() > sizeOfLargeEqu )
    {
        sizeOfLargeEqu = mapOfWords[word].size();
        keyOfLargEqu = word;
    }
    numOfWords++;
  }
    std::cout << 
    "enter a word and I will tell you what I know about it\n";
    std::cout << 
    "  when you are done, type ctrl-d (ctrl-z on windows)\n\n";
    std::cout << "> ";


  while(std::cin >> word) 
  {
    
    ssort(word);

    if(mapOfWords.count(word) == 0)
    {
        cout << "no anagrams found...try again" << endl;
    }
    else
    {
        cout << "English Anagrams Found: " << endl;
    }

    for(int i = 0; i < mapOfWords[word].size(); i++)
    {
        cout << "   " << mapOfWords[word].at(i) << endl;
    }
    std::cout << "> ";
  }

  cout << "REPORT: "<<endl;
  cout << "\n numOfWords: " << numOfWords << endl;
  cout << " numOfEqu: " << numOfEqu << endl;
  cout << " sizeOfLargeEqu: " << sizeOfLargeEqu << endl;
  cout << " keyOfLargeEqu: " << keyOfLargEqu << endl;
  cout << " members of largest class: " << endl << endl;

  for(int i = 0; i < mapOfWords[keyOfLargEqu].size(); i++)
  {
      cout << " '" << mapOfWords[keyOfLargEqu].at(i) << "' " << endl;
  }
  return 0;
}
