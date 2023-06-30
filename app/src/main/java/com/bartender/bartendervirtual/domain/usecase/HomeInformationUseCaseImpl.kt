package com.bartender.bartendervirtual.domain.usecase

import com.bartender.bartendervirtual.domain.model.HomeInformation
import com.bartender.bartendervirtual.domain.repository.HomeInformationRepository
import kotlinx.coroutines.flow.Flow

class HomeInformationUseCaseImpl(private val homeInformationRepository: HomeInformationRepository) :
    HomeInformationUseCase {

    override suspend fun homeInformation(): Flow<HomeInformation> =
        homeInformationRepository.homeInformation()

}