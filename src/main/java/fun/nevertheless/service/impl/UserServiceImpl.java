package fun.nevertheless.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.nevertheless.bean.po.User;
import fun.nevertheless.dao.UserDao;
import fun.nevertheless.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ArrayList<User> getUserList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<User> list = userDao.selectAllUser();
        User pageinfo = new User();

        PageInfo<User> page = new PageInfo<User>(list);
        System.out.println("总数量：" + page.getTotal());
        System.out.println("当前页查询记录：" + page.getList().size());
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("每页显示数量：" + page.getPageSize());
        System.out.println("总页：" + page.getPages());

        pageinfo.setUser_id(-999);
        pageinfo.setUser_passw(String.valueOf(page.getPages()).trim());
        pageinfo.setUser_account(String.valueOf(page.getPageNum()).trim());
        list.add(pageinfo);
        return list;
    }

    @Override
    public int deleteUserById(User user) {
        return userDao.deleteUserById(user);
    }

    @Override
    public int updateUserById(User user) {
        User user1 = userDao.selectUserById(user);
        return userDao.updateUserById(user1, user);
    }

    @Override
    public User ValidateLogon(User user) {
        User user1 = userDao.selectUserByAccount(user);

        if (user1.getUser_passw().equals(user.getUser_passw())){
            return user1;
        }
        return null;
    }

    @Override
    public int insertUser(User user) {
        User user1 = userDao.selectUserByAccount(user);
        if (user1 == null){
            return userDao.insertUser(user);
        }
        else{
            return -1;
        }

    }
}
