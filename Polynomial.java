import java.util.LinkedList; //import linked list library
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Polynomial extends JPanel implements ActionListener { //beginning of Polynomial class

    JTextField t1, t2, t3, t4;
    JTextArea t5;
    JLabel p1, p2, op, val, exp;
    JComboBox<String> operator;
    private LinkedList<Term> poly; 

    public Polynomial() { //default constructor
        poly = new LinkedList<Term>();
    }

    /**
     * Creates the panel with a null layout. Initializes the linked list and initializes the 
     * elements of the frame. 
     * @param width width of the panel
     * @param height height of the panel
     */
    public Polynomial(int width, int height) { //explicit constructor
        poly = new LinkedList<Term>(); //initializes the linked list
        setLayout(null); 
        setBackground(new Color(220,220,255));
        setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLUE));
        setPreferredSize( new Dimension(width,height)); //sets the size of the panel

        operator = new JComboBox<String>(); //initializes the elements of the interface
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextArea();
        p1 = new JLabel("Enter the first polynomial");
        p2 = new JLabel("Enter the second polynomial");
        op = new JLabel("Enter the operator");
        val = new JLabel("Enter the value at which the first polynomial is to be evaluated");
        exp = new JLabel("Enter the exponent value");

        operator.addItem("+"); //adds options to the combo box
        operator.addItem("-");
        operator.addItem("*");
        operator.addItem("^ (for 1st polynomial)");
        operator.setBackground(Color.WHITE);

        JButton b1 = new JButton("Compute"); //adds two buttons and implements the action listener interface for both buttons
        b1.addActionListener(this);
        b1.setBackground(Color.GRAY);

        JButton b2 = new JButton("Clear");
        b2.addActionListener(this);
        b2.setBackground(Color.GRAY);

        add(t1); //adds the elements to the frame
        add(t2);
        add(t3);
        add(t4);
        add(t5);
        add(val);
        add(exp);
        add(p1);
        add(p2);
        add(op);
        add(b1);
        add(operator);
        add(b2);

        p1.setBounds(15, height-490, width-30, 23); //sets the size and the location of the elements in the frame
        t1.setBounds(12, height-465, (width-165)/2, 28);
        p2.setBounds(15, height-425, width-30, 23);
        t2.setBounds(12, height-400, (width-165)/2, 28);
        val.setBounds(15, height-360, width-30, 23);
        t3.setBounds(12, height-335, (width-165)/2, 28);    
        exp.setBounds(15, height-295, width-30, 23);
        t4.setBounds(12, height-270, (width-165)/2, 28);
        b1.setBounds(width/2-10, height-303, (width-30)/2, 28);        
        operator.setBounds(width/2-10, height-433, (width-40)/2, 28);
        op.setBounds(244, height-458, width-30,23);
        t5.setBounds(12, height-225, width-24, 172);
        b2.setBounds(width/2-10, height-40, (width-30)/2, 28);
    }

    public LinkedList getLinkedList() { 
        return poly; //returns the linked list
    }

    public void getInput(String input) { //receives string polynomial and creates a linked list out of it
        String[] splitString = input.split("(?=[+-])"); //splits string with + and - signs as delimiters
        createNode(splitString); //calls createNode method to create linked list 
    }

    /**
     * when user clicks on the button, the below method will implement what function to
     * perform. 
     */
    public void actionPerformed(ActionEvent event) { 
        String action = event.getActionCommand();
        if (action.equals("Compute")) {
            if (t1.getText().equals("") || t2.getText().equals("")) { //checks for empty text fields
                t5.setText("Enter both polynomials.");
            }
            else {
                Polynomial y = new Polynomial(); //instantiates the two objects 
                Polynomial x = new Polynomial();
                x.getInput(t1.getText());
                y.getInput(t2.getText());
                switch (operator.getSelectedIndex()) { //switch case for the option chosen 
                    case 0: //add
                        if (t3.getText().equals("")) {
                        x.add(y);
                        t5.setText("The resultant polynomial is: " + x.toString() + "\n");
                        } 
                        else {
                            double val = x.evaluate(Double.parseDouble(t3.getText()));
                            x.add(y);
                            t5.setText("The resultant polynomial is: " + x.toString() + "\n"); 
                            t5.append("The evaluated value of the first polynomial is: " + val);
                        }   
                        break;   
                
                    case 1: //subtract
                        if (t3.getText().equals("")) {
                            x.subtract(y);
                            t5.setText("The resultant polynomial is: " + x.toString() + "\n");
                        }
                        else {
                            double val = x.evaluate(Double.parseDouble(t3.getText()));
                            x.subtract(y);
                            t5.setText("The resultant polynomial is: " + x.toString() + "\n"); 
                            t5.append("The evaluated value of the first polynomial is: " + val);
                        }
                        break;

                    case 2: //multiply
                        if (t3.getText().equals("")){
                            x.multiply(y);
                            t5.setText("The resultant polynomial is: " + x.toString() + "\n");
                        }
                        else {
                            double val = x.evaluate(Double.parseDouble(t3.getText()));
                            x.multiply(y);
                            t5.setText("The resultant polynomial is: " + x.toString() + "\n"); 
                            t5.append("The evaluated value of the first polynomial is: " + val);
                        }
                        break;
                
                    case 3: //exponentiation
                        Polynomial temp = new Polynomial();
                        temp.getInput(t1.getText()); 
                        if (t4.getText().equals("")) { //checks if user has not inputed the exponent value
                            t5.setText("Error. Please enter exponent value");
                        }
                        else if (t3.getText().equals("")) {
                            x.exponentiation(Integer.parseInt(t4.getText()), temp);
                            t5.setText("The resultant polynomial is: " + x.toString() + "\n");
                        }
                        else {   
                            double val = x.evaluate(Double.parseDouble(t3.getText()));
                            x.exponentiation(Integer.parseInt(t4.getText()), temp);
                            t5.setText("The resultant polynomial is: " + x.toString() + "\n"); 
                            t5.append("The evaluated value of the first polynomial is: " + val); 
                        }
                        break;
                }
            }    
        }
        
        else if (action.equals("Clear")) {
            t5.setText("");
        }
    }

    public void AddTerm(int coefficient, int exponent) { //adds term to linked list with coefficient and exponent passed in
        Term t1 = new Term(coefficient, exponent); //creates new Term node
        if (exponent > poly.getFirst().getExponent()) { //checks if new node could be the new head of the linked list
            poly.addFirst(t1);
        }
        else {
            for (int j = 0; j < poly.size(); j++) { //traverses through linked list
                if (poly.get(j).getExponent() == exponent) { //checks for existing exponent to overwrite it
                    Term t2 = new Term(coefficient+poly.get(j).getCoefficient(), exponent); //creates new node with sum of coefficients
                    poly.set(j, t2); //sets the node at that position to the new node
                    break;
                }
                else if (poly.getLast() == poly.get(j)) { //checks for the end of the linked list
                    poly.addLast(t1);
                    break;
                }
                else if (poly.get(j).getExponent() > t1.getExponent() && t1.getExponent() > poly.get(j+1).getExponent()) { //inserts node in appropriate position
                    poly.add(j+1, t1);
                    break;
                }
            }
        }         
    }

    public void SubTerm(int coefficient, int exponent) { //adds term to linked list with coefficient and exponent passed in
        Term t1 = new Term(-coefficient, exponent); //creates new Term node
        if (exponent > poly.getFirst().getExponent()) { //checks if new node could be the new head of the linked list
            poly.addFirst(t1);
        }
        else {
            for (int j = 0; j < poly.size(); j++) { //traverses through linked list
                if (poly.get(j).getExponent() == exponent) { //checks for existing exponent to overwrite it
                    Term t2 = new Term(poly.get(j).getCoefficient()-coefficient, exponent); //creates new node with sum of coefficients
                    poly.set(j, t2); //sets the node at that position to the new node
                    break;
                }
                else if (poly.getLast() == poly.get(j)) { //checks for the end of the linked list
                    poly.addLast(t1);
                    break;
                }
                else if (poly.get(j).getExponent() > t1.getExponent() && t1.getExponent() > poly.get(j+1).getExponent()) { //inserts node in appropriate position
                    poly.add(j+1, t1);
                    break;
                }
            }
        }         
    }

    public String toString() { //outputs the linked list as the polynomial in the standard form
        String polynomial = ""; //initializes string to null string
        for (Term temp: poly) {
            if (temp.getCoefficient() == 0) {
                continue;
            }

            else if (temp != poly.getFirst() && temp.getCoefficient() > 0) { //checks for the head of the list for appropriate formatting
                polynomial += "+" + temp.toString();
            }
            else {
                polynomial += temp.toString();
            }  
        }
        if (polynomial.equals("")) {
            return "0";
        }
        else if (polynomial.charAt(0) == '+') {
            String newPolynomial = polynomial.substring(1, polynomial.length());
            return newPolynomial;
        }
        return polynomial; //returns the created polynomial string
    }
    
    public void createNode(String[] splitString) {
        for (int i = 0; i < splitString.length; i++) { //traverses through the String array created in getInput method
            String[] split = splitString[i].split("[x^]"); //splits string using 'x' and '^' as delimiters
            if (split.length == 3) { //checks for the length of the newly created string array
                String node = split[0]; //string value of the coefficient in the string array
                switch (node) { //switch case for coefficient 
                    case "-": 
                        Term t1 = new Term(-1, Integer.parseInt(split[2])); //Integer wrapper class used to convert the string values to integers
                        poly.add(t1);
                        break;
                    case "+":
                        Term t2 = new Term(1, Integer.parseInt(split[2]));
                        poly.add(t2);
                        break;
                    case "":
                        Term t3 = new Term(1, Integer.parseInt(split[2]));
                        poly.add(t3);
                        break;                   
                    default:
                        Term t4 = new Term(Integer.parseInt(split[0]), Integer.parseInt(split[2]));
                        poly.add(t4);
                        break;
                }
            }
            else if (split.length == 1) { 
                if (splitString[i].contains("x")==false) { //checks if the original string contains the variable term 'x'
                    Term t1 = new Term(Integer.parseInt(split[0]), 0); 
                    poly.add(t1);
                }
                else if (splitString[i].contains("x")==true) { 
                    String node = split[0];
                    switch (node) {
                        case "-":
                            Term t1 = new Term(-1, 1);
                            poly.add(t1);
                            break;
                        
                        case "+":
                            Term t2 = new Term(1, 1);
                            poly.add(t2);
                            break;

                        default:
                            Term t3 = new Term(Integer.parseInt(split[0]), 1);
                            poly.add(t3);
                            break;
                    }
                }
            }
            else if (split.length == 0) { //checks if there is leading term 'x'
                Term t1 = new Term (1,1);
                poly.add(t1);
            }
        }
    }

    public double evaluate(double value) { //evaluates the total value of the polynomial with the passed in value
        double totalValue = 0; //initializes the total value
        for (Term temp: poly) {
            totalValue += temp.evaluate(value); //calls the evaluate method in the Term class to evaluate value of the node
        }
        return totalValue; //returns the calculated value
    }

    public void add(Polynomial y) { //adds two polynomials together and modifies the linked list
        for (Term temp: y.poly) {        
            AddTerm(temp.getCoefficient(), temp.getExponent()); //calls the AddTerm method to either add or insert the node      
        }
    }

    public void subtract(Polynomial y) { //subtracts two polynomials together and mo
        for (Term temp: y.poly) {
            SubTerm(temp.getCoefficient(), temp.getExponent());
        }
    }

    public void multiply(Polynomial y) { //multiplies the polynomials together
        LinkedList<Term> newPoly = new LinkedList<Term>(); //initializes new linked list which is the product of the two polynomials
        for (Term temp: poly) { //traverses through the first polynomial
            for (Term newTemp: y.poly) { //traverses through second polynomial for distributive property of multiplication
                Term newTerm = temp.multiply(temp, newTemp);
                if (newPoly.size() == 0) { //if linked list is empty, term is just simply added
                    newPoly.add(newTerm);
                }
                else if (newTerm.getExponent() > newPoly.getFirst().getExponent()) { //checks if there could be a new head of the linked list
                    newPoly.addFirst(newTerm); 
                }
                else {
                    for (int j = 0; j < newPoly.size(); j++) { //traverses through the newly created linked list
                        if (newPoly.get(j).getExponent() == newTerm.getExponent()) { //if exponent already exists, it adds the two coefficients together
                            Term t2 = new Term(newTerm.getCoefficient()+newPoly.get(j).getCoefficient(), newTerm.getExponent());
                            newPoly.set(j, t2); //sets node at that position to new node
                            break;
                        }
                        else if (newPoly.getLast() == newPoly.get(j)) { //checks for the tail of the linked list
                            newPoly.addLast(newTerm);
                            break;
                        }
                        else if (newPoly.get(j).getExponent() > newTerm.getExponent() && newTerm.getExponent() > newPoly.get(j+1).getExponent()) { //inserts term in appropriate position for descending order
                            newPoly.add(j+1, newTerm);
                            break;
                        }
                    }
                }
            }
        }
        poly = newPoly; //after new linked list created, original linked list is modified to new linked list
    }

    public void exponentiation(int exponent, Polynomial y) {
        for (int i = 0; i < exponent-1; i++) {
            multiply(y);
        }
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Polynomial Calculator");
        window.setContentPane(new Polynomial(500,500));
        window.pack();
        window.setResizable(true);
        window.setLocation(150,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
} //end of Polynomial class
