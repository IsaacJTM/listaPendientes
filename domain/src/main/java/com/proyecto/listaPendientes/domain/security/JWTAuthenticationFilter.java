package com.proyecto.listaPendientes.domain.security;


import com.proyecto.listaPendientes.domain.aggregates.exception.ExceptionGlobal;
import com.proyecto.listaPendientes.domain.port.out.JWTServiceOut;
import com.proyecto.listaPendientes.domain.port.out.UsuarioServiceOut;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTServiceOut jwtServiceOut;
    private final UsuarioServiceOut usuarioServiceOut;
    private final ExceptionGlobal exceptionGlobal;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{

        try {
            final String autHeader = request.getHeader("Authorization");
            final String jwt;
            final String userEmail;


            if (StringUtils.isEmpty(autHeader) || !StringUtils.startsWithIgnoreCase(autHeader, "Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
                jwt = autHeader.substring(7);
                userEmail = jwtServiceOut.extractUserNameOut(jwt);

                if (Objects.nonNull(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetailsBD = usuarioServiceOut.userDetailsService().loadUserByUsername(userEmail);
                    if (jwtServiceOut.validarTokenOut(jwt, userDetailsBD)) {
                        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                        UsernamePasswordAuthenticationToken autToken = new UsernamePasswordAuthenticationToken(
                                userDetailsBD, null, userDetailsBD.getAuthorities());

                        autToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        securityContext.setAuthentication(autToken);
                        SecurityContextHolder.setContext(securityContext);
                    }
                }

            filterChain.doFilter(request, response);

        }catch (Exception ex){
            // Captura la excepción y maneja la respuesta
            ResponseEntity<String> errorResponse = exceptionGlobal.manejoException(ex);

            // Establece la respuesta en el HttpServletResponse
            response.setStatus(errorResponse.getStatusCodeValue());
            response.setContentType("application/json");
            response.getWriter().write(errorResponse.getBody());
        }
    }
}
