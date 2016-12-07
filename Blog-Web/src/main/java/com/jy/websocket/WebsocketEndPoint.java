package com.jy.websocket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 功能说明：websocket处理类, 使用J2EE7的标准
 *         切忌直接在该连接处理类中加入业务处理代码
 * 作者：liuxing(2014-11-14 04:20)
 */
//relationId和userCode是我的业务标识参数,websocket.ws是连接的路径，可以自行定义
@ServerEndpoint("/websocket")
public class WebsocketEndPoint {

    private static Log log = LogFactory.getLog(WebsocketEndPoint.class);

    @OnOpen
    public void onOpen (Session session) {
        System.out.println("Client connected");
    }

    @OnMessage
    public void onMessage(String message, Session session)
            throws IOException, InterruptedException {

        // Print the client message for testing purposes
        System.out.println("Received: " + message);

        // Send the first message to the client
        session.getBasicRemote().sendText("This is the first server message");

        // Send 3 messages to the client every 5 seconds
        int sentMessages = 0;
        while(sentMessages < 3){
            Thread.sleep(5000);
            session.getBasicRemote().
                    sendText("This is an intermediate server message. Count: "
                            + sentMessages);
            sentMessages++;
        }

        // Send a final message to the client
        session.getBasicRemote().sendText("This is the last server message");

        session.close();
    }

    @OnError
    public void onError(Throwable throwable, Session session) {
        log.info(throwable.getMessage(), throwable);
    }

    @OnClose
    public void onClose (Session session) {
        System.out.println("Connection closed");
    }

}