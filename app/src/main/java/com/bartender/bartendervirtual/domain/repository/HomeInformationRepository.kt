package com.bartender.bartendervirtual.domain.repository

import com.bartender.bartendervirtual.domain.model.HomeInformation
import kotlinx.coroutines.flow.Flow

interface HomeInformationRepository {

    suspend fun homeInformation(): Flow<HomeInformation>

}