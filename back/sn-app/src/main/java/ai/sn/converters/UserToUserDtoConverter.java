package ai.sn.converters;

import ai.sn.constants.Gender;
import ai.sn.entities.User;
import ai.sn.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static ai.sn.constants.Constants.ROLE_ADMIN;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(User user) {
        if (user == null)
            return null;

        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .sex(Gender.getGenderById(user.getSex()).name())
                .isAdmin(user.getRoles().stream().anyMatch(r -> r.getName().equals(ROLE_ADMIN)))
                .image(user.getImage())
                .dob(user.getDob())
                .build();
    }
}

