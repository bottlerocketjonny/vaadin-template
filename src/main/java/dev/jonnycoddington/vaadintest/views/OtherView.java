package dev.jonnycoddington.vaadintest.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "/other", layout = MainLayout.class)
@PageTitle("Other")
@PermitAll
public class OtherView extends VerticalLayout {

  public OtherView() {
    VerticalLayout layout = new VerticalLayout();
    layout.setAlignItems(Alignment.CENTER);
    
    layout.add(new H1("Some other page!"));
    
    Paragraph paragraph1 = new Paragraph("Here's some text....");
    Paragraph paragraph2 = new Paragraph("And more text...");
    Paragraph paragraph3 = new Paragraph("And even more text...");
    
    layout.add(paragraph1, paragraph2, paragraph3);
    add(layout);
  }

}
