package GUIfactory;

import UI.Button;
import UI.ButtonMac;
import UI.ButtonWindows;
import UI.CheckBox;
import UI.CheckBoxMac;
import UI.CheckBoxWindows;

public class WindowsFactory implements Factory{
        @Override
    public Button getButton()
    {
        return new ButtonWindows();
    }

    @Override
    public CheckBox getCheckBox()
    {
        return new CheckBoxWindows();
    }
}
