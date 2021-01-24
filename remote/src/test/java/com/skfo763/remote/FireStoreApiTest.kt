package com.skfo763.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.skfo763.remote.api.IAppBaseInfoApi
import com.skfo763.remote.impl.AppBaseInfoApi
import com.skfo763.remote.base.toCustomDataType
import com.skfo763.remote.data.AppBaseInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito


@RunWith(JUnit4::class)
class FireStoreApiTest {

    private lateinit var appBaseInfoApi: IAppBaseInfoApi
    val testableMap = mapOf<String, Any>(
        "app_id" to "앱 아이디",
        "store_url" to "https://www.naver.com"
    )

    @Before
    fun initialize() {
        appBaseInfoApi = AppBaseInfoApi(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build(),
            Mockito.mock(FirebaseFirestore::class.java)
        )
    }


    /**
     * @field:Json(name = "app_id") val appId: String,
     * @field:Json(name = "store_url") val storeUrl: String
     */
    @Test
    fun testMoshiParser() {
        val data = testableMap.toCustomDataType(appBaseInfoApi.getMoshi(), AppBaseInfo::class.java)
        println(data)
    }

}