package ai.sn.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ai.sn.service.UserService;





    @Controller
    @RequestMapping("/admin")
    @RequiredArgsConstructor
    public class AdminController {

        private final UserService userService;

        @PostMapping("/makeAdmin")
        public void makeAdmin(@RequestParam Long userId) {
            userService.makeUserAdmin(userId);

        }

        @PostMapping("/block")
        public void blockUser(@RequestParam Long userId) {
            userService.blockUser(userId);

        }

    }
