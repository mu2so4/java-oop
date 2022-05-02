package mur.lab2.operator;

import mur.lab2.calculator.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OperatorFactoryTest {
    @ParameterizedTest
    @ValueSource(classes = {DefineOperator.class, DivideOperator.class, MinusOperator.class,
            MultiplyOperator.class, PlusOperator.class, PopOperator.class, PushOperator.class,
            SkipOperator.class, SkipOperator.class, SqrtOperator.class})
    @DisplayName("instance all our operators")
    void normalInstance(Class<Operator> operatorClass) {
            assertDoesNotThrow(() -> OperatorFactory.newInstance(operatorClass.getCanonicalName()));
    }

    @Test
    @DisplayName("with reading from a resource")
    void read() throws IOException {
        Map<String, Operator> operators = OperatorFactory.loadCommandsFromResource("operators.conf");
    }
}
