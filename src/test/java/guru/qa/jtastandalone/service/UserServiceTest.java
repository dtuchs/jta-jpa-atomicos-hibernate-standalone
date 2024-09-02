package guru.qa.jtastandalone.service;

import guru.qa.jtastandalone.entity.CurrencyValues;
import guru.qa.jtastandalone.entity.auth.AuthUserEntity;
import guru.qa.jtastandalone.entity.auth.Authority;
import guru.qa.jtastandalone.entity.auth.AuthorityEntity;
import guru.qa.jtastandalone.entity.ud.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;


class UserServiceTest {

  UserService us = new UserService();
  static final PasswordEncoder pe = PasswordEncoderFactories.createDelegatingPasswordEncoder();

  @Test
  void createUser() throws Exception {
    AuthUserEntity authUser = new AuthUserEntity();
    authUser.setUsername("jta-4");
    authUser.setPassword(pe.encode("12345"));
    authUser.setEnabled(true);
    authUser.setAccountNonExpired(true);
    authUser.setAccountNonLocked(true);
    authUser.setCredentialsNonExpired(true);
    authUser.addAuthorities(Arrays.stream(Authority.values())
        .map(a -> {
          AuthorityEntity ae = new AuthorityEntity();
          ae.setAuthority(a);
          return ae;
        }).toArray(AuthorityEntity[]::new));

    UserEntity userdataUser = new UserEntity();
    userdataUser.setUsername(null);
    userdataUser.setCurrency(CurrencyValues.RUB);
    us.createUser(authUser, userdataUser);
  }

}