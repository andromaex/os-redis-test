package crisp.explore.model

enum class EContentElementType private constructor(val fragmentName: String) {

    PLAIN_TEXT("content-element-type/plain_text :: default"),
    IMAGE("content-element-type/image :: default"),
    VIDEO("content-element-type/video :: default"),
    FORMATTED_TEXT("content-element-type/formatted_text :: default"),
    RESOURCE_KEY("content-element-type/resource_key :: default"),
}
