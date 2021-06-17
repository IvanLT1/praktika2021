package ai.sn.controllers;

import ai.sn.dto.MessageDTO;
import ai.sn.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ai.sn.service.FriendsService;
import ai.sn.service.MessagesService;
import ai.sn.service.UserService;
import java.io.IOException;
import java.util.Set;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final FriendsService friendsService;
    private final MessagesService messagesService;

    @GetMapping("/profile")
    public Set<UserDTO> getProfilePage(Long userId) {
        UserDTO user = userService.getUserById(userId);
        MessageDTO recentMessage = messagesService.getRecentMessage(user.getId());
        Set<UserDTO> friends = friendsService.getAcceptedFriendshipUsers(user.getId());
        return friends;
    }

    @GetMapping("/profile/{id}")
    public Set<UserDTO> getUserPage(@PathVariable Long userId) throws IOException {
        UserDTO user = userService.getUserById(userId);
        //TO DO : friendship is or not
        Set<UserDTO> friends = friendsService.getAcceptedFriendshipUsers(userId);
        return friends;
    }
}

