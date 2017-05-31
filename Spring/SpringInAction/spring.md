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