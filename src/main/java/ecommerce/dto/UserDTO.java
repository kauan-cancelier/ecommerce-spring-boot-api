package ecommerce.dto;

import ecommerce.models.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;

    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static List<UserDTO> fromListEntity(List<User> users) {
        ArrayList<UserDTO> usersDto = new ArrayList<UserDTO>();
        users.forEach(user -> {
            usersDto.add(fromEntity(user));
        });
        return usersDto;
    }
}
