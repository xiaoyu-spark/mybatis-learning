# mybatis-learning
1. MyBatis 同样提供了一级缓存和二级缓存的支持。
    * 一级缓存: 基于PerpetualCache 的 HashMap本地缓存，其存储作用域为 Session，当 Session flush 或 close 之后，该Session中的所有 Cache 就将清空。
    * 二级缓存与一级缓存其机制相同，默认也是采用 PerpetualCache，HashMap存储，不同在于其存储作用域为 Mapper(Namespace)，并且可自定义第三方存储源，如 Ehcache框架等。
    * 对于缓存数据更新机制，当某一个作用域(一级缓存Session/二级缓存Namespaces)的进行了 C/U/D 操作后，默认该作用域下所有 select 中的缓存将被clear。
    * MyBatis中一级缓存是默认开启的，即在查询中(一次SqlSession中)。只要当SqlSession不关闭，那么你的操作会默认存储使用一级缓存。
2. dataSource 元素使用标准的 JDBC 数据源接口来配置 JDBC 连接对象的资源。
   
       许多 MyBatis 的应用程序会按示例中的例子来配置数据源。虽然这是可选的，但为了使用延迟加载，数据源是必须配置的。
   
   有三种内建的数据源类型（也就是 type=”[UNPOOLED|POOLED|JNDI]”）：
   
   * UNPOOLED– 这个数据源的实现只是每次被请求时打开和关闭连接。虽然有点慢，但对于在数据库连接可用性方面没有太高要求的简单应用程序来说，是一个很好的选择。 不同的数据库在性能方面的表现也是不一样的，对于某些数据库来说，使用连接池并不重要，这个配置就很适合这种情形。UNPOOLED 类型的数据源仅仅需要配置以下 5 种属性：
   
       driver – 这是 JDBC 驱动的 Java 类的完全限定名（并不是 JDBC 驱动中可能包含的数据源类）。
       url – 这是数据库的 JDBC URL 地址。
       username – 登录数据库的用户名。
       password – 登录数据库的密码。
       defaultTransactionIsolationLevel – 默认的连接事务隔离级别。
   
       作为可选项，你也可以传递属性给数据库驱动。要这样做，属性的前缀为“driver.”，例如：
       
           driver.encoding=UTF8
       
       这将通过 DriverManager.getConnection(url,driverProperties) 方法传递值为 UTF8 的 encoding 属性给数据库驱动。
   
   * POOLED– 这种数据源的实现利用“池”的概念将 JDBC 连接对象组织起来，避免了创建新的连接实例时所必需的初始化和认证时间。 这是一种使得并发 Web 应用快速响应请求的流行处理方式。
   
       除了上述提到 UNPOOLED 下的属性外，还有更多属性用来配置 POOLED 的数据源：
       
           poolMaximumActiveConnections – 在任意时间可以存在的活动（也就是正在使用）连接数量，默认值：10
           poolMaximumIdleConnections – 任意时间可能存在的空闲连接数。
           poolMaximumCheckoutTime – 在被强制返回之前，池中连接被检出（checked out）时间，默认值：20000 毫秒（即 20 秒）
           poolTimeToWait – 这是一个底层设置，如果获取连接花费了相当长的时间，连接池会打印状态日志并重新尝试获取一个连接（避免在误配置的情况下一直安静的失败），默认值：20000 毫秒（即 20 秒）。
           poolMaximumLocalBadConnectionTolerance – 这是一个关于坏连接容忍度的底层设置， 作用于每一个尝试从缓存池获取连接的线程. 如果这个线程获取到的是一个坏的连接，那么这个数据源允许这个线程尝试重新获取一个新的连接，但是这个重新尝试的次数不应该超过 poolMaximumIdleConnections 与 poolMaximumLocalBadConnectionTolerance 之和。 默认值：3 (新增于 3.4.5)
           poolPingQuery – 发送到数据库的侦测查询，用来检验连接是否正常工作并准备接受请求。默认是“NO PING QUERY SET”，这会导致多数数据库驱动失败时带有一个恰当的错误消息。
           poolPingEnabled – 是否启用侦测查询。若开启，需要设置 poolPingQuery 属性为一个可执行的 SQL 语句（最好是一个速度非常快的 SQL 语句），默认值：false。
           poolPingConnectionsNotUsedFor – 配置 poolPingQuery 的频率。可以被设置为和数据库连接超时时间一样，来避免不必要的侦测，默认值：0（即所有连接每一时刻都被侦测 — 当然仅当 poolPingEnabled 为 true 时适用）。
       
   * JNDI – 这个数据源的实现是为了能在如 EJB 或应用服务器这类容器中使用，容器可以集中或在外部配置数据源，然后放置一个 JNDI 上下文的引用。这种数据源配置只需要两个属性：
   
       initial_context – 这个属性用来在 InitialContext 中寻找上下文（即，initialContext.lookup(initial_context)）。这是个可选属性，如果忽略，那么 data_source 属性将会直接从 InitialContext 中寻找。
       data_source – 这是引用数据源实例位置的上下文的路径。提供了 initial_context 配置时会在其返回的上下文中进行查找，没有提供时则直接在 InitialContext 中查找。
       
       和其他数据源配置类似，可以通过添加前缀“env.”直接把属性传递给初始上下文。比如：
       
           env.encoding=UTF8
       
       这就会在初始上下文（InitialContext）实例化时往它的构造方法传递值为 UTF8 的 encoding 属性。
       
       你可以通过实现接口 org.apache.ibatis.datasource.DataSourceFactory 来使用第三方数据源：
       
       public interface DataSourceFactory {
         void setProperties(Properties props);
         DataSource getDataSource();
       }
       
       org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory 可被用作父类来构建新的数据源适配器，比如下面这段插入 C3P0 数据源所必需的代码：
       
       import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
       import com.mchange.v2.c3p0.ComboPooledDataSource;
               
       public class C3P0DataSourceFactory extends UnpooledDataSourceFactory {
       
         public C3P0DataSourceFactory() {
           this.dataSource = new ComboPooledDataSource();
         }
       }
       
       为了令其工作，记得为每个希望 MyBatis 调用的 setter 方法在配置文件中增加对应的属性。下面是一个可以连接至 PostgreSQL 数据库的例子：
       
       <dataSource type="org.myproject.C3P0DataSourceFactory">
         <property name="driver" value="org.postgresql.Driver"/>
         <property name="url" value="jdbc:postgresql:mydb"/>
         <property name="username" value="postgres"/>
         <property name="password" value="root"/>
       </dataSource>

3. 在 MyBatis 中有两种类型的事务管理器（也就是 type=”[JDBC|MANAGED]”）：
   
       JDBC – 这个配置就是直接使用了 JDBC 的提交和回滚设置，它依赖于从数据源得到的连接来管理事务作用域。
       MANAGED – 这个配置几乎没做什么。它从来不提交或回滚一个连接，而是让容器来管理事务的整个生命周期（比如 JEE 应用服务器的上下文）。 默认情况下它会关闭连接，然而一些容器并不希望这样，因此需要将 closeConnection 属性设置为 false 来阻止它默认的关闭行为。
       