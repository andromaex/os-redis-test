package crisp.explore.model

data class NavItem(
    val label: String,
    val url: String
)

//////////////////////////////////////////////// new //////////////////

data class NavigationEntry(
    val title: String,
    val parent: NavigationEntry?,
    val claims: List<String>?,
    val page: Page
)

data class Page(
    val id: String,
    val type: EPageType,
    val status: EPageStatus = EPageStatus.DRAFT,
    val components: Map<String, Component>?,
    val claims: List<String>?
)
data class PageName(
    val name: String
)
data class Component(
    val type: EComponentType,
    val elements: Map<String, ContentElement>?,
    val components: Map<String, Component>?,
    val metaData: Map<String, String>?
)

data class ContentElement(
    val type: EContentElementType,
    val data: String,
    val metaData: Map<String, String>?
)
