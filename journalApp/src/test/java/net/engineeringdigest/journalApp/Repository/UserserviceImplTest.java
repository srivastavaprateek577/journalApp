package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

public class UserserviceImplTest {

    @Autowired
    private UserRepository userRepository;


    @Disabled
    @Test
    void testGetUsersForSentimentAnalysis(){

        List<User> users = userRepository.getUsersForSentimentAnalysis();

        assertNotNull(users);

        System.out.println("Users returned = " + users.size());

    }
}
