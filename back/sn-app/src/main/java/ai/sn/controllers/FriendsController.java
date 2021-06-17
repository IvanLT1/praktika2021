package ai.sn.controllers;

import ai.sn.dto.UserDTO;
import ai.sn.service.FriendsService;
import ai.sn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping("/user/friends")
@RequiredArgsConstructor
public class FriendsController {


    private final FriendsService friendsService;

    @Autowired
    UserService userService;



    @GetMapping
    public Map<String, Set<UserDTO>> getAllFriends(@RequestParam(value = "search", required = false) String search,Long userId){
        UserDTO user = userService.getUserById(userId);
        Map<String, Set<UserDTO>> friends = friendsService.getFriends(userId, search);
        return friends;
    }

    @GetMapping("/{friendId}/decline")
    public void deleteFriendship(@PathVariable Long friendId,Long userId) {
        UserDTO user = userService.getUserById(userId);
        friendsService.deleteFriendship(user, friendId);

    }

    @GetMapping("/{friendId}/accept")
    public void acceptFriendship(@PathVariable Long friendId,Long userId) {
        UserDTO user = userService.getUserById(userId);
        friendsService.acceptFriendship(user, friendId);
    }

    @GetMapping("/{friendId}/addToFriends")
    public void addToFriends(@PathVariable Long friendId,Long userId) {
        UserDTO user = userService.getUserById(userId);
        friendsService.addToFriends(user, friendId);
    }

}

