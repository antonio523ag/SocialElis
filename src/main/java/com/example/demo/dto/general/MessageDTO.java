package com.example.demo.dto.general;

import com.example.demo.exception.ClasseNonTrovataException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Messaggio", description = "messaggio per le info di errore")
public class MessageDTO {
    private String message;
    private String time;

    public MessageDTO(Exception exception) {
        this(exception.getMessage());
    }

    public MessageDTO(String msg){
        message=msg;
        time=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy kk:mm:ss"));
    }
}
