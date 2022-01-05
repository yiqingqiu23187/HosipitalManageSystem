package fudan.se.lab2.security.jwt;

import fudan.se.lab2.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Write your code to make this filter works.
 *
 * @author LBW
 */
@Component
public class JwtReFilter extends OncePerRequestFilter {
    @Value("${validate.url}")
  private String validateUrl;
    @Value("${token_Header}")
    private String token_Header;
   private JwtTokenUtil jwtTokenUtil;
   private JwtUserDetailsService jwtUserDetailsService;
   @Autowired
    public JwtReFilter(JwtUserDetailsService jwtUserDetailsService,
                       JwtTokenUtil jwtTokenUtil){
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // TODO: Implement the filter.
        String token = request.getHeader("Authorization");
        System.out.println(request.getRequestURI());
        System.out.println(request.getMethod());
        if(request.getMethod().equals("OPTIONS"))
            filterChain.doFilter(request, response);
        if(!request.getRequestURI().equals(validateUrl)){
            filterChain.doFilter(request, response);
        }else if(!token.isEmpty()&&token.startsWith(token_Header)){
            token = token.substring(token_Header.length());
           // System.out.println(token);
            try {
                    String usrName = jwtTokenUtil.getUsernameFromToken(token);
                    UserDetails user= jwtUserDetailsService.loadUserByUsername(usrName);
                    if(!jwtTokenUtil.validateToken(token,user))
                        response401(response);
                    else
                        filterChain.doFilter(request,response);
                }catch (UsernameNotFoundException | ExpiredJwtException ex){
                    response401(response);
                }
        }
        else {
            response401(response);
        }
    }
    private void response401(HttpServletResponse response) throws IOException{
        JSONObject data = new JSONObject();
        response.setStatus(200);
        try {
            data.put("code",401);
        }catch (JSONException ex){
            ex.printStackTrace();
            logger.debug(ex);
        }
        PrintWriter writer = response.getWriter();
        writer.print(data);
        writer.flush();
        writer.close();
    }
}
