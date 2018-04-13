/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, Miles Chandler and Jacob Hungerford, this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 Miles Chandler
 *  UTEID: mac9325
 *  email address: miles.chandler@ichandler.net
 *  Grader name: Anthony
 *  Section number: 51345
 *
 *  Student 2 Jacob Hungerford
 *  UTEID: jdh5468
 *  email address: JHungerford1516@utexas.edu
 *  Grader name: Anthony
 *  Section number: 51345
 *
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

/*
CS 314 Students, put your results to the experiments and answers to
questions here:

Heart of Darkness by Joseph Conrad
229KB
Total number of words in text including duplicates: 40934
Number of distinct words in this text 9581
Sorted Set:
Time to add the elements in the text to this set: elapsed time: 0.4434078 seconds.
Unsorted Set:
Time to add the elements in the text to this set: elapsed time: 0.685004194 seconds.
Java HashSet:
Time to add the elements in the text to this set: elapsed time: 0.054158419 seconds.
Java TreeSet:
Time to add the elements in the text to this set: elapsed time: 0.052360212 seconds.

Pride and Prejudice by Jane Austen
708KB
Total number of words in text including duplicates: 124592 (3.043x)
Number of distinct words in this text 13638 (1.423)
Sorted Set:
Time to add the elements in the text to this set: elapsed time: 0.619919999 seconds. (1.398x)
Unsorted Set:
Time to add the elements in the text to this set: elapsed time: 1.489714064 seconds. (2.175x)
Java HashSet:
Time to add the elements in the text to this set: elapsed time: 0.101165536 seconds. (1.932x)
Java TreeSet:
Time to add the elements in the text to this set: elapsed time: 0.131521739 seconds. (2.512x)

Moby Dick by Herman Melville
1241KB
Total number of words in text including duplicates: 215830 (1.732x)
Number of distinct words in this text 33587 (2.463x)
Sorted Set:
Time to add the elements in the text to this set: elapsed time: 3.807976624 seconds. (6.143x)
Unsorted Set:
Time to add the elements in the text to this set: elapsed time: 9.23218183 seconds. (6.197x)
Java HashSet:
Time to add the elements in the text to this set: elapsed time: 0.169442246 seconds. (1.675x)
Java TreeSet:
Time to add the elements in the text to this set: elapsed time: 0.240086036 seconds. (1.825x)

A Tale of Two Cities by Charles Dickens
785KB
Total number of words in text including duplicates: 138883 (0.643x)
Number of distinct words in this text 19666 (0.586x)
Sorted Set:
Time to add the elements in the text to this set: elapsed time: 1.25135598 seconds. (0.329x)
Unsorted Set:
Time to add the elements in the text to this set: elapsed time: 3.307363805 seconds. (0.358x)
Java HashSet:
Time to add the elements in the text to this set: elapsed time: 0.098633482 seconds. (0.582x)
Java TreeSet:
Time to add the elements in the text to this set: elapsed time: 0.163775702 seconds. (0.682x)

O(NlogM) for SortedSet processText
O(N * M) for UnsortedSet processText
O(N) for SortedSet processText
O(N) for UnsortedSet processText
O(1) for HashSet add
O(logN) for TreeSet add
A HashSet prints the elements out without any specific order each time while a
TreeSet prints the elements out in ascending sorted order.

CS314 Students, why is it unwise to implement all three of the
intersection, union, and difference methods in the AbstractSet class:

The intersection, union, and difference methods in the AbstractSet class use
eachother to construct a new set since the AbstractSet class is not allowed to
explicitly create one of its children classes.  Since these methods call each
other implementing more than one of them would create an infinite amount of 
method calls.
*/

public class SetTester {

