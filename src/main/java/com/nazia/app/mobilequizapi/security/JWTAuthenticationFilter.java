package com.nazia.app.mobilequizapi.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.nazia.app.mobilequizapi.security.SecurityConstants.EXPIRATION_TIME;
import static com.nazia.app.mobilequizapi.security.SecurityConstants.HEADER_STRING;
import static com.nazia.app.mobilequizapi.security.SecurityConstants.SECRET;
import static com.nazia.app.mobilequizapi.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import com.auth0.jwt.JWT;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setAuthenticationManager(authenticationManager);
    }
    
    private SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();
    //path to which this filter will intercept
    RequestMatcher customFilterUrl = new AntPathRequestMatcher("/dologin");
    private boolean continueChainBeforeSuccessfulAuthentication = false;
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        
    	HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        //if no match then go to next filter
        if (!customFilterUrl.matches(request)) {
           chain.doFilter(request, response);
        } else {

          Authentication authResult;
          try {
              authResult = this.attemptAuthentication(request, response);
              if (authResult == null) {
                  return;
              }
              this.sessionStrategy.onAuthentication(authResult, request, response);
          } catch (InternalAuthenticationServiceException var8) {
              this.logger.error("An internal error occurred while trying to authenticate the user.", var8);
              this.unsuccessfulAuthentication(request, response, var8);
              return;
          } catch (AuthenticationException var9) {
              this.unsuccessfulAuthentication(request, response, var9);
              return;
          }

          if (this.continueChainBeforeSuccessfulAuthentication) {
              chain.doFilter(request, response);
          }
          successfulAuthentication(request, response, chain, authResult);
      }
  }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
        	return super.attemptAuthentication(req, res);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}