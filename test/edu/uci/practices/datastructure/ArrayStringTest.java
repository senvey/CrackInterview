package edu.uci.practices.datastructure;

import junit.framework.TestCase;

import org.junit.Test;

public class ArrayStringTest extends TestCase {
	
	@Test	//1.8
	public void testIsRotation() {
		String left = "waterbottle";
		String right = "erbottlewat";
		assertTrue(ArrayString.p8IsRotation(left, right));
		
		left = "";
		right = "hello";
		assertFalse(ArrayString.p8IsRotation(left, right));
		
		left = "hello";
		right = "";
		assertFalse(ArrayString.p8IsRotation(left, right));
		
		left = "nicea";
		right = "hello";
		assertFalse(ArrayString.p8IsRotation(left, right));
		
		left = "hellh";
		right = "llheb";
		assertFalse(ArrayString.p8IsRotation(left, right));
	}
	
	@Test	// 1.7
	public void testZeronize() {
		int[][] matrix = new int[][] {
			new int[] {1, 2, 0, 4},
			new int[] {0, 1, 2, 3},
			new int[] {1, 2, 3, 4}
		};
		
		ArrayString.p7Zeronize(matrix);
		printMatrix(matrix);
	}
	
	private void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix[i].length; ++j) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	@Test	// 1.6
	public void testRotateImage() {
		byte[][][] image = new byte[][][] {
			new byte[][] {
				new byte[] {-55, -66, -55, -66}, new byte[] {123, 123, 123, 123}
			},
			new byte[][] {
				new byte[] {-123, -123, -123, -123}, new byte[] {66, 77, 66, 77}
			}
		};
		
		assertEquals(0, ArrayString.p6RotateImageIncorrect(image));
		printImage(image, 2);
		assertEquals(0, ArrayString.p6RotateImage(image));
		printImage(image, 2);
	}
	
	private void printImage(byte[][][] image, int shape) {
		System.out.println("Image:");
		for (int i = 0; i < shape; ++i)
		{
			for (int j = 0; j < shape; ++j) 
			{
				for (int k = 0; k < 4; ++k)
					System.out.print(image[i][j][k] + "\t");
				System.out.print("\t\t");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	@Test	// 1.5
	public void testEncodeSpace() {
		String input = "This is a test!";
		String output = "";
		String expected = "This%20is%20a%20test!";
		output = ArrayString.p5EncodeSpace(input);
		assertEquals(expected, output);
		
		assertNull(ArrayString.p5EncodeSpace(null));
		assertEquals("%20", ArrayString.p5EncodeSpace(" "));
		assertEquals("abc", ArrayString.p5EncodeSpace("abc"));
	}
	
	@Test	// 1.4
	public void testAnagram() {
		String left = "abcdefg";
		String right = "gfedcba";
		assertTrue(ArrayString.p4Anagram(left, right));
		
		assertFalse(ArrayString.p4Anagram(null, ""));
		assertFalse(ArrayString.p4Anagram("1", ""));
	}

	@Test	// 1.3
	public void testRemoveDups() {
		/* Simple happy path */
		String expected = "This ate!\n";
		char[] actual ="This is a test!".toCharArray();
		checkDups(expected, actual);
		actual ="This is a test!".toCharArray();
		checkDupsAlt(expected, actual);
		
		/* boundary 1 */
		expected = "a\n";
		actual = "aaaaaa".toCharArray();
		checkDups(expected, actual);
		actual = "aaaaaa".toCharArray();
		checkDupsAlt(expected, actual);
		
		/* boundary 2 */
		expected = "ab\n";
		actual = "aaabbbb".toCharArray();
		checkDups(expected, actual);
		actual = "aaabbbb".toCharArray();
		checkDupsAlt(expected, actual);
		
		/* boundary 3 */
		expected = "abcdefg";
		actual = "abcdefg".toCharArray();
		checkDups(expected, actual);
		actual = "abcdefg".toCharArray();
		checkDupsAlt(expected, actual);

		/* null and empty input */
		ArrayString.p3RemoveDups(null);
		ArrayString.p3RemoveDups(new char[] {});
		ArrayString.p3RemoveDupsAlt(null);
		ArrayString.p3RemoveDupsAlt(new char[] {});
	}
	
	private void checkDups(String expected, char[] actual) {
		ArrayString.p3RemoveDups(actual);
		for (int i = 0; i < expected.length(); i++)
			assertEquals(expected.charAt(i), actual[i]);
	}
	
	private void checkDupsAlt(String expected, char[] actual) {
		ArrayString.p3RemoveDupsAlt(actual);
		for (int i = 0; i < expected.length(); i++)
			assertEquals(expected.charAt(i), actual[i]);
	}

	@Test	// 1.1
	public void testUnique() {
		assertFalse(ArrayString.p1UniqueChars("123bbc"));
		assertFalse(ArrayString.p1UniqueChars("1255bbc"));
		assertFalse(ArrayString.p1UniqueChars("**3bbc"));
		assertFalse(ArrayString.p1UniqueChars("123bO''"));
		assertFalse(ArrayString.p1UniqueChars("@(3d3?.bc"));
		assertTrue(ArrayString.p1UniqueChars("123abc"));
	}
}













