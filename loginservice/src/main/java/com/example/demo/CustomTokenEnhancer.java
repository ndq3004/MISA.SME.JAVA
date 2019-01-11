package com.example.demo;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		
		//them thong tin vao token access tra ve
		
		CustomUser user = (CustomUser) authentication.getPrincipal();
		Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());
		if (user.getContactMobile() != null)
			info.put("contactMobile", user.getContactMobile());
		if (user.getStatus() != null)
			info.put("last_name", user.getStatus());
	
		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(info);
		return super.enhance(customAccessToken, authentication);
	}	
}