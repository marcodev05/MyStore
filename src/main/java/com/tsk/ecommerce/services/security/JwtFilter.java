package com.tsk.ecommerce.services.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.tsk.ecommerce.configs.jwt.JwtProvider;
import com.tsk.ecommerce.exceptions.UnauthorizedException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.tsk.ecommerce.configs.security.CustomUserDetails;

@Component
public class JwtFilter extends GenericFilterBean{

	private final JwtProvider jwtProvider;
	private final CustomUserDetailsService customUserDetailsService;
	private static final String AUTHORIZATION = "Authorization";

	public JwtFilter(JwtProvider jwtProvider, CustomUserDetailsService customUserDetailsService) {
		this.jwtProvider = jwtProvider;
		this.customUserDetailsService = customUserDetailsService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		System.out.println(" je doit filtrer cette requette");
		String token = this.getTokenFromRequest((HttpServletRequest) request);
		try {
			if (token != null && jwtProvider.validateToken(token)) {
				String username = jwtProvider.getLoginFromToken(token);
				CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken auth =
						new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			chain.doFilter(request, response);
		} catch (UnauthorizedException | IOException | NullPointerException | ServletException e) {
			e.printStackTrace();
			throw new UnauthorizedException(" Access denied");
		}
	}

	public String getTokenFromRequest(HttpServletRequest request) {
		String bearer = request.getHeader(AUTHORIZATION);
		if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")){
			return bearer.substring(7);
		}
		return null;
	}

}
