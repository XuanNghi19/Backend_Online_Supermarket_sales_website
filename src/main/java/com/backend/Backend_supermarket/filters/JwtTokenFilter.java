package com.backend.Backend_supermarket.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.backend.Backend_supermarket.components.JwtTokenUtils;
import com.backend.Backend_supermarket.models.User;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    
    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsService userDetailsService;

    @Value("${api.prefix}")
    private String apiPrefix;
    
    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
             
            if(isBypassToken(request)){
                filterChain.doFilter(request, response);
                return;
            }

            final String authHeader = request.getHeader("Authorization");
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }
            // Lấy ra chuỗi token
            final String token = authHeader.substring(7);
            // Lấy ra phoneNumber từ token
            final String phoneNumber = jwtTokenUtils.extractPhoneNumber(token);

            if(phoneNumber != null 
                && SecurityContextHolder.getContext().getAuthentication() == null
            ){
                // Lấy ra user bằng phoneNumber từ token
                User user = (User) userDetailsService.loadUserByUsername(phoneNumber);
                // xác thực người dùng 
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    phoneNumber, null, user.getAuthorities()
                );

                // thiết lập thông tin người dùng
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // gắn authenticationToken vào SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }

    private boolean isBypassToken(@NonNull HttpServletRequest request) {
        final List<Pair<String, String>> bypassTokens = Arrays.asList(
            Pair.of(String.format("%s/products", apiPrefix), "GET"),
            Pair.of(String.format("%s/categories", apiPrefix), "GET"),
            Pair.of(String.format("%s/users/login", apiPrefix), "POST"),
            Pair.of(String.format("%s/users/register", apiPrefix), "POST")
        ) ;

        for(Pair<String, String> bypassToken : bypassTokens){
            if(
                request.getServletPath().contains(bypassToken.getFirst()) && 
                    request.getMethod().equals(bypassToken.getSecond())
            )
                return true;
        }

        return false;
    }
    
}
