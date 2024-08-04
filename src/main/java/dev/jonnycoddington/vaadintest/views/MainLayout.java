package dev.jonnycoddington.vaadintest.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.spring.security.AuthenticationContext;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding.End;
import com.vaadin.flow.theme.lumo.LumoUtility.TextAlignment;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;

public class MainLayout extends AppLayout {
  
  private final AuthenticationContext authenticationContext;
  
  private H2 viewTitle;
  
  public MainLayout(AuthenticationContext authenticationContext) {
    this.authenticationContext = authenticationContext;
    setPrimarySection(Section.DRAWER);
    addNavbarContent();
    addDrawerContent();
  }
  
  private void addNavbarContent() {
    DrawerToggle toggle = new DrawerToggle();
    toggle.setAriaLabel("Menu toggle");
    toggle.setTooltipText("Toggle navigation menu");
    
    viewTitle = new H2();
    viewTitle.addClassNames(FontSize.LARGE, Margin.NONE, Flex.GROW);
    
    Button logout = new Button("Log out " + authenticationContext.getPrincipalName().orElse(""),
        event -> authenticationContext.logout());
    
    Icon moonIcon = VaadinIcon.MOON_O.create();
    Button themeToggle = new Button(moonIcon, event -> toggleDarkTheme());
    themeToggle.addClassNames(Width.SMALL, Margin.SMALL);
    
    Header header = new Header(toggle, viewTitle, themeToggle, logout);
    header.addClassNames(AlignItems.CENTER, Display.FLEX, End.MEDIUM, Width.FULL);
    
    addToNavbar(header);
  }
  
  private void addDrawerContent() {
    Div drawerContent = new Div();
    drawerContent.addClassNames(Display.FLEX, FlexDirection.COLUMN, Width.FULL, Height.FULL, TextAlignment.CENTER);
    
    Span appName = new Span("Template application");
    appName.addClassNames(FontSize.LARGE, FontWeight.SEMIBOLD, Padding.LARGE);
    
    Scroller scroller = new Scroller(createSideNav());
    scroller.addClassNames(Flex.GROW);
    
    drawerContent.add(appName, scroller);
    
    addToDrawer(drawerContent);
    setDrawerOpened(false);
  }
  
  private void toggleDarkTheme() {
    if (getElement().getThemeList().contains(Lumo.DARK)) {
      getElement().getThemeList().remove(Lumo.DARK);
      getElement().getThemeList().add(Lumo.LIGHT);
    } else {
      getElement().getThemeList().remove(Lumo.LIGHT);
      getElement().getThemeList().add(Lumo.DARK);
    }
  }
  
  private SideNav createSideNav() {
    SideNav nav = new SideNav();
    
    nav.addItem(new SideNavItem("Home", HomeView.class));
    nav.addItem(new SideNavItem("Other", OtherView.class));
    
    return nav;
  }
  
  private String getCurrentPageTitle() {
    if (getContent() == null) {
      return "";
    } else if (getContent() instanceof HasDynamicTitle titleHolder) {
      return titleHolder.getPageTitle();
    } else {
      PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
      return title == null ? "" : title.value();
    }
  }
  
  @Override
  protected void afterNavigation() {
    super.afterNavigation();
    viewTitle.setText(getCurrentPageTitle());
  }
  
}
