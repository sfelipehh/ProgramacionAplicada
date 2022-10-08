package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;

import java.util.function.BiFunction;

public abstract class CalculatorBaseController implements Initializable {
    enum ButtonCodes{
        Op2(""),
        Pow2("%.4f^"),
        Root2("√%.4f"),
        yPow("%.4f^"),
        x10Pow("10^%.4f"),
        Log10("log(%.4f)"),
        eLog("ln(%.4f)"),
        piConstant("\uD835\uDED1"),
        inverso("1/%.4f"),
        parentesisOpen("("),
        seven("\uD835\uDFF3"),
        four("\uD835\uDFF0"),
        one("\uD835\uDFED"),
        sign("∓"),
        eConstant("e"),
        abs("|%.4f|"),
        parentesisClose(")"),
        eight("\uD835\uDFF4"),
        five("\uD835\uDFF1"),
        two("\uD835\uDFEE"),
        cero("\uD835\uDFEC"),
        clear(""),
        exp10(",e+%.4f"),
        factorial("fact(%.4f)"),
        nine("\uD835\uDFF5"),
        six("\uD835\uDFF2"),
        three("\uD835\uDFEF"),
        floatPoint(","),
        delete(""),
        mod(" Mod "),
        division(" ÷ "),
        multiply(" × "),
        less(" − "),
        add(" + "),
        equal(" = "),

        a("A"),

        b("B"),

        c("C"),

        d("D"),

        e("E"),

        f("F");

        private final String symbol;
        ButtonCodes(String symbol){
            this.symbol = symbol;
        }
        public String getSymbol(){
            return symbol;
        }
    }
    enum CalculationState{
        FIRST_INPUT, SECOND_INPUT, ACTION_CHOOSE, ACTION_PERFORMED;
    }
    protected final String[] memory = new String[]{"0", "0", "0"};
    protected final ObjectProperty<CalculationState> stateProperty = new SimpleObjectProperty<>(this, "state", CalculationState.FIRST_INPUT);
    protected BiFunction<Double,Double,Double> actionChosen;
    protected final StringBuilder memoryStringBuilder = new StringBuilder();
    protected final StringBuilder actualStringBuilder = new StringBuilder();
    public abstract String getCalculatorName();
}
