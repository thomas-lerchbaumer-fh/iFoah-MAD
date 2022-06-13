@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier,
    gameVideos: List<VideoResultEntity>
) {
    val context = LocalContext.current
    val mediaItems = arrayListOf<MediaItem>()

    // create MediaItem
    gameVideos.forEach {
        mediaItems.add(
            MediaItem.Builder()
                .setUri(it.video)
                .setMediaId(it.id.toString())
                .setTag(it)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setDisplayTitle(it.name)
                        .build()
                )
                .build()
        )
    }

    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build().apply {
            this.setMediaItems(mediaItems)
            this.prepare()
            this.playWhenReady = true
        }
    }

    // views same as above code snippet
}