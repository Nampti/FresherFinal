package com.lthdv.authservice.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lthdv.authservice.vo.enums.CoreErrorApp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEntity {
    Object data;
    MessEntity mess;

    public ResponseEntity() {
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public MessEntity getMess() {
        return this.mess;
    }

    public void setMess(MessEntity mess) {
        this.mess = mess;
    }

    public static Object responseToClient(Object itemObject) {
        ResponseEntity responseEntity = new ResponseEntity();
        CoreErrorApp errorApp;
        if (itemObject == null) {
            errorApp = CoreErrorApp.DATAEMTY;
        } else {
            errorApp = CoreErrorApp.SUCCESS;
            responseEntity.setData(itemObject);
        }

        MessEntity itemEntity = new MessEntity();
        itemEntity.setCode(errorApp.getCode());
        itemEntity.setDescription(errorApp.getDescription());
        responseEntity.setMess(itemEntity);
        return responseEntity;
    }
}
