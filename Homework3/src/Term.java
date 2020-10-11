public class Term implements Comparable<Term> {

    private int coefficient;
    private int exponent;

    public Term(){
        coefficient = 0;
        exponent = 0;
    }

    public Term(int coefficient, int exponent){
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public int getCoefficient(){
        return coefficient;
    }

    public int getExponent(){
        return exponent;
    }

    public void setCoefficient(int other){
        this.coefficient += other;
    }

    public void setExponent(int other){
        this.exponent += other;
    }

    public int compareTo(Term term) {
        if (this.exponent == term.exponent)
            return 0;
        else if (this.exponent < term.exponent)
            return -1;
        else
            return 1;
    }
}



