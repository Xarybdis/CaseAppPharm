package com.example.caseapppharmy.data_manager.modals

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class RssChannel(
    @field:Element(name = "item")
    val item: ArrayList<Item>
)