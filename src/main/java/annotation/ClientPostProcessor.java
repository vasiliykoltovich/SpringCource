package annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClientPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Field[] fields = bean.getClass().getDeclaredFields();

        for (Field field : fields) {
            SetInject annotation = field.getAnnotation(SetInject.class);
            if (annotation != null) {
//                Object value = field.get(bean);
//                Set<String> value = convertToSet(annotation.propertyValue());
//                System.out.println("Elements in set BPP: ");
//                new ArrayList(value).stream().forEach(System.out::println);
                try {
                    ReflectionUtils.makeAccessible(field);
                    Object valueSet = field.get(bean);
                    Set<String> value = convertToSet((Set)valueSet);

                    ReflectionUtils.setField(field,bean,value);

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return bean;

    }

    private Set<String> convertToSet(Set<String> value) {
        return  new HashSet<>(Arrays.asList(value.iterator().next().split(",")));
    }
    private Set<String> convertToSet(String value) {
     return  new HashSet<>(Arrays.asList(value.split(",")));
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return null;
    }
}
