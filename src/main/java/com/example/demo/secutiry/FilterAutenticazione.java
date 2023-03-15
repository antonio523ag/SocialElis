package com.example.demo.secutiry;


import com.example.demo.dto.general.MessageDTO;
import com.example.demo.exception.UtenteNonTrovatoException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class FilterAutenticazione extends OncePerRequestFilter {

    private final GestoreToken jwtService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        UserDetails userDetails;
        System.out.println(request.getRequestURI());

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        try {

            userDetails = jwtService.getUtenteFromToken(jwt);
        }catch (ExpiredJwtException e){
            try{
                String token=jwtService.refreshToken(jwt);
                response.setStatus(450);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setHeader("Authorization",token);
                response.setHeader("Access-Control-Expose-Headers","*");
                MessageDTO m=new MessageDTO("il token è scaduto, controllare l'header per il nuovo token");
                new ObjectMapper().writeValue(response.getOutputStream(),m);
                return;
            }catch (ExpiredJwtException ex) {
                scriviErrore(response, "il token è scaduto");
                return;
            }
        }catch (MalformedJwtException | UtenteNonTrovatoException e){
            scriviErrore(response,"il token non è valido");
            return;
        }

        if (userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void scriviErrore(HttpServletResponse response,String msg) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        MessageDTO m=new MessageDTO(msg);
        new ObjectMapper().writeValue(response.getOutputStream(),m);
    }
}