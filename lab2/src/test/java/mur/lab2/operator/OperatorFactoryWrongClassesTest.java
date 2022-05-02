package mur.lab2.operator;

import mur.lab2.calculator.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperatorFactoryWrongClassesTest {
    @Test
    @DisplayName("instance other classes")
    void instanceOtherClasses() {
        assertThrows(InstantiationException.class,
                () -> OperatorFactory.newInstance(Query.class.getCanonicalName()));
        assertThrows(InstantiationException.class,
                () -> OperatorFactory.newInstance(Calculator.class.getCanonicalName()));
        assertThrows(ClassCastException.class,
                () -> OperatorFactory.newInstance(Context.class.getCanonicalName()));
        assertThrows(ClassNotFoundException.class,
                () -> OperatorFactory.newInstance(SqrtOperator.class.getSimpleName()));
    }
}
