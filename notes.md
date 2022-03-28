#### 2022.03.25（初步完成登录功能）

在控制器的方法doLogin传入的参数loginVo加上@Valid注解，对loginVo进行校验；

在LoginVo类的属性上加上已有的@NotNull注解以及自己定义的@IsMobile注解，对手机号码的格式进行校验；

在类IsMobile上加上固有注释，message写校验错误的信息。在@Constraint里定义自己的校验规则，写成IsMobileValidator类，继承ConstraintValidator类；

重写isValid方法，调用ValidatorUtil判断手机号是否合格；

**若不合格：**

定义GlobalException类，继承RuntimeException，有RespBeanEnum属性；

定义GlobalExceptionHandler，加上@RestControllerAdvice注解，处理全局异常；

如果捕获到的是全局异常，则根据异常类型返回对应的RespBean；

如果捕获到的是校验的异常，则抛出RespBean.error(RespBeanEnum.BIND_ERROR)；

**若合格：**

根据手机号获取用户，出现的异常由GlobalExceptionHandler处理；





#### 2022.03.28（设置cookie）

![image-20220328154909912](../../Typroa_images/image-20220328154909912.png)

在控制器的参数传入request和response；

UserServiceImpl类中doLogin()方法，在返回前生成cookie，在request的session中设置cookie

```java
//        生成cookie
        String ticket = UUIDUtil.uuid();
        request.getSession().setAttribute(ticket, user);
        CookieUtil.setCookie(request, response, "userTicket", ticket);
```

