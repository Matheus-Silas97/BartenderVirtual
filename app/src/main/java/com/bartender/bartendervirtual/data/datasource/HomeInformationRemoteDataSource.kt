package com.bartender.bartendervirtual.data.datasource

import com.bartender.bartendervirtual.domain.model.HomeInformation

interface HomeInformationRemoteDataSource {

    suspend fun homeInformation(): HomeInformation

}