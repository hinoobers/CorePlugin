package org.hinoob.coreplugin.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class ReflectionUtil {

    public Optional<Method> isMethodFound(Class<?> clazz, String name){
        return Optional.of(Arrays.stream(clazz.getDeclaredMethods()).filter(method -> method.getName().equals(name)).findFirst().get());
    }

    public List<Parameter> getMethodParameters(Method method){
        return Arrays.asList(method.getParameters());
    }
}
