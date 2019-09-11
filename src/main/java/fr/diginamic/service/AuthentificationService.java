package fr.diginamic.service;

import fr.diginamic.controller.dto.InfosAuthentificationPost;
import fr.diginamic.repository.AuthentificationRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public AuthentificationService(AuthentificationRepository authentificationRepository, PasswordEncoder passwordEncoder) {
        this.authentificationRepository = authentificationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> authenticate(InfosAuthentificationPost infos) {
        return  this.authentificationRepository.findByIdentifiant(infos.getIdentifiant())
                .filter(utilisateur -> passwordEncoder.matches(infos.getMdp(),utilisateur.getMotDePasse()))
                .map(utilisateur -> {
                    Map<String,Object> infosSupplementaireToken = new HashMap<>();
                    infosSupplementaireToken.put("roles",utilisateur.getRole());
                    infosSupplementaireToken.put("commune",utilisateur.getCommune().getCodeCommune());


                    String jetonJWT = Jwts.builder()
                            .setSubject(utilisateur.getIdentifiant())
                            .addClaims(infosSupplementaireToken)
                            .setExpiration(new Date(System.currentTimeMillis()+EXPIRES_IN*1000))
                            .signWith(SignatureAlgorithm.HS512,SECRET)
                            .compact();

                    ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE,jetonJWT)
                            .httpOnly(true)
                            .maxAge(EXPIRES_IN)
                            .path("/")
                            .build();

                    return ResponseEntity.ok()
                            .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                            .build();

                })
                .orElseGet(()->ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}
