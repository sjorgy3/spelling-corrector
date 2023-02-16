package spell;

public class Trie implements ITrie{
    private Node root = new Node();
    private int wordCount = 0;
    private int nodeCount = 1;
    @Override
    public void add(String word) {

        INode currentNode = root;
        word = word.toLowerCase();

        for (int i = 0; i < word.length(); i++) {
            char letterToAdd = word.charAt(i);
            int index = letterToAdd - 'a';
            if (currentNode.getChildren()[index] == null){
                Node newNode = new Node();
                currentNode.getChildren()[index] = newNode;
                nodeCount++;
                currentNode = currentNode.getChildren()[index];
                if (i == word.length()-1){
                    currentNode.incrementValue();
                    wordCount++;
                }
            }
            else{
                currentNode = currentNode.getChildren()[index];
                if (i == word.length()-1){
                    currentNode.incrementValue();
                    if (currentNode.getValue() == 1){
                        wordCount++;
                    }
                }
            }
        }

    }

    @Override
    public INode find(String word) {
        INode currentNode = root;
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            char letterToFind = word.charAt(i);
            int index = letterToFind - 'a';
            if (currentNode.getChildren()[index] == null) {
                return null;
            }
            currentNode = currentNode.getChildren()[index];
            }
        if(currentNode.getValue() == 0){
            return null;
        }
        return currentNode;
    }

    @Override
    public int getWordCount() {
        return wordCount;
    }

    @Override
    public int getNodeCount() {
        return nodeCount;
    }

    @Override
    public String toString(){
        StringBuilder curWord = new StringBuilder();
        StringBuilder output = new StringBuilder();
        toString_Helper(root,curWord,output);
        return output.toString();
    }
    private void toString_Helper(INode n, StringBuilder curWord, StringBuilder output){
        if(n.getValue() > 0){
            //Append the nod's word to the output
            output.append(curWord.toString());
            output.append("\n");
        }

        for (int i = 0; i <n.getChildren().length; i++) {
            INode child = n.getChildren()[i];
            if (child != null){
                char childLetter = (char) ('a' + i);
                curWord.append(childLetter);
                toString_Helper(child, curWord, output);

                curWord.deleteCharAt(curWord.length()-1);

            }
        }



    }



    @Override
    public int hashCode(){
        int hCode = nodeCount * wordCount;
        for (int i = 0; i < root.getChildren().length; i++) {

            if (root.getChildren()[i] != null){
                hCode = hCode * i;

            }
        }
        //take the value of the trie and calculate an integer based on the tree.
        // combin the following values
        //1. wordcount
        //2. nodecount
        //3. the index of each of the root node's non-null children // more info in the hashcode to make it more unique.

        return hCode;
    }

    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if(o == this){
            return true;
        }
        if(o.getClass() != this.getClass()){
            return false;
        }
        //check trees to see if you have identical structure.
       Trie d = (Trie) o;
       //do this and d have the same wordCount and nodeCount
        if(this.wordCount != d.wordCount || this.nodeCount != d.nodeCount){
            return false;

        }


        return equals_Helper(this.root, d.root);
    }
    private boolean equals_Helper(INode n1, INode n2){
        //ensures they have the same count
        if(n1.getValue() != n2.getValue()){
            return false;
        }
        //goes the children of n1 and n2 and checks to see if one value is null and the other isn't and vice versa
        for (int i = 0; i < n1.getChildren().length; i++) {
            if(n1.getChildren()[i] == null && n2.getChildren()[i] != null){
                return false;
            }
            if(n1.getChildren()[i] != null && n2.getChildren()[i] == null){
                return false;
            }


        }
        //goes through all of the children and if they are not null traverses to them and goes through their contents.
            for (int i = 0; i < n1.getChildren().length; i++) {
                if (n1.getChildren()[i] != null && n2.getChildren()[i] != null) {
                    boolean returnedEquals = equals_Helper(n1.getChildren()[i], n2.getChildren()[i]);
                    if(returnedEquals == false){
                        return false;
                    }
                }
            }

        return true;



        /*if(n1.getValue() == n2.getValue()){
            for (int i = 0; i < n1.getChildren().length; i++) {
                if((n1.getChildren()[i] != null && n2.getChildren()[i] == null) ||(n1.getChildren()[i] == null && n2.getChildren()[i] != null) ){
                    return false;
                }
                if(n1.getChildren()[i] != null) {
                    equals_Helper(n1.getChildren()[i], n2.getChildren()[i]);
                }
                //try and figure out what to do when the children are null, should mean that in some way they are equivelant.
            }
            *//*for (int i = 0; i < n1.getChildren().length; i++) {

            }*//*

        }
        else{
            return false;
        }

        //compare n1 and n2 to see if they are the same or do they look equal
            // do n1 and n2 have the same count?
            // same count; do n1 and n2 hace non-null children in exactly the same indexes.
        //recurse on the children and compare the child subtrees.

        return true;*/
    }







}
