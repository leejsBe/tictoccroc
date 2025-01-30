package kr.co.moin.global.config;

import kr.co.moin.global.converter.CustomPasswordEncoder;
import kr.co.moin.global.jwt.JwtAccessDeniedHandler;
import kr.co.moin.global.jwt.JwtAuthenticationEntryPoint;
import kr.co.moin.global.jwt.JwtSecurityConfig;
import kr.co.moin.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

  private final TokenProvider tokenProvider;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

  // PasswordEncoder는 CustomPasswordEncoder 사용
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new CustomPasswordEncoder();
  }


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()))
      // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
      .csrf().disable()

      .exceptionHandling()
      .authenticationEntryPoint(jwtAuthenticationEntryPoint)
      .accessDeniedHandler(jwtAccessDeniedHandler)

      // enable h2-console
      .and()
      .headers()
      .frameOptions()
      .sameOrigin()

      // 세션을 사용하지 않기 때문에 STATELESS로 설정
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

      .and()
      .authorizeHttpRequests() // HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다.
      .requestMatchers(new AntPathRequestMatcher("/user/login")).permitAll() // 로그인 api
      .requestMatchers(new AntPathRequestMatcher("/user/signup")).permitAll() // 회원가입 api
      .requestMatchers(PathRequest.toH2Console()).permitAll()// h2-console, favicon.ico 요청 인증 무시
      .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
      .requestMatchers(new AntPathRequestMatcher("/swagger-resources/**")).permitAll()
      .requestMatchers(new AntPathRequestMatcher("/swagger-resources")).permitAll()
      .requestMatchers(new AntPathRequestMatcher("/configuration/ui")).permitAll()
      .requestMatchers(new AntPathRequestMatcher("/configuration/security")).permitAll()
      .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()

      .anyRequest().authenticated() // 그 외 인증 없이 접근X

      .and()
      .apply(new JwtSecurityConfig(tokenProvider)); // JwtFilter를 addFilterBefore로 등록했던 JwtSecurityConfig class 적용

    return httpSecurity.build();
  }

  // CORS 설정 정의
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("*")); // 필요한 도메인을 지정
    configuration.setAllowedMethods(List.of(HttpMethod.GET.name(),
      HttpMethod.POST.name(),
      HttpMethod.PUT.name(),
      HttpMethod.DELETE.name(),
      HttpMethod.PATCH.name(),
      HttpMethod.OPTIONS.name()));
    configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
