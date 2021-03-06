#ifndef _BST_H
#define _BST_H

#include <iostream>
#include <vector>
#include <algorithm>

template <typename T>
class bst {

  private: 
    struct bst_node {
      T      val;
      bst_node *left;
      bst_node *right;

      //to know how many children each side has which makes it easier to do
      int leftVal;
      int rightVal;

      bst_node ( const T & _val = T{}, bst_node * l = nullptr, bst_node *r = nullptr)
        : val { _val },  left { l }, right {r} 
      { leftVal = 0; rightVal = 0; }
    };



  public:
    // constructor:  initializes an empty tree
    bst(){
      root = nullptr;
    }

  private:
    // helper function which recursively deallocates nodes
    //   in a tree.
    static void delete_nodes(bst_node *r){
      if(r==nullptr) return;
      delete_nodes(r->left);
      delete_nodes(r->right);
      delete r;
    }

  public:
    // destructor
    ~bst() {
      delete_nodes(root);
    }

  private:
  
  static bst_node *leftRotate(bst_node *x)  
  {  
      bst_node *y = x->right;  
      bst_node *T2 = y->left;  
    
      y->left = x;  
      x->right = T2;  
      return y;  
  }
  static bst_node *rightRotate(bst_node *y)  
  {  
    bst_node *x = y->left;  
    bst_node *T2 = x->right;  
    
    x->right = y;  
    y->left = T2;  

    return x;  
  }  
  static bool isBalanced(bst_node* node)
    {
        return std::max(node->rightVal, node->leftVal) <= (2 * std::min(node->rightVal, node->leftVal)) + 1;
    }
/**
 * function:  insert()
 * desc:      recursive helper function inserting x into
 *            binary search tree rooted  at r.
 *
 * returns:   pointer to root of tree after insertion.
 *
 * notes:     if x is already in tree, no modifications are made.
 */
    static bst_node * _insert(bst_node *r, T & x, bool &success){
      bool right;
      if(r == nullptr){
        success = true;
        return new bst_node(x, nullptr, nullptr);
      }

      if(r->val == x){
        success = false;
        return r;
      }
      if(x < r->val){
        r->left = _insert(r->left, x, success);
        if(success == true)
        {
          right = false;
          r->leftVal++;
        }
        //return r;
      }
      else {
        r->right = _insert(r->right, x, success);
        if(success == true)
        {
          right = true;
          r->rightVal++;
        }
        //return r;
      }
      
      if(isBalanced(r) == false)
      {
        if(right == false)
        {
          r = leftRotate(r);
        }
        else
        {
          r = rightRotate(r);
        }
      }
      return r;
    }


  public:
  /**
   * function:  insert
   * desc:      inserts x into BST given by t.  Note that
   *            a BST stores a SET -- no duplicates.  Thus,
   *            if x is already in t when call made, no 
   *            modifications to tree result.
   *
   * note:      helper function does most of the work.
   *
   */
   bool insert(T & x){
      bool success;
      root = _insert(root, x, success);
      return success;
   }

/**
 * function:  contains()
 * desc:      returns true or false depending on whether x is an
 *            element of BST (calling object)
 *
 */
    bool contains(const T & x){
      bst_node *p = root;

      while(p != nullptr){

        if(p->val == x)
          return true;
        if(x < p->val){
          p = p->left;
        }
        else
          p = p->right;
      }
      return false;  
    }

  private:
    // returns pointer to node containing
    //   smallest value in tree rooted at r
    static bst_node * _min_node(bst_node *r ){
      if(r==nullptr)
        return nullptr; // should never happen!
      while(r->left != nullptr)
        r = r->left;
      return r;
    }

    // returns pointer to node containing
    //   biggest value in tree rooted at r
    static bst_node * _max_node(bst_node *r ){
      if(r==nullptr)
        return nullptr; // should never happen!
      while(r->right != nullptr)
        r = r->right;
      return r;
    }

