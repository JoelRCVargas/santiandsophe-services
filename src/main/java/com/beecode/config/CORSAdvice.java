package com.beecode.config;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//@EnableWebMvc
public class CORSAdvice implements Filter, WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		 registry.addMapping("/**");
	}
	
	@Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
      HttpServletResponse response = (HttpServletResponse) res;
      HttpServletRequest request = (HttpServletRequest) req;
      response.setHeader("Access-Control-Allow-Origin", "*");
      response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
      response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
      response.setHeader("Access-Control-Max-Age", "3600");
      response.setHeader("Access-Control-Allow-Credentials", "true");
      response.setHeader("Access-Control-Expose-Headers", "Authorization");
      response.addHeader("Access-Control-Expose-Headers", "responseType");
      response.addHeader("Access-Control-Expose-Headers", "observe");
      if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
          try {
              chain.doFilter(req, res);
          } catch(Exception e) {
              e.printStackTrace();
          }
      } else {
          response.setHeader("Access-Control-Allow-Origin", "*");
          response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
          response.setHeader("Access-Control-Max-Age", "3600");
          response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers"+"Authorization, content-type," +
          "USERID"+"ROLE"+
                  "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType,observe");
          response.setStatus(HttpServletResponse.SC_OK);
      }

    }

	/*
	 * @Bean CorsConfigurationSource corsConfigurationSource() { CorsConfiguration
	 * configuration = new CorsConfiguration(); configuration.addAllowedOrigin("*");
	 * configuration.addAllowedHeader("*"); configuration.addAllowedMethod("*");
	 * configuration.setAllowCredentials(true); UrlBasedCorsConfigurationSource
	 * source = new UrlBasedCorsConfigurationSource();
	 * source.registerCorsConfiguration("/**", configuration); return source; }
	 */
}