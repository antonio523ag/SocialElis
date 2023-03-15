package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(name = "request - idUtenteBulk", description = "elenco degli id utenti da accettare in contemporanea")
public class IdUtenteBulkRequest {
    private List<Long> idUtenti;
}
