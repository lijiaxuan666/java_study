package com.itheima.dao.impl;

/**
 * 此类用于抽取dao中的重复代码
 * 这个类Spring已经为我们提供，所以可以不写
 * import org.springframework.jdbc.core.support.JdbcDaoSupport;
 * 只需要该类继承JdbcDaoSupport即可
 * 但是要用注解配置的话由于我们无法修改Spring的源码，要使用注解开发就十分麻烦
 * 而自己写的这个类，就可以随便修改
 * 所以在使用xml开发时可以使用Spring提供的，使用注解开发可以自己写

public class JdbcDaoSupport {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    //在里面注入dataSource之后，会自动调用dataSource的set方法，从而创建一个jdbcTemplate
    //不需要再对jdbcTemplate进行注入
    public void setDataSource(DataSource dataSource) {
        if(jdbcTemplate == null){
            jdbcTemplate = createJdbcTemplate(dataSource);
        }
    }

    private JdbcTemplate createJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
 */