package jp.hotdrop.costore.service

import jp.hotdrop.costore.repository.KeyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ScrapingLastItemKeyService @Autowired constructor(
        val repository: KeyRepository
) {

    fun find(): String? =
            repository.findLastItemKey()

    fun save(lastCompanyKey: String) {
        repository.saveLastItemKey(lastCompanyKey)
    }

    fun delete() {
        repository.deleteLastItemKey()
    }
}