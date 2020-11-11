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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 已经认证的用户
     */
    public static HashMap<String, ChannelInfo> userChannelMap = new HashMap<>();

    public static HashMap<Integer, Room> rooms = new HashMap<>();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("{} 加入服务器", ctx.channel().remoteAddress().toString());
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("{} 离开服务器", ctx.channel().remoteAddress().toString());
        channels.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String[] msg = textWebSocketFrame.text().split(" ");
        CommandType commandType = CommandType.getByCode(msg[0]);
        if (commandType == CommandType.Login) {
            // [type, userId]
            ChannelInfo channelInfo = new ChannelInfo();
            channelInfo.setUserId(msg[1]);
            channelInfo.setChannelId(ctx.channel().id());
            userChannelMap.put(msg[1], channelInfo);
            log.info("userId={},已经成功登陆服务器", msg[1]);
        } else {
            String userId = getUserId(ctx.channel().id());
            if (userId == null) {
                return;
            }
            switch (commandType) {
                case CreateRoom: {
                    // [type]
                    Room room = new Room();
                    room.setRoomId((int) Math.round(Math.random() * 10000));
                    room.setCreator(userId);
                    room.setParticipant(new ArrayList<>());
                    room.getParticipant().add(userId);
                    rooms.put(room.getRoomId(), room);
                    log.info("userId={},创建房间id={}", userId, room.getRoomId());
                    break;
                }
                case EnterRoom: {
                    // [type, roomId]
                    Room room = rooms.get(Integer.parseInt(msg[1]));
                    room.getParticipant().add(userId);
                    log.info("userId={},已经加入房间Id={}", userId, room.getRoomId());
                    break;
                }
                case GameStart: {
                    // [type, roomId]
                    Room room = rooms.get(Integer.parseInt(msg[1]));
                    for (String partUserId : room.getParticipant()) {
                        // [type]
                        getChannel(partUserId).writeAndFlush(new TextWebSocketFrame(CommandType.GameStartResp.getCode()));
                        log.info("roomId={},向参与者userid={}发送游戏开始命令", room.getRoomId(), partUserId);
                    }
                    break;
                }
                case Pending: {
                    // [type, roomId, percentage]
                    Room room = rooms.get(Integer.parseInt(msg[1]));
                    for (String partUserId : room.getParticipant()) {
                        // [type, userId, percentage]
                        getChannel(partUserId).writeAndFlush(new TextWebSocketFrame(
                                String.format("%s %s %s",
                                        CommandType.GameStartResp.getCode(),
                                        getUserId(ctx.channel().id()),
                                        msg[2]
                                )));
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
    }

    @Data
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
        for (ChannelInfo value : userChannelMap.values()) {
            if (value.getChannelId().equals(channelId)) {
                return value.getUserId();
            }
        }
        return null;
    }

    private Channel getChannel(String userId) {
        return channels.find(userChannelMap.get(userId).getChannelId());
    }
}
