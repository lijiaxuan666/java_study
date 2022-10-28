package first.dao;

import first.domain.QueryVo;
import first.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {
    //查询索引操作
    @Select("select * from stu")
    List<User> findAll();

    List<User> findAll22();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    User findById(Integer id);

    List<User> findByName(String name);

    List<User> findByVo(QueryVo vo);

    int findTotal();

    List<User> findByCondition(User user);//条件查询,使用if标签

    List<User> findByCondition2(User user);//条件查询,使用where标签

    List<User> findInIds(QueryVo vo);//根据QueryVo中提供的id集合查找信息
}
