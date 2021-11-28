package cn.bybing;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static cn.bybing.jwt.JwtUtil.SECRET;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/26/18:04
 * @Description:
 */
@SpringBootTest
public class TokenParserTest {

    @Test
    public void test(){
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyTmFtZSI6Impob25ueSIsImV4cCI6MTY0MTUxODc2MX0.LTmlpIZ4-UfsQzYUiN6qDofzq1kCt6L-bGrjbDfEbbg_ogL2uzKOxPlCqp3YWcgsSS9p8KPkwV3fjEaX8lhIhA";
        JwtParser parser = Jwts.parser();
        Jws<Claims> claimsJws = parser.setSigningKey(SECRET).parseClaimsJws(token);
        Claims claim = claimsJws.getBody();
        Object userName = claim.get("userName");
        System.out.println(userName);
    }
}
