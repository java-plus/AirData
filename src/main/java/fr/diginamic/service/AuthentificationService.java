package fr.diginamic.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.diginamic.controller.dto.InfosAuthentificationPost;
import fr.diginamic.entites.Role;
import fr.diginamic.repository.AuthentificationRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Cette classe est un service qui gère l'authentification de l'utilisateur et
 * la création ou non d'un cookie qui lui permettra d'être authentifié et donc
 * d'avoir accès aux differentes pages de l'application (selon son rôle)
 * 
 * @author Diginamic02
 *
 */
@Service
public class AuthentificationService {

	private AuthentificationRepository authentificationRepository;
	private PasswordEncoder passwordEncoder;

	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN;

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Value("${jwt.secret}")
	private String SECRET;

	public AuthentificationService(AuthentificationRepository authentificationRepository,
			PasswordEncoder passwordEncoder) {
		this.authentificationRepository = authentificationRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Cette methode gère la création du cookie correspondant aux information
	 * postées par l'utilisateur dans le corps du POST correspondant. Elle
	 * retrouve l'utilisateur et ses infos dans la base de donnée s'il existe.
	 * Elle vérifie que le mot de passe fournit par l'utilisateur correspond à
	 * celui enregistré en BDD Elle crée un cookie contenant l'identifiant de
	 * l'utilisateur, son rôle, sa commune, sa date d'expiration (=date création
	 * + 86400 secondes soit 24h) puis le crypte avec la variable String SECRET
	 * renseignée dans le application.propertie
	 * 
	 * @param infos
	 * @return
	 */
	public ResponseEntity<?> authenticate(InfosAuthentificationPost infos) {
		return this.authentificationRepository.findByIdentifiant(infos.getIdentifiant())
				.filter(utilisateur -> passwordEncoder.matches(infos.getMdp(), utilisateur.getMotDePasse()))
				.map(utilisateur -> {
					Map<String, Object> infosSupplementaireToken = new HashMap<>();
					infosSupplementaireToken.put("roles",
							utilisateur.getRole().stream().map(Role::getLibelle).collect(Collectors.joining(",")));
					infosSupplementaireToken.put("commune", utilisateur.getCommune().getCodeCommune());

					String jetonJWT = Jwts.builder().setSubject(utilisateur.getIdentifiant())
							.addClaims(infosSupplementaireToken)
							.setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
							.signWith(SignatureAlgorithm.HS512, SECRET).compact();

					ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE, jetonJWT).httpOnly(true)
							.maxAge(EXPIRES_IN).path("/").build();

					return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString()).build();

				}).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

}
