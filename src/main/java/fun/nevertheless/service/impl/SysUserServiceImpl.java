package fun.nevertheless.service.impl;

import fun.nevertheless.bean.po.SysUser;
import fun.nevertheless.dao.SysUserDao;
import fun.nevertheless.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserDao sysUserDao;

    @Override
    public boolean ValidateLogon(SysUser sysUser) {
        SysUser sysUser1 = sysUserDao.selectSysUserByAccount(sysUser);
        if(sysUser1 != null && sysUser1.getSys_user_passw().equals(sysUser.getSys_user_passw())) return true;
        return false;
    }
}
