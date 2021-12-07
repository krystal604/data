package com.lnu.structure.Queue.Element;
/**
 *@Description : TODO 多项式实体累
 *@Date :  20:34
 *@Version : 1.0
 **/
public class ElementNode {
    //系数
    private int coefficients;
    //指数
    private int exponential ;

    public ElementNode() {
    }

    public ElementNode(int coefficients, int exponential) {
        this.coefficients = coefficients;
        this.exponential = exponential;
    }

    public int getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(int coefficients) {
        this.coefficients = coefficients;
    }

    public int getExponential() {
        return exponential;
    }

    public void setExponential(int exponential) {
        this.exponential = exponential;
    }

    @Override
    public String toString() {
        return "ElementNode{" +
                "coefficients=" + coefficients +
                ", exponential=" + exponential +
                '}';
    }
}
