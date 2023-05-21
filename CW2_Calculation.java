/**
 * CW2_Calculation
 * 
 * Module to perform calculation from GUI
 * 
 * @author    Matthew Gordon / Conor Healy 
 * 
 */
public class CW2_Calculation
{
    private double result;              // current result of calculation
    private String lastOperator;        // operator

   /**
   * Constructor
   */ 
    CW2_Calculation()
    {
        // reset the calculator ro zero and set the operator to addition
        clear();
    }

   /**
   * Reset calculator
   * @exception Any exception
   * @return No return value.
   */ 
    public void clear()
    {
        // Hold the current result
        result = 0;
        // What the last operator is
        lastOperator = "+";
    }

   /**
   * Set the result
   * @param number the number
   * @exception Any exception
   * @return No return value.
   */ 
    public void setResult(double number)
    {
        result = number;
    }

   /**
   * Get the last operator
   * @exception Any exception
   * @return operator
   */ 
    public String getLastOperator()
    {
        // return the last operator
        return lastOperator;
    }

   /**
   * Execute the calculation
   * @param operator addition, subtraction, multiplication and division
   * @param number the number
   * @exception Any exception
   * @return No return value.
   */ 
    public double calculate(String operator, double number)
    {   
        // Works on operating on the last operator not the current one
        // so that you can continously press the operator buttons
        // like a nornmal calculator
        //
        // Switch the last operator
        switch (lastOperator)
        {
            // Addition
            case "+":
            {
                result = result + number;
                break;
            }
            // Subtraction
            case "-":
            {
                result = result - number;
                break;
            }
            // Multiplication
            case "X":
            {
                result = result * number;
                break;
            }
            // Division
            case "÷":
            {
                result = result / number;
                break;
            }
        }
        // Set the last operator to the current operator
        lastOperator = operator;
        // return the result
        return result;
    }
}