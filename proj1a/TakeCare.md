# Linked List Double Ended Queue

1. If you want to initialize a node with generic parameter, you can use null to initialize. For example:

   ```java
   public TNode(T i, TNode p, TNode n) {
               item = i;
               next = n;
               prev = p;
   }
   ```

   You have no idea about what T is, so, you can:

   ```java
   TNode s = new TNode(null, null, null);
   ```

2. 

# Array Double Ended Queue

1. We can't use:

   ```java
   T[] items = new T[8];
   ```

   to  create an array of generic object.

   Instead, we have to use the awkward syntax shown below:

   ```java
   T[] items = (T []) new Object[8];
   ```

2. Two integers divided by each other, the result is still integer. For example:

   ```java
   int a = 1;
   int b = 4;
   float c = a / b;		// c = 0
   ```

   If you want the exact value,try:

   ```java
   float x = a;
   float y = b;
   float c = x / y;
   ```

3. In Java, `-1 % 8 = -1`. So, if you want get 7,you should:

   ```java
   (- 1 + 8) % 8;
   ```

   

# Check Styles

1. There needs a space before "{".
2. There needs a space after "for", "if", "while".
3. Spaces are required before and after any symbol.
4. You should use four space instead of tab.