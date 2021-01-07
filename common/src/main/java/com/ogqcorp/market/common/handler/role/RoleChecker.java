package com.ogqcorp.market.common.handler.role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/07/23 12:05 오후 Last Modified At: 2020/07/23
 */
public class RoleChecker {

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
  public @interface CanOperate {}

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public @interface IsAdmin {}

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @PreAuthorize("hasRole('ROLE_OPERATOR')")
  public @interface IsOperator {}

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @PreAuthorize("hasRole('ROLE_CREATOR')")
  public @interface IsCreator {}

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public @interface IsCustomer {}

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface AnyOne {}

}
