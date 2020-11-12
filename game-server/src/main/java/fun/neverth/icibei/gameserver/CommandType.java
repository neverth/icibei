package fun.neverth.icibei.gameserver;

import io.netty.util.internal.StringUtil;
import lombok.Data;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author NeverTh
 * @date 20:51 2020/11/11
 */
@Getter
public enum CommandType {
    PendingResp("游戏进行中", "1008"),
    GameWordListResp("游戏需要练习的单词", "1007"),
    DuplicateLoginResp("重复登录被下线", "1006"),
    LoginResp("连接服务器响应", "1005"),
    UserEnterRoomResp("其他用户连接房间响应", "1004"),
    EnterRoomResp("连接房间响应", "1003"),
    CreateRoomResp("创建房间响应", "1002"),
    GameStartResp("游戏开始响应", "1001"),

    Pending("游戏进行中", "5"),
    GameStart("游戏开始", "4"),
    CreateRoom("创建房间", "3"),
    EnterRoom("连接房间", "2"),
    Unknown("Unknown", "999"),
    Login("连接服务器请求", "1");

    private String name;
    private String code;

    CommandType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static CommandType getByCode(String code){
        for (CommandType value : CommandType.values()) {
            if (value.getCode().equals(code)){
                return value;
            }
        }
        return Unknown;
    }
}
