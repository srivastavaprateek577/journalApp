package net.engineeringdigest.journalApp.controller;


import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Service.UserDetailsServiceImpl;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.Utils.JwtUtil;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/healthcheck")
    public String healthCheck(){
        return "Ok";
    }

    @PostMapping("/signup")
    public void createUser(@RequestBody User user) {

        userService.saveNewEntry(user);

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
      UserDetails userdetail = userDetailsService.loadUserByUsername(user.getUsername());
      String jwt =  jwtUtil.generateToken(userdetail.getUsername());
        return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (AuthenticationException e) {
         log.error("Exception occured while creating token"+e);
         return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }

    }
}
