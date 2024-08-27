package dev.thesarfo.webfluxdemo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Response {

    private Date date = new Date();
    private int output;

    public Response(int output) {
        this.output = output;
    }
}
