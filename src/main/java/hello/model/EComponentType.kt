package crisp.explore.model

enum class EComponentType private constructor(val fragmentName: String) {

    ARTICLE("component-type/article :: default"),
    BOTTOM_TEASER("component-type/bottom_teaser :: default"),
    CAROUSEL("component-type/carousel :: default"),
    CAROUSEL_ONE_PAGE("component-type/carousel :: one_page"),
    CAROUSEL_ELEMENT("component-type/carousel_element :: default"),
    CAROUSEL_ELEMENT_ONE_PAGE("component-type/carousel_element :: one_page"),
    LINK_EXTERNAL("component-type/link_external :: default"),
    LINK_INTERNAL("component-type/link_internal :: default"),
    LINK_LIST("component-type/link_list :: default"),
    RAW_MARKUP("component-type/raw_markup :: default"),
    SECTION("component-type/section :: default"),
    SECTION_ACCORDION("component-type/section :: accordion"),
    SECTION_TEXT_IMAGE("component-type/section :: text_image"),
    SECTION_TEXT_VIDEO_BIG("component-type/section :: text_video_big"),
    SECTION_OVERLAY("component-type/section :: overlay"),
    SECTION_CAROUSEL("component-type/section :: carousel"),
    SIDE_TEASER("component-type/side_teaser :: default"),
    TILE_CONTAINER("component-type/tile_container :: default"),
    TILE("component-type/tile :: default"),
}
