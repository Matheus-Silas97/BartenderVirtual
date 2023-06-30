package com.bartender.bartendervirtual.domain.usecase

import com.bartender.bartendervirtual.domain.model.HomeInformation
import kotlinx.coroutines.flow.Flow

interface HomeInformationUseCase {

    suspend fun homeInformation(): Flow<HomeInformation>

}