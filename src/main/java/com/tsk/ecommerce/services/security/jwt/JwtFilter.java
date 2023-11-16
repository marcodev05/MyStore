package com.tsk.ecommerce.services.security.jwt;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsk.ecommerce.dtos.responses.Response;
import com.tsk.ecommerce.dtos.responses.ResponseFactory;
import com.tsk.ecommerce.services.i18n.I18nService;
import com.tsk.ecommerce.services.security.CustomUserDetails;
import com.tsk.ecommerce.services.security.CustomUserDetailsService;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;


//@Component
public class JwtFilter extends GenericFilterBean{

	private final JwtProvider jwtProvider;
	private final CustomUserDetailsService customUserDetailsService;
	private static final String AUTHORIZATION = "Authorization";
	private final ObjectMapper objectMapper;
	private final I18nService i18nService;

	public JwtFilter(JwtProvider jwtProvider, CustomUserDetailsService customUserDetailsService, ObjectMapper objectMapper, I18nService i18nService) {
		this.jwtProvider = jwtProvider;
		this.customUserDetailsService = customUserDetailsService;
		this.objectMapper = objectMapper;
		this.i18nService = i18nService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
		String token = this.getTokenFromRequest((HttpServletRequest) request);
		try {
			if (token != null && jwtProvider.validateToken(token)) {
				String username = jwtProvider.getLoginFromToken(token);
				CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			chain.doFilter(request, response);
		} catch (Exception e) {
			final Response<String> data = ResponseFactory.unauthorized(i18nService.get("error.access.authorization"));
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			objectMapper.writeValue(response.getOutputStream(), data);
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
