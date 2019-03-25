package calculator;

import javax.ejb.Remote;

@Remote
public interface Calculator {
    void add();

    void subtract();

    void multiplicate();

    void divide();

    void saveToMemory();

    void loadToFirst();

    void loadToSecond();

    void clearMemory();

    void setFirstOp(double value);

    void setSecondOp(double value);

    double getFirstOp();

    double getSecondOp();

    double getResult();

    double getMemory();
}
