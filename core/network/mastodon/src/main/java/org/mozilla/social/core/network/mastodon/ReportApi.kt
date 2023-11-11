package org.mozilla.social.core.network.mastodon

import org.mozilla.social.core.network.mastodon.model.request.NetworkReportCreate
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ReportApi {

    @POST("/api/v1/reports")
    suspend fun report(
        @Body body: NetworkReportCreate,
    )
}