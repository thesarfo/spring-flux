package dev.thesarfo.webfluxdemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Response {

    private Date date = new Date();
    private int output;

    public Response(int output) {
        this.output = output;
    }
}
