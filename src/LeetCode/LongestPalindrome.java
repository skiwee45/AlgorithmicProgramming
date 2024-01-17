package LeetCode;

public class LongestPalindrome {
    public String longestPalindrome(String s) {
        Palindrome longestPalindrome = new Palindrome("", 0);
        char[] charArray = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            Palindrome palindrome = longestPalindromeFromCenter(charArray, i);
            if (palindrome.length > longestPalindrome.length) {
                longestPalindrome = palindrome;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            Palindrome palindrome = longestPalindromeFromCursor(charArray, i);
            if (palindrome.length > longestPalindrome.length) {
                longestPalindrome = palindrome;
            }
        }

        return longestPalindrome.value;
    }

    private Palindrome longestPalindromeFromCenter(char[] s, int center) {
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s[center]);
        while (center - i >= 0 && center + i < s.length && s[center - i] == s[center + i]) {
            stringBuilder.append(s[center + i]);
            stringBuilder.insert(0, s[center - i]);
            i++;
        }
        return new Palindrome(stringBuilder.toString(), i * 2 - 1);
    }

    private Palindrome longestPalindromeFromCursor(char[] s, int cursor) {
        // cursor is the left more index of the pair
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (cursor - i >= 0 && cursor + i + 1 < s.length && s[cursor - i] == s[cursor + i + 1]) {
            stringBuilder.append(s[cursor + i + 1]);
            stringBuilder.insert(0, s[cursor - i]);
            i++;
        }
        return new Palindrome(stringBuilder.toString(), i * 2);
    }

    private class Palindrome {
        public String value;
        public int length;

        public Palindrome(String v, int l) {
            value = v;
            length = l;
        }
    }
}
