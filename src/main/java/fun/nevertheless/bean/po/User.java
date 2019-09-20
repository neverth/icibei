package fun.nevertheless.bean.po;

import lombok.Data;

import java.io.Serializable;


@Data
public class User implements Serializable {
    private static final long serialVersionUID = 7961302672392824411L;
    private int user_id;
    private String user_account;
    private String user_passw;
    private String user_last_login;
}
