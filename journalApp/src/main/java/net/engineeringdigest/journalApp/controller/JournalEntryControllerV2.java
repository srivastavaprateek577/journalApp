package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Service.JournalEntryService;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Void> createEntry(@RequestBody JournalEntry entry) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        journalEntryService.saveEntry(entry, username);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable Long id) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
       Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<Void> deleteJournalEntry(@PathVariable Long id,@PathVariable String username) {
        journalEntryService.deleteById(id,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{username}/{id}")
    public  ResponseEntity<?> updateJournalEntry(
            @PathVariable long id,
            @RequestBody JournalEntry newEntry,
            @PathVariable String username
    ){
  JournalEntry old = journalEntryService.findById(id).orElse(null);
  if (old!= null){
      old.setTitle(newEntry.getTitle()!= null&& ! newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
      old.setContent(newEntry.getContent()!= null&& ! newEntry.getContent().equals("")? newEntry.getContent() : old.getContent());
      journalEntryService.saveEntry(old,username);
      return  new ResponseEntity<>(old,HttpStatus.OK);


  }
  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
