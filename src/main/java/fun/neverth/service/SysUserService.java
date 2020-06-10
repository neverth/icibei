package fun.neverth.service;

import fun.neverth.bean.po.SysUser;

public interface SysUserService {
    public boolean ValidateLogon(SysUser sysUser);
}
