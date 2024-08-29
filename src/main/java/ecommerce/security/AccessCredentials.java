package ecommerce.security;

import ecommerce.models.Permission;
import ecommerce.models.Role;
import ecommerce.models.User;
import ecommerce.services.AbstractCategoryService;
import ecommerce.services.AbstractPermissionService;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessCredentials implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String login;

	private String pass;

	private List<GrantedAuthority> permissions;


	public AccessCredentials(User user, List<Permission> permissions) {
		this.login = user.getEmail();
		this.pass = user.getPassword();
		this.permissions = new ArrayList<GrantedAuthority>();
		for (Permission p : permissions) {
			this.permissions.add(new SimpleGrantedAuthority(p.getName()));
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getPermissions();
	}

	@Override
	public String getPassword() {
		return this.getPass();
	}

	@Override
	public String getUsername() {
		return this.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
