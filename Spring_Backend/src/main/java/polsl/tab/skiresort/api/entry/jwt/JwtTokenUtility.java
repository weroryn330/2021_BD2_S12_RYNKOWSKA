package polsl.tab.skiresort.api.entry.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtility {
    public static final long JWT_TOKEN_VALIDITY = 5L * 60L * 60L;

    private static final String TOKEN_START = "Bearer ";

    @Value("${jwt.secret}")
    private String secret;

    private String validateTokenStart(String token) {
        return token.startsWith(TOKEN_START) ? token.replace(TOKEN_START, "") : token;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(validateTokenStart(token), Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(validateTokenStart(token), Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final var claims = getAllClaimsFromToken(validateTokenStart(token));
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(validateTokenStart(token)).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final var expiration = getExpirationDateFromToken(validateTokenStart(token));
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(validateTokenStart(token));
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
