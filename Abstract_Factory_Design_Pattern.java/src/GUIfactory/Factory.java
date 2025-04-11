package GUIfactory;

import UI.Button;
import UI.CheckBox;

public interface Factory {
    public Button getButton();
    public CheckBox getCheckBox();
}
