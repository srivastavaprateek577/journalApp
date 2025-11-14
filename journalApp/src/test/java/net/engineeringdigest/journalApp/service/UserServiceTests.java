
package net.engineeringdigest.journalApp.service;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

   @Autowired
    private UserRepository userRepository;

   @Autowired
   private UserService userService;
@Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    void testSaveNewUser(org.springframework.security.core.userdetails.User user) {
        assertNotNull(user);
    }

}
