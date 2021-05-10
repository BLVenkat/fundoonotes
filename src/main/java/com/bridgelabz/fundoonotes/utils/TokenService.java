package com.bridgelabz.fundoonotes.utils;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.exception.FundooException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenService {

	@Value("${secret.token}")
	public String TOKEN_SECRET;

	public String createToken(Long id) {

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		JwtBuilder builder = Jwts.builder().setId(String.valueOf(id))
				// .setExpiration(new Date (System.currentTimeMillis()+( 180 * 1000)))
				.signWith(signatureAlgorithm, DatatypeConverter.parseString(TOKEN_SECRET));
		return builder.compact();

	}

	public Long decodeToken(String token) {
		Claims claim = Jwts.parser().setSigningKey(DatatypeConverter.parseString(TOKEN_SECRET)).parseClaimsJws(token)
				.getBody();
		return Long.parseLong(claim.getId());

	}

}
