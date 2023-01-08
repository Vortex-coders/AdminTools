package admintools;

import mindustry.Vars;
import mindustry.mod.*;

public class AdminTools extends Mod {
    @Override
    public void init() {
        Vars.ui.listfrag = new CustomPlayerListFragment();
        Vars.ui.listfrag.build(Vars.ui.hudGroup);
    }
}
