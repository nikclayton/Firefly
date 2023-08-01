package org.mozilla.social.core.data.repository

import org.mozilla.social.core.network.MastodonApi
import org.mozilla.social.model.entity.Account
import org.mozilla.social.model.entity.HashTag

class SearchRepository internal constructor(
    private val mastodonApi: MastodonApi,
) {

    suspend fun searchForAccounts(
        query: String,
    ): List<Account> {
        return mastodonApi.search(
            query,
            ACCOUNTS
        ).accounts
    }

    suspend fun searchForHashtags(
        query: String,
    ): List<HashTag> {
        return mastodonApi.search(
            query,
            HASHTAGS,
        ).hashtags
    }

    companion object {
        const val ACCOUNTS = "accounts"
        const val HASHTAGS = "hashtags"
        const val STATUSES = "statuses"
    }
}