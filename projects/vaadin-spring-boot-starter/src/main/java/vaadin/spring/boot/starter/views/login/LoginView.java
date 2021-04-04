package vaadin.spring.boot.starter.views.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;

@Route("login")
@PageTitle("Login")
@CssImport("./views/style.css")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginForm login;

    public LoginView(DaoAuthenticationProvider daoAuthProvider) {
        setId("login-container");
//        addClassName("login-container");
        setSizeFull();


        login = new LoginForm();
        login.setId("login");
        login.setI18n(createI18n());
        login.addLoginListener(e -> {
//            boolean isAuthenticated = authenticate(e);
//            if (isAuthenticated) {
//                navigateToMainPage();
//            } else {
//                component.setError(true);
//            }

            String u = e.getUsername();
            String p = e.getPassword();

            try {
                Authentication auth = daoAuthProvider.authenticate(new UsernamePasswordAuthenticationToken(u, p));
                SecurityContextHolder.getContext().setAuthentication(auth);
                UI.getCurrent().navigate("");
            } catch (Exception ex){
                ex.printStackTrace();
                login.setEnabled(true);
                login.setError(true);
            }
        });


        add(login);
    }

    private LoginI18n createI18n() {
        final LoginI18n i18n = LoginI18n.createDefault();

//        i18n.setHeader(new LoginI18n.Header());
//        i18n.getHeader().setTitle("Nome do aplicativo");
//        i18n.getHeader().setDescription("Descrição do aplicativo");
        i18n.getForm().setUsername("Usuario");
        i18n.getForm().setTitle("Vaadin Spring Boot");
        i18n.getForm().setSubmit("Ingresar");
        i18n.getForm().setPassword("Password");
        i18n.getForm().setForgotPassword("");
        i18n.getErrorMessage().setTitle("Usuario/Password incorrectos");
        i18n.getErrorMessage().setMessage("");
        i18n.setAdditionalInformation("");
        return i18n;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // inform the user about an authentication error
        // (yes, the API for resolving query parameters is annoying...)
        if(!event.getLocation().getQueryParameters().getParameters().getOrDefault("error", Collections.emptyList()).isEmpty()) {
            login.setError(true);
        }
    }
}
