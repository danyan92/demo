引入maven
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.3.0</version>
</dependency>

Service CRUD 接口
通用 Service CRUD 封装IService接口，进一步封装 CRUD 采用
 get 查询单行 
 remove 删除 
 list 查询集合
 page 分页 
 前缀命名方式区分 Mapper 层避免混淆，
泛型 T 为任意实体对象
对象 Wrapper 为 条件构造器:https://mybatis.plus/guide/wrapper.html#abstractwrapper


Mapper CRUD 接口
通用 CRUD 封装BaseMapper接口，为 Mybatis-Plus 启动时自动解析实体表关系映射转换为 Mybatis 内部对象注入容器
泛型 T 为任意实体对象
参数 Serializable 为任意类型主键 Mybatis-Plus 不推荐使用复合主键约定每一张表都有自己的唯一 id 主键
对象 Wrapper 为 条件构造器

