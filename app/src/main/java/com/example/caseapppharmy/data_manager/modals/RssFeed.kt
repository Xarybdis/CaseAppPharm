package com.example.caseapppharmy.data_manager.modals

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class RssFeed(
    @field:ElementList(name = "item", entry = "item", required = false, inline = true)
    @Path("channel")
    val item: ArrayList<Item>

)