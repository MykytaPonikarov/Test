package diplom.ponikarov.controller;

import javafx.scene.Parent;

public class AbstractController implements Controller {

    private Parent view;

    @Override
    public void setView(Parent view) {
        this.view = view;
    }

    @Override
    public Parent getView() {
        return view;
    }
}
