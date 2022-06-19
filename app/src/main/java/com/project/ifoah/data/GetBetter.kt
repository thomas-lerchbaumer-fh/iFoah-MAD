package com.project.ifoah.data

data class GetBetter(
    val id: String,
    val title: String,
    val description: String,
    val source: String,
    val imgSource: String)

fun getGetBetter(): List<GetBetter> {
    return listOf(
        GetBetter(
            id = "mov-001",
            title = "5 General Tips to improve your turn",
            description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
            source = "asdf",
            imgSource="https://images.pexels.com/photos/1271147/pexels-photo-1271147.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        ),
        GetBetter(
            id = "mov-002",
            title = "How to improve your balance",
            description = "We’ve all heard the \"you need to lean further forward\" tip, however, there is much more than meets the eye when it comes to maintaining good stance and balance.\n" +
                    "\n" +
                    "In this lesson, Tom Gellie shares his expert knowledge with 3 actionable tips to help you ski steeper slopes, and short turns with better balance.\n" +
                    "These tips should help you ski with more confidence on steeps, prevent your skis washing out at the end of your short turns and keep you focused on small fore-aft movement shifts that make a big difference.",
            source = "www",
            imgSource = "https://images.pexels.com/photos/848595/pexels-photo-848595.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        ),
        GetBetter(
            id = "mov-003",
            title = "Placeholder",
            description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
            source = "asdf",
            imgSource="https://images.pexels.com/photos/3639709/pexels-photo-3639709.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        ),
        GetBetter(
            id = "mov-004",
            title = "Placeholder",
            description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
            source = "asdf",
            imgSource="https://images.pexels.com/photos/416796/pexels-photo-416796.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        ),
        GetBetter(
            id = "mov-005",
            title = "Placeholder",
            description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
            source = "asdf",
            imgSource="https://images.pexels.com/photos/3651800/pexels-photo-3651800.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        ),
    )
}