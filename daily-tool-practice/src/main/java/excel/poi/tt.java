package excel.poi;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.Date;

public class tt {
    public static String TOKEN_SUFFIX = "Bearer ";

    public static String getToken(){
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(JOSEObjectType.JWT)
                .keyID(Constants.keyId)
                .build();

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .issueTime(new Date(System.currentTimeMillis()))
                .expirationTime(new Date(System.currentTimeMillis() + 1000 * 60 * 15 ))//15分钟有效期
                .audience(Constants.audience)
                .issuer(Constants.clientId)
                .claim("account_type", Constants.account_type)
                .build();

        SignedJWT jwt = new SignedJWT(jwsHeader, claimsSet);
        String token;
        try {
            RSAKey rsaKey = RSAKey.parse(Constants.privateKey);
            JWSSigner signer = new RSASSASigner(rsaKey, true);
            jwt.sign(signer);
            token = jwt.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //拼接Bearer
        token= TOKEN_SUFFIX + token;
        return token;
    }

    public static void main(String[] args) {
        System.out.println(getToken());
    }
}
