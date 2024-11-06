package com.se2.proj.olms.security;

/*
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.context.SecurityContextHolder; import
 * org.springframework.security.web.authentication.
 * WebAuthenticationDetailsSource; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.filter.OncePerRequestFilter;
 * 
 * import com.se2.proj.olms.service.JwtUtil;
 * 
 * import javax.servlet.FilterChain; import javax.servlet.ServletException;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import java.io.IOException;
 * 
 * @Component public class JwtRequestFilter extends OncePerRequestFilter {
 * 
 * @Autowired private JwtUtil jwtUtil;
 * 
 * protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response, FilterChain chain) throws ServletException,
 * IOException { String header = request.getHeader("Authorization"); String
 * token = null;
 * 
 * if (header != null && header.startsWith("Bearer ")) { token =
 * header.substring(7); }
 * 
 * 
 * if (token != null && jwtUtil.validateToken(token)) {
 * UsernamePasswordAuthenticationToken authentication =
 * jwtUtil.getAuthentication(token); authentication.setDetails(new
 * WebAuthenticationDetailsSource().buildDetails(request));
 * SecurityContextHolder.getContext().setAuthentication(authentication); }
 * 
 * 
 * chain.doFilter(request, response); }
 * 
 * @Override protected void
 * doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
 * jakarta.servlet.http.HttpServletResponse response,
 * jakarta.servlet.FilterChain filterChain) throws
 * jakarta.servlet.ServletException, IOException {
 * 
 * } }
 */