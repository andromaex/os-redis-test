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

@Service
class RedisPageService {

    val pages: List<Page>
        get() {

            val client = RedisClient.create("redis://redis.test43.svc")
            val connection = client.connect()
            val sync = connection.sync()
            val value = sync.get("pages") as String
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


    fun getPage(id: String, locale: Locale): Page {
        val client = RedisClient.create("redis://redis.test43.svc")
        val connection = client.connect()
        val sync = connection.sync()
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

