package ai.sn.controllers;

import ai.sn.dto.PageDTO;
import ai.sn.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ai.sn.service.UserService;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    @Value("3")
    private Integer defaultPageSize;

    @GetMapping("/users")
    public PageDTO<UserDTO> getUserList(@RequestParam(value = "search", required = false) String search,
                              @RequestParam(value = "page", required = false) Integer page,
                              Long userId) {
        UserDTO user = userService.getUserById(userId);
        if (page == null)
            page = 0;

        PageRequest pageRequest = PageRequest.of(page, defaultPageSize, Sort.by("lastName").and(Sort.by("firstName")));
        PageDTO<UserDTO> allPageable;
        if (StringUtils.isEmpty(search)) {
            allPageable = userService.findAllPageable(user.getId(), pageRequest);
        } else {
            allPageable = userService.findAllWithSearch(user.getId(), search, pageRequest);
        }
        return allPageable;
    }
}

