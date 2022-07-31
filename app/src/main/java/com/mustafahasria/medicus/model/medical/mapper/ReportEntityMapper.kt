package com.mustafahasria.medicus.model.medical.mapper

import com.mustafahasria.medicus.model.generic.mapper.DomainMapper
import com.mustafahasria.medicus.model.medical.domain.Report
import com.mustafahasria.medicus.model.medical.entity.ReportEntity
import javax.inject.Inject

class ReportEntityMapper  @Inject constructor() : DomainMapper<ReportEntity, Report> {

    override fun mapFromDomain(domain: Report): ReportEntity {
        return ReportEntity(
            id = domain.id,
            date = domain.date,
            info = domain.info,
            color = domain.color,
            value = domain.value,
            symbol = domain.symbol,
            insight = domain.insight,
            category = domain.category,
        )
    }

    override fun mapFromModel(model: ReportEntity): Report {
        return Report(
            id = model.id,
            date = model.date,
            info = model.info,
            color = model.color,
            value = model.value,
            symbol = model.symbol,
            insight = model.insight,
            category = model.category,
        )

    }

    fun mapFromModelList(modelList: List<ReportEntity>): List<Report> {
        return modelList.map { mapFromModel(it) }
    }

    fun mapFromDomainList(domainList: List<Report>): List<ReportEntity> {
        return domainList.map { mapFromDomain(it) }
    }
}