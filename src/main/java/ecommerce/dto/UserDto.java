package ecommerce.dto;

import ecommerce.models.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String email;

    public static UserDto fromEntity(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static List<UserDto> fromListEntity(List<User> users) {
        ArrayList<UserDto> usersDto = new ArrayList<UserDto>();
        users.forEach(user -> {
            usersDto.add(fromEntity(user));
        });
        return usersDto;
    }
}
