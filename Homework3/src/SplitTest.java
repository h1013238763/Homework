import java.util.LinkedList;

public class SplitTest {

    public static void main(String[] args){

        LinkedList<Term> b = new LinkedList<>();

        String input = "2x^3 + 3x^4 + 4x^5 ";

        input = input.replace(" ", "");                                                             // delete all the space
        input = input.replace("^", "");
        input = input.toLowerCase();

        System.out.println(input+"\n");

        String[] list = input.split("\\+");                                                                      // break input in terms


        for(int i = 0; i < list.length; i ++) { System.out.println(list[i]); }
        System.out.println();

        for(int i = 0; i < list.length; i ++) {
            String[] term = list[i].split("x");
            System.out.println(Integer.valueOf(term[0])+" "+Integer.valueOf(term[1]));
            Term a = new Term(Integer.parseInt(term[0]),Integer.parseInt(term[1]));
            b.add(a);
        }


    }

}
