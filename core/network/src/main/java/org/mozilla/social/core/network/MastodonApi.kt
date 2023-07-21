package org.mozilla.social.core.network

import okhttp3.MultipartBody
import org.mozilla.social.model.entity.Attachment
import org.mozilla.social.model.entity.request.StatusCreate
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MastodonApi {

    @POST("api/v1/statuses")
    suspend fun postStatus(
        @Body status: StatusCreate
    ): Response<Unit>

    @Multipart
    @POST("/api/v2/media")
    suspend fun uploadMedia(
        @Part file: MultipartBody.Part,
        @Part("description") description: String? = null,
    ): Attachment
}