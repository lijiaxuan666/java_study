package first.dao;

import first.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {
    @Select("select * from stu")
    List<User> findAll();

    @Select("select * from stu")
    @Results(id="userMap",value={
            @Result(id=true,column = "id",property = "userId"),
            @Result(column = "name ",property = "userName"),
            @Result(column = "score",property = "userScore"),
            @Result(column = "birthday",property = "Birthday"),
            @Result(column = "insert_time",property = "Insert_Time"),
            //@Result(property = "account",column="aid",one=@One(select="first.dao.IAccountDao.findById",fetchType=FetchType.EAGER))
            //多表查询一对一使用one=@one(),一对多用many=@Many()，这里没有建立Account表，所以无法使用
            //property属性是对应要封装的Account，column是查询的字段
            //slect属性是方法的全限定类名，fetchType属性LAZY是延迟加载，EAGER是立即加载
    })
    List<User> findAll2();
    //使用Results和Result来解决属性名和数据库列名的不对应
    //id="userMap"中的userMap是唯一标识，若其他要使用，
    //只需在@Select下面加上@ResultMap(value={"userMap"})即可
    //若value只有一个属性，value是可以省略的，若数组内只有一个元素，大括号也可以省略

    @Delete("delete from stu where id=#{id}")
    void deleteUser(Integer id);

    @Select("select * from stu where id = #{id}")
    User findById(Integer id);

    //模糊查询
    @Select("select * from stu where name like #{name}")
    List<User> findByName(String name);

    //上面的大括号中的name是占位符，取什么名字都可以。调用时需要给字符串加上%
    //此处value是固定属性名称，大括号里面必须写value。调用时无需再加%
    @Select("select * from stu where name like '%${value}%'")
    List<User> findByName2(String name);

    @Select("select count(*) from stu")
    int findTotal();
}