    // recursive helper function for node removal
    //   returns root of resulting tree after removal.
    static bst_node * _remove(bst_node *r, T & x, bool &success){
      bst_node *tmp;
      bool sanity;

      if(r==nullptr){
        success = false;
        return nullptr;
      }
      if(r->val == x){
        success = true;

        if(r->left == nullptr){
          tmp = r->right;
          delete r;
          return tmp;
        }
        if(r->right == nullptr){
          tmp = r->left;
          delete r;
          return tmp;
        }
        // if we get here, r has two children
        r->val = _min_node(r->right)->val;
        r->right = _remove(r->right, r->val, sanity);
        if(!sanity)
          std::cerr << "ERROR:  remove() failed to delete promoted value?\n";
        return r;
      }
      if(x < r->val){
        r->left = _remove(r->left, x, success);
      }
      else {
        r->right = _remove(r->right, x, success);
      }
      return r;

    }

  public:

    bool remove(T & x){
      bool success;
      root = _remove(root, x, success);
      return success;
    }


  private:
    // recursive helper function to compute size of
    //   tree rooted at r
    static int _size(bst_node *r){
      if(r==nullptr) return 0;
      return _size(r->left) + _size(r->right) + 1;
    }

  public: 
    int size() {
      return _size(root);
    }

  private:

    static int _height(bst_node *r){
      int l_h, r_h;

      if(r==nullptr) return -1;
      l_h = _height(r->left);
      r_h = _height(r->right);
      return 1 + (l_h > r_h ? l_h : r_h);
    }

  public:

    int height() {
      return _height(root);
    }

    bool min(T & answer) {
      if(root == nullptr){
        return false;
      }
      answer = _min_node(root)->val;
      return true;
    }

    T max() {
      return _max_node(root)->val;
    }

    /******************************************
     *
     * "stubs" for assigned TODO functions below 
     *
     *****************************************/

    /* TODO
     * Function:  to_vector
     * Description:  allocates a vector of type T and populates
     *               it with the elements of the tree in sorted
     *               order.  A pointer to the vector is returned.
     *
     * runtime:  O(n) where n is the tree size.
     *
     */
    std::vector<T> * to_vector() 
    {
      std::vector<T> *order = new std::vector<T>;
      in_order(root, order);
      return order;
    }

    private:
    //helper function for going down the tree
     void in_order(bst_node *root, std::vector<T> *order)
     {
       if(root == NULL)
       {
         return;
       }
       in_order(root->left, order);
       order->push_back(root->val);
       in_order(root->right, order);

       return;
     }

  public:

    /* TODO
     * Function:  get_ith
     * Description:  determines the ith smallest element in t and
     *    "passes it back" to the caller via the reference parameter x.  
     *    i ranges from 1..n where n is the number of elements in the 
     *    tree.
     *
     *    If i is outside this range, false is returned.
     *    Otherwise, true is returned (indicating "success").
     *
     * Runtime:  O(h) where h is the tree height
     */
    bool get_ith(int i, T &x) 
    {
      bst_node *currentRoot = root;
      if(currentRoot == NULL){
        return false;
      }

      int currentIndex = -1; //initializes the current index and will be updated later in the loop

      if((currentRoot->leftVal) + (currentRoot->rightVal) + 1 < i || i < 1)
      {
        return false;
      }
      
      currentIndex = (currentRoot->leftVal) + 1;

      //while the current index doesn't equal i, keep looking for it 
      //currentIndex is being changed on the inside of the loop
      while(currentIndex != i)
      {
        int currentLeftIndex = currentIndex - (currentRoot->leftVal) + (currentRoot->left->leftVal);
        int currentRightIndex = (currentRoot->rightVal) + currentIndex - (currentRoot->right->rightVal);

        //if the left index of the current root is < the i, 
        //the left index of the current root will be the new current root
        if(currentIndex < i)
        {
          currentRoot = currentRoot->right; 
          currentIndex = currentRightIndex;
        }
        //if the right index of the current root is < the i, 
        //the right index of the current root will be the new current root
        else
        {
          currentRoot = currentRoot->left;
          currentIndex = currentLeftIndex;
        }
      }

      x = currentRoot->val;
      return true; //returns true once the ith value is found
    }


