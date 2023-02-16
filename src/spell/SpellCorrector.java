package spell;

import java.io.IOException;

public class SpellCorrector implements ISpellCorrector {
    private Trie trieDictionary = new Trie();

    public SpellCorrector() {



    }

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
    //make a file object using the fileName path
        // using that file create a scanner object
        //use scanner.next and it will grab the next word.
        //while scanner.hasnext {add to a trie} scanner.next grabs the next value.
        //add the
    }

    @Override
    public String suggestSimilarWord(String inputWord) {
        return null;
    }
    //use trie dictionary to add words to the thing
    //look through dictionary and look for one off of things.
    //create list of probable words
    //create




    //first check should be is it in the dictionary??
    //make as many helper functions as you can. Alterations etc.

}
