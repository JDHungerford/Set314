/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, Miles Chandler and Jacob Hungerford, this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:
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


CS314 Students, why is it unwise to implement all three of the
intersection, union, and difference methods in the AbstractSet class:

The intersection, union, and difference methods in the AbstractSet class use
eachother to construct a new set since the AbstractSet class is not allowed to
explicitly create one of its children classes.  Since these methods call each
other implementing more than one of them would create an infinite amount of 
method calls.
*/


public class SetTester {

    public static void main(String[] args){

        ISet<String> s1 = new UnsortedSet<String>();
        s1.add("A");
        s1.add("C");
        s1.add("A");
        s1.add("B");

        //test 1
        if( s1.contains("A") )
            System.out.println("Passed test 1: add and contains methods SortedSet");
        else
            System.out.println("Failed test 1: add and contains methods SortedSet");

        //test 2
        s1.remove("A");
        if( !s1.contains("A") )
            System.out.println("Passed test 2: remove method UnsortedSet");
        else
            System.out.println("Failed test 2: remove method UnsortedSet");

        //test 3
        if( s1.size() == 2 )
            System.out.println("Passed test 3: size method UnsortedSet");
        else
            System.out.println("Failed test 3: size method UnsortedSet");

        ISet<String> s2 = new UnsortedSet<String>();
        s2.add("C");
        s2.add("A");
        s2.add("B");

        //test 4
        if( s2.containsAll(s1) )
            System.out.println("Passed test 4: containsAll method UnsortedSet");
        else
            System.out.println("Failed test 4: containsAll method UnsortedSet");

        //test 5
        if( !s1.containsAll(s2) )
            System.out.println("Passed test 5: containsAll method UnsortedSet");
        else
            System.out.println("Failed test 5: containsAll method UnsortedSet");

        //test 6
        ISet<String> s3 = s2.difference(s1);
        ISet<String> expected = new UnsortedSet<String>();
        expected.add("A");
        if( s3.equals(expected))
            System.out.println("Passed test 6: difference and equals methods UnsortedSet");
        else
            System.out.println("Failed test 6: difference and equals methods UnsortedSet");

        //test 7
        s3 = s2.union(s1);
        expected.add("B");
        expected.add("C");
        if( s3.equals(expected))
            System.out.println("Passed test 7: union and equals methods UnsortedSet");
        else
            System.out.println("Failed test 7: union and equals methods UnsortedSet");

        //test 8
        s3 = s2.intersection(s1);
        expected.remove("A");
        if( s3.equals(expected))
            System.out.println("Passed test 8: intersection and equals methods UnsortedSet");
        else
            System.out.println("Failed test 8: intersection and equals methods UnsortedSet");

        //sorted sets
        s1 = new SortedSet<String>();
        s1.add("A");
        s1.add("C");
        s1.add("A");
        s1.add("B");

        //test 9
        if( s1.contains("A") )
            System.out.println("Passed test 9: add and contains methods SortedSet");
        else
            System.out.println("Failed test 9: add and contains methods SortedSet");

        //test 10
        s1.remove("A");
        if( !s1.contains("A") )
            System.out.println("Passed test 10: remove and contains methods SortedSet");
        else
            System.out.println("Failed test 10: remove and contains methods SortedSet");

        //test 11
        if( s1.size() == 2 )
            System.out.println("Passed test 11: size method SortedSet");
        else
            System.out.println("Failed test 11: size method SortedSet");

        s2 = new SortedSet<String>();
        s2.add("C");
        s2.add("A");
        s2.add("B");

        //test 12
        if( s2.containsAll(s1) )
            System.out.println("Passed test 12: containsAll method SortedSet");
        else
            System.out.println("Failed test 12: containsAll method SortedSet");

        //test 13
        if( !s1.containsAll(s2) )
            System.out.println("Passed test 13: containsAll method SortedSet");
        else
            System.out.println("Failed test 13: containsAll method SortedSet");

        //test 14
        s3 = s2.difference(s1);
        expected = new SortedSet<String>();
        expected.add("A");
        if( s3.equals(expected))
            System.out.println("Passed test 14: difference and equals methods SortedSet");
        else
            System.out.println("Failed test 14: difference and equals methods SortedSet");

        //test 14.1
        s3 = s1.difference(s2);
        expected = new SortedSet<String>();
        if( s3.equals(expected))
            System.out.println("Passed test 14.1: difference and equals methods SortedSet");
        else
            System.out.println("Failed test 14.1: difference and equals methods SortedSet");

        //test 15
        s3 = s1.union(s2);
        expected = new SortedSet<String>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        if( s3.equals(expected))
            System.out.println("Passed test 15: union and equals methods SortedSet");
        else
            System.out.println("Failed test 15: union and equals methods SortedSet");

        //test 16
        s3 = s1.intersection(s2);
        expected.remove("A");
        if( s3.equals(expected))
            System.out.println("Passed test 16: intersection and equals methods SortedSet");
        else
            System.out.println("Failed test 16: intersection and equals methods SortedSet");

        // test 17
        s1.add("A");
        Iterator<String> it1 = s1.iterator();
        Iterator<String> it2 = s2.iterator();
        boolean good = true;
        while( good && it1.hasNext() )
            good = it1.next().equals(it2.next());
        if( good )
            System.out.println("Passed test 17: iterator and add methods SortedSet");
        else
            System.out.println("Failed test 17: iterator and add methods SortedSet");

        // test 18
        s1 = new UnsortedSet<String>();
        UnsortedSet<Integer> si1 = new UnsortedSet<Integer>();
        if(si1.equals(s1))
            System.out.println("Passed test 18: equals methods UnsortedSet");
        else
            System.out.println("Failed test 18: equals methods UnsortedSet");

        // test 19
        s1.add("is");
        s1.add("a");
        si1.add(12);
        si1.add(13);
        si1.add(12);
        if(!si1.equals(s1))
            System.out.println("Passed test 19: equals methods UnsortedSet");
        else
            System.out.println("Failed test 19: equals methods UnsortedSet");

        // test 20
        ArrayList<Integer> ar = new ArrayList<Integer>();
        ar.add(12);
        ar.add(13);
        if(!si1.equals(ar))
            System.out.println("Passed test 20: equals methods UnsortedSet");
        else
            System.out.println("Failed test 20: equals methods UnsortedSet");

        // test 21
        Object obj1 = s1;
        s2 = new UnsortedSet<String>();
        s2.add("a");
        s2.add("is");
        Object obj2 = s2;
        if(obj1.equals(obj2))
            System.out.println("Passed test 21: equals methods UnsortedSet");
        else
            System.out.println("Failed test 21: equals methods UnsortedSet");

        // test 22
        s1 = new SortedSet<String>();
        s1.add("A");
        s1.add("A");
        s1.add("B");
        ISet<Integer> ss2 = new SortedSet<Integer>();
        ss2.add(12);
        ss2.add(15);
        ss2.add(12);
        ss2.add(15);
        if(!s1.equals(ss2))
            System.out.println("Passed test 22: equals methods SortedSet - different types");
        else
            System.out.println("Failed test 22: equals methods SortedSet - different types");

        // test 23
        if(!ss2.equals(s1))
            System.out.println("Passed test 23: equals methods SortedSet - different types");
        else
            System.out.println("Failed test 23: equals methods SortedSet - different types");

        // CS314 Students. Uncomment this section when ready to 
        // run your experiments
        //        try {
        //            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //        }
        //        catch(Exception e) {
        //            System.out.println("Unable to change look and feel");
        //        }
        //		Scanner sc = new Scanner(System.in);
        //		String response = "";
        //		do {
        //			largeTest();
        //			System.out.print("Another file? Enter y to do another file: ");
        //			response = sc.next();
        //		} while( response != null && response.length() > 0 
        //              && response.substring(0,1).equalsIgnoreCase("y") );

    }

