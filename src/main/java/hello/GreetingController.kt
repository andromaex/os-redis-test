package hello

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class GreetingController (private val service: RedisPageService){

    @GetMapping("/greeting")
    fun greeting(@RequestParam(name = "name", required = false, defaultValue = "World") name: String, model: Model): String {
        model.addAttribute("name", name)
        return "greeting"
    }


    @GetMapping("/getValues")
    fun getValues(@RequestParam(name = "name", required = false, defaultValue = "World") name: String, model: Model): String {
        var allPages="nix:"
        val pages = service.pages
        for ((name) in pages) {
            allPages=allPages+name
        }
        System.out.println("getValues return with:"+allPages)
        return allPages
    }


}
