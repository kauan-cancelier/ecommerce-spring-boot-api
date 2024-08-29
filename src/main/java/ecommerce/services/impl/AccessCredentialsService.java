package ecommerce.services.impl;

import com.google.common.base.Preconditions;
import ecommerce.models.Permission;
import ecommerce.models.User;
import ecommerce.repositories.UserRepository;
import ecommerce.security.AccessCredentials;
import ecommerce.services.AbstractPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccessCredentialsService implements UserDetailsService {

	@Autowired
	private UserRepository usersRepository;

	@Autowired
	private AbstractPermissionService permissionService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = usersRepository.findBy(email);
		Preconditions.checkNotNull(user, "Não há cadastros para esse email. ");
		List<Permission> permissions = permissionService.listBy(user.getRole());
		permissions.forEach(permission -> System.out.println("********Loaded permission: " + permission.getName()));

		return new AccessCredentials(user, permissions);
	}

}
