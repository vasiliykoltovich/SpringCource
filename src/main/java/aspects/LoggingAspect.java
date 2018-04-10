package aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

@Aspect
//@Component
public class LoggingAspect {

    private Map<Class<?>, Integer> counter;

    //pointcut
    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventsMethods() {
    }

//    @Pointcut("execution(* *.logEvent(..)) ")
    @Pointcut("within(client.ConsoleEventLogger*)")
    private void consoleLoggerMethods() {

    }

    //    //advice
    //    @Before("allLogEventsMethods()")
    //    public void logBefore(JoinPoint joinpoint) throws IOException {
    //
    //        Files.write(Paths.get("aspect-log.txt"),
    //                    ((joinpoint.getTarget().getClass().getSimpleName() + " : " + joinpoint.getSignature().getName()) + System.lineSeparator()).getBytes(),
    //                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    //
    //    }

    //    @AfterReturning(pointcut = "allLogEventsMethods()", returning = "retval")
    //    public void logAfter(Object retval) throws IOException {
    //        Files.write(Paths.get("aspect-log.txt"), ("потрачено:" + retval + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE,
    //                    StandardOpenOption.APPEND);
    //    }

    @AfterReturning(pointcut = "allLogEventsMethods()")
    public void count(JoinPoint joinPoint) throws IOException {
        Class<?> clazz = joinPoint.getTarget().getClass();
        if (!counter.containsKey(clazz)) {
            counter.put(clazz, 0);
        }
        counter.put(clazz, counter.get(clazz) + 1);

        Files.write(Paths.get("aspect-log.txt"),
                    ((joinPoint.getTarget().getClass().getSimpleName() + " : " + joinPoint.getSignature().getName() + " called by counter " + counter.get(
                            joinPoint.getTarget().getClass()) + "/" + counter.size()) + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
    }

    @Around("consoleLoggerMethods() && args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint pjp,Object evt) throws Throwable {

        if(counter.get(pjp.getTarget().getClass())!=null && counter.get(pjp.getTarget().getClass()) <2){
            pjp.proceed(new Object[]{evt});
        }else{
            ///
            System.out.println("MORE THEN 2");
        }
    }

    public void setCounter(Map<Class<?>, Integer> counter) {
        this.counter = counter;
    }
}
