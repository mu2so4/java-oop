package mur.lab3.operator;

import mur.lab3.calculator.Calculator;
import mur.lab3.calculator.Command;
import mur.lab3.calculator.Context;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class OperatorFactoryTest {
    @Test
    @DisplayName("instance all our operators")
    void normalInstance() {
        Class[] classes = {DefineOperator.class, DivideOperator.class, MinusOperator.class,
                MultiplyOperator.class, PlusOperator.class, PopOperator.class, PushOperator.class,
                SkipOperator.class, SkipOperator.class, SqrtOperator.class};
        for(Class cl: classes) {
            assertDoesNotThrow(() -> OperatorFactory.newInstance(cl.getCanonicalName()));
        }
    }

    @Test
    @DisplayName("instance other classes")
    void instanceOtherClasses() {
        assertThrows(InstantiationException.class,
                () -> OperatorFactory.newInstance(Command.class.getCanonicalName()));
        assertThrows(ClassCastException.class,
                () -> OperatorFactory.newInstance(Calculator.class.getCanonicalName()));
        assertThrows(ClassCastException.class,
                () -> OperatorFactory.newInstance(Context.class.getCanonicalName()));
        assertThrows(ClassNotFoundException.class,
                () -> OperatorFactory.newInstance(SqrtOperator.class.getSimpleName()));
    }
}
