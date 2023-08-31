package org.mozilla.social.core.ui.postcard

import org.mozilla.social.core.ui.poll.PollInteractions

interface PostCardInteractions : PollInteractions {
    fun onReplyClicked(statusId: String) = Unit
    fun onBoostClicked(statusId: String, isBoosting: Boolean) = Unit
    fun onFavoriteClicked(statusId: String, isFavoriting: Boolean) = Unit
    fun onPostCardClicked(statusId: String) = Unit
}