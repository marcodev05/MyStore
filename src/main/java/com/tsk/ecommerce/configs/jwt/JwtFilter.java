package com.tsk.ecommerce.configs.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.tsk.ecommerce.configs.CustomUserDetails;
import com.tsk.ecommerce.configs.CustomUserDetailsService;

@Component
public class JwtFilter extends GenericFilterBean{

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	private static final String AUTHORIZATION = "Authorization";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
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
			
		} catch (Exception e) {
			e.printStackTrace();
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