	public static void main(String[] args) {
		studentTests();

		// CS314 Students. Uncomment this section when ready to
		// run your experiments
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (Exception e) {
		// System.out.println("Unable to change look and feel");
		// }
		// Scanner sc = new Scanner(System.in);
		// String response = "";
		// do {
		// largeTest();
		// System.out.print("Another file? Enter y to do another file: ");
		// response = sc.next();
		// } while (response != null && response.length() > 0
		// && response.substring(0, 1).equalsIgnoreCase("y"));
	}

	/*
	 * Method asks user for file and compares run times to add words from file
	 * to various sets, including CS314 UnsortedSet and SortedSet and Java's
	 * TreeSet and HashSet.
	 */
	private static void largeTest() {
		System.out.println();
		System.out.println(
				"Opening Window to select file. You may have to minimize other windows.");
		String text = convertFileToString();
		System.out.println();
		System.out.println("***** CS314 SortedSet: *****");
		processTextCS314Sets(new SortedSet<String>(), text);
		System.out.println("****** CS314 UnsortedSet: *****");
		processTextCS314Sets(new UnsortedSet<String>(), text);
		System.out.println("***** Java HashSet ******");
		processTextJavaSets(new HashSet<String>(), text);
		System.out.println("***** Java TreeSet ******");
		processTextJavaSets(new TreeSet<String>(), text);
	}

	private static void studentTests() {
		// Unsorted
		// 1 addAll
		UnsortedSet<Integer> s1 = new UnsortedSet<>();
		UnsortedSet<Integer> s2 = new UnsortedSet<>();
		s2.add(2);
		s2.add(-100);
		s1.addAll(s2);
		UnsortedSet<Integer> expected = new UnsortedSet<>();
		expected.add(2);
		expected.add(-100);
		if (s1.equals(expected))
			System.out.println("Passed test 1: addAll");
		else
			System.out.println("Failed test 1");

		// 2 clear
		s1.clear();
		expected = new UnsortedSet<>();
		if (s1.equals(expected))
			System.out.println("Passed test 2: clear");
		else
			System.out.println("Failed test 2");

		// 3 add
		s1.add(1);
		expected.add(1);
		if (s1.equals(expected))
			System.out.println("Passed test 3: add");
		else
			System.out.println("Failed test 3");

		// 4 difference
		s2.clear();
		s2.add(1);
		ISet<Integer> exp = s1.difference(s2);
		if (exp.size() == 0)
			System.out.println("Passed test 4: difference");
		else
			System.out.println("Failed test 4");

		// 5 intersection
		exp = s1.intersection(s2);
		if (s1.equals(exp))
			System.out.println("Passed test 5: intersection");
		else
			System.out.println("Failed test 5");

		// 6 iterator
		exp.clear();
		s1.add(2);
		s2.add(2);
		for (Integer i : s1) {
			exp.add(i);
		}
		for (Integer i : s2) {
			exp.add(i);
		}
		if (exp.size() == 2)
			System.out.println("Passed test 6: iterator");
		else
			System.out.println("Failed test 6");

		// 7 size()
		if (s1.size() == s2.size())
			System.out.println("Passed test 7: size");
		else
			System.out.println("Failed test 7");

		// 8 union
		s2.add(3);
		exp = s1.union(s2);
		s1.add(3);
		if (s1.equals(exp))
			System.out.println("Passed test 8: Union");
		else
			System.out.println("Failed test 8");

		// 9 constructor
		s1 = new UnsortedSet<>();
		exp.clear();
		if (s1.equals(exp))
			System.out.println("Passed test 9: constructor");
		else
			System.out.println("Failed test 9");

		// 10 remove
		s1.add(1);
		s1.remove(1);
		if (s1.equals(exp))
			System.out.println("Passed test 10: remove");
		else
			System.out.println("Failed test 10");

		// 11 contains
		if (!s1.contains(40))
			System.out.println("Passed test 11: contains");
		else
			System.out.println("Failed test 11");

		// 12 containsAll
		s1.add(-10);
		s2.clear();
		s2.add(-10);
		if (s1.containsAll(s2))
			System.out.println("Passed test 12: containsALl");
		else
			System.out.println("Failed test 12");

		// 13 toString
		if (s1.toString().equals("(-10)"))
			System.out.println("Passed test 13: toString");
		else
			System.out.println("Failed test 13");

		// 14 equals
		s1.add(2);
		s2.add(3);
		if (!s1.equals(s2))
			System.out.println("Passed test 14: equals");
		else
			System.out.println("Failed test 14");

		// Sorted

		// 15 constructor
		SortedSet<Integer> ss1 = new SortedSet<>();
		if (ss1.size() == 0)
			System.out.println("Passed test 15: constructor");
		else
			System.out.println("Failed test 15");

		// 16 iterator
		String res = "";
		ss1.add(1);
		ss1.add(2);
		ss1.add(10);
		ss1.add(8);
		for (Integer i : ss1) {
			res += "" + i;
		}
		if (res.equals("12810"))
			System.out.println("Passed test 16: iterator");
		else
			System.out.println("Failed test 16");

		// 17 add
		ss1.add(1);
		if (ss1.size() == 4)
			System.out.println("Passed test 17: add");
		else
			System.out.println("Failed test 17");

		// 18 clear()
		ss1.clear();
		if (ss1.size() == 0)
			System.out.println("Passed test 18: clear");
		else
			System.out.println("Failed test 18");

		// 19 size()
		ss1.add(1);
		if (ss1.size() == 1)
			System.out.println("Passed test 19: size");
		else
			System.out.println("Failed test 19");

		// 20 addAll
		SortedSet<Integer> ss2 = new SortedSet<>();
		ss2.add(1);
		ss2.add(3);
		ss1.addAll(ss2);
		if (ss1.size() == 2)
			System.out.println("Passed test 20: addAll");
		else
			System.out.println("Failed test 20");

		// 21 contains
		if (ss1.contains(3))
			System.out.println("Passed test 21: contains");
		else
			System.out.println("Failed test 21");

		// 22 containsAll
		if (ss1.containsAll(ss2))
			System.out.println("Passed test 22: containsAll");
		else
			System.out.println("Failed test 22");

		// 23 constructor (arg)
		UnsortedSet<Integer> s3 = new UnsortedSet<>();
		s3.add(4);
		s3.add(2);
		ss1 = new SortedSet<>(s3);
		if (ss1.size() == 2)
			System.out.println("Passed test 23: constructor(arg)");
		else
			System.out.println("Failed test 23");

		// 24 min
		if (ss1.min() == 2)
			System.out.println("Passed test 24: min");
		else
			System.out.println("Failed test 24");

		// 25 max
		if (ss1.max() == 4)
			System.out.println("Passed test 25: max");
		else
			System.out.println("Failed test 25");

		// 26 difference
		SortedSet<Integer> ss3 = new SortedSet<>();
		ss3.add(100);
		ss3.add(4);
		ISet<Integer> exp2 = ss1.difference(ss3);
		if (exp2.size() == 0)
			System.out.println("Passed test 26: difference");
		else
			System.out.println("Failed test 26");

		// 27 intersection
		ISet<Integer> exp4 = ss1.intersection(ss3);
		if (exp4.contains(4))
			System.out.println("Passed test 27: intersection");
		else
			System.out.println("Failed test 27");

	}

	/*
	 * pre: set != null, text != null Method to add all words in text to the
	 * given set. Words are delimited by white space. This version for CS314
	 * sets.
	 */
	private static void processTextCS314Sets(ISet<String> set, String text) {
		Stopwatch s = new Stopwatch();
		Scanner sc = new Scanner(text);
		int totalWords = 0;
		s.start();
		while (sc.hasNext()) {
			totalWords++;
			set.add(sc.next());
		}
		s.stop();
		sc.close();

		showResultsAndWords(set, s, totalWords, set.size());
	}

	/*
	 * pre: set != null, text != null Method to add all words in text to the
	 * given set. Words are delimited by white space. This version for Java
	 * Sets.
	 */
	private static void processTextJavaSets(Set<String> set, String text) {
		Stopwatch s = new Stopwatch();
		Scanner sc = new Scanner(text);
		int totalWords = 0;
		s.start();
		while (sc.hasNext()) {
			totalWords++;
			set.add(sc.next());
		}
		s.stop();
		sc.close();

		showResultsAndWords(set, s, totalWords, set.size());
	}

	/*
	 * Show results of add words to given set.
	 */
	private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s,
			int totalWords, int setSize) {

		System.out.println("Time to add the elements in the text to this set: "
				+ s.toString());
		System.out
				.println("Total number of words in text including duplicates: "
						+ totalWords);
		System.out.println("Number of distinct words in this text " + setSize);

		System.out.print("Enter y to see the contents of this set: ");
		Scanner sc = new Scanner(System.in);
		String response = sc.next();

		if (response != null && response.length() > 0
				&& response.substring(0, 1).equalsIgnoreCase("y")) {
			for (Object o : set)
				System.out.println(o);
		}
		System.out.println();
	}

	/*
	 * Ask user to pick a file via a file choosing window and convert that file
	 * to a String. Since we are evalutatin the file with many sets convert to
	 * string once instead of reading through file multiple times.
	 */
	private static String convertFileToString() {
		// create a GUI window to pick the text to evaluate
		JFileChooser chooser = new JFileChooser(".");
		StringBuilder text = new StringBuilder();
		int retval = chooser.showOpenDialog(null);

		chooser.grabFocus();

		// read in the file
		if (retval == JFileChooser.APPROVE_OPTION) {
			File source = chooser.getSelectedFile();
			try {
				Scanner s = new Scanner(new FileReader(source));

				while (s.hasNextLine()) {
					text.append(s.nextLine());
					text.append(" ");
				}

				s.close();
			} catch (IOException e) {
				System.out.println(
						"An error occured while trying to read from the file: "
								+ e);
			}
		}

		return text.toString();
	}
}