    /* 
     * get_ith_SLOW:  slow version of get_ith which gives the right answer, but
     *   does so by "walking the entire tree".
     * might be helpful for debugging?
     * Private helper below does most of the work
     */
    bool get_ith_SLOW(int i, T &x) {
      int n = size();
      int sofar=0;

      if(i < 1 || i > n) 
        return false;

      _get_ith_SLOW(root, i, x, sofar);
      return true;
    }

  private:
    // recursive helper function that does most of the work
    static void _get_ith_SLOW(bst_node *t, int i, T &x, int &sofar) {
      if(t==nullptr) 
        return;
      _get_ith_SLOW(t->left, i, x, sofar);

      if(sofar==i) 
        return;
      sofar++;
      if(sofar==i) {
        x = t->val;
        return;
      }
      _get_ith_SLOW(t->right, i, x, sofar);
    }

  public:

    /* TODO
     * Function: position_of 
     * Description:  this is like the inverse of
     *       get_ith:  given a value x, determine the 
     *       position ("i") where x would appear in a
     *       sorted list of the elements and return
     *       the position as an integer.
     *       If x is not in the tree, -1 is returned.
     *       Examples:
     *           if x happens to be the minimum, 1 is returned
     *           if x happens to be the maximum, n is returned where
     *               n is the tree size.                  
     *                  
     *       Notice the following property:  for a bst t with n nodes,                  
     *           pick an integer pos:1 <= pos <= n.                  
     *           Now consider the following:
     *       
                    T x;
                    int pos, pos2;
                    // set pos to a value in {1..n}
                    t.get_ith(pos, x);  // must return true since pos is in {1..n}

                    // now let's find the position of x (just retrieved) 
                    pos2 = t.position_of(x);
                    if(pos != pos2) {
                        std::cout << "THERE MUST BE A BUG!\n";
                    }

                 See how position_of performs the inverse operation of get_ith?
     *       
     * Return:  -1 if x is not in the tree; otherwise, returns the position where x 
     *          would appear in the sorted sequence of the elements of the tree (a
     *          value in {1..n}          
     *
     * Runtime:  O(h) where h is the tree height
     */
    int position_of(const T & x) 
    {
      bst_node *currentRoot = root;
      if(currentRoot == NULL){
        return -1;
      }

      int currentIndex = -1; //initializes the current index and will be updated later in the loop

      //need to consider if where x is being put in is null
      
      currentIndex = (currentRoot->leftVal) + 1;

      //while the current index doesn't equal i, keep looking for it 
      //currentIndex is being changed on the inside of the loop
      while(currentIndex != (x->leftVal) + 1)
      {
        int currentLeftIndex = currentIndex - (currentRoot->leftVal) + (currentRoot->left->leftVal);
        int currentRightIndex = (currentRoot->rightVal) + currentIndex - (currentRoot->right->rightVal);

        //if the current index is < i then the current root will now be the right child of that root
        if(currentIndex < 0)
        {
          if(currentRoot->leftVal == 0 && currentRoot->rightVal == 0)
          //the current root IS the smallest value in the tree and it's the end of the tree
          {
            return 1; //we're at the minimum value
          }
          else
          //the current root isn't an end node
          {
            currentRoot = currentRoot->right; 
            currentIndex = currentRightIndex;
          }
        }
        //if the current index is > the i, the current root will now be the left child of that root
        else
        {
          if(currentRoot->leftVal == 0 && currentRoot->rightVal == 0)
          //the current root IS the biggest value in the tree and it's the end of the tree
          {
            return (root->leftVal) + (root->rightVal) + 1; //we're at the maximum value
          }
          currentRoot = currentRoot->left;
          currentIndex = currentLeftIndex;
        }
      }

      return currentIndex;
    }

