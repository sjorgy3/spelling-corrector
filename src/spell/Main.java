package spell;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Scanner;

/**
 * A simple main class for running the spelling corrector. This class is not
 * used by the passoff program.
 */
public class Main {
	
	/**
	 * Give the dictionary file name as the first argument and the word to correct
	 * as the second argument.
	 */
	public static void main(String[] args) throws IOException {

		String dictionaryFileName = args[0];
		String inputWord = args[1];
		//todo Finish writing the code to put all the words in a hashmap with the correct size.

		HashMap wordMap = new HashMap();
		File dictionary = new File(dictionaryFileName);
		Scanner sc = new Scanner(dictionary);


		while(sc.hasNextLine()){
			String word = sc.nextLine();
			if(wordMap.containsKey(word)){
				System.out.println(word);
				wordMap.replace(word,)s
			}


		}










		//
        //Create an instance of your corrector here
        //
		ISpellCorrector corrector = null;

		
		corrector.useDictionary(dictionaryFileName);
		String suggestion = corrector.suggestSimilarWord(inputWord);
		if (suggestion == null) {
		    suggestion = "No similar word found";
		}
		
		System.out.println("Suggestion is: " + suggestion);
	}

}
