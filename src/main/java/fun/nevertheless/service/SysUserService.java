package fun.nevertheless.service;

import fun.nevertheless.bean.po.SysUser;

public interface SysUserService {
    public boolean ValidateLogon(SysUser sysUser);
}
