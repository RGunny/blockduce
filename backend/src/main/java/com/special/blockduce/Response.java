package com.special.blockduce;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Response {
    private String reponse;
    private String message;
    private Object data;

    public Response(String reponse, String message, Object data) {
        this.reponse = reponse;
        this.message = message;
        this.data = data;
    }
}
