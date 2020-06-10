package fun.neverth.service;

import fun.neverth.bean.po.User;

import java.util.ArrayList;

public interface UserService {
    public User ValidateLogon(User user);
    public ArrayList<User> getUserList(int pageNum, int pageSize);
    int deleteUserById(User user);
    int updateUserById(User user);
    int insertUser(User user);
}
