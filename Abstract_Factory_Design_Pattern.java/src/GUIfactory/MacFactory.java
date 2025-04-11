package GUIfactory;

import UI.Button;
import UI.ButtonMac;
import UI.CheckBox;
import UI.CheckBoxMac;

public class MacFactory implements Factory{
    @Override
    public Button getButton()
    {
        return new ButtonMac();
    }

    @Override
    public CheckBox getCheckBox()
    {
        return new CheckBoxMac();
    }
}
