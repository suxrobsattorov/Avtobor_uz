package ok.suxrob.util;

import io.jsonwebtoken.*;
import ok.suxrob.dto.profileDTO.ProfileJwtDTO;
import ok.suxrob.enums.ProfileType;
import ok.suxrob.exceptions.ForbiddenException;
import ok.suxrob.exceptions.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtil {
    private final static String secretKey = "maniki";

    public static String createJwt(Integer id) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setSubject(String.valueOf(id));
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000)));
        jwtBuilder.setIssuer("avtobor");
        return jwtBuilder.compact();
    }

    public static String createJwt(Integer id, ProfileType type) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setSubject(String.valueOf(id));
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000)));
        jwtBuilder.setIssuer("avtobor");
        jwtBuilder.claim("type", type.name());
        return jwtBuilder.compact();
    }

    public static ProfileJwtDTO decodeJwt(String jwt) {
        JwtParser jwtParser = Jwts.parser();

        jwtParser.setSigningKey(secretKey);
        Jws jws = jwtParser.parseClaimsJws(jwt);

        Claims claims = (Claims) jws.getBody();
        String id = claims.getSubject();
        ProfileType profileType = ProfileType.valueOf((String) claims.get("type"));

        return new ProfileJwtDTO(Integer.parseInt(id), profileType);
    }

    public static Integer decodeJwtAndGetId(String jwt) {
        JwtParser jwtParser = Jwts.parser();

        jwtParser.setSigningKey(secretKey);
        Jws jws = jwtParser.parseClaimsJws(jwt);

        Claims claims = (Claims) jws.getBody();

        return Integer.valueOf(claims.getSubject());
    }

    public static ProfileJwtDTO getProfile(HttpServletRequest request, ProfileType required) {
        ProfileJwtDTO jwtDTO = (ProfileJwtDTO) request.getAttribute("jwtDTO");
        if (jwtDTO == null) {
            throw new UnauthorizedException("Not authorized");
        }
        if (!jwtDTO.getType().equals(required)) {
            throw new ForbiddenException("Forbidden exp");
        }
        return jwtDTO;
    }

    public static ProfileJwtDTO getProfile(HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = (ProfileJwtDTO) request.getAttribute("jwtDTO");
        if (jwtDTO == null) {
            throw new UnauthorizedException("Not authorized");
        }
        return jwtDTO;
    }
}
