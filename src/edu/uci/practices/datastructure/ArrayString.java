package edu.uci.practices.datastructure;

/**
 * @author William
 */
public class ArrayString {

	/**
	 * p1.8
	 * 
	 * Assume you have a method isSubstring which checks if one word is a
	 * substring of another. Given two strings, s1 and s2, write code to check if
	 * s2 is a rotation of s1 using only one call to isSubstring (i.e.,
	 * “waterbottle” is a rotation of “erbottlewat”).
	 */
	
	// solution on the book is more elegant
	public static boolean p8IsRotation(String left, String right) {
		if (left == null || right == null
				|| left.length() != right.length())
			return false;
		
		StringBuilder head = new StringBuilder();
		StringBuilder tail = new StringBuilder(left);
		
		for (int cur = 0; cur < left.length(); ++cur) {
			head.append(left.charAt(cur));
			tail.deleteCharAt(0);
			
			if (isSubstring(head.toString(), right) &&
					isSubstring(tail.toString(), right)) {
				StringBuilder expected = new StringBuilder(tail);
				expected.append(head);
				if (right.equals(expected.toString()))
					return true;
			}
		}
		
		return false;
	}
	
	private static boolean isSubstring(String sub, String whole) {
		if (sub == null || whole == null)
			return false;
		return whole.indexOf(sub) >= 0;
	}

	/**
	 * p1.7
	 * 
	 * Write an algorithm such that if an element in an MxN matrix is 0, its
	 * entire row and column is set to 0.
	 */

	public static void p7Zeronize(int[][] matrix) {
		boolean[] rows = new boolean[matrix.length];
		boolean[] cols = new boolean[matrix[0].length];

		for (int i = 0; i < rows.length; ++i)
			for (int j = 0; j < cols.length; ++j)
				if (matrix[i][j] == 0) {
					rows[i] = true;
					cols[j] = true;
				}

		for (int i = 0; i < rows.length; ++i)
			for (int j = 0; j < cols.length; ++j)
				if (rows[i] || cols[j])
					matrix[i][j] = 0;
	}

	/**
	 * p1.6
	 * 
	 * Given an image represented by an NxN matrix, where each pixel in the
	 * image is 4 bytes, write a method to rotate the image by 90 degrees. Can
	 * you do this in place?
	 */

	public static int p6RotateImage(byte[][][] image) {
		int shape = image.length;
		if (image[0].length != shape)
			return -1;

		for (int layer = 0; layer < shape / 2; ++layer)
			rotateCycle(image, layer);

		return 0;
	}

	// rotate one cycle
	private static void rotateCycle(byte[][][] image, int layer) {
		int shape = image.length;
		int end = shape - layer - 1; // end count, not index

		for (int ptr = layer; ptr < end; ++ptr) {
			// System.out.println(layer + " " + ptr + " " + end);

			// top <--> right
			xorSwap(image[layer][ptr], image[ptr][shape - 1 - layer]);

			// left <--> top
			xorSwap(image[shape - 1 - ptr][layer], image[layer][ptr]);

			// bottom <--> left
			xorSwap(image[shape - 1 - layer][shape - 1 - ptr], image[shape - 1
					- ptr][layer]);
		}
	}

	// incorrect -- rotate 180 degree in effect
	public static int p6RotateImageIncorrect(byte[][][] image) {
		int shape = image.length;

		for (int row = 0; row < shape; ++row) {
			if (image[row].length != shape)
				return -1;

			for (int col = 0; col < shape; ++col) {
				int shiftRow = col;
				int shiftCol = shape - row - 1;
				if (image[row][col].length != 4
						|| image[shiftRow][shiftCol].length != 4)
					return -2;

				xorSwap(image[row][col], image[shiftRow][shiftCol]);
			}
		}

		return 0;
	}

	private static void xorSwap(byte[] from, byte[] to) {
		for (int i = 0; i < 4; ++i) {
			from[i] ^= to[i];
			to[i] ^= from[i];
			from[i] ^= to[i];
		}
	}

	/**
	 * p1.5
	 * 
	 * Write a method to replace all spaces in a string with ‘%20’.
	 */

	public static String p5EncodeSpace(String input) {

		if (input == null)
			return null;
		char[] brk = input.toCharArray();

		// unlike StringBuffer, StringBuilder is not synchronized
		StringBuilder sb = new StringBuilder();
		final char[] code = "%20".toCharArray();
		for (char c : brk) {
			if (' ' == c)
				sb.append(code);
			else
				sb.append(c);
		}

		return sb.toString();
	}

	/**
	 * p1.4
	 * 
	 * Write a method to decide if two strings are anagrams or not.
	 */

	public static boolean p4Anagram(String left, String right) {
		if (left == null || right == null || left.length() != right.length())
			return false;

		int j = right.length() - 1;
		for (int i = 0; i < left.length(); i++)
			if (left.charAt(i) != right.charAt(j--))
				return false;

		return true;
	}

	/**
	 * p1.3
	 * 
	 * Design an algorithm and write code to remove the duplicate characters in
	 * a string without using any additional buffer. NOTE: One or two additional
	 * variables are fine. An extra copy of the array is not. FOLLOW UP Write
	 * the test cases for this method.
	 */

	public static void p3RemoveDups(char[] s) {
		if (s == null)
			return;

		int len = s.length;

		for (int i = 0; i < len; i++) {
			final int ptr = i;

			int insert = i + 1;
			for (int j = i + 1; j < len; j++) {
				if (s[ptr] != s[j]) {
					s[insert++] = s[j];
				}
			}

			len = insert;
		}

		if (len < s.length)
			s[len] = '\n';
	}

	public static void p3RemoveDupsAlt(char[] s) {
		if (s == null || s.length < 2)
			return;

		boolean charSet[] = new boolean[256];

		int ptr = 0;
		for (char c : s) {
			if (!charSet[c]) {
				charSet[c] = true;
				s[ptr++] = c;
			}
		}

		if (ptr < s.length)
			s[ptr] = '\n';
	}

	/**
	 * p1.2
	 * 
	 * Write code to reverse a C-Style String. (C-String means that “abcd” is
	 * represented as five characters, including the null character.)
	 */

	public static void p2Reverse(String s) {
	}

	/**
	 * p1.1
	 * 
	 * Implement an algorithm to determine if a string has all unique
	 * characters. What if you can not use additional data structures?
	 */

	public static boolean p1UniqueChars(String s) {
		boolean[] charSet = new boolean[256];

		for (int i = 0; i < s.length(); i++) {
			if (charSet[s.charAt(i)])
				return false;
			charSet[s.charAt(i)] = true;
		}

		return true;
	}

}
