package fr.diginamic;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Classe WebSecurityConfig Cette classe gère la méthode configure(HttpSecurity
 * http) qui définit le filtre de l'application et donc les urls accessibles par
 * les utilisateurs
 * 
 * @author Diginamic02
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Autowired
	JwtAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
		// setAllowCredentials(true) is important, otherwise:
		// The value of the 'Access-Control-Allow-Origin' header in the response
		// must not be the wildcard '*' when the request's credentials mode is
		// 'include'.
		configuration.setAllowCredentials(true);
		// setAllowedHeaders is important! Without it, OPTIONS preflight request
		// will fail with 403 Invalid CORS request
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	/**
	 * Cette methode définit les urls disponibles (en GET ou POST) par
	 * l'utilisateur en fonction de son role (ADMIN et/ou USER) . Cette methode
	 * est activée à chaque url appellée par l'utilisateur et redirige ou nom
	 * vers les classes et méthodes concernées.
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
		http.headers().frameOptions().disable();

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/auth").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/communes").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/compte").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/communes/geojson").hasAnyRole("USER", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PATCH, "/compte").hasAnyRole("USER", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/compte").hasAnyRole("USER", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/compte_avec_admin").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/**").hasAnyRole("USER", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/favoris").hasAnyRole("USER", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PATCH, "/favoris").hasAnyRole("USER", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/favoris").hasAnyRole("USER", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/**").hasAnyRole("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();

		http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors();
		http.logout().logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpStatus.OK.value()))
				.deleteCookies(TOKEN_COOKIE);
	}

}
