package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repositery.UserEntryRepositery;
import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Disabled
public class UserEntryServiceTests {

    @Autowired
    private UserEntryService userService;

    @Autowired
    private UserEntryRepositery userRepository;

    @ParameterizedTest
    @CsvSource({
            "ayush",
            "Zeeshan",
            "Arbab",
            "aditi"
    })
    @Disabled
    public void testFindByUserName(String userName)
    {
        assertNotNull(userRepository.findByUserName(userName));
        User user = userRepository.findByUserName(userName);
        assertTrue(!user.getJournalEntries().isEmpty());
    }
}
