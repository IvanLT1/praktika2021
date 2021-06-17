package ai.sn.controllers;

import ai.sn.dto.MessageDTO;
import ai.sn.dto.UserDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ai.sn.service.MessagesService;
import ai.sn.service.UserService;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;



@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MessagesController {
    @NonNull
    private  MessagesService messagesService;
    @NonNull
    private  UserService userService;

    private List<MessageDTO> addConversation(Long companionId, Long userId) {
        UserDTO user = userService.getUserById(userId);
        UserDTO companion = userService.getUserById(companionId);
        List<MessageDTO> messages = messagesService.findConversation(user.getId(), companionId);
        return messages;
    }

    @GetMapping("/messages")
    public Collection<MessageDTO> getMessages(Long userId) {
        UserDTO user = userService.getUserById(userId);
        Collection<MessageDTO> recentMessages = messagesService.findAllRecentMessages(user.getId());
        return recentMessages;
    }

    @GetMapping("/conversation/{companionId}")
    public List<MessageDTO> getConversation(@PathVariable Long companionId, Long userId) {
        return addConversation(companionId,  userId);
    }

    @PostMapping("/conversation/{companionId}")
    public void postMessage(@PathVariable Long companionId,
                              @Valid MessageDTO messageDTO,Long userId){
        UserDTO user = userService.getUserById(userId);
        UserDTO companion = userService.getUserById(companionId);
        messageDTO.setSender(user);
        messageDTO.setReceiver(companion);
        messageDTO.setTime(LocalDateTime.now());
        messagesService.postMessage(messageDTO);

    }

}

