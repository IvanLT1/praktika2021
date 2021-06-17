package ai.sn.controllers;

import ai.sn.dto.PasswordChangeDTO;
import ai.sn.dto.UserDTO;
import ai.sn.service.ImageService;
import ai.sn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Set;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class SettingsController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ImageService imageService;

    @Value("#{'image/jpeg,image/png'.split(',')}")
    private Set<String> allowedExtensions;

    @GetMapping("/settings")
    public void getSettingsPage(   ) {
       // TO DO
    }

    @PostMapping("/settings")
    public void updateSettings(UserDTO user) {
        userService.updateUser(user);
        // TO DO

    }

    @PostMapping("/updatePassword")
    public void updatePassword(@Valid  PasswordChangeDTO passwordChangeDTO,
                                 Long userId) {

        UserDTO user = userService.getUserById(userId);
        String password = user.getPassword();
        boolean passwordsMatch = bCryptPasswordEncoder.matches(passwordChangeDTO.getOldPassword(), password);
   //TO Do : match
            userService.updatePassword(passwordChangeDTO.getPassword(), user.getId());

    }

    @PostMapping("/uploadImage")
    public void uploadImage(MultipartFile file,Long userId) throws IOException {
      // TO Do : extensions;
        UserDTO user = userService.getUserById(userId);

        String newFileName = imageService.updateProfileImage(user, file);
        userService.updateUserImage(user, newFileName);

    }

}

