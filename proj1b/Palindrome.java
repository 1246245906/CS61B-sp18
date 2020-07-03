//import java.util.concurrent.TransferQueue;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i += 1) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word) {
        Deque wordDeque = wordToDeque(word);
        while (wordDeque.size() > 1) {
            if (wordDeque.removeFirst() != wordDeque.removeLast()) {
                return false;
            }
        }
        return true;
    }

    /**
      * isPalindrome with recursion,It's look good. But I love the one above.
    private boolean isPalindromeRecursion(Deque deque) {
        if(deque.size() <= 1){
            return true;
        }

        if (deque.removeFirst() != deque.removeLast()) {
            return false;
        }

        return isPalindromeRecursion(deque);
    }

    public boolean isPalindrome(String word) {
        Deque wordDeque = wordToDeque(word);
        return isPalindromeRecursion(wordDeque);
    }
    */

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        while (wordDeque.size() > 1) {
            if (!cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
