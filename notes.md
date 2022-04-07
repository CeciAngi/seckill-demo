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







#### 2022.03.31（优化登录功能）

使用redis存储用户信息

虚拟机后台开启redis：

```shell
redis-server redis.conf
```

本机连CentOS的redis

创建redisTemplate：

```java
@Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //key序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //value序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //hash类型 key序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //hash类型 value序列化
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        //注入连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
```



登录时：将用户信息存入redis。

![image-20220331161140660](../../Typroa_images/image-20220331161140660.png)



对请求进行拦截：

设置WebConfig类

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private UserArgumentResolver userArgumentResolver;


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }
}
```



UserArgumentResolver中对用户是否登录进行校验：

```java
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private IUserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        return clazz == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        String ticket = CookieUtil.getCookieValue(request, "userTicket");
        if (StringUtils.isEmpty(ticket))
            return null;
        User user = userService.getUserByCookie(ticket, request, response);
        return user;
    }
}
```



从redis中获取用户信息：

```java
@Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(userTicket)){
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        if (user != null){
            CookieUtil.setCookie(request, response, "userTicket", userTicket);
        }
        return user;
    }
```





#### 2022.04.07（初步完成秒杀功能）

每人每种商品限购一件，在秒杀之前判断用户是否已秒杀该商品

没有什么技术难点，后续还要完善。
