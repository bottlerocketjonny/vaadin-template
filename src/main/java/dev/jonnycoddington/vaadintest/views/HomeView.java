package dev.jonnycoddington.vaadintest.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Home")
@PermitAll
public class HomeView extends VerticalLayout {

  public HomeView() {
    VerticalLayout layout = new VerticalLayout();
    layout.setAlignItems(Alignment.CENTER);
    
    layout.add(new H1("Authenticated page!"));
    
    Paragraph paragraph1 = new Paragraph("This is a template application using Vaadin and Spring Boot.");
    Paragraph paragraph2 = new Paragraph("You can replace this text with your own content.");
    Paragraph paragraph3 = new Paragraph("It's pretty great!");
    
    layout.add(paragraph1, paragraph2, paragraph3);
    add(layout);
  }

}
