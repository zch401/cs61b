public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        int index = word.length() / 2;//number of times to check if two ends have equal character
        return isPalindromeHelper(deque, index);

    }

    private boolean isPalindromeHelper(Deque<Character> deque, int index) {
        if (index == 0) {//out of character to compare, left only one or zero character
            return true;
        }
        Character a = deque.removeFirst();//get character from front
        Character b = deque.removeLast();//get character from end

        if (a.equals(b)) {
            return isPalindromeHelper(deque, index - 1);
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        int index = word.length() / 2;
        while (index != 0) {
            Character a = deque.removeFirst();
            Character b = deque.removeLast();
            if (!cc.equalChars(a, b)) {
                return false;
            }
            index--;
        }
        return true;
    }

}
