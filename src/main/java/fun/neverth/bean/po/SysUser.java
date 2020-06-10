package fun.neverth.bean.po;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 8869754981104209353L;
    private int sys_user_id;
    private String sys_user_account;
    private String sys_user_passw;
    private Timestamp sys_user_last_login;
}
