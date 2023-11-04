package org.mozilla.social.core.navigation.usecases

import org.mozilla.social.core.navigation.BottomBarNavigationDestination
import org.mozilla.social.core.navigation.EventRelay
import org.mozilla.social.core.navigation.NavigationDestination

class NavigateTo(
    private val eventRelay: EventRelay
) {
    operator fun invoke(navDestination: NavigationDestination) {
        eventRelay.emitEvent(navDestination)
    }

    operator fun invoke(navDestination: BottomBarNavigationDestination) {
        eventRelay.emitEvent(navDestination)
    }
}
