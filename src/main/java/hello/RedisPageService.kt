package hello

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.io.IOException
import java.util.*

import com.fasterxml.jackson.module.kotlin.KotlinModule
import crisp.explore.model.EPageStatus
import crisp.explore.model.EPageType
import crisp.explore.model.Page
import crisp.explore.model.PageName

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import org.springframework.beans.factory.annotation.Autowired
import org.slf4j.LoggerFactory


@Service
class RedisPageService {
    @Autowired
    lateinit var connectionProperties: ConnectionProperties
    private val logger = LoggerFactory.getLogger(RedisPageService::class.java)


    fun getRedisConnection(): StatefulRedisConnection<String, String> {
        logger.info("got redis url:" + connectionProperties.hostname)
        val client = RedisClient.create("redis://" + connectionProperties.hostname)
        return client.connect()
    }

    val pages: List<Page>
        get() {
            val sync = getRedisConnection().sync()
            var value = sync.get("pages")
            if (value == null) {
                createStub()
            }
            value = sync.get("pages") as String
            val mapper = ObjectMapper().registerModule(KotlinModule())
            val pl = ArrayList<Page>()
            try {
                val pageNames = mapper.readValue(value, Array<PageName>::class.java)
                for ((name) in pageNames) {
                    pl.add(Page(name, EPageType.ERROR, EPageStatus.DRAFT, null, null))
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return pl
        }

    fun addPage(name: String): String {
        logger.info("addPage with:"+name)
        val sync = getRedisConnection().sync()
        val mapper = ObjectMapper().registerModule(KotlinModule())
        var pageName = PageName(name)
        val value = sync.get("pages") as String
        val pageNames = mapper.readValue(value, Array<PageName>::class.java)
        var newPages=pageNames.plus(pageName)
        try {
            val newPagesStr = mapper.writeValueAsString(newPages)
            logger.info("new pages Str" + newPagesStr)
            sync.set("pages", newPagesStr)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return name;
    }


    fun createStub() {
        val sync = getRedisConnection().sync()
        val stub = "[{\"name\":\"homepage\"},{\"name\":\"campaign\"},{\"name\":\"onepagecampaign\"}]"
        try {
            sync.set("pages", stub)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun getPage(id: String, locale: Locale): Page {

        val sync = getRedisConnection().sync()
        val value = sync.get(id) as String
        val mapper = ObjectMapper().registerModule(KotlinModule())
        var p = Page(id, EPageType.ERROR, EPageStatus.DRAFT, null, null)
        try {
            p = mapper.readerFor(Page::class.java).readValue(value)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return p
    }

}

