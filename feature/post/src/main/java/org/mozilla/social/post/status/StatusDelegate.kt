package org.mozilla.social.post.status

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mozilla.social.common.logging.Log
import org.mozilla.social.common.utils.accountText
import org.mozilla.social.common.utils.buildAnnotatedStringForAccountsAndHashtags
import org.mozilla.social.common.utils.buildAnnotatedStringWithSymbols
import org.mozilla.social.common.utils.hashtagText
import org.mozilla.social.common.utils.replaceAccount
import org.mozilla.social.common.utils.replaceHashtag
import org.mozilla.social.core.data.repository.SearchRepository

class StatusDelegate(
    private val searchRepository: SearchRepository,
    private val log: Log,
) : StatusInteractions {

    lateinit var coroutineScope: CoroutineScope

    private val _statusText = MutableStateFlow(TextFieldValue(""))
    val statusText = _statusText.asStateFlow()

    private val _accountList = MutableStateFlow<List<Account>?>(null)
    val accountList = _accountList.asStateFlow()

    private val _hashtagList = MutableStateFlow<List<String>?>(null)
    val hashtagList = _hashtagList.asStateFlow()

    private var searchJob: Job? = null

    override fun onStatusTextUpdated(textFieldValue: TextFieldValue) {
        _statusText.update {
            log.d("updating onStatusTextUpdated")
            textFieldValue
        }

        searchForAccountsAndHashtags(textFieldValue)
    }

    private fun searchForAccountsAndHashtags(textFieldValue: TextFieldValue) {
        searchJob?.cancel()

        val accountText = textFieldValue.accountText()
        if (accountText.isNullOrBlank()) {
            _accountList.update { null }
        }

        val hashtagText = textFieldValue.hashtagText()
        if (hashtagText.isNullOrBlank()) {
            _hashtagList.update { null }
        }

        searchJob = coroutineScope.launch {
            // let the user stop typing before trying to search
            delay(500)

            if (!accountText.isNullOrBlank()) {
                try {
                    _accountList.update {
                        searchRepository.searchForAccounts(accountText).map {
                            Account(
                                accountId = it.acct,
                                profilePicUrl = it.avatarStaticUrl
                            )
                        }
                    }
                } catch (e: Exception) {
                    log.e(e)
                }
            }

            if (!hashtagText.isNullOrBlank()) {
                try {
                    _hashtagList.update {
                        searchRepository.searchForHashtags(hashtagText).map { it.name }
                    }
                } catch (e: Exception) {
                    log.e(e)
                }
            }
        }
    }

    override fun onAccountClicked(accountName: String) {
        _statusText.update { it.replaceAccount(accountName) }
        _accountList.update { null }
    }

    override fun onHashtagClicked(hashtag: String) {
        _statusText.update { it.replaceHashtag(hashtag) }
        _hashtagList.update { null }
    }
}