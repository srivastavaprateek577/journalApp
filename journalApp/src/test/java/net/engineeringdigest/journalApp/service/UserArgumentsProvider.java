package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.security.core.userdetails.User;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
  @Disabled
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(User.builder()
                        .username("aman")
                        .password("aman")
                        .roles("USER")  // ✅ Adds ROLE_USER internally
                        .build()),
                Arguments.of(User.builder()
                        .username("suraj")
                        .password("suraj")
                        .roles("USER")  // ✅ Adds ROLE_USER internally
                        .build())
        );
    }
}

