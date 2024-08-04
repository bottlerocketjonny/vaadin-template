package dev.jonnycoddington.vaadintest.errors;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.server.ErrorEvent;
import com.vaadin.flow.server.ErrorHandler;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomErrorHandler implements ErrorHandler {
  
  private static final Logger log = LoggerFactory.getLogger(CustomErrorHandler.class);
  
  @Override
  public void error(ErrorEvent event) {
    log.error("Unexpected error caught", event.getThrowable());
    showError();
  }
  
  private void showError() {
    Optional.ofNullable(UI.getCurrent()).ifPresent(ui -> ui.access(() -> {
      Notification notification = Notification.show("An unexpected error occurred. Please try again later.");
      notification.setPosition(Position.MIDDLE);
      notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
    }));
  }
}
