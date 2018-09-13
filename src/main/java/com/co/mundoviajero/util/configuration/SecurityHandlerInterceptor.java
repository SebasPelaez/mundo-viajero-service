package com.co.mundoviajero.util.configuration;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class SecurityHandlerInterceptor implements HandlerInterceptor {

    private MessageSourceAccessor messageSource;

    public SecurityHandlerInterceptor() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        this.messageSource = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
        throws Exception {
        /* no-code */
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
        throws Exception {
        /* no-code */
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg) throws Exception {
        String token = request.getHeader("Authorization");

        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        if (token != null) {
            try {
                String[] authParts = token.split("\\s+");
                String authInfo = authParts[1];
                //public static final String CLAVE_JWT = "62703757a09d19c112e19501f77a6faa008ef7e6b193d14afb136f4f8e2fc537244c62c8b5b8bcd64018d373e7160d785cf68e5b8cd9ea6e14f5a31671173189";
                Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(Constantes.CLAVE_JWT))
                    .parseClaimsJws(authInfo).getBody();
                claims.getId();
                claims.getSubject();
                claims.getIssuer();
                claims.getExpiration();
            } catch (ExpiredJwtException e) {
                throw new ExpiredTokenException(messageSource.getMessage("DESC_EXPIRED_TOKEN"));
            } catch (Exception e) {
                throw new InvalidTokenException(messageSource.getMessage("DESC_INVALID_TOKEN"));
            }

            return true;
        } else {
            throw new InvalidTokenException(messageSource.getMessage("DESC_INVALID_TOKEN"));
        }
    }

}