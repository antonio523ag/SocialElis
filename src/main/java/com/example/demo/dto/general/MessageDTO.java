package com.example.demo.dto.general;

import com.example.demo.exception.ClasseNonTrovataException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private String message;
    private LocalDateTime time;

    public MessageDTO(Exception exception) {
        message=exception.getMessage();
        time=LocalDateTime.now();
    }
}
