@ImprotResource("classpath:config.xml")，可以指定spring的xml配置

@Profile("xxx")注解可指定某个bean属于哪个profile，可标注于类级别或是方法级别，并且只有当规定的profile激活时，相应的bean才会被创建，可以使用多种方式启用Profile，如@ActiveProfiles("dev")

@Conditional，条件化的Bean，spring4引入了@Conditional注解，可以用到带有@Bean注解的方法之上，如果给定的条件计算结果为true，就会创建这个Bean，否则这个Bean就会被忽略，这个方法可以检查环境变量，bean定义，属性，加载的资源，注解的方法上还有什么其他的注解



```java
//@Autowired标注了一个方法
@Autowired
public void setDessert(Dessert dessert){
	this.dessert = dessert
}
//Dessert是一个接口，并且有三个类实现了这个接口
@Component
public class Cake implements Dessert{...}

@Component
public class Cookie implements Dessert{...}

@Component
public class IceCream implements Dessert{...}

//当Spring试图自动装配setDessert()中的Dessert参数时，它并没有唯一，无歧义性的可选值，Spring无法作出选择，只能抛出NoUniqueBeanDefinitionException
```

@Primary，将一个其中一个可选的bean设置为首选的bean，避免自动装配时的歧义性。

@Qualifier("iceCream")，是使用限定符的主要方式，它可以与@Autowired和@Inject协同使用。

所有使用@Component注解声明的类都会创建为bean，并且bean的ID为首字母变为小写的类名，因此@Qualifier("iceCream")指向的是组件扫描时所创建的bean，并且这个bean是IceCream类的实例。

```java
//为bean设置自己的限定符，而不是依赖于Bean ID作为限定符，只需在bean声明上添加@Qualifier("xxxx")注解就好了。
@Component
@Qualifier("cold")
public class IceCream implements Dessert {....}

@Autowired
@Qualifier("cold")
public void setDessert(Dessert dessert){
  	this.dessert = dessert;
}
```
Environment，注入外部的值，通过它可以获取到设置在properties中的值，也可以获取到哪些profile处于激活的状态。

@Value("${xxx.xxx}")注入properties中的值，不过要配置一个PropertySourcesPlaceholderConfigurer bean。或是使用xml   \<context: property-placeholder /\> 