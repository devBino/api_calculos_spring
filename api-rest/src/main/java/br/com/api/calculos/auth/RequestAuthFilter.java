package br.com.api.calculos.auth;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.api.calculos.provider.TokenProvider;
import br.com.api.calculos.service.TokenUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;

/**
 * Sobreescreve método doFilterInternal de {{@link OncePerRequestFilter#doFilter(jakarta.servlet.ServletRequest, jakarta.servlet.ServletResponse, FilterChain)}}
 * e verifica a presença do token de autenticação em cada requisição
 */
@Component
public class RequestAuthFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private TokenUserDetailsService tokenUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
        FilterChain chain) throws ServletException, IOException {
        
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            
            jwtToken = requestTokenHeader.substring(7);

            try {
                username = tokenProvider.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException exception) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException exception) {
                System.out.println("JWT Token has expired");
            }
            
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = tokenUserDetailsService.loadUserByUsername(username);

            if (tokenProvider.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
            
        }

        chain.doFilter(request, response);
        
    }

}