    /* TODO
     * Function:  num_geq
     * Description:  returns the number of elements in tree which are 
     *       greater than or equal to x.
     *
     * Runtime:  O(h) where h is the tree height
     */
    int num_geq(const T & x) 
    {
      //int counter = num_geq_SLOW(x);

      bst_node* currentNode = root;
      int counter = 0;

      // counting all the values that are higher than the specified value as you go down the tree
      // and then stop once you get to the bottom of the tree
      while(currentNode != NULL)
      {
        if(currentNode->val < x)
        {
          currentNode = currentNode->right;
        }
        else if(currentNode->val >= x)
        {
          counter += currentNode->rightVal + 1;
          currentNode = currentNode->left;
        }
      }

      return counter;  //returns the number of elements that are greater or equal
    }

    /*
     * function:     num_geq_SLOW
     * description:  same functionality as num_geq but sloooow (linear time)
     *                (private helper does most of the work)
     *
     */
    int num_geq_SLOW(const T & x) {
      return _num_geq_SLOW(root, x);
    }

  private:
    static int _num_geq_SLOW(bst_node * t, const T & x) {
      int total;

      if(t==nullptr) return 0;
      total =_num_geq_SLOW(t->left, x) + _num_geq_SLOW(t->right, x);

      if(t->val >= x)
        total++;
      return total;
    }
    
  public:

    /* TODO
     * Function:  num_leq
     * Description:  returns the number of elements in tree which are less
     *      than or equal to x.
     *
     * Runtime:  O(h) where h is the tree height
     *
     **/
    int num_leq(const T &x) 
    {
      //int counter = num_leq_SLOW(x);
      bst_node* currentNode = root;
      int counter = 0;

      //counting all the values that are less than the specified value as you go down the tree
      //and then stop once you get to the bottom of the tree
      while(currentNode != NULL)
      {
        if(currentNode->val > x)
        {
          currentNode = currentNode->left;
        }
        else if(currentNode->val <= x)
        {
          counter += currentNode->leftVal + 1;
          currentNode = currentNode->right;
        }
      }

      return counter;  //returns the number of elements that are greater or equal
    }

    /*
     * function:     num_leq_SLOW
     * description:  same functionality as num_leq but sloooow (linear time)
     *               (private helper _num_leq_SLOW does most of the work)
     */
    int num_leq_SLOW(const T & x) {
      return _num_leq_SLOW(root, x);
    }
  private:

    // helper for num_leq_SLOW
    static int _num_leq_SLOW(bst_node *t, const T &x) {
      int total;

      if(t==nullptr) return 0;
      total =_num_leq_SLOW(t->left, x) + _num_leq_SLOW(t->right, x);

      if(t->val <= x)
        total++;
      return total;
    }

  public:

    /* TODO
     * Function:  num_range
     * Description:  returns the number of elements in tree which are
     *       between min and max (inclusive).
     *
     * Runtime:  O(h) where h is the tree height
     *
     **/
    int num_range(const T & min, const T & max) 
    {
      int minCounter = num_leq(min);
      int maxCounter = num_leq(max);
      return maxCounter - minCounter;
    }

    /*
     * function:     num_range_SLOW
     * description:  same functionality as num_range but sloooow (linear time)
     *               (private helper _num_range_SLOW does most of the work)
     *
     */
    int num_range_SLOW(const T & min, const T & max) {
      return _num_range_SLOW(root, min, max);
    }

  private:
    static int _num_range_SLOW(bst_node *t, const T &min, const T &max) {
      int total;

      if(t==nullptr) return 0;
      total =_num_range_SLOW(t->left, min, max) + 
                _num_range_SLOW(t->right, min, max);

      if(t->val >= min && t->val <= max)
        total++;
      return total;
    }

  public:

