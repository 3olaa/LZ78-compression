import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Vector;

public class LZ78_Compression {
    Vector dictionary = new Vector();
    Vector position = new Vector();
    Vector next_Symbol = new Vector();

    public void compression(){
        System.out.println("**LZ87_Compression** ");
        dictionary.add(null);
        System.out.print("Enter your Text: ");
        Scanner in = new Scanner(System.in);
        String word = in.next();
        String nextSymbol = "";
        String symbol = "";
        for(int i = 0 ; i < word.length() ; i++){
            nextSymbol += word.charAt(i);
            if(nextSymbol.length() == 1 && !dictionary.contains(nextSymbol)){ //the first case
                position.add(0);
                next_Symbol.add(word.charAt(i));
                dictionary.add(nextSymbol);
                nextSymbol = "";
            }
            else if(dictionary.contains(nextSymbol)){
                if(i == word.length()-1){ //if i have reached the end of the text
                    position.add(dictionary.indexOf(nextSymbol));
                    next_Symbol.add(null);
                    break;
                }
                else{
                    symbol = nextSymbol; //to take the index of the symbol
                    continue;
                }
            }
            else if(!dictionary.contains(nextSymbol)){
                position.add(dictionary.indexOf(symbol));
                next_Symbol.add(word.charAt(i));
                dictionary.add(nextSymbol);
                nextSymbol = "";
                symbol = "";
            }
        }
    }
    public void print_tags(){
        System.out.println("Tags : ");
        for(int i = 0 ; i < position.size() ; i++){
            System.out.println("< " + position.get(i) + " , " + next_Symbol.get(i) + " >");
        }
    }
    public void print_dictionary(){
        System.out.println("Dictionary : ");
        for(int i = 0 ; i < dictionary.size() ; i++){
            System.out.print(i);
            System.out.println("     " + dictionary.get(i));
        }
        dictionary.clear();
    }

    public void decompression (){
        Vector <String> symbol = new Vector();
        Vector <Integer> myPosition = new Vector();
        dictionary.add(null);
        String text = "";
        System.out.print("Enter how many tags you want to decompress: ");
        Scanner in = new Scanner(System.in);
        Scanner pos = new Scanner(System.in);
        Scanner nxtSymbol = new Scanner(System.in);
        int tagsNum = in.nextInt();
        System.out.println("Enter your Tags");
        for (int i = 0 ; i < tagsNum ; i++){
            int x = pos.nextInt();
            String s = nxtSymbol.next();
            myPosition.add(x);
            symbol.add(s);
        }
        System.out.print("you have Entered: ");
        for (int k = 0 ; k < myPosition.size(); k++){
            System.out.print("<" + myPosition.get(k) + "," + symbol.get(k) + "> ");
        }
        System.out.println("");

        for (int i = 0 ; i< myPosition.size(); i++){
            if (myPosition.elementAt(i) == 0){
                text += symbol.get(i);
                dictionary.add(symbol.get(i));
            }
            else if (symbol.elementAt(i).equalsIgnoreCase("null")){ // <1,null>
                int index = myPosition.elementAt(i);
                text += dictionary.get(index);
            }
            else {
                int index = myPosition.elementAt(i);
                dictionary.add(dictionary.get(index) + symbol.get(i));
                text += dictionary.get(index);
                text += symbol.get(i);
            }
        }
        System.out.println("your Text is: " + text);

        //Collections are like containers that group multiple items in a single unit
        Collection noDup = new LinkedHashSet(dictionary); //removing duplicates from the dictionary by puting duplicate vector in linkedHashSet which will remove the duplicate values.
        dictionary.clear();
        dictionary.addAll(noDup);
    }
    public static void main(String args[]){

        /*LZ78_Compression c1 = new LZ78_Compression();
        c1.compression();
        c1.print_tags();
        c1.print_dictionary();*/

        LZ78_Compression c2 = new LZ78_Compression();
        c2.decompression();
        c2.print_dictionary();





    }
}
