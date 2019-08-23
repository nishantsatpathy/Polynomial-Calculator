import java.lang.Math; //import Math library
public class Term { //beginning of Term class

    private int coefficient; //initialize the coefficient and exponent variables
    private int exponent; 

    public Term(int coeff, int exp) { //explicit constructor
        coefficient = coeff; //sets variables to passed in values
        exponent = exp;
    }

    public void setExponent(int newExp) { //sets exponent to new exponent value
        exponent = newExp; 
    }

    public void setCoefficient(int newCoeff) { //sets coefficient to new coefficient value
        coefficient = newCoeff; 
    }

    public int getExponent() {
        return exponent; //returns the exponent
    }

    public int getCoefficient() {
        return coefficient; //returns the coefficient
    }

    public String toString() { //start of toString method
        String term; //initializes the string term
        switch (exponent) { //creates switch cases for the exponent of the monomial
            case 0: //case for when exponent is zero 
                term = String.valueOf(coefficient);
                return term;
                         
            case 1: //case for when the exponent is one     
                switch (coefficient) { //switch case for coefficient
                    case 1:
                        term = "x";
                        return term; 
                    case -1:
                        term = "-x";
                        return term;
                    default:
                        term = String.valueOf(coefficient) + "x";
                        return term;
                }   
            default: //default case when the exponent is greater than one    
                switch (coefficient) { //switch case for the coefficient
                    case 1:
                        term = "x" + "^" + String.valueOf(exponent);
                        return term;  
                    case -1:
                        term = "-x" + "^" + String.valueOf(exponent);
                        return term;
                    default:
                        term = String.valueOf(coefficient) + "x" + "^" + String.valueOf(exponent);
                        return term;
                }   
        }     
    }

    public double evaluate(double value) { //evaluates the term with the passed in value
        double numValue = 0;
        numValue = Double.valueOf(coefficient) * (Math.pow(value, Double.valueOf(exponent)));
        return numValue; //returns the value of the term
    }

    public Term multiply(Term mono1, Term mono2) { //evaluates the product of the two terms
        int newCoeff = mono1.getCoefficient() * mono2.getCoefficient();
        int newExp = mono1.getExponent() + mono2.getExponent();
        Term newTerm = new Term(newCoeff, newExp);
        return newTerm; //returns the product of the two terms
    }
}//end of Term class