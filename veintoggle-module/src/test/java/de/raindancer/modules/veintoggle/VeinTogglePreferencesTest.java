package de.raindancer.modules.veintoggle;

import de.raindancer.core.ui.messages.Messages;
import de.raindancer.core.ui.preference.Preference;
import de.raindancer.core.ui.profile.PlayerSwitch;
import de.raindancer.modules.veintoggle.util.PermissionNodes;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VeinTogglePreferencesTest {

    private final Player player = mock(Player.class);
    private final Messages messages = mock(Messages.class);
    private final PlayerSwitch veins = mock(PlayerSwitch.class);
    private final VeinToggleServices services = mock(VeinToggleServices.class);
    private Preference preference;

    @BeforeEach
    void wire() {
        when(services.veins()).thenReturn(veins);
        when(services.messages()).thenReturn(messages);
        preference = VeinTogglePreferences.veins(() -> services);
    }

    @Test
    void isTheVeinsSwitchAndGoesThroughIt() {
        when(services.wantsVeins(player)).thenReturn(false);
        assertThat(preference.id()).isEqualTo("veintoggle:veins");
        assertThat(preference.options()).containsExactly("On", "Off");
        assertThat(preference.state(player)).isEqualTo("Off");

        preference.choose().accept(player, 0);
        verify(veins).set(player, true);
        verify(messages).send(player, "veintoggle.switched-on");
        preference.choose().accept(player, 1);
        verify(veins).set(player, false);
        verify(messages).send(player, "veintoggle.switched-off");
    }

    @Test
    void offeredOnlyToWhoMayUseItWhereVeinminerIsInstalled() {
        when(player.hasPermission(PermissionNodes.USE)).thenReturn(true);
        when(services.veinminerInstalled()).thenReturn(true);
        assertThat(preference.shown().test(player)).isTrue();
        when(services.veinminerInstalled()).thenReturn(false);
        assertThat(preference.shown().test(player)).isFalse();
        when(services.veinminerInstalled()).thenReturn(true);
        when(player.hasPermission(PermissionNodes.USE)).thenReturn(false);
        assertThat(preference.shown().test(player)).isFalse();
    }
}
