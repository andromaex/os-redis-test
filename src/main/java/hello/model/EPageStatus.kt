package crisp.explore.model

enum class EPageStatus private constructor(val status: String) {

    DRAFT("draft"),
    WAITING_FOR_RELEASE("waiting"),
    RELEASED("released"),
}
