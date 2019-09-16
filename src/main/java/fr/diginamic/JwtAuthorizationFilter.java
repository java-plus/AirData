package fr.diginamic;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import fr.diginamic.controller.dto.UtilisateurConnecteService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Classe JwtAuthorizationFilter Cette classe gère la méthode
 * doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain
 * chain) qui place dans le context les informations sur l'utilisateur présentes
 * dans son cookie d'authentification
 * 
 * @author Diginamic02
 *
 */
@Configuration
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Value("${jwt.secret}")
	private String SECRET;

	private UtilisateurConnecteService utilisateurConnecteService = new UtilisateurConnecteService();

	/**
	 * Cette methode récupère le cookie d'authentification de l'utilisateur et
	 * place deans le contexte de l'application les informations suivantes sur
	 * l'utilisateur: nom, code commune et rôle
	 * 
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		if (req.getCookies() != null) {
			Stream.of(req.getCookies()).filter(cookie -> cookie.getName().equals(TOKEN_COOKIE)).map(Cookie::getValue)
					.forEach(token -> {
						Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();

						utilisateurConnecteService.setUsername(body.getSubject());
						utilisateurConnecteService.setCodeCommune(body.get("commune", String.class));
						List<SimpleGrantedAuthority> authorities = Arrays
								.asList(body.get("roles", String.class).split(",")).stream()
								.map(roleString -> new SimpleGrantedAuthority(roleString)).collect(Collectors.toList());
						Authentication authentication = new UsernamePasswordAuthenticationToken(
								utilisateurConnecteService, null, authorities);
						SecurityContextHolder.getContext().setAuthentication(authentication);
					});
		}

		chain.doFilter(req, res);

	}
}
