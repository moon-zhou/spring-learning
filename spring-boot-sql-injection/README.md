#### SQL injection
本示例使用springboot + jdbcTemplate进行展示

#### sql注入本质
1. 查询条件（外部输入）
```
1 or 1=1
```
1. 查询sql（代码）
```java
String sql = "select id,name,gender from user where id = " + id;
return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
```
1. 执行sql（最终执行）
```sql
select * from user where id=1 or 1=1;
```

#### 如何避免
1. 不使用sql拼接，采用预编译方式，执行sql，在jdbcTemplate上体现为使用：`query(String sql, @Nullable Object[] args, RowMapper<T> rowMapper)`
1. 做好上层的数据校验，长度，类型，sql关键词（不是根本有效手段，潜在的防御方式，根本还是第一点）