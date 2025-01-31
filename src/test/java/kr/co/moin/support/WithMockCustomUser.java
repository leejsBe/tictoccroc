package kr.co.moin.support;

import org.springframework.core.annotation.AliasFor;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

  String userId() default "";


  @Retention(RetentionPolicy.RUNTIME)
  @WithMockCustomUser()
  @interface WithMockUser {

    @AliasFor(annotation = WithMockCustomUser.class)
    String userId();

  }


}
