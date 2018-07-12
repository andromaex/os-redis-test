package crisp.explore.model

enum class EPageType private constructor(val templateName: String) {

    ERROR("page-type/error"),
    HOMEPAGE("page-type/homepage"),
    LANDING_PAGE("page-type/landing_page"),
    OUTSIDE_CAMPAIGN("page-type/outside_campaign"),
    PRODUCT_CATEGORY("page-type/product_category"),
    PRODUCT_LIST("page-type/product_list"),
}