    /*
     * TODO
     * function:     extract_range
     * Description:  allocates a vector of element type T
     *               and populates it with the tree elements
     *               between min and max (inclusive) in order.  
     *               A pointer to the allocated and populated
     *               is returned.
     *
     * notes/comments:  even if the specified range is empty, a
     *                  vector is still allocated and returned;
     *                  that vector just happens to be empty.
     *                  (The function NEVER returns nullptr).
     *
     * runtime:  the runtime requirement is "output dependent".
     *           Let k be the number of elements in the specified range
     *           (and so the length of the resulting vector) and let h
     *           be the height of the tree.  The runtime must be:
     *
     *                  O(h + k)
     *
     *           So...while k can be as large as n, it can be as small
     *           as zero.  
     *
     */
    std::vector<T> * extract_range(const T & min, const T & max) 
    {
      bst_node* currentNode = root;
      std::vector<T>* orderOfVal = new vector<T>;

      //contiously loops through until the end of the tree is reached
      while(currentNode != nullptr)
      {
        //moves the current node if the value of the node is < min or > max.
        if(currentNode->val < min)
        {
          currentNode = currentNode->left;
        }
        else if(currentNode->val > max)
        {
          currentNode = currentNode->right;
        }
        
        //pushes onto the list of values if the current node fits within the given range
        if(currentNode->val >= min && currentNode->val <= max)
        {
          orderOfVal.push_back(currentNode->val);
        }
      }

      return orderOfVal;
    }

  /***************************************************
  *  PREORDER AND INORDER TREE TRAVERSALS BELOW      * 
  *                                                  * 
  ***************************************************/

  private:
    static void indent(int m){
      int i;
      for(i=0; i<m; i++)
        std::cout << "-";
    }

    static void _inorder(bst_node *r){
      if(r==nullptr) return;
      _inorder(r->left);
      std::cout << "[ " << r->val << " ]\n";
      _inorder(r->right);
    }

    static void _preorder(bst_node *r, int margin){
      if(r==nullptr) {
        indent(margin);
        std::cout << " nullptr \n";
      } 
      else {
        indent(margin);
        std::cout << "[ " << r->val << " ]\n";
        _preorder(r->left, margin+3);
        _preorder(r->right, margin+3);
      }
    }

  public:
    void inorder() {
      std::cout << "\n======== BEGIN INORDER ============\n";
      _inorder(root);
      std::cout << "\n========  END INORDER  ============\n";
    }


    void preorder() {

      std::cout << "\n======== BEGIN PREORDER ============\n";
      _preorder(root, 0);
      std::cout << "\n========  END PREORDER  ============\n";

    }

  /***************************************************
  *  END PREORDER AND INORDER TREE TRAVERSALS        * 
  *                                                  * 
  ***************************************************/

  private:
    /* 
     * Recursive  helper function _from_vec, used by
     * from_sorted_arr(...). The function must return a sub-tree that is
     * perfectly balanced, given a sorted array of elements a.
     */
    static bst_node * _from_vec(const std::vector<T> &a, int low, int hi){
      int m;
      bst_node *root;

      if(hi < low) return nullptr;
      m = (low+hi)/2;
      root = new bst_node(a[m]);
      root->left  = _from_vec(a, low, m-1);
      root->right = _from_vec(a, m+1, hi);
      return root;

    }

  public:
    static bst * from_sorted_vec(const std::vector<T> &a, int n){

      bst * t = new bst();
      t->root = _from_vec(a, 0, n-1);
      return t;
    }

  private:

    /*
     * DO NOT MODIFY THE TWO FUNCTIONS BELOW!!!
     */
    static void _to_preorder_vec(bst_node *t, std::vector<T> &vec) {
      if(t==nullptr) return;
      vec.push_back(t->val);
      _to_preorder_vec(t->left, vec);
      _to_preorder_vec(t->right, vec);
    }
    std::vector<T> * to_preorder_vec() {
      std::vector<T> *vec = new std::vector<T>();
      _to_preorder_vec(root, *vec);
      return vec;
    }

  /*
   * Finally, the data members of the bst class
   */
  private:
    bst_node *root;


}; // end class bst

#endif
