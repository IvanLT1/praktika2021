package ai.sn.controllers;

import ai.sn.dto.UserDTO;
import ai.sn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserService userService;


    @GetMapping("/")
    public void indexPage() throws IOException {
  //TO DO  enable
    }

    @GetMapping("/access-denied")
    public void accessDenied() {
    //TO Do enable
            }

    @PostMapping("/register")
    public void registerUser(@Valid  UserDTO userDTO) {
  //TO DO full process
        String email = userDTO.getEmail();
        UserDTO userByEmail = userService.getUserByEmail(email);
        if (userByEmail != null) {
  //  TO DO  checks
        }
        userService.createUser(userDTO);
//TO DO
    }

}

