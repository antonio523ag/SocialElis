package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IdUtenteBulkRequest {
    private List<IdUtenteRequest> utenti;
}
