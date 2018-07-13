package hello

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class GreetingController (private val service: RedisPageService){

    private val logger = LoggerFactory.getLogger(RedisPageService::class.java)


    @GetMapping("/greeting")
    fun greeting(@RequestParam(name = "name", required = false, defaultValue = "World") name: String, model: Model): String {
        model.addAttribute("name", name)
        return "greeting"
    }


    @GetMapping("/getPages")
    fun getPages( model: Model): String {
        var allPages=""
        val pages = service.pages
        for ((name) in pages) {
            allPages=allPages+name+","
        }
        logger.info("getPages  with:"+allPages)
        model.addAttribute("avblPages", pages)
        return "greeting"
    }

    @PostMapping("/getPages")
    fun addPage(@RequestParam(name = "name", required = true) name: String, model: Model): String {
        var allPages=""
        var res=service.addPage(name)
        logger.info("addPage with:"+res)
        model.addAttribute("name", res)
        return getPages(model)
    }




}
