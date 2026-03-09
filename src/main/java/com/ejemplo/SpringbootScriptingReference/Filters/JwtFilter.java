package com.ejemplo.SpringbootScriptingReference.Filters;

import com.ejemplo.SpringbootScriptingReference.Services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter { //Middleware/filtro para verificar que haya un token de sesion en el bearer token y sea valido, luego se podra acceder en las rutas con getATtribute("userId")

    @Autowired private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws java.io.IOException, jakarta.servlet.ServletException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtService.isValid(token)) {
                request.setAttribute("userId", jwtService.extractUserId(token));
            }
        }
        chain.doFilter(request, response);
    }
}
