package fun.nevertheless.dao;

import fun.nevertheless.bean.po.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;

@Repository
public interface SysUserDao {
    // 查
    public ArrayList<SysUser> selectAllSysUser();
    public SysUser selectSysUserById(@Param("sysUser")SysUser sysUser);
    public SysUser selectSysUserByAccount(@Param("sysUser")SysUser sysUser);

    // 插
    public int insertSysUser(@Param("sysUser")SysUser sysUser);

    // 删
    public int deleteSysUserById(@Param("sysUser")SysUser sysUser);
    public int deleteSysUserByAccount(@Param("sysUser")SysUser sysUser);

    // 改
    public int updateSysUserById(@Param("sysUser")SysUser sysUser, @Param("sysUser1")SysUser sysUser1);
    public int updateSysUserByAccount(@Param("sysUser")SysUser sysUser, @Param("sysUser1")SysUser sysUser1);

}
