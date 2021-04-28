package com.bridgelabz.fundoonotes.utils;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenService {

@Value("${secret.token}")
public  String TOKEN_SECRET;
	
	public  String createToken(Long id)
	{

		
		try {
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			JwtBuilder builder = Jwts.builder().setId(String.valueOf(id))
			//.setExpiration(new Date (System.currentTimeMillis()+( 180 * 1000)))
			.signWith(signatureAlgorithm, DatatypeConverter.parseString(TOKEN_SECRET));
						return builder.compact();
			
			} catch (Exception e) {
	
				e.printStackTrace();
				return null;
			}
			}
	
	
	public  Long decodeToken(String token)
	{
		try {
		Claims claim =  Jwts.parser().setSigningKey(DatatypeConverter.parseString(TOKEN_SECRET)).parseClaimsJws(token).getBody();
		return Long.parseLong(claim.getId());
		
		} 
		catch (Exception e) 
		{
		e.printStackTrace();
		}
		return (long) 0;

	}
	
}
