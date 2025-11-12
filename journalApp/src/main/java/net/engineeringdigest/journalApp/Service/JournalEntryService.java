package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;



    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
       try {


        User user = userService.findByUsername(username);
        if (user != null) {
            journalEntry.setUser(user);
            journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(journalEntry);
            userService.saveUser(user);
        }
       } catch (Exception e) {

           throw new RuntimeException(e);
       }
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(Long id) {
        return journalEntryRepository.findById(id);
    }
    @Transactional
    public boolean deleteById(Long id,String userName) {
          boolean removed = false;
        try {
            User user = userService.findByUsername(userName);
             removed =  user.getJournalEntries().removeIf(x-> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }

        } catch (Exception e) {
            throw new RuntimeException("An error occured while deleting the entry",e);
        }
        return removed;
    }

    public List<JournalEntry> findByUsername(String username){
       return null;

    }
}
