package vaadin.spring.boot.starter.views.admin;

import vaadin.spring.boot.starter.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "admin", layout = MainView.class)
@PageTitle("Admin")
@CssImport("./views/style.css")
public class AdminView extends VerticalLayout {

    public AdminView() {
        add(new Label("Bienvenido admin!"));
    }


}
