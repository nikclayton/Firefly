package org.mozilla.social.core.test.fakes

import kotlinx.datetime.Instant
import org.mozilla.social.core.model.Account
import org.mozilla.social.core.model.Status
import org.mozilla.social.core.model.StatusVisibility

object Models {
    val status = Status(
        statusId = "vivendo",
        uri = "est",
        createdAt = Instant.fromEpochMilliseconds(5000),
        account = Account(
            accountId = "nihil",
            username = "Caroline Meadows",
            acct = "legere",
            url = "https://www.google.com/#q=salutatus",
            displayName = "Celina Calhoun",
            bio = "inceptos",
            avatarUrl = "https://www.google.com/#q=atqui",
            avatarStaticUrl = "http://www.bing.com/search?q=himenaeos",
            headerUrl = "https://search.yahoo.com/search?p=detraxit",
            headerStaticUrl = "http://www.bing.com/search?q=cu",
            isLocked = false,
            emojis = listOf(),
            createdAt = Instant.fromEpochMilliseconds(5000),
            lastStatusAt = null,
            statusesCount = 1526,
            followersCount = 4322,
            followingCount = 3024,
            isDiscoverable = null,
            movedTo = null,
            isGroup = false,
            fields = listOf(),
            isBot = null,
            source = null,
            isSuspended = null,
            muteExpiresAt = null
        ),
        content = "qui",
        visibility = StatusVisibility.Public,
        isSensitive = false,
        contentWarningText = "conubia",
        mediaAttachments = listOf(),
        mentions = listOf(),
        hashTags = listOf(),
        emojis = listOf(),
        boostsCount = 3122,
        favouritesCount = 6279,
        repliesCount = 3980,
        application = null,
        url = null,
        inReplyToId = null,
        inReplyToAccountId = null,
        boostedStatus = null,
        poll = null,
        card = null,
        language = null,
        plainText = null,
        isFavourited = null,
        isBoosted = null,
        isMuted = null,
        isBookmarked = null,
        isPinned = null
    )

    val networkAccount = Account(
        accountId = "mediocritatem",
        username = "Rosie Small",
        acct = "eripuit",
        url = "https://duckduckgo.com/?q=laoreet",
        displayName = "Jeanie Dunlap",
        bio = "arcu",
        avatarUrl = "http://www.bing.com/search?q=populo",
        avatarStaticUrl = "https://duckduckgo.com/?q=ligula",
        headerUrl = "https://www.google.com/#q=convenire",
        headerStaticUrl = "http://www.bing.com/search?q=ne",
        isLocked = false,
        emojis = listOf(),
        createdAt = Instant.fromEpochMilliseconds(5000),
        lastStatusAt = null,
        statusesCount = 2056,
        followersCount = 8489,
        followingCount = 5952,
        isDiscoverable = null,
        movedTo = null,
        isGroup = false,
        fields = listOf(),
        isBot = null,
        source = null,
        isSuspended = null,
        muteExpiresAt = null
    )
}