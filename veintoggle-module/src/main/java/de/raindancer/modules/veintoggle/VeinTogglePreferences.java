package de.raindancer.modules.veintoggle;

import de.raindancer.core.ui.preference.Preference;
import de.raindancer.modules.veintoggle.util.PermissionNodes;

import java.util.function.Supplier;

/** What /preferences offers for vein mining: the same switch /vein flips. */
public final class VeinTogglePreferences {

    private VeinTogglePreferences() {
    }

    /** Offered to whoever may use /vein, and only where Veinminer is installed to be switched. */
    public static Preference veins(Supplier<VeinToggleServices> services) {
        return Preference.toggle("veintoggle:veins", "Vein mining", "Vein mining",
                        "Breaks a whole vein or tree at once while you mine it.",
                        player -> services.get().wantsVeins(player),
                        (player, on) -> {
                            VeinToggleServices live = services.get();
                            live.veins().set(player, on);
                            live.messages().send(player, on ? "veintoggle.switched-on" : "veintoggle.switched-off");
                        })
                .shownTo(player -> player.hasPermission(PermissionNodes.USE) && services.get().veinminerInstalled());
    }
}
