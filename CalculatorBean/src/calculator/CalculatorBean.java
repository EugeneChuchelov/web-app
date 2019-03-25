package calculator;

import javax.ejb.Stateful;

@Stateful
public class CalculatorBean implements Calculator {
    private double operand0;

    private double operand1;

    private double result;

    private double memory;

    public void add(){
        result = operand0 + operand1;
    }

    public void subtract(){
        result = operand0 - operand1;
    }

    public void multiplicate(){
        result = operand0 * operand1;
    }

    public void divide(){
        result = operand0/operand1;
    }

    public void saveToMemory(){
        memory = result;
    }

    public void loadToFirst(){
        operand0 = memory;
    }

    public void loadToSecond(){
        operand1 = memory;
    }

    public void clearMemory(){
        memory = 0;
    }

    public void setFirstOp(double value){
        operand0 = value;
    }

    public void setSecondOp(double value){
        operand1 = value;
    }

    public double getFirstOp(){
        return operand0;
    }

    public double getSecondOp(){
        return operand1;
    }

    public double getResult(){
        return result;
    }

    public double getMemory(){
        return memory;
    }
}
