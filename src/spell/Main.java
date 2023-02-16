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
		File dictionary = new File(dictionaryFileName);
		HashMap wordMap = new HashMap();
		Scanner sc = new Scanner(dictionary);

		int wordCount = 0;
		while(sc.hasNextLine()){
			String word = sc.nextLine();

			if(!wordMap.containsKey(word)){
				wordCount=1;
				wordMap.put(word,wordCount);

			}
			else{
				wordCount++;
				wordMap.replace(word,wordCount-1,wordCount);
			}

		}










		//
        //Create an instance of your corrector here
        //
		ISpellCorrector corrector = new SpellCorrector();

		
		corrector.useDictionary(dictionaryFileName);
		String suggestion = corrector.suggestSimilarWord(inputWord);
		if (suggestion == null) {
		    suggestion = "No similar word found";
		}
		
		System.out.println("Suggestion is: " + suggestion);
	}

}
