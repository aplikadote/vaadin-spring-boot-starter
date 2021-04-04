package vaadin.spring.boot.starter.views.main;

import vaadin.spring.boot.starter.views.admin.AdminView;
import vaadin.spring.boot.starter.views.cartola.CartolaView;
import vaadin.spring.boot.starter.views.dashboard.DashboardView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * The main view is a top-level placeholder for other views.
 */
@PWA(name = "My App", shortName = "My App", enableInstallPrompt = false)
@JsModule("./styles/shared-styles.js")
@CssImport("./views/main/main-view.css")
public class MainView extends AppLayout {

    private final Tabs menu;
    private H1 viewTitle;

    public MainView() {
        setPrimarySection(Section.DRAWER);

        viewTitle = new H1();

        HorizontalLayout header = new HorizontalLayout();
        header.setId("header");
        header.getThemeList().set("dark", true);
        header.setWidthFull();
        header.setSpacing(false);
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.add(new DrawerToggle());
        header.add(viewTitle);
        header.add(new Avatar());
        this.addToNavbar(true, header);

        menu = createMenu();

        VerticalLayout menuAndContent = new VerticalLayout();
        menuAndContent.setSizeFull();
        menuAndContent.setPadding(false);
        menuAndContent.setSpacing(false);
        menuAndContent.getThemeList().set("spacing-s", true);
        menuAndContent.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new Image("images/logo.png", "My App logo"));
        logoLayout.add(new H1("Ecardex"));
        menuAndContent.add(logoLayout, menu);

        this.addToDrawer(menuAndContent);
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");

        tabs.add(tab("Dashboard", DashboardView.class));
        tabs.add(tab("Cartola", CartolaView.class));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getAuthorities().stream().forEach(System.out::println);
        boolean admin = auth.getAuthorities().stream().anyMatch(e -> e.getAuthority().equals("ADMIN"));
        if (admin) {
            tabs.add(tab("Admin", AdminView.class));
        }

        return tabs;
    }

    private static Tab tab(String text, Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();

        menu.getChildren()
                .filter(tab -> ComponentUtil.getData(tab, Class.class).equals(getContent().getClass()))
                .findFirst()
                .map(Tab.class::cast)
                .ifPresent(menu::setSelectedTab);

        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        String titleStr = title == null ? "" : title.value();
        viewTitle.setText(titleStr);
    }
}
