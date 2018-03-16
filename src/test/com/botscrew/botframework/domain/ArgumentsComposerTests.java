package com.botscrew.botframework.domain;

import com.botscrew.botframework.exception.ProcessorInnerException;
import com.botscrew.botframework.model.ArgumentType;
import com.botscrew.botframework.model.CompositeParameter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Parameter;
import java.util.Collections;
import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArgumentsComposerTests {


    @Test(expected = ProcessorInnerException.class)
    public void shouldThrowExceptionIfNoApplicableConverter() {
        Parameter param = TestClass.class.getMethods()[0].getParameters()[0];

        CompositeParameter parameter = new CompositeParameter(ArgumentType.PARAM_INTEGER, param);

        ArgumentsComposer composer = new ArgumentsComposer(Collections.singletonList(parameter), new HashMap<>());

        ArgumentKit kit = new DefaultArgumentKit();
        kit.put(ArgumentType.PARAM_INTEGER, new SimpleArgumentWrapper("2"));

        Object[] compose = composer.compose(kit);
    }

    private static class TestClass {

        public static void testMethod(Integer intParam) {
        }
    }
}
