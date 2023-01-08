package admintools;

import arc.scene.ui.TextField;
import arc.scene.ui.layout.Table;

import arc.util.Strings;
import mindustry.gen.Call;
import mindustry.gen.Player;
import mindustry.net.Packets;
import mindustry.ui.dialogs.BaseDialog;

public class BanDialog extends BaseDialog {

    public String reason;
    public String banTime = "0";

    public BanDialog(Player user) {
        super("ban");

        closeOnBack();
        shown(() -> {
            cont.clear();
            Table table = new Table();
            table.add(user.name);
            table.row();
            table.add("Причина: ").padRight(8f);
            table.defaults().height(60f).padTop(8);
            table.field(null, value -> reason = value);
            table.row();
            table.add("Дни бана: ").padRight(8f);
            table.field(null, TextField.TextFieldFilter.digitsOnly, value -> banTime = value);
            table.row();
            cont.row();
            cont.add(table);
        });
        buttons.defaults().size(200f, 50f);
        buttons.button("@cancel", this::hide);
        buttons.button("@ok", () -> {
            Call.sendChatMessage(Strings.format("/ban @ @ @", user.id, banTime, reason));
            hide();
        });
        buttons.button("Бан навсегда", () -> {
            Call.adminRequest(user, Packets.AdminAction.ban);
            hide();
        });
    }
}