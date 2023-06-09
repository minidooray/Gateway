package com.nhnacademy.minidooray.gateway.gateway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountRegister;
import com.nhnacademy.minidooray.gateway.gateway.service.account.AccountService;
import com.nhnacademy.minidooray.gateway.gateway.service.github.GithubLoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Objects;

@Controller
@PropertySource("classpath:github.properties")
@RequiredArgsConstructor
public class LoginController {
    private final GithubLoginServiceImpl githubLoginService;
    private final RedisTemplate<String, String> redisTemplate;
    private final AccountService accountService;
    @Value("${github.client_id}")
    private String clientId;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("clientId", clientId);
        model.addAttribute("scope", "user:email");

        return "login";
    }

    @GetMapping("/login/oauth2/code/github")
    public String callbackFromGithub(String code,
                                     HttpServletRequest request) throws JsonProcessingException {
        String emailAddress = githubLoginService.login(code);

        request.setAttribute("email", emailAddress);

        return "forward:/loginSuccess";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccessHandler(HttpServletRequest request) {
        String emailAddress = (String) request.getAttribute("email");

        AccountDto account = accountService.getAccountByEmail(emailAddress);


        if(Objects.isNull(account)) {
            return "redirect:/login?error=email";
        }

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        account.getAccountId(),
                        account.getAccountPwd(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));

        HttpSession session = request.getSession(true);

        redisTemplate.opsForHash().put(session.getId(), "username", account.getAccountId());
        redisTemplate.opsForHash().put(session.getId(), "authority", "ROLE_USER");

        session.setAttribute("username", account.getAccountId());
        session.setAttribute("authority", "ROLE_USER");
        return "/project";
    }

    //회원가입 이동
    @GetMapping("/loginform")
    public String logincreateForm() {
        return "logincreate";
    }

    //회원가입 진행
    @PostMapping("/loginform")
    public String logincreate(@RequestParam("id") String id,
                              @RequestParam("pwd") String password,
                              @RequestParam("name") String name,
                              @RequestParam("email") String email,
                              RedirectAttributes redirectAttributes) {
        AccountRegister account = new AccountRegister();
        account.setAccountId(id);
        account.setAccountName(name);
        account.setAccountPwd(password);
        account.setAccountEmail(email);
        // 계정 등록 로직 수행
        accountService.registerAccount(account);
        redirectAttributes.addAttribute("success", "true");

        return "redirect:/login";
    }

}
