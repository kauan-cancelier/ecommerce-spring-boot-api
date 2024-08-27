package ecommerce.services.impl;

import com.google.common.base.Preconditions;
import ecommerce.models.User;
import ecommerce.repositories.UserRepository;
import ecommerce.security.AccessCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AccessCredentialsService implements UserDetailsService {

	@Autowired
	private UserRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = usersRepository.findBy(email);
		Preconditions.checkNotNull(user, "Não há cadastros para esse email. ");
		return new AccessCredentials(user, user.getRole());
	}

}
