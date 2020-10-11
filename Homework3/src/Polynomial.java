import java.util.LinkedList;

public interface Polynomial {

    /** Break user's input as term and sort them*/
    static LinkedList<Term> breakInOrder(String input){
        LinkedList<Term> resultBeforeSort = new LinkedList<>();
        LinkedList<Term> result = new LinkedList<>();
        input = input.replace(" ", "");                                                             // delete all the space
        input = input.replace("^", "");
        input = input.toLowerCase();

        System.out.println(input);

        String[] list = input.split("\\+");                                                                      // break input in terms


        for(int i = 0; i < list.length; i ++) { System.out.println(list[i]); }


        for(int i = 0; i < list.length; i ++) {
            String[] term = list[i].split("x");
            resultBeforeSort.add(new Term(Integer.parseInt(term[0]),Integer.parseInt(term[1])));                      // store terms
        }

        int length = resultBeforeSort.size();

        for(int i = 0; i < length; i ++){
            int index = -1;
            for(int j = 0; j < resultBeforeSort.size(); j ++){                                                          // loop through the list
                int max = -1;
                if(resultBeforeSort.get(j).getExponent() > max) {                                                       // find the term with largest exponent
                    max = resultBeforeSort.get(j).getExponent();
                    index = j;
                }
            }
            result.addLast(resultBeforeSort.get(index));                                                                // add it at the last of list
            resultBeforeSort.remove(index);
            index = -1;
        }
        return result;
    }

    /** Clear the polynomial*/
    static void clear(LinkedList<Term> poly) {
        poly.clear();
    }

    /** Add terms. for different exponents, put them in order. for same exponent, add them together*/
    static public LinkedList<Term> addTerm(LinkedList<Term> poly, LinkedList<Term> term){
        LinkedList<Term> result = new LinkedList<>();

        int polyIndex = 0;
        int termIndex = 0;
        while(termIndex < term.size()){
            if(polyIndex >= poly.size()){                                                                               // if all poly have been tested and larger than the term left
                result.add(term.get(termIndex));                                                                        // just add all term left at the last of result
                termIndex ++;
            }
            if(poly.get(polyIndex).compareTo(term.get(termIndex)) == 0){                                                // if two terms have same exponent, add them together
                result.add(new Term(                                                                                    // add the new term at the last of result
                        (poly.get(polyIndex).getCoefficient()+term.get(termIndex).getCoefficient()),
                        poly.get(polyIndex).getExponent()));
                termIndex ++;                                                                                           // go on to next term
                polyIndex ++;
            }
            else if(poly.get(polyIndex).compareTo(term.get(termIndex)) == 1) {                                          // if poly term has larger exponent
                result.add(new Term(poly.get(polyIndex).getCoefficient(),poly.get(polyIndex).getExponent()));           // add it at the last of result
                polyIndex ++;                                                                                           // move on to next poly for test
            }
            else if(poly.get(polyIndex).compareTo(term.get(termIndex)) == -1){                                          // if term's term has larger exponent
                result.add(new Term(term.get(termIndex).getCoefficient(),term.get(termIndex).getExponent()));           // add it at the last of result
                termIndex ++;                                                                                           // move on to next term for test
            }
        }
        while(polyIndex < poly.size()){                                                                                 // if any poly left after all term have been tested
            result.add(poly.get(polyIndex));                                                                            // add all poly at the last of result
            polyIndex++;
        }
        return result;
    }

    /** overwrite for user input*/
    static public LinkedList<Term> addTerm(LinkedList<Term> poly, String input) {
        LinkedList<Term> term = breakInOrder(input);
        return addTerm(poly,term);
    }
}
