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
