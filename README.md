# mybatis-learning
1. MyBatis 同样提供了一级缓存和二级缓存的支持。
    * 一级缓存: 基于PerpetualCache 的 HashMap本地缓存，其存储作用域为 Session，当 Session flush 或 close 之后，该Session中的所有 Cache 就将清空。
    * 二级缓存与一级缓存其机制相同，默认也是采用 PerpetualCache，HashMap存储，不同在于其存储作用域为 Mapper(Namespace)，并且可自定义第三方存储源，如 Ehcache框架等。
    * 对于缓存数据更新机制，当某一个作用域(一级缓存Session/二级缓存Namespaces)的进行了 C/U/D 操作后，默认该作用域下所有 select 中的缓存将被clear。
    * MyBatis中一级缓存是默认开启的，即在查询中(一次SqlSession中)。只要当SqlSession不关闭，那么你的操作会默认存储使用一级缓存。
    
