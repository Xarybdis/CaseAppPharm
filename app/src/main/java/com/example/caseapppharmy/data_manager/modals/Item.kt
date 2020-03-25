package com.example.caseapppharmy.data_manager.modals

import org.simpleframework.xml.Element
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class Item(
    @Element(name = "title")
    @Path("channel")
    val title: String?,
    @field:Element(name = "link")
    @Path("channel")
    val link: String?,
    @field:Element(name = "pubDate")
    @Path("channel")
    val pubDate: String?,
    @field:Element(name = "enclosure")
    @Path("channel")
    val enclosure: String?
)
/*

@Xml(name = "enclosure")
data class Enclosure(
    @Attribute(name = "url")
    val imageUrl: String?
)*/
