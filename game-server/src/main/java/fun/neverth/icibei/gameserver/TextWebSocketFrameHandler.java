package fun.neverth.icibei.gameserver;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 处理文本协议数据，处理TextWebSocketFrame类型的数据，websocket专门处理文本的frame就是TextWebSocketFrame
 *
 * @author NeverTh
 * @date 14:43 2020/11/10
 */
@Slf4j
@Component
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    /**
     * 将保存所有已经连接的channel
     */
    public static ChannelGroup CHANNELS = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 已经认证的用户 [userId, ChannelInfo]
     */
    public static HashMap<String, ChannelInfo> USER_CHANNEL_INFO = new HashMap<>();

    /**
     * 创建的房间 [roomId, Room]
     */
    public static HashMap<Integer, Room> ROOMS = new HashMap<>();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("{} 加入服务器", ctx.channel().remoteAddress().toString());
        CHANNELS.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("{} 离开服务器", ctx.channel().remoteAddress().toString());
        CHANNELS.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String[] msg = textWebSocketFrame.text().split(" ");
        CommandType cType = CommandType.getByCode(msg[0]);

        // 登陆
        if (cType == CommandType.Login) {
            // [type, userId]
            // 如果之前已经登录并且在线，则下线前一个channel
            Channel c = getChannel(msg[1]);
            if (c != null && c.id() != ctx.channel().id()){
                c.writeAndFlush(message(CommandType.DuplicateLoginResp));
                c.close();
                log.info("userId={},channelId={}.被挤下线", msg[1], c.id());
            }
            USER_CHANNEL_INFO.put(msg[1], new ChannelInfo(msg[1], ctx.channel().id()));
            ctx.channel().writeAndFlush(message(CommandType.LoginResp));
            log.info("userId={},已经成功登陆服务器", msg[1]);
            return;
        }

        // 需要登陆之后在进行处理的命令
        String userId = getUserId(ctx.channel().id());
        if (userId == null) {
            return;
        }
        switch (cType) {
            case CreateRoom: {
                // [type]
                Room room = new Room();
                int roomId = (int) Math.round(Math.random() * 10000);
                room.setRoomId(roomId);
                room.setCreator(userId);
                room.setParticipant(new ArrayList<>());
                room.getParticipant().add(userId);
                ROOMS.put(room.getRoomId(), room);
                // [type, roomId]
                ctx.channel().writeAndFlush(message(CommandType.CreateRoomResp, roomId));
                log.info("userId={},创建房间id={}", userId, room.getRoomId());
                break;
            }
            case EnterRoom: {
                // [type, roomId]
                Room room = ROOMS.get(Integer.parseInt(msg[1]));
                room.getParticipant().add(userId);
                StringBuilder sb = new StringBuilder();
                for (String partUserId : room.getParticipant()) {
                    // [type, userId]
                    getChannel(partUserId).writeAndFlush(message(CommandType.UserEnterRoomResp, userId));
                    sb.append(partUserId);
                    sb.append(",");
                }
                // [type, "userId,userId"]
                ctx.channel().writeAndFlush(message(CommandType.EnterRoomResp, sb.deleteCharAt(sb.length() - 1).toString()));
                log.info("userId={},已经加入房间Id={}", userId, room.getRoomId());
                break;
            }
            case GameStart: {
                // [type, roomId]
                Room room = ROOMS.get(Integer.parseInt(msg[1]));
                for (String partUserId : room.getParticipant()) {
                    // [type]
                    getChannel(partUserId).writeAndFlush(message(CommandType.GameStartResp));
                    getChannel(partUserId).writeAndFlush(message(CommandType.GameWordListResp, "abandon"));
                    log.info("roomId={},向参与者userid={}发送游戏开始命令", room.getRoomId(), partUserId);
                }
                break;
            }
            case Pending: {
                // [type, roomId, percentage]
                Room room = ROOMS.get(Integer.parseInt(msg[1]));
                for (String partUserId : room.getParticipant()) {
                    // [type, userId, percentage]
                    getChannel(partUserId).writeAndFlush(message(CommandType.PendingResp, getUserId(ctx.channel().id()), msg[2]));
                    log.info("roomId={},向参与者useId={}发送userId={},进行到percentage={}", room.getRoomId(), partUserId, getUserId(ctx.channel().id()), msg[2]);
                }
                break;
            }
            case Unknown:
                System.out.println(1);
                break;
            default:
                break;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChannelInfo {
        private String userId;
        private ChannelId channelId;
    }

    @Data
    public static class Room {
        private int roomId;
        // userId
        private String creator;
        // userId
        private ArrayList<String> participant;
    }

    private String getUserId(ChannelId channelId) {
        for (ChannelInfo value : USER_CHANNEL_INFO.values()) {
            if (value.getChannelId().equals(channelId)) {
                return value.getUserId();
            }
        }
        return null;
    }

    private Channel getChannel(String userId) {
        ChannelInfo channelInfo = USER_CHANNEL_INFO.get(userId);
        return channelInfo == null ? null : CHANNELS.find(channelInfo.getChannelId());
    }

    private TextWebSocketFrame message(CommandType commandType, Object... objects) {
        // 未传可变参数的时候，Object为length为0的数组
        StringBuilder sb = new StringBuilder();
        sb.append(commandType.getCode());
        for (Object o : objects) {
            sb.append(" ").append(o);
        }
        return new TextWebSocketFrame(sb.toString());
    }
}
