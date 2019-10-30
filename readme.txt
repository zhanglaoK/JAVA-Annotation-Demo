java中元注解(用来标识注解的注解)有四个： @Retention @Target @Document @Inherited；

　　 @Retention：注解的保留位置　　　　　　　　　
　　　　@Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
　　　　@Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
　　　　@Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到

　　@Target:注解的作用目标　　　　　　　　
　　　　@Target(ElementType.TYPE)   //给一个类型进行注解，比如类、接口、枚举
　　　　@Target(ElementType.FIELD) //字段、枚举的常量
　　　　@Target(ElementType.METHOD) //给方法进行注解
　　　　@Target(ElementType.PARAMETER) //给一个方法内的参数进行注解
　　　　@Target(ElementType.CONSTRUCTOR)  //给构造方法进行注解
　　　　@Target(ElementType.LOCAL_VARIABLE)//给局部变量进行注解
　　　　@Target(ElementType.ANNOTATION_TYPE)//给一个注解进行注解
　　　　@Target(ElementType.PACKAGE) ///给一个包进行注解

     @Document：说明该注解将被包含在javadoc中
　  @Inherited：说明子类可以继承父类中的该注解

工程中的
Constraints：字段注解，主要的作用就是定义是否为主键，是否非空，是否唯一
SQLInteger：字段注解，代表SQL中的int型，需要设定列名，必要时设置Constraints
SQLString：字段注解，代表SQL中的varchar型，需要设定列名和长度，必要时设置Constraints
DBTable：类注解，这个注解时打在类上的，代表SQL中的表，需要指定表明，比如打在Person类上，@DBTable(name = "PERSON")，代表表名为PERSON
Member类：创建表实例，在里面使用到了以上四个自定义注解，分别定义表的名字，还有每个字段名字和字段类型，
TableCreator类：打印解析类，在此类中，解析注解，内含有2个方法。
    getConstraints方法：解析注解，是否为空，是否唯一
    createTableSql方法：对注解进行解析拼接

