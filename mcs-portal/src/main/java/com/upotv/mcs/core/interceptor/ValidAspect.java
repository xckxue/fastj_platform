package com.upotv.mcs.core.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import com.upotv.mcs.core.exception.ParamValidException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by wow on 2017/7/24.
 */
@Aspect
@Component
public class ValidAspect {
    private ObjectError error;

    @Pointcut("execution(public * com.upotv..*.controller.*.*(..))")
    public void validParam(){};

    ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Before("validParam()")
    public void before(JoinPoint point) throws NoSuchMethodException, SecurityException, ParamValidException{
        // 获得切入方法参数
        Object [] args = point.getArgs();
        //取参数，如果没参数，那肯定不校验了
        if (args.length == 0) {
            return;
        }

        //bean类方式
        List<ObjectError> errors = new ArrayList<>();
        for (Object object : args) {
            if (object instanceof BeanPropertyBindingResult) {
                //有校验
                BeanPropertyBindingResult result = (BeanPropertyBindingResult) object;
                if (result.hasErrors()) {
                    errors.addAll(result.getAllErrors());
                }
            }
        }

        //基本数据类型
        //获得切入目标对象
        Object target = point.getThis();
        // 获得切入的方法
        Method method = ((MethodSignature)point.getSignature()).getMethod();
        // 执行校验，获得校验结果
        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, args);
        //如果有校验不通过的
        if (!validResult.isEmpty()) {
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method); // 获得方法的参数名称
            for(ConstraintViolation<Object> constraintViolation : validResult) {
                PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();  // 获得校验的参数路径信息
                int paramIndex = pathImpl.getLeafNode().getParameterIndex(); // 获得校验的参数位置
                String paramName = parameterNames[paramIndex];  // 获得校验的参数名称
                ObjectError error = new ObjectError(paramName,constraintViolation.getMessage());  // 将需要的信息包装成简单的对象，方便后面处理
                errors.add(error);
            }
        }

        if(errors.size() > 0 ){
            throw new ParamValidException(errors);  // 我个人的处理方式，抛出异常，交给上层处理
        }
    }

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator validator = factory.getValidator().forExecutables();

    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object [] params){
        return validator.validateParameters(obj, method, params);
    }
}