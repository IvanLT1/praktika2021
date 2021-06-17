package ai.sn.service.impl;

import ai.sn.converters.MessageDtoToMessageConverter;
import ai.sn.converters.MessageToMessageDtoConverter;
import ai.sn.entities.Message;
import ai.sn.entities.User;
import ai.sn.repositories.MessageRepository;
import ai.sn.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ai.sn.service.MessagesService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MessagesServiceImpl implements MessagesService {

    private final MessageRepository messageRepository;
    private final MessageToMessageDtoConverter messageToMessageDtoConverter;
    private final MessageDtoToMessageConverter messageDtoToMessageConverter;

    @Override
    @Transactional(readOnly = true)
    public Collection<MessageDTO> findAllRecentMessages(Long id) {
        Iterable<Message> all = messageRepository.findAllRecentMessages(id);
        Map<User, MessageDTO> map = new HashMap<>();
        all.forEach(m -> {
            MessageDTO messageDTO = messageToMessageDtoConverter.convert(m, id);
            User user = m.getSender().getId().equals(id) ? m.getReceiver() : m.getSender();
            map.put(user, messageDTO);
        });
        return map.values();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageDTO> findConversation(Long userId, Long companionId) {
        List<Message> all = messageRepository.findConversation(userId, companionId);
        List<MessageDTO> messages = new LinkedList<>();
        all.forEach(m -> messages.add(messageToMessageDtoConverter.convert(m, userId)));
        return messages;
    }

    @Override
    @Transactional(readOnly = true)
    public MessageDTO getRecentMessage(Long id) {
        Message message = messageRepository.findFirstBySenderIdOrReceiverIdOrderByIdDesc(id, id);
        MessageDTO messageDTO = messageToMessageDtoConverter.convert(message, id);
        return messageDTO;
    }

    @Override
    @Transactional
    public void postMessage(MessageDTO messageDTO) {
        Message message = messageDtoToMessageConverter.convert(messageDTO);
        messageRepository.save(message);
    }


}
