package spell;

import javax.naming.OperationNotSupportedException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SpellCorrector implements ISpellCorrector {
    private Trie trieDictionary = new Trie();

    public SpellCorrector() {



    }
    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        File fileToRead = new File(dictionaryFileName);
        Scanner scanner = new Scanner(fileToRead);
        while (scanner.hasNextLine()){

            String nextWord = scanner.next();
            nextWord = nextWord.toLowerCase();
            trieDictionary.add(nextWord);
        }

    //make a file object using the fileName path
        // using that file create a scanner object
        //use scanner.next and it will grab the next word.
        //while scanner.hasnext {add to a trie} scanner.next grabs the next value.
        //add the word
    }

    @Override
    public String suggestSimilarWord(String inputWord) {
        //test if it is in the dictionary
        if(testIsInDictionary(inputWord)!=null){
            return testIsInDictionary(inputWord);
        }
        String ed1SuggestedWord = geted1SuggestedWord(inputWord);
        if (ed1SuggestedWord.equals("")){

            ed1SuggestedWord = geted2SuggestedWord(inputWord);
        }
        if (ed1SuggestedWord.equals("")){
            return null;
        }



        //only worry about one or two deletion distances
        //for edit distance one you are going to generate a ton of words
        //generate all of the children and which one has the highest occurence.
        //generate all of the words one one edit distance. Generate the words first, then test them. If there are no words then worry about
        //use a set so you don't repeat words.

        
        return ed1SuggestedWord;
    }


    private String geted2SuggestedWord(String inputWord) {
        Set<String>bigSet = ed1GetBigSet(inputWord);
        bigSet = (Set<String>) ed2SuperSet(bigSet);
        int maxvalue= 0;
        String currentSugWord = "";
        for (String wordToTest : bigSet) {
            if(trieDictionary.find(wordToTest)!= null){
                int valueFromNewWord = trieDictionary.find(wordToTest).getValue();
                if (valueFromNewWord > maxvalue){
                    maxvalue=valueFromNewWord;
                    currentSugWord = wordToTest;
                } else if (maxvalue == valueFromNewWord) {
                    if(currentSugWord.compareTo(wordToTest) > 0){
                        currentSugWord = wordToTest;
                    }
                }

            }


        }

        return currentSugWord;
    }

    private Object ed2SuperSet(Set<String> bigSet) {
        Set<String>superSet = new HashSet<>();
        for (String word:bigSet
             ) {
            superSet.addAll(geted1AlterSet(word));
            superSet.addAll(geted1TransposeSet(word));
            superSet.addAll(geted1DeleteSet(word));
            superSet.addAll(geted1InsertSet(word));
        }
        return superSet;
    }


    private String geted1SuggestedWord(String inputWord) {

        Set<String>bigSet = ed1GetBigSet(inputWord);
        int maxvalue= 0;
        String currentSugWord = "";
        for (String wordToTest : bigSet) {
            if(trieDictionary.find(wordToTest)!= null){
                int valueFromNewWord = trieDictionary.find(wordToTest).getValue();
                if (valueFromNewWord > maxvalue){
                    maxvalue=valueFromNewWord;
                    currentSugWord = wordToTest;
                } else if (maxvalue == valueFromNewWord) {
                    if(currentSugWord.compareTo(wordToTest) > 0){
                        currentSugWord = wordToTest;
                    }
                }

            }


        }

        return currentSugWord;
    }

    private Set<String> ed1GetBigSet(String inputWord) {
        Set<String>bigSet = new HashSet<>();
        Set<String>deleteSet = geted1DeleteSet(inputWord);
        Set<String>insertSet = geted1InsertSet(inputWord);
        Set<String>alterSet = geted1AlterSet(inputWord);
        Set<String>transposeSet = geted1TransposeSet(inputWord);

        bigSet.addAll(deleteSet);
        bigSet.addAll(insertSet);
        bigSet.addAll(alterSet);
        bigSet.addAll(transposeSet);


        return bigSet;
    }

    private Set<String> geted1AlterSet(String inputWord)  {
        Set<String>setToReturn = new HashSet<>();
        for (int k = 0; k < 25; k++) {
            char letterToTry = (char) ('a' + k);
            for (int i = 0; i < inputWord.length(); i++) {
                StringBuilder wordToAdd = new StringBuilder();
                for (int j = 0; j < inputWord.length(); j++) {
                    if(i==j){
                        wordToAdd.append(letterToTry);
                    }
                    else{
                        wordToAdd.append(inputWord.charAt(j));
                    }

                }
                setToReturn.add(wordToAdd.toString());
            }
        }



        return setToReturn;
    }

    private Set<String> geted1InsertSet(String inputWord)  {
        Set<String>setToReturn = new HashSet<>();
        for (int k = 0; k < 25; k++) {
            char letterToTry = (char) ('a' + k);
            for (int i = 0; i < inputWord.length()+1; i++) {
                StringBuilder wordToAdd = new StringBuilder();
                for (int j = 0; j < inputWord.length()+1; j++) {
                    if(i==j){
                        wordToAdd.append(letterToTry);
                    }
                    if(j<inputWord.length()){
                        wordToAdd.append(inputWord.charAt(j));
                    }




                }
                setToReturn.add(wordToAdd.toString());
            }
        }



        return setToReturn;
    }

    private Set<String> geted1TransposeSet(String inputWord)  {
        Set<String>setToReturn = new HashSet<>();

        for (int i = 0; i < inputWord.length()-1; i++) {
            StringBuilder wordToAdd = new StringBuilder();

            for (int j = 0; j < inputWord.length(); j++) {
                if(i==j){
                    wordToAdd.append(inputWord.charAt(j+1));
                    wordToAdd.append(inputWord.charAt(j));
                    j++;

                }
                else{
                    wordToAdd.append(inputWord.charAt(j));

                }

            }
            setToReturn.add(wordToAdd.toString());
        }


        return setToReturn;
    }

    private Set<String> geted1DeleteSet(String inputWord) {
        Set<String>setToReturn = new HashSet<>();

        for (int i = 0; i < inputWord.length(); i++) {
            StringBuilder wordToAdd = new StringBuilder();

            for (int j = 0; j < inputWord.length(); j++) {
                if(i!=j){
                    wordToAdd.append(inputWord.charAt(j));

                }

            }
            setToReturn.add(wordToAdd.toString());
        }

        
        return setToReturn;
    }


    private String testIsInDictionary(String inputWord) {
        INode finalNode= trieDictionary.find(inputWord);
        inputWord = inputWord.toLowerCase();
        if(finalNode!= null){
            return inputWord;
        }
        return null;
    }
    //use trie dictionary to add words to the thing
    //look through dictionary and look for one off of things.
    //create list of probable words
    //create




    //first check should be is it in the dictionary??
    //make as many helper functions as you can. Alterations etc.

}