    /*
     * Method asks user for file and compares run times to add words from file to
     * various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest(){
        System.out.println();
        System.out.println("Opening Window to select file. You may have to minimize other windows.");
        String text = convertFileToString();
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets( new HashSet<String>(), text);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets( new TreeSet<String>(), text);
    }

    private static void studentTests(){
        
    }


    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for CS314 sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }


    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for Java Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
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

        System.out.println("Time to add the elements in the text to this set: " + s.toString() );
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);


        System.out.print("Enter y to see the contents of this set: ");
        Scanner sc = new Scanner(System.in);
        String response = sc.next();

        if( response != null && response.length() > 0 && response.substring(0,1).equalsIgnoreCase("y") ){
            for(Object o : set)
                System.out.println(o);
        }
        System.out.println();
    }


    /*
     * Ask user to pick a file via a file choosing window and
     * convert that file to a String. Since we are evalutatin the file
     * with many sets convert to string once instead of reading through
     * file multiple times.
     */
    private static String convertFileToString() {
        //create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        //read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            try {
                Scanner s = new Scanner( new FileReader( source ) );

                while( s.hasNextLine() ) {
                    text.append( s.nextLine() );
                    text.append(" ");
                }

                s.close();
            }
            catch(IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            }
        }

        return text.toString();
    }
}