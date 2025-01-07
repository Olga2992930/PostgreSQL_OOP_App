package se.deved;

import se.deved.menu.Menu;
import se.deved.menu.UserMenu;

public class SimpleMenuManager implements MenuManager {
    private Menu menu;

    public SimpleMenuManager(Application application) {
        this.menu = new UserMenu(application);
        this.menu.display();
    }

    @Override
    public void setMenu(Menu menu) {
        this.menu = menu;
        menu.display();
    }

    @Override
    public Menu getCurrentMenu() {
        return menu;
    }
}
