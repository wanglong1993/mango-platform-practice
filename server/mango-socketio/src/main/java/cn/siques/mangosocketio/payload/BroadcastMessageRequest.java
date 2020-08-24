package cn.siques.mangosocketio.payload;

import lombok.Data;

/**
 * <p>
 * 广播消息载荷
 * </p>
 */
@Data
public class BroadcastMessageRequest {
    /**
     * 消息内容
     */
    private String message;
}
