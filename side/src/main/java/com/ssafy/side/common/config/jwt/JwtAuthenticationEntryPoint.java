package com.ssafy.side.common.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.side.common.exception.FailResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {
    setResponse(response, HttpStatus.UNAUTHORIZED, authException.getMessage());
  }


  public void setResponse(HttpServletResponse response, HttpStatus statusCode, String error) throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    FailResponse apiResponse = FailResponse.fail(statusCode.value(), error);
    response.getWriter().println(mapper.writeValueAsString(apiResponse));
  }

}
