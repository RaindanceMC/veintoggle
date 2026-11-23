package de.raindancer.modules.veintoggle.service;

import de.raindancer.core.ui.messages.Messages;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

class UndoNoticesTest {

    private final Messages messages = mock(Messages.class);
    private final Player player = mock(Player.class);
    private final UndoNotices notices = new UndoNotices(messages);

    @Test
    void aFullyRestoredVeinIsSeenNotTold() {
        notices.outcome(player, new VeinUndoService.Outcome(3, 0, 0));
        verifyNoInteractions(messages);
    }

    @Test
    void aVeinThatCouldNotGoBackIsStillTold() {
        notices.outcome(player, new VeinUndoService.Outcome(0, 0, 2));
        verify(messages).send(player, "veintoggle.undo-none");
    }
}
