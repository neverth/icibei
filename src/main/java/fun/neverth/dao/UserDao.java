package fun.neverth.dao;



import fun.neverth.bean.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserDao {

    // 查
    public ArrayList<User> selectAllUser();
    public User selectUserById(@Param("user")User user);
    public User selectUserByAccount(@Param("user")User user);

    // 插
    public int insertUser(@Param("user")User user);

    // 删
    public int deleteUserById(@Param("user") User user);
    public int deleteUserByAccount(@Param("user")User user);

    // 改
    public int updateUserById(@Param("user")User user, @Param("user1")User user1);
    public int updateUserByAccount(@Param("user")User user, @Param("user1")User user1);
}
