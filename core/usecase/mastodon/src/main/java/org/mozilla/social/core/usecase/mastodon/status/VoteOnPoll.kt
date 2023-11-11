package org.mozilla.social.core.usecase.mastodon.status

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.mozilla.social.common.utils.StringFactory
import org.mozilla.social.core.repository.mastodon.model.status.toDatabaseModel
import org.mozilla.social.core.repository.mastodon.model.status.toExternalModel
import org.mozilla.social.core.database.SocialDatabase
import org.mozilla.social.core.navigation.usecases.ShowSnackbar
import org.mozilla.social.core.usecase.mastodon.R

class VoteOnPoll(
    private val externalScope: CoroutineScope,
    private val statusApi: org.mozilla.social.core.network.mastodon.StatusApi,
    private val socialDatabase: SocialDatabase,
    private val showSnackbar: ShowSnackbar,
    private val dispatcherIo: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend operator fun invoke(
        pollId: String,
        pollChoices: List<Int>,
    ) = externalScope.async(dispatcherIo) {
        try {
            socialDatabase.pollDao().updateOwnVotes(pollId, pollChoices)
            val poll = statusApi.voteOnPoll(
                pollId,
                org.mozilla.social.core.network.mastodon.model.request.NetworkPollVote(pollChoices),
            ).toExternalModel()
            socialDatabase.pollDao().update(poll.toDatabaseModel())
        } catch (e: Exception) {
            socialDatabase.pollDao().updateOwnVotes(pollId, null)
            showSnackbar(
                text = StringFactory.resource(R.string.error_voting),
                isError = true,
            )
            throw VoteOnPollFailedException(e)
        }
    }.await()

    class VoteOnPollFailedException(e: Exception) : Exception(e)
}