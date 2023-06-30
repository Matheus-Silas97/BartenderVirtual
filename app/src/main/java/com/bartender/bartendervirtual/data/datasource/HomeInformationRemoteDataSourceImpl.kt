package com.bartender.bartendervirtual.data.datasource

import com.bartender.bartendervirtual.data.mapper.convertToHomeInformation
import com.bartender.bartendervirtual.data.remote.service.DrinkService
import com.bartender.bartendervirtual.domain.model.HomeInformation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeInformationRemoteDataSourceImpl(
    private val service: DrinkService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : HomeInformationRemoteDataSource {

    override suspend fun homeInformation(): HomeInformation  =
        withContext(defaultDispatcher) {
            service.homeRecommendations().convertToHomeInformation()
        }

}