package jp.hotdrop.comlis.store.service

import jp.hotdrop.comlis.store.exception.ComlisException
import jp.hotdrop.comlis.store.model.Company
import jp.hotdrop.comlis.store.repository.CompanyRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompanyService @Autowired constructor(
        val repository: CompanyRepository
) {

    private val log = LoggerFactory.getLogger("jp.hotdrop.comlis.store.trace")

    fun save(companies: List<Company>) {
        log.info("Number of saved companies: ${companies.size}")
        companies.forEach { company ->
            if(company.id.isEmpty() || company.name.isEmpty()) {
                throw ComlisException(400, "Detected Error data! ID or name is empty. ID=${company.id}, Name=${company.name} ")
            }
            repository.save(company)
        }
    }

    fun load(fromDateEpoch: Long): List<Company>? {
        val companies = repository.load(fromDateEpoch)
        if(companies == null) {
            log.info("There is no company data loaded.")
        } else {
            log.info("Number of loaded companies: ${companies.size}.")
        }
        return companies
    }
}
