package com.zxy.mallchat.common.websocket.domain.vo.req;

import com.zxy.mallchat.common.websocket.domain.enums.WSReqTypeEnum;
import lombok.Data;

@Data
public class WSBaseReq {
    /**
     * @see com.zxy.mallchat.common.websocket.domain.enums.WSReqTypeEnum
     */
    private Integer type;
    private String data;
}
