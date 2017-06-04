### Bean作用域

默认情况下，Spring应用的上下文中所有的bean都是作为以单例的形式创建的，也就是说，不管给定的一个bean被注入到其他bean多少次，每次所注入的都是同一个实例。

大多数情况下，单例bean是很理想的方案，初始化和垃圾回收对象实例所带来的成本只留给一些小规模任务。但是有些时候，你所使用的类是易变的(mutable)，它们会保持一些状态，因此重用是不安全的，在这种情况下，将class声明为单例的bean就不是什么好主意了，因为对象会被污染

spring定义了多种作用域，可以基于这些作用域创建bean

-   单例(Singleton)，在整个应用中，只创建bean的一个实例
-   原型(Prototype)，每次注入或者通过Spring应用上下文获取的时候，都会创建一个新的bean实例
-   会话(Session)，在Web应用中，为每个会话创建一个bean实例
-   请求(Request)，在Web应用中，为每个请求创建一个bean实例

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Notepad {...}

@Bean
@Scope("prototype")
public Notepad notepad() {
  	return new Notepad;
}

<bean id = "notepad" class = "com.myapp.Notepad" scope = "prototype" />
```
注意一下proxyMode

```java
@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.INTERFACES)
public ShoppingCart cart() {....}


@Component
public class StoreService{
  	
  	private ShoppingCart shoppingCart;
  	
  	@Autowired
  	public void setShoppingCart(ShoppingCart shoppingCart) {
      	this.shoppingCart = shoppingCart;
  	}
}
```

StoreService是一个单例的bean，但是ShoppingCart bean是会话作用域的，spring应用上下文加载的时候，加载StoreService，而此时ShoppingCart并不存在。另外，系统中会有多个ShoppingCart实例，我们并不想让Spring注入某个固定的ShoppingCart实例到StoreService中，我们希望StoreService处理购物车功能时，他所使用的ShoppingCart实例刚好是当前会话所对应的那一个

实际上，Spring并不会将时机的ShoppingCart  bean 注入到StoreService中，Spring会注入一个到ShoppingCart bean的代理，这个代理会暴露与ShoppingCart相同的方法，所以StoreService认为它就是一个购物车。但是当StoreService调用ShoppingCart的方法时，代理会对其进行懒解析并将调用委托给会话作用域内的真正的ShoppingCart bean。

### 依赖注入的方式

注意到如果@Autowried直接放到一个字段上，intellij会报一个warming，field injection is not recommended，StackOverflow上有人回答了为何这样不建议https://stackoverflow.com/questions/39890849

```java
@Autowried
private BlankDisc cd;

@Autowried
private TrackCounter counter;
```

而建议采用构造器注入的方式，其次采用setter的注入方式

```java
@Autowired
public TrackCounterTest(BlankDisc cd, TrackCounter counter) {
    this.cd = cd;
    this.counter = counter;
}
```

字段直接注入的缺点

-   你不能创建不可改变的object，而构造器注入就可以创建final类型的
-   类与DI 容器强耦合，不能在外部使用它
-   如果不使用反射，类不能被实例化（比如单元测试）。需要DI容器去实例化它们，这种问题更多地出现在集成测试中
-   真正的依赖对外隐藏，没有显示地显示在interface，构造器或是方法中
-   这很容易拥有10个依赖，如果使用构造器注入，拥有10个参数的构造器太恶心。不过更加不能使用字段注入的方式，违反单一职责的原则

构造器注入的优点

-   更加容易测试，在单元测试中不需要mocking library 或是spring context。而且速度快，反射的效率比较低
-   不可改变，可以使得这些依赖不可改变
-   代码更加安全，构造器注入的方式要么这个object被完全创建了，要么就是创建失败，不可能存在某些字段没有被注入的情况，出现空指针的情况减少。
-   减少强制依赖，减少耦合

总结：根据需求，优先考虑构造器注入或是构造器和setter方法混合注入。字段注入有太多的缺点，应该避免，而它的唯一优势只是容易书写而已，而拥有了太多的缺点