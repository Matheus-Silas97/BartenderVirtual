package com.bartender.bartendervirtual.data.repository

import com.bartender.bartendervirtual.data.datasource.HomeInformationRemoteDataSource
import com.bartender.bartendervirtual.domain.model.HomeInformation
import com.bartender.bartendervirtual.domain.repository.HomeInformationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class HomeInformationRepositoryImpl(private val homeInformationDataSource: HomeInformationRemoteDataSource): HomeInformationRepository {

    override suspend fun homeInformation(): Flow<HomeInformation> = flow {
        emit(homeInformationDataSource.homeInformation())
    }

}