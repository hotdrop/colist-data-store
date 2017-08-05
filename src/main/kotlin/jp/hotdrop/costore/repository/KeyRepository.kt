package jp.hotdrop.costore.repository

import org.springframework.stereotype.Repository
import redis.clients.jedis.Jedis

@Repository
class KeyRepository {

    private val DATABASE_NO = 0
    private val LAST_COMPANY_KEY = "last_company_key"
    private val SENT_KEY = "sent_key"

    fun getLastCompanyKey() = find(LAST_COMPANY_KEY)

    fun saveLastCompanyKey(value: String) {
        save(LAST_COMPANY_KEY, value)
    }

    fun getSentToMobileAppCompanyKey() = find(SENT_KEY)

    fun saveSentToMobileAppCompanyKey(value: String) {
        save(SENT_KEY, value)
    }

    private fun find(key: String): String {
        val jedis = getJedis()
        return if(jedis.exists(key)) jedis.get(key) else ""
    }

    private fun save(key: String, value: String) {
        val jedis = getJedis()
        jedis.set(key, value)
    }

    // TODO 外部に切り出す
    private fun getJedis(): Jedis = Jedis("127.0.0.1", 3000).also { it.select(DATABASE_NO) }